package persistance.modèle;

import mediatek2021.Utilisateur;

public class LoggedUser implements Utilisateur {

    private String username;
    private String password;

    public LoggedUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String login() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }

    @Override
    public String toString() {
        return this.username;
    }
}
