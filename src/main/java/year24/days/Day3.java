package main.java.year24.days;

import main.java.year24.Day2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day2024 {

    public Day3() {
        super(3);
    }

    @Override
    public void printSolution() {
        super.printSolution();
    }

    @Override
    public Object part1() {
        int productForAllRows = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePathInput + "day3Input.txt"))) {
            String row;

            while ((row = br.readLine()) != null) {
                productForAllRows += getProductsInRow(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return productForAllRows;
    }

    @Override
    public Object part2() {
        int product = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePathInput + "day3Input.txt"))) {
            String row;
            // Start by not ignoring, and we need it outside loop to save the last known state before row change
            boolean isDisabled = false;

            while ((row = br.readLine()) != null) {
                // Matchers
                Matcher matcherMul = getMulMatcher(row);
                Matcher matcherDont = getDontMatcher(row);
                Matcher matcherDo = getDoMatcher(row);

                // Process the input
                int lastProcessedIndex = 0; // Keeps track of the last processed position

                while (true) {
                    // Find the next relevant match (mul, don't, or do)
                    int nextMulIndex = getNextMulIndex(matcherMul, lastProcessedIndex);
                    int nextDontIndex = getNextDontIndex(matcherDont, lastProcessedIndex);
                    int nextDoIndex = getNextDoIndex(matcherDo, lastProcessedIndex);

                    // Determine the next event
                    int nextEventIndex = getNextEventIndex(nextMulIndex, nextDontIndex, nextDoIndex);

                    if (nextEventIndex == Integer.MAX_VALUE) {
                        break; // No more matches
                    }

                    if (nextEventIndex == nextMulIndex) {
                        // Process a mul() only if not disabled
                        if (!isDisabled) {
                            product += calculateProductFromMatcher(matcherMul);
                        }
                        lastProcessedIndex = matcherMul.end();
                    } else if (nextEventIndex == nextDontIndex) {
                        // Encountered a don't(), disable multiplying
                        isDisabled = true;
                        lastProcessedIndex = matcherDont.end();
                        System.out.println("Disabled " + lastProcessedIndex);
                    } else if (nextEventIndex == nextDoIndex) {
                        // Encountered a do(), enable multiplying
                        isDisabled = false;
                        lastProcessedIndex = matcherDo.end();
                        System.out.println("Enabled " + lastProcessedIndex);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }

    private int getProductsInRow(String row) {
        // Regular expression to match mul(number,number) where number is 1-3 digits
        String regex = "mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(row);

        int product = 0;

        // Find all matches
        while (matcher.find()) {

            // Extract the two numbers from the match
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));

            // Multiply the numbers
            product += num1 * num2;
        }

        return product;
    }

    private int calculateProductFromMatcher(Matcher matcherMul) {
        int product = 0;
        int num1 = Integer.parseInt(matcherMul.group(1));
        int num2 = Integer.parseInt(matcherMul.group(2));

        product += num1 * num2;
        System.out.println("Match: " + matcherMul.group() + " -> " + num1 + " * " + num2 + " = " + product);

        return product;
    }

    private Matcher getDoMatcher(String row) {
        String regexDo = "do\\(\\)";
        Pattern patternDo = Pattern.compile(regexDo);
        return patternDo.matcher(row);
    }

    private Matcher getDontMatcher(String row) {
        String regexDont = "don't\\(\\)";
        Pattern patternDont = Pattern.compile(regexDont);
        return patternDont.matcher(row);
    }

    private Matcher getMulMatcher(String row) {
        String regexMul = "mul\\((\\d{1,3}),\\s*(\\d{1,3})\\)";
        Pattern patternMul = Pattern.compile(regexMul);
        return patternMul.matcher(row);
    }

    private int getNextMulIndex(Matcher matcherMul, int lastProcessedIndex) {
        return matcherMul.find(lastProcessedIndex) ? matcherMul.start() : Integer.MAX_VALUE;
    }

    private int getNextDontIndex(Matcher matcherDont, int lastProcessedIndex) {
        return matcherDont.find(lastProcessedIndex) ? matcherDont.start() : Integer.MAX_VALUE;
    }

    private int getNextDoIndex(Matcher matcherDo, int lastProcessedIndex) {
        return matcherDo.find(lastProcessedIndex) ? matcherDo.start() : Integer.MAX_VALUE;
    }

    private int getNextEventIndex(int nextMulIndex, int nextDontIndex, int nextDoIndex) {
        return Math.min(nextMulIndex, Math.min(nextDontIndex, nextDoIndex));
    }
}