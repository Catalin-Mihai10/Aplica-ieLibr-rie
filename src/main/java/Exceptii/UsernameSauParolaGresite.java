package Exceptii;

public class UsernameSauParolaGresite extends Exception{

    public UsernameSauParolaGresite() {
        super(String.format("Username sau parola gresite"));
    }
}
