package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class KolejnoscSamochodow {

    private Map<Integer, Integer> listaDziennaSamochodow = new TreeMap<>();

    public Map<Integer, Integer> getListaDziennaSamochodow() {
        int licznik = 1;
        Connection conn = ConnectionHelper.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct(nr_hodowcy) FROM wazenia_stork  where data = cast(now() as date)");
            //ResultSet rs = stmt.executeQuery("SELECT distinct(nr_hodowcy) FROM wazenia_stork  where data = cast(now() as date)");
            while (rs.next()) {
                listaDziennaSamochodow.put(rs.getInt("nr_hodowcy"), licznik);
                licznik++;
            }
            conn.close();
        } catch (SQLException e1) {
            Plik.zapiszWyjatek(e1);
            System.out.println("Nie można utworzyć listy dziennej hodowców");
            e1.printStackTrace();
        }
        return listaDziennaSamochodow;
    }
}
