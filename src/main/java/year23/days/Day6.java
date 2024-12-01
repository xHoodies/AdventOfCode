package main.java.year23.days;

import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 extends Day2023 {
    static final File inputFile = new File(filePathInput + "day6Input.txt");

    public Day6() {
        super(6);
    }

    public static void main(String[] args) {
        new Day6().printSolution();
    }

    @Override
    public Object part1() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);

        List<Integer> time = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        Long res = 1L;

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                time = getValues(inputScanner.nextLine());
            } else
                distance = getValues(inputScanner.nextLine());
        }

        for (int i = 0; i < time.size(); i++) {
            res = res * calculateSolutions(Long.valueOf(time.get(i)), Long.valueOf(distance.get(i)));
        }

        inputScanner.close();

        return res;
    }

    @Override
    public Object part2() {
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);

        long time = 0L;
        long distance = 0L;
        long res = 0L;

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                String currentLine = inputScanner.nextLine();
                time = Long.parseLong(currentLine.replace("Time:", "").replaceAll("\\s", "").trim());
            } else
                distance = Long.parseLong(inputScanner.nextLine().replace("Distance:", "").replaceAll("\\s", "").trim());
        }

        res = calculateSolutions(time, distance);

        inputScanner.close();

        return res;
    }

    private List<Integer> getValues(String currentLine) {
        String[] test;
        List<Integer> res = new ArrayList<>();

        if (currentLine.contains("Time:"))
            test = currentLine.replace("Time:", "").trim().split("\\s+");
        else
            test = currentLine.replace("Distance:", "").trim().split("\\s+");

        for (String s : test) res.add(Integer.parseInt(s));

        return res;
    }

    private static long calculateSolutions(Long time, Long distance) {
        long solution = 0;
        for (long i = 1; i < time; i++) {
            if ((time - i) * i > distance) {
                solution++;
            }
        }

        return solution;
    }
}