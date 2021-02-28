package persistance.modèle;

import mediatek2021.Document;

public class PDocument implements Document {

    private int id;
    private int type;
    private String titre;
    private String autheur;
    private boolean emprunt;

    public PDocument(int id, int type, String titre, String autheur, boolean emprunt) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.autheur = autheur;
        this.emprunt = emprunt;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        switch(this.type) {
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
        return this.titre;
    }

    public String getAutheur() {
        return this.autheur;
    }

    public boolean estEmprunté() {
        return this.emprunt;
    }

    @Override
    public String toString() {
        return "#" + id + "\"" + titre + "\" (" + type + ")";
    }
}
