package edu.bsu.cs;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RefreshTracker {
    protected static void saveCurrentTime(String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(LocalDateTime.now().toString().substring(0,16));
            fileWriter.close();
        }catch (Exception ignore){}
    }

    protected static String readTimeFile(String fileName) throws IOException {
        File timeFile = new File(fileName);
        Scanner scanner = new Scanner(timeFile);
        return scanner.nextLine();
    }
}
