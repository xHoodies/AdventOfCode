package main.java.year23.days;

import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.util.Scanner;

public class Day2 extends Day2023 {

    static final File inputFile = new File(filePathInput + "day2Input.txt");
    static final int maxBlue = 14;
    static final int maxRed = 12;
    static final int maxGreen = 13;

    public Day2() {
        super(2);
    }

    public static void main(String[] args) {
        new Day2().printSolution();
    }

    @Override
    public Object part1() {
        Scanner input = new InputManipulation().getScannerFromFile(inputFile);

        int sum = 0;
        while (input.hasNextLine()) {
            //Checks if the game is valid, if it is add to sum
            sum += isValidGame(input.nextLine());
        }

        return sum;
    }

    @Override
    public Object part2() {
        Scanner input = new InputManipulation().getScannerFromFile(inputFile);

        int sum = 0;
        while (input.hasNextLine()) {
            sum += minPowers(input.nextLine());
        }

        return sum;
    }

    private int isValidGame(String inputLine) {
        String[] gameSplit = inputLine.split(":");
        int gameNumber = Integer.parseInt(gameSplit[0].split("Game ")[1]);
        String[] cases = gameSplit[1].split("[,;]");
        for (String c : cases) {
            /*
             * without .trim() before .split(), count will show error due to " "
             */
            String[] cube = c.trim().split(" ");
            int count = Integer.parseInt(cube[0]);
            String color = cube[1];

            /*
             * if count greater than given limit of color --> Not Valid
             */
            if ((count > maxRed && color.equals("red"))
                    || (count > maxGreen && color.equals("green"))
                    || (count > maxBlue && color.equals("blue"))) {
                return 0;
            }
        }
        return gameNumber;
    }

    public static int minPowers(String inputLine) {
        String[] gameSplit = inputLine.split(":");
        String[] cases = gameSplit[1].split("[,;]");

        int rMax, gMax, bMax;
        rMax = gMax = bMax = 0;

        for (String c : cases) {
            /*
             * without .trim() before .split(), count will show error due to " "
             */
            String[] cube = c.trim().split(" ");

            int count = Integer.parseInt(cube[0]);
            String colour = cube[1].trim();

            /*
             * ----------------------------
             * Update Maximum of each Color
             * ----------------------------
             */
            if (count > rMax && colour.equals("red")) rMax = count;
            if (count > gMax && colour.equals("green")) gMax = count;
            if (count > bMax && colour.equals("blue")) bMax = count;
        }
        return rMax * gMax * bMax; // product
    }
}
