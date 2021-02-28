package persistance.modèle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2021.*;
import persistance.DatabaseConnection;
import persistance.Session;


public class MediatekData implements PersistentMediatek {

	static {
		Mediatek.getInstance().setData(new MediatekData());
	}

	private MediatekData() {}

	// renvoie la liste de tous les documents de la bibliothèque
	// type :
	// - 0 Tous
	// - 1 Livre
	// - 2 DVD
	// - 3 CD
	@Override
	public List<Document> catalogue(int type) {
		List<Document> documents = new ArrayList<>();

		String sql = "SELECT * FROM DOCUMENTS";

		if(type > 0) {
			sql += " WHERE type = ?";
		}

		try {
			PreparedStatement statement = Session.getConnection().prepareStatement(sql);
			if(type > 0) {
				statement.setInt(1, type);
			}
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int typedoc = rs.getInt("type");
				String titre = rs.getString("titre");
				String autheur = rs.getString("autheur");
				boolean emprunt = rs.getBoolean("emprunt");
				PDocument doc = new PDocument(id, typedoc, titre, autheur, emprunt);
				documents.add(doc);
			}
		} catch (SQLException ignored) {}

		return documents;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String username, String password) {
		Connection db = DatabaseConnection.getConnection();
		String sql = "SELECT identifiant, motdepasse " +
					 "FROM UTILISATEURS " +
					 "WHERE identifiant = ? AND motdepasse = ?";

		try {
			PreparedStatement statement = db.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				LoggedUser user = new LoggedUser(username, password);
				DatabaseConnection.close(db);
				return user;
			}
			DatabaseConnection.close(db);
		} catch (SQLException ignored) {}

		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String sql = "SELECT * " +
					 "FROM DOCUMENTS " +
					 "WHERE id = ?";

		try {
			PreparedStatement statement = Session.getConnection().prepareStatement(sql);
			statement.setInt(1, numDocument);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int typedoc = rs.getInt("type");
				String titre = rs.getString("titre");
				String autheur = rs.getString("autheur");
				boolean emprunt = rs.getBoolean("emprunt");

				return new PDocument(id, typedoc, titre, autheur, emprunt);
			}
		} catch (SQLException ignored) {}

		return null;
	}

	// ajoute un nouveau document
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		String sql = "INSERT INTO DOCUMENTS (TYPE, TITRE, AUTHEUR) VALUES (?,?,?)";

		try {
			PreparedStatement statement = Session.getConnection().prepareStatement(sql);
			statement.setInt(1, type);
			statement.setString(2, (String) args[0]);
			statement.setString(3, (String) args[1]);

			statement.executeUpdate();
		} catch (Exception e) {
			throw new NewDocException("Impossible d'ajouter le document");
		}
	}

	// supprime un document
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		PDocument doc = (PDocument) getDocument(numDoc);
		if(doc == null)
			throw new SuppressException("Document " + doc + " inexistant ou déjà supprimé.");
		if(doc.estEmprunté())
			throw new SuppressException("Impossible de supprimer le document " + doc + " car il est actuellement emprunté.");

		String sql = "DELETE FROM DOCUMENTS WHERE ID = ?";
		try {
			PreparedStatement statement = Session.getConnection().prepareStatement(sql);
			statement.setInt(1, numDoc);

			statement.executeUpdate();
		} catch (Exception e) {
			throw new SuppressException("Impossible de supprimer le document");
		}
	}

}
