package main.java.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class InputManipulation {

    public String getInputFromTxt(String filePath) throws IOException {
        return Files.readAllLines(Paths.get("src\\main\\java\\" + filePath)).toString();
    }

    public Scanner getScannerFromFile(File inputFile) {
        //return new Scanner(inputFile);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scanner;
    }

    public List<String> getListofPartitionedTxtFile(String filePath) {
        String inputfromFile;
        try {
            inputfromFile = getInputFromTxt(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new StringManipulation().partitionStringByComma(inputfromFile);
    }

    public int getNumberOfLinesInFile(String fileName) {
        Path path = Paths.get(fileName);

        int lines = 0;
        try {

            // much slower, this task better with sequence access
            //lines = Files.lines(path).parallel().count();

            lines = (int) Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
