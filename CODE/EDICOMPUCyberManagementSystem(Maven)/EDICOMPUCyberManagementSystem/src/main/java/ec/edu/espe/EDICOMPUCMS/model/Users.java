package ec.edu.espe.EDICOMPUCMS.model;

import ec.edu.espe.EDICOMPUCMS.utils.PasswordManager;

public class Users {
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = PasswordManager.encrypt(password); // Encriptar la contraseña al crear el usuario
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return PasswordManager.decrypt(password); // Desencriptar la contraseña al obtenerla
    }

    public void setPassword(String password) {
        this.password = PasswordManager.encrypt(password); // Encriptar la contraseña al establecerla
    }
}
