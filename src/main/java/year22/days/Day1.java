package main.java.year22.days;

import main.java.year22.Day2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 extends Day2022 {
    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1().printSolution();
    }

    @Override
    public Object part1() {
        //String input = new String(Files.readAllBytes(Paths.get("Day1Input.txt")));
        //String input = readFile(Paths.get("Day1Input.txt"), StandardCharsets.UTF_8));
        String input;
        try {
            input = getInputFromTxt("Day1Input.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(input);
        List<String> partitionedInput = partitionByComma(input);
        System.out.println(partitionedInput);

        return input;
    }

    @Override
    public Object part2() {
        return null;
    }

    private String getInputFromTxt(String fileName) throws IOException {
        return Files.readAllLines(Paths.get("src\\main\\java\\year22\\input\\Day1Input.txt")).toString();
    }

    private List<String> partitionByComma(String input) {
        List<String> partitionedInput = new ArrayList<String>(Arrays.asList(input
                .substring(1, input.length() - 1)
                .split(",")));

        partitionedInput.removeAll(Arrays.asList(" ", null));
        return partitionedInput;
    }

}
