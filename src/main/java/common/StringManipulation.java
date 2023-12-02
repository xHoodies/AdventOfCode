package main.java.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StringManipulation {
/**
Input filePath after src\main\java\ and filename.
 */
    public String getInputFromTxt(String filePath) throws IOException {
        return Files.readAllLines(Paths.get("src\\main\\java\\"+filePath)).toString();
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

    public List<String> partitionStringByComma(String input) {
        return new ArrayList<String>(Arrays.asList(input
                .substring(1, input.length() - 1)
                .split(",")));
    }

    public List<String> removeEmptyAndNull(List<String> input) {
        input.removeAll(Arrays.asList(" ", null));
        return input;
    }

    public List<String> getListofPartitionedTxtFile(String filePath) {
        String inputfromFile;
        try {
            inputfromFile = getInputFromTxt(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return partitionStringByComma(inputfromFile);
    }
}
