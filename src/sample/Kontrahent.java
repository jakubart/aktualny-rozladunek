package sample;

public abstract class Kontrahent {

    private int numerHodowcy;
    private String nazwa;
    private int numerDzienny;

    public Kontrahent() {
    }

    public Kontrahent(int numerHodowcy, String nazwa, int numerDzienny) {
        this.numerHodowcy = numerHodowcy;
        this.nazwa = nazwa;
        this.numerDzienny = numerDzienny;
    }

    public int getNumerHodowcy() {
        return numerHodowcy;
    }

    public void setNumerHodowcy(int numerHodowcy) {
        this.numerHodowcy = numerHodowcy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getNumerDzienny() {
        return numerDzienny;
    }

    public void setNumerDzienny(int numerDzienny) {
        this.numerDzienny = numerDzienny;
    }

}

