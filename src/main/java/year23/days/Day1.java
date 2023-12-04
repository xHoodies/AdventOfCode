package main.java.year23.days;

import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 extends Day2023 {

    Map<String, String> stringToNumMap = new HashMap<String, String>() {{
        put("one", "o1e");
        put("two", "t2o");
        put("three", "thr3ee");
        put("four", "fo4r");
        put("five", "fi5e");
        put("six", "s6x");
        put("seven", "se7en");
        put("eight", "ei8ht");
        put("nine", "n9ne");
    }};

    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1().printSolution();
    }

    @Override
    public Object part1() {
        List<String> input = new InputManipulation().getListofPartitionedTxtFile("year23\\input\\day1Input.txt");

        return getSumOfCalibrationValues(input);
    }

    @Override
    public Object part2() {
        File input = new File("C:\\Users\\ditte\\IdeaProjects\\AoC\\src\\main\\java\\year23\\input\\day1Input.txt");

        return getSumOfCalibrationValues(input);
    }

    private Integer getSumOfCalibrationValues(List<String> input) {
        List<Integer> first = extractFirstNumbers(input);
        List<Integer> last = extractLastNumbers(input);
        List<Integer> concatenateIntegers = concatenateTwoIntegers(first, last);

        return concatenateIntegers.stream().mapToInt(Integer::intValue).sum();
    }

    private Integer getSumOfCalibrationValues(File inputFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String currLine = "";
        int sum = 0;

        while (scanner.hasNextLine()) {

            String concatNum = "";
            currLine = scanner.nextLine();

            for (Map.Entry<String, String> entry : stringToNumMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                //TODO: check if no chars are left. If not continue
                if (currLine.contains(key)) {
                    StringBuilder str = new StringBuilder(currLine);
                    currLine = currLine.replace(key, value);
                }
            }

            String onlyNumeric = currLine.replaceAll("[^0-9]", "");
            if (Character.isDigit(onlyNumeric.charAt(0))) {
                int n = onlyNumeric.length();
                char first = onlyNumeric.charAt(0);
                char last = onlyNumeric.charAt(n - 1);

                concatNum = new StringBuilder().append(first).append(last).toString();
            }

            sum += Integer.parseInt(concatNum);
        }
        return sum;
    }

    private static List<Integer> extractFirstNumbers(List<String> inputList) {
        List<Integer> resultList = new ArrayList<>();

        for (String s : inputList) {
            int i = 0;
            while (i < s.length() && !Character.isDigit(s.charAt(i))) {
                i++;
            }

            if (Character.isDigit(s.charAt(i))) {
                resultList.add(Integer.parseInt(s.substring(i, i + 1)));
            }

        }
        return resultList;
    }

    private static List<Integer> extractLastNumbers(List<String> inputList) {
        List<Integer> resultList = new ArrayList<>();

        for (String str : inputList) {
            String s = new StringBuilder(str).reverse().toString();
            int i = 0;
            while (i < s.length() && !Character.isDigit(s.charAt(i))) {
                i++;
            }

            if (Character.isDigit(s.charAt(i))) {
                resultList.add(Integer.parseInt(s.substring(i, i + 1)));
            }
        }
        return resultList;
    }

    private List<Integer> concatenateTwoIntegers(List<Integer> firstIntegers, List<Integer> lastIntegers) {
        List<Integer> concatedIntegers = new ArrayList<>();

        for (int i = 0; i < firstIntegers.toArray().length; i++) {
            concatedIntegers.add(Integer.parseInt(firstIntegers.get(i) + Integer.toString(lastIntegers.get(i))));
        }

        return concatedIntegers;
    }

}