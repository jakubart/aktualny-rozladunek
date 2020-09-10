package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class Samochod extends Kontrahent {

    private int lp;
    private String nrRejestracyjny;
    private int iloscKontenerow;
    private int konteneryZrobione;

    public Samochod() {
    }

    public Samochod(int numerHodowcy, String nazwa, int numerDzienny, int lp, String nrRejestracyjny, int iloscKontenerow, int konteneryZrobione) {
        super(numerHodowcy, nazwa, numerDzienny);
        this.lp = lp;
        this.nrRejestracyjny = nrRejestracyjny;
        this.iloscKontenerow = iloscKontenerow;
        this.konteneryZrobione = konteneryZrobione;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public int getIloscKontenerow() {
        return iloscKontenerow;
    }

    public void setIloscKontenerow(int iloscKontenerow) {
        this.iloscKontenerow = iloscKontenerow;
    }

    public int getKonteneryZrobione() {
        return konteneryZrobione;
    }

    public void setKonteneryZrobione(int konteneryZrobione) {
        this.konteneryZrobione = konteneryZrobione;
    }

    public ArrayList<Samochod> pobierzListeSamochodow(Map<Integer, Integer> kolejnoscSamochodow) {
        ArrayList<Samochod> listaSamochodow = new ArrayList<>();
        int licznik = 1;
        Connection conn = ConnectionHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT s.id, s.nazwa, s.nr_hodowcy, ifnull(r.nr_rejestracyjny,' ') as nr_rejestracyjny, s.kontenery, s.kontenery_zrobione FROM meyn.wazenia_stork s" +
                    " LEFT JOIN meyn.wazenia_stork_rozliczenie r on s.id = r.id where s.data = cast(now() as date) ORDER BY s.id");//
//           ResultSet rs = stmt.executeQuery("SELECT nazwa, nr_hodowcy, ifnull(nr_rejestracyjny,' ') as nr_rejestracyjny, kontenery, kontenery_zrobione FROM wazenia_stork s" +
//                  " where s.data = cast(now() as date)");
            while (rs.next()) {
                listaSamochodow.add(new Samochod(
                        rs.getInt("nr_hodowcy"),
                        rs.getString("nazwa"),
                        kolejnoscSamochodow.get(rs.getInt("nr_hodowcy")),
                        licznik++,
                        rs.getString("nr_rejestracyjny"),
                        rs.getInt("kontenery"),
                        rs.getInt("kontenery_zrobione"))
                );
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Nie można pobrać listy samochodów");
            Plik.zapiszWyjatek(e);
            e.printStackTrace();
        }
        return listaSamochodow;
    }
}

