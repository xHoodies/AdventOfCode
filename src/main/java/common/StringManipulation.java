package main.java.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringManipulation {
    /**
     * Input filePath after src\main\java\ and filename.
     */


    public List<String> partitionStringByComma(String input) {
        return new ArrayList<String>(Arrays.asList(input
                .substring(1, input.length() - 1)
                .split(",")));
    }

    public List<String> partitionStringByWhiteSpace(String input) {
        return new ArrayList<String>(Arrays.asList(input
                .split("\\s+")));
    }

    public List<List<Integer>> partitionStringByWhitespaceAndComma(String input, int numbersOnLine) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                //Split line by whitespace
                String[] numbers = line.trim().split("\\s+");

                //Check if there is expected number of numbers on line
                if (numbers.length == numbersOnLine) {
                    //Parse the numbers and add to respective List
                    // TODO: Make the number of lists a variable. (Keys?)
                    list1.add(Integer.parseInt(numbers[0]));
                    list2.add(Integer.parseInt(numbers[1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(list1, list2);
    }

    public List<String> removeEmptyAndNull(List<String> input) {
        input.removeAll(Arrays.asList(" ", null));
        return input;
    }

}
