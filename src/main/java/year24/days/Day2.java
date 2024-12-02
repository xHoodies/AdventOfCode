package main.java.year24.days;

import main.java.common.StringManipulation;
import main.java.year24.Day2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 extends Day2024 {

    int MAX_DIFFERENCE = 3;
    int MIN_DIFFERENCE = 1;

    public Day2() {
        super(2);
    }

    @Override
    public Object part1() {
        return getNumberOfSafeReportsPart1();
    }

    @Override
    public Object part2() {
        int safeRows = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePathInput + "day2Input.txt"))) {
            String row;

            while ((row = br.readLine()) != null) {
                List<Integer> convertedRowToInt = getRowsAsIntegers(row);

                if (isRowSafe(convertedRowToInt)) {
                    safeRows += 1;
                } else {
                    safeRows = isRowSafeAfterAlteration(convertedRowToInt) ? safeRows + 1 : safeRows;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return safeRows;
    }

    @Override
    public void printSolution() {
        super.printSolution();
    }

    private Integer getNumberOfSafeReportsPart1() {
        int safeRows = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePathInput + "day2Input.txt"))) {
            String row;

            while ((row = br.readLine()) != null) {
                List<Integer> convertedRowToInt = getRowsAsIntegers(row);

                if (isRowSafe(convertedRowToInt)) {
                    safeRows += 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return safeRows;
    }

    private boolean isRowSafe(List<Integer> convertedRowToInt) {
        // Check if the row is safe
        boolean isSafe = true;
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < convertedRowToInt.size(); i++) {
            int prev = convertedRowToInt.get(i - 1);
            int curr = convertedRowToInt.get(i);

            // Check difference between adjacent levels
            int difference = Math.abs(curr - prev);
            if (difference < MIN_DIFFERENCE || difference > MAX_DIFFERENCE) {
                return isSafe = false;
            }

            // Check if levels are increasing or decreasing
            isIncreasing = isLevelIncreasing(prev, curr) && isIncreasing;
            isDecreasing = isLevelDecreasing(prev, curr) && isDecreasing;

        }

        // The row must be either strictly increasing or strictly decreasing
        if (!isIncreasing && !isDecreasing) {
            isSafe = false;
        }

        return isSafe;
    }

    private List<Integer> getRowsAsIntegers(String row) {
        return new StringManipulation().partitionStringByWhiteSpace(row).stream()
                .map(Integer::parseInt)
                .toList();
    }

    private boolean isLevelIncreasing(int prev, int curr) {
        return curr >= prev;
    }

    private boolean isLevelDecreasing(int prev, int curr) {
        return curr < prev;
    }

    private boolean isRowSafeAfterAlteration(List<Integer> convertedRowToInt) {
        for (int i = 0; i < convertedRowToInt.size(); i++) {
            List<Integer> modifiedRow = new ArrayList<>(convertedRowToInt);
            modifiedRow.remove(i); // Remove one element

            if (isRowSafe(modifiedRow)) {
                return true;
            }
        }
        return false;
    }

}
