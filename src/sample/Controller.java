package sample;


import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDateTime;


public class Controller {

    public TableView<Samochod> tableView = new TableView<>();
    public TableColumn<Tabela, String> tableColumnHodowca;
    public Label labelData;

    public void initialize() {
        utworzTabele();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int sekunda = 0;
                while (sekunda < 11) {
                    if (sekunda == 10) {
                        Platform.runLater(() -> utworzTabele());
                        //utworzTabele();
                        System.out.println("Pobrałem nowe dane");
                        sekunda = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Plik.zapiszWyjatek(e);
                        System.out.println("Watek czasu zostal przerwany");
                        e.printStackTrace();
                    }
                    sekunda++;
                    System.out.println(sekunda);
                }
            }
        });
        thread.start();
    }

    private void utworzTabele() {
        labelData.setText(LocalDateTime.now().toLocalDate().toString());
        KolejnoscSamochodow kolejnoscSamochodow = new KolejnoscSamochodow();
        Samochod samochod = new Samochod();
        //warunek sprawdza czy są wstawione rekordy w bazie danych
        if (kolejnoscSamochodow.getListaDziennaSamochodow() != null) {
            Tabela tabela = new Tabela(samochod.pobierzListeSamochodow(kolejnoscSamochodow.getListaDziennaSamochodow()));
            tableView.setMaxSize(1300, 750);
            tableView.setItems(tabela.stworzTabele());
            kolor();
            zawijajTekst();
        }
    }

    private void kolor() {
        tableView.setRowFactory(row -> new TableRow<Samochod>() {
            @Override
            protected void updateItem(Samochod item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                } else {
                    if (item.getLp() == Tabela.aktualnaPozycja) {
                        this.setId("kolor");
                        //System.out.println("kolor" + item.getLp());
                    } else if (item.getLp() < Tabela.aktualnaPozycja) {
                        this.setId("zrobione");
                        //System.out.println("zrobione" + item.getLp());
                    } else {
                        this.setId("niezrobione");
                        //System.out.println("niezrobione" + item.getLp());
                    }

                }
            }
        });
    }

    private void zawijajTekst() {
        tableColumnHodowca.setCellFactory(param -> {
            return new TableCell<Tabela, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        //setText(null);
                        //setStyle("");
                    } else {
                        Text text = new Text(item);
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                        //System.out.println(item);
                    }
                }
            };
        });
    }
}

