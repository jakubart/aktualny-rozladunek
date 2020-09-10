package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Tabela {

    private ArrayList<Samochod> listaSamochodow;
    public static int aktualnaPozycja;

    public Tabela(ArrayList<Samochod> listaSamochodow) {
        this.listaSamochodow = listaSamochodow;
    }

    public ArrayList<Samochod> getListaSamochodow() {
        return listaSamochodow;
    }

    public void setListaSamochodow(ArrayList<Samochod> listaSamochodow) {
        this.listaSamochodow = listaSamochodow;
    }

    public ObservableList<Samochod> stworzTabele() {
        ObservableList<Samochod> tabela = FXCollections.observableArrayList();
        aktualnaPozycja = 0;
        //przeglądanie listy od końca i ustalenie aktualnej pozzycji
        for (int i = 0; i < listaSamochodow.size(); i++) {
            //wyszukaj pierwszy od końca rozpoczęty samochód
            if (listaSamochodow.get(listaSamochodow.size() - i - 1).getKonteneryZrobione() > 0) {
                //jęzeli kontenery zrobione = ilość kontenerów to przesuń pozycje dalej
                if (listaSamochodow.get(listaSamochodow.size() - i - 1).getKonteneryZrobione() == listaSamochodow.get(listaSamochodow.size() - i - 1).getIloscKontenerow()) {
                    aktualnaPozycja = listaSamochodow.size() - i + 1;
                } else {
                    aktualnaPozycja = listaSamochodow.size() - i;
                }
                break;
            }
        }
        //jeżeli aktualna pozycja < od 4 to dodaj całą listę do widoku w innym wypadku weź aktualną pozycję oraz jeden samochód zrobiony
        if (aktualnaPozycja > 0) {
            if (aktualnaPozycja < 4 && listaSamochodow.size() > 0) {
                tabela.addAll(listaSamochodow);
            } else if (aktualnaPozycja == listaSamochodow.size()) {
                tabela.clear();
            } else {
                for (int i = aktualnaPozycja - 2; i < listaSamochodow.size(); i++) {
                    tabela.add(listaSamochodow.get(i));
                }
            }
        }
        return tabela;
    }
}
