package Carti;

public class Carte {
    private String titlu;
    private String autor;
    private String editura;
    private String pret;
    private String categorie;

    public Carte(String t, String a, String e, String p, String c){
        this.titlu=t;
        this.autor=a;
        this.pret=p;
        this.editura=e;
        this.categorie=c;
    }

    public String getTitlu(){
        return this.titlu;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getEditura(){
        return this.editura;
    }

    public String getPret(){
        return this.pret;
    }

    public String getCategorie(){
        return this.categorie;
    }
}
