package javafx;

public class Argazki {

    private String izena;
    private String fitxategia;

    public Argazki(String izena, String location) {
        this.izena = izena;
        this.fitxategia = location;
    }

    public String getIzena() {
        return izena;
    }

    public String getFitxategia() {
        return fitxategia;
    }

    public String toString() {
        return this.izena;
    }
}
