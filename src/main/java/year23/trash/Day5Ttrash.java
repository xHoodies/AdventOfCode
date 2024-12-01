package main.java.year23.trash;

import com.sun.jdi.IntegerType;
import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

public class Day5Ttrash  {
    static final String inputFileName = "day5Input.txt";
    static final File inputFile = new File(inputFileName);
    //static final String inputPathDay = filePathInput + inputFileName;
    //static int numberOfLinesInFile = new InputManipulation().getNumberOfLinesInFile(inputPathDay);
    private static Long maxArraySize; //TODO: Doesn't have to be long
    private static final List<String> maps = List.of("seed-to-soil",
            "soil-to-fertilizer",
            "fertilizer-to-water",
            "water-to-light",
            "light-to-temperature",
            "temperature-to-humidity",
            "humidity-to-location");

    //public Day5() {
    //    super(5);
    //}

    public static void main(String[] args) {
        new main.java.year23.days.Day5().printSolution();
    }

    //@Override
    public Object part1() {
        //Convert "  " to 0
        Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);


        List<Long> conversions = new ArrayList<>();



        conversions = getConversions(inputScanner);

        return conversions;//.stream().mapToInt(v -> v).min();
    }

    //@Override
    public Object part2() {
        return null;
    }

    private List<Long> getConversions(Scanner inputScanner) {
        List<Long> convertedSeeds = new ArrayList<>();
        while (inputScanner.hasNextLine()) {

            String currentLine = inputScanner.nextLine();

            List<Long> seeds = new ArrayList<>();
            if (currentLine.contains("seeds:")) {
                seeds = getSeeds(currentLine);
                inputScanner.nextLine();
                currentLine = inputScanner.nextLine();
            }


            for (String map : maps) {
                if (currentLine.contains(map)) {
                    currentLine = inputScanner.nextLine();

                    List<String> startToDestinationMapRows = new ArrayList<>();
                    while (!currentLine.isEmpty()) {
                        startToDestinationMapRows.add(currentLine);

                        // Check if we are at the end of the file, else jump out of loop
                        if (inputScanner.hasNextLine())
                            currentLine = inputScanner.nextLine();
                        else break;
                    }

                    //Long[] seedToSoilmap = getSeedToSoilMap(startToDestinationMapRows);
                    List<Long> seedToSoilmap = getSeedToSoilMap(startToDestinationMapRows);


                    for (int i = 0; i < seeds.size(); i++) {
                        if (convertedSeeds.size() < seeds.size()) {
                            //convertedSeeds.add(Math.toIntExact(seedToSoilmap[Math.toIntExact(seeds.get(i))]));
                            convertedSeeds.add(seedToSoilmap.get(Math.toIntExact(seeds.get(i))));
                        } else {
                            //convertedSeeds.set(i, Math.toIntExact((seedToSoilmap[convertedSeeds.get(i)])));
                            convertedSeeds.set(0, seedToSoilmap.get(Math.toIntExact(seeds.get(i))));
                        }

                    }
                    //int count = 0;
                    //for (Integer convertedSeed : convertedSeeds) {
                    //    System.out.println("Converted seeds: " + convertedSeed.toString() + " Count: " + count);
                    //    count++;
                    //}
                    if (inputScanner.hasNextLine())
                        currentLine = inputScanner.nextLine();
                }
            }
        }
        return convertedSeeds;
    }

    private List<Long> getSeeds(String currentLine) {
        List<Long> seeds = new ArrayList<>();
        if(currentLine.contains("seeds:")) {

            Arrays.stream(currentLine
                            .replace("seeds:", "")
                            .trim()
                            .split("\\s"))
                    .forEach(seedString-> seeds.add(Long.parseLong(seedString)));
        }
        maxArraySize = getMaxArraySize(seeds);
        return seeds;
    }

    /*private Long[] getSeedToSoilMap(List<String> rows) {
        //row: 50 98 02
        //Long[] map = new Long[BigInteger.valueOf(maxArraySize).intValue()];

        List<Long> map = new ArrayList<>();
        // Fill array with initial values
        for (int i = 0; i < maxArraySize; i++) {
            map[i] = Long.valueOf(i);
        }

        for (String row : rows) {
            String[] rowNumbers = row.split(" ");

            //rowNumber[0] = Destination
            //rowNumber[1] = Source
            //rowNumber[2] = Span

            for (int i = 0; i < Integer.parseInt(rowNumbers[2]); i++) {
                int startFrom = Integer.parseInt(rowNumbers[1]);
                map[startFrom+i] = Long.parseLong(rowNumbers[0])+i;
            }
        }
        return map;
    }*/

    private List<Long> getSeedToSoilMap(List<String> rows) {
        //row: 50 98 02
        //Long[] map = new Long[BigInteger.valueOf(maxArraySize).intValue()];

        List<Long> map = new ArrayList<>();
        // Fill array with initial values
        for (int i = 0; i < maxArraySize; i++) {
            //map[i] = Long.valueOf(i);
            map.add(i, Long.valueOf(i));
        }

        for (String row : rows) {
            String[] rowNumbers = row.split(" ");

            //rowNumber[0] = Destination
            //rowNumber[1] = Source
            //rowNumber[2] = Span

            for (int i = 0; i < Integer.parseInt(rowNumbers[2]); i++) {
                int startFrom = Integer.parseInt(rowNumbers[1]);
                //map[startFrom+i] = Long.parseLong(rowNumbers[0])+i;
                map.set(startFrom+i, Long.parseLong(rowNumbers[0])+i);
            }
        }
        return map;
    }

    private Long getMaxArraySize(List<Long> seeds) {
        return seeds.stream().mapToLong(v -> v).max().orElse(100);
    }

    // To circumvent the integer size problem, make another conversion myself e.g.
    // Get all numbers in Long[]
    // Sort all the numbers Collections.sort(numbers);
    // Replace longs with converted integers
    // And then unconvert after calculations

}
