package Exceptii;

public class DetaliiCardIncorecte extends RuntimeException{
    public DetaliiCardIncorecte(){
        super(String.format("Datele introduse sunt incorecte!"));
    }
}
