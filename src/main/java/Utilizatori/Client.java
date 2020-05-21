package Utilizatori;

public class Client {



    private String username;

    private String password;


    public Client() {

    }



    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {

        return password;

    }



    public void setPassword(String password) {

        this.password = password;

    }

    @Override

    public int hashCode() {

        int result = username.hashCode();

        result = 31 * result + password.hashCode();

        return result;

    }



    @Override

    public String toString() {

        return "ClientDTO{" +

                "username='" + username + '\'' +

                ", password='" + password + '\'' +
                '}';

    }

}