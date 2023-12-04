package main.java.year23.days;


import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.util.Scanner;

public class Day4 extends Day2023 {
    static final File inputFile = new File(filePathInput + "day4Input.txt");
    static final String inputPathDay = filePathInput + "day4Input.txt";
    static int numberOfLinesInFile = new InputManipulation().getNumberOfLinesInFile(inputPathDay);

    public Day4() {
        super(4);
    }

    public static void main(String[] args) {
        new Day4().printSolution();
    }

    @Override
    public Object part1() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);
        int points = 0;

        while (inputScanner.hasNextLine()) {
            String convertedCurrentLine = convertSingleDigitSpaceToZero(inputScanner.nextLine());

            points += calculatePoints(getWinnerString(convertedCurrentLine), getNumbers(convertedCurrentLine), false);
        }

        return points;
    }

    @Override
    public Object part2() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);
        int[] copiedCards = new int[numberOfLinesInFile];
        int lineCounter = 0;
        int sumOfCopyCards = 0;

        for (int i = 0; i < numberOfLinesInFile; i++) {
            copiedCards[i] = 1;
        }

        while (inputScanner.hasNextLine()) {
            String convertedCurrentLine = convertSingleDigitSpaceToZero(inputScanner.nextLine());
            int points = calculatePoints(getWinnerString(convertedCurrentLine), getNumbers(convertedCurrentLine), true);

            for (int i = 0; i < points; i++) {
                for (int j = 0; j < copiedCards[lineCounter]; j++) {
                    copiedCards[i + lineCounter + 1] += 1;
                }
            }

            lineCounter++;
        }

        for (int i = 0; i < copiedCards.length; i++) {
            sumOfCopyCards += copiedCards[i];
        }

        return sumOfCopyCards;
    }

    private int calculatePoints(String winner, String[] numbers, boolean isPart2) {
        int count = 0;
        int points = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (winner.contains(numbers[i])) {
                if (count == 0 || isPart2) {
                    points += 1;
                    count++;
                } else {
                    points = points * 2;
                    count++;
                }
            }
        }
        return points;
    }

    private String getWinnerString(String line) { //TODO: make more generatic. E.g so it either can return winner and number substring, based on input.
        int indexOfColon = getPlacementOfSymbol(line, ':');
        int indexOfLine = getPlacementOfSymbol(line, '|');

        return line.substring(indexOfColon, indexOfLine)
                .replace(":", "")
                .replace("|", "")
                .trim();
    }

    private String[] getNumbers(String line) {
        int indexOfLine = getPlacementOfSymbol(line, '|');

        String numberLine = line.substring(indexOfLine)
                .replace("|", "")
                .trim();

        return numberLine.split("\\s+");
    }

    private int getPlacementOfSymbol(String line, char c) {
        return line.indexOf(String.valueOf(c));
    }

    private String convertSingleDigitSpaceToZero(String line) {
        return line.replace("  ", " 0");
    }


    private static int getNumberOfLinesInScanner(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }
        return count;
    }
}



