package persistance.modèle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2021.*;
import persistance.DatabaseConnection;

// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de dépendance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-François Brette 01/01/2018
	static {
		// injection dynamique de la dépendance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
	System.out.println("LOADED");
	}

	private MediatekData() {
	}

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
			PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
			if(type > 0) {
				statement.setInt(1, type);
			}
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				PDocument doc = new PDocument(rs.getInt("id"), rs.getInt("type"),
						rs.getString("titre"), rs.getBoolean("emprunt"));
				documents.add(doc);
			}
		} catch (SQLException | ClassNotFoundException ignored) {}

		return documents;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String username, String password) {
		String sql = "SELECT username, password " +
					 "FROM USERS " +
					 "WHERE username = ? AND password = ?";

		try {
			PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				LoggedUser user = new LoggedUser(username, password);
				return user;
			}
		} catch (SQLException | ClassNotFoundException ignored) {}

		return null;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	// ajoute un nouveau document - exception à définir
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
	}

	// supprime un document - exception à définir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}

}
