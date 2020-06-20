package Exceptii;

public class UtilizatorulExistaDeja extends Exception{

    public UtilizatorulExistaDeja(String username) {
        super(String.format("Un cont cu username %s exista deja!", username));
    }
}
