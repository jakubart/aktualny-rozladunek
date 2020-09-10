package sample;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Plik {

    public static void zapiszWyjatek(Exception e) {
        FileWriter file = null;
        try {
            file = new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory() + "\\aktualnyUbojErrorLog.txt", true);
            BufferedWriter out = new BufferedWriter(file);
            PrintWriter pw = new PrintWriter(file);
            pw.append("****************** Czas wystąpienia : ").append(LocalDateTime.now().toString()).append("**********").append("\n");
            e.printStackTrace(pw);
            //out.append(pw.toString());
            out.close();
            pw.close();
        } catch (IOException e1) {
            System.out.println("Nie można zapisać do pliku");
            e1.printStackTrace();
        }
    }
}
