package persistance.modèle;

import mediatek2021.Document;

import java.util.HashMap;
import java.util.Map;

public class PDocument implements Document {

    private Map<String, Object> data = new HashMap<String, Object>();

    public PDocument(int id, int type, String titre, String auteur, boolean emprunt) {
        data.put("id", id);
        data.put("type", type);
        data.put("titre", titre);
        data.put("auteur", auteur);
        data.put("emprunt", emprunt);
    }

    @Override
    public Object[] data() {
        //retourne Map.Entry<String, Object>[] -> Set<Map.Entry<String, Object>>
        return data.entrySet().toArray();
    }

    public int getId() {
        return (int) data.get("id");
    }

    public String getType() {
        switch((int)data.get("type")) {
            case 1:
                return "Livre";
            case 2:
                return "DVD";
            case 3:
                return "CD";
            default:
                break;
        }
        return "invalide";
    }

    public String getTitre() {
        return (String) data.get("titre");
    }

    public String getAuteur() {
        return (String) data.get("auteur");
    }

    public boolean estEmprunté() {
        return (boolean) data.get("emprunt");
    }

    @Override
    public String toString() {
        return "#" + data.get("id") + "\"" + data.get("titre") + "\" (" + this.getType() + ")";
    }
}
