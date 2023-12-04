package main.java.year23.days;

import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 extends Day2023 {

    static final File inputFile = new File(filePathInput + "day3Input.txt");
    static int numberOfLinesInscanner = 0;

    public Day3() {
        super(3);
    }

    public static void main(String[] args) {
        new Day3().printSolution();
    }

    @Override
    public Object part1() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);
        //Make sure to craete a new scanner object, else the main object will be used.
        Scanner inputScanner2 = new InputManipulation().getScannerFromFile(inputFile);

        numberOfLinesInscanner = getNumberOfLinesInScanner(inputScanner2);

        String[] inputArray = new String[numberOfLinesInscanner];

        for (int i = 0; i < numberOfLinesInscanner; i++) {
            inputArray[i] = inputScanner.nextLine();
        }

        //Calculate the sum of part numbers
        return calculatePartNumberSum(inputArray);
    }

    @Override
    public Object part2() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);
        //Make sure to create a new scanner object, else the main object will be used.
        Scanner inputScanner2 = new InputManipulation().getScannerFromFile(inputFile);

        numberOfLinesInscanner = getNumberOfLinesInScanner(inputScanner2);

        String[] inputArray = new String[numberOfLinesInscanner];

        for (int i = 0; i < numberOfLinesInscanner; i++) {
            inputArray[i] = inputScanner.nextLine();
        }

        return null;//calculateGearRatios(inputArray);
    }

    private static int getNumberOfLinesInScanner(Scanner scanner) {
        int count = 0;

        while (scanner.hasNextLine()) {
            count++;
            scanner.nextLine();
        }
        return count;
    }

    private static int calculatePartNumberSum(String[] schematic) {
        int sum = 0;

        for (int i = 0; i < numberOfLinesInscanner; i++) {
            boolean prevCharWasDot = false;
            int currentWholeNumber = 0;
            for (int j = 0; j < schematic[i].length(); j++) {
                char currentChar = schematic[i].charAt(j);

                //Only want whole number when either at the start of schematic, or it was after a ".", and the current Char is a digit
                if ((j == 0 || prevCharWasDot) && Character.isDigit(currentChar)) {
                    currentWholeNumber = getCurrentWholeNumber(schematic[i], j);
                    //Remember to reset prevCharWasDot, else it never will be in this inner for loop.
                    prevCharWasDot = false;
                }

                // Check if the current character is a digit
                if (Character.isDigit(currentChar)) {
                    // Check adjacent positions (including diagonals) for symbols
                    if (hasAdjacentSymbol(schematic, i, j)) {
                        sum += currentWholeNumber;
                        currentWholeNumber = 0;
                    }
                }

                if (!Character.isDigit(currentChar)) {
                    prevCharWasDot = true;
                }
            }
        }

        return sum;
    }

    private static int getCurrentWholeNumber(String row, int startFrom) {
        StringBuilder temp = new StringBuilder();
        for (int y = startFrom; y < row.length(); y++) {
            char currentChar = row.charAt(y);
            if (Character.isDigit(currentChar)) {
                temp.append(currentChar);
            } else {
                break; // Stop loop when a non-digit character is encountered
            }
        }
        return Integer.parseInt(temp.toString());
    }

    private static int getCurrentWholeNumberFromAnyPosition(String row, int startFrom) {
        StringBuilder temp = new StringBuilder();
        int digitsToLeft = 0;
        int digitsToRight = 0;

        for (int i = 0; i < 3; i++) {
            char currentChar = row.charAt(startFrom - i);
            if (Character.isDigit(currentChar)) {
                digitsToLeft++;

            } else break;
        }
        digitsToLeft -= 1;

        for (int i = 0; i < 3; i++) {
            char currentChar = row.charAt(Math.max(0, startFrom + i));
            if (Character.isDigit(currentChar)) {
                digitsToRight++;

            } else break;
        }
        digitsToRight -= 1;

        for (int i = digitsToLeft; i <= digitsToLeft; i -= 1) {
            char currentChar = row.charAt(startFrom - i);
            if (digitsToLeft == 0) {
                break;
            }
            if (Character.isDigit(currentChar)) {
                temp.append(currentChar);

            } else break;
        }

        for (int i = digitsToRight; i >= digitsToRight; i--) {
            char currentChar = row.charAt(Math.max(0, startFrom + i));
            if (digitsToRight == 0) {
                break;
            }
            if (Character.isDigit(currentChar)) {
                temp.append(currentChar);
            } else break;
        }

        return Integer.parseInt(temp.toString());
    }

    private static boolean hasAdjacentSymbol(String[] schematic, int row, int col) {
        // Define the relative positions of adjacent cells
        int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};

        // Check each adjacent position for symbols
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // Check if the new position is within bounds and contains a symbol
            if (newRow >= 0 && newRow < numberOfLinesInscanner && newCol >= 0 && newCol < schematic[newRow].length()) {
                char symbol = schematic[newRow].charAt(newCol);

                if (symbol != '.' && symbol != ' ' && !Character.isDigit(symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int calculateGearRatios(String[] schematic) {
        int sum = 0;

        for (int i = 0; i < numberOfLinesInscanner; i++) {
            for (int j = 0; j < schematic[i].length(); j++) {
                char currentChar = schematic[i].charAt(j);

                // Check if the current character is an asterisk
                if (currentChar == '*') {
                    int[] partNumbers = getAdjacentPartNumbers(schematic, i, j);
                    // If there are exactly two adjacent part numbers, calculate the gear ratio and add to sum
                    if (partNumbers.length == 2) {
                        int gearRatio = partNumbers[0] * partNumbers[1];
                        sum += gearRatio;
                    }
                }
            }
        }

        return sum;
    }

    private static int[] getAdjacentPartNumbers(String[] schematic, int row, int col) {
        List<Integer> adjacentPartNumbers = new ArrayList<>();

        // Define the relative positions of adjacent cells
        int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};

        // Check each adjacent position for part numbers
        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            // Check if the new position is within bounds and contains a digit
            if (newRow >= 0 && newRow < numberOfLinesInscanner && newCol >= 0 && newCol < schematic[newRow].length()) {
                char currentChar = schematic[newRow].charAt(newCol);
                if (Character.isDigit(currentChar)) {
                    adjacentPartNumbers.add(getCurrentWholeNumberFromAnyPosition(schematic[newRow], newCol));
                }
            }
        }

        // Convert the list of integers to an array
        return adjacentPartNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
