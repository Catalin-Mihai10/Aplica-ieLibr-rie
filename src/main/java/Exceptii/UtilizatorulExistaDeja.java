package Exceptii;

public class UtilizatorulExistaDeja extends Exception{
    private String username;

    public UtilizatorulExistaDeja(String username) {
        super(String.format("Un cont cu username %s exista deja!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
