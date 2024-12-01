package main.java.year23.days;

import main.java.common.InputManipulation;
import main.java.year23.Day2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day5 extends Day2023 {
    static final String inputFileName = "day5Input.txt";
    static final File inputFile = new File(filePathInput + inputFileName);
    static final String inputPathDay = filePathInput + inputFileName;
    static int numberOfLinesInFile = new InputManipulation().getNumberOfLinesInFile(inputPathDay);
    private static Long maxArraySize; //TODO: Doesn't have to be long
    private static final List<String> maps = List.of("seed-to-soil",
            "soil-to-fertilizer",
            "fertilizer-to-water",
            "water-to-light",
            "light-to-temperature",
            "temperature-to-humidity",
            "humidity-to-location");

    public Day5() {
        super(5);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day5().printSolution();
            /*File f = new File("C:\\Users\\ditte\\IdeaProjects\\AoC\\src\\main\\java\\year23\\input\\day5Input.txt");
            Scanner s = new Scanner(f);
            long out = Long.MAX_VALUE, out2 = Long.MAX_VALUE;
            String l = s.nextLine();
            Scanner ss = new Scanner(l.substring(l.indexOf(':') + 2));
            ArrayList<Long> vals = new ArrayList<Long>();
            ArrayList<long[]> vals2 = new ArrayList<long[]>();
            while (ss.hasNextLong()) vals.add(ss.nextLong());
            for (int i = 0; i < vals.size(); i += 2) vals2.add(new long[] {vals.get(i), vals.get(i) + vals.get(i + 1) - 1});
            ss.close();
            s.nextLine();
            while (s.hasNextLine()) {
                s.nextLine(); // skip map title
                ArrayList<Long> newVals = new ArrayList<Long>();
                for (long i : vals) newVals.add(i);
                ArrayList<long[]> mapped = new ArrayList<long[]>();
                while (s.hasNextLine() && (l = s.nextLine()).length() > 0) {
                    ss = new Scanner(l);
                    long[] L = new long[3];
                    for (int i = 0; i < 3; i++) L[i] = ss.nextLong();
                    ss.close();
                    final long dest = L[0], src = L[1], len = L[2];
                    for (int i = 0; i < vals.size(); i++) {
                        long v = vals.get(i);
                        if (v >= src && v < src + len) newVals.set(i, v + dest - src);
                    }
                    for (int i = 0; i < vals2.size(); i++) {
                        long start = vals2.get(i)[0], end = vals2.get(i)[1];
                        if (start < src + len && end >= src) { // ranges overlap
                            long[] m = {start + dest - src, end + dest - src};
                            boolean hasUnmapped = false;
                            if (start < src) { // split if input range starts before source range
                                m[0] = dest;
                                vals2.get(i)[0] = start;
                                vals2.get(i)[1] = src - 1;
                                hasUnmapped = true;
                            }
                            if (end >= src + len) { // split if input range ends after source range
                                m[1] = dest + len - 1;
                                if (hasUnmapped) vals2.add(new long[] {src + len, end});
                                else {
                                    vals2.get(i)[0] = src + len;
                                    vals2.get(i)[1] = end;
                                    hasUnmapped = true;
                                }
                            }
                            mapped.add(m);
                            if (!hasUnmapped) vals2.remove(i--);
                        }
                    }
                }
                vals = newVals;
                vals2.addAll(mapped);
            }
            s.close();
            for (long i : vals) if (i < out) out = i;
            for (long[] i : vals2) if (i[0] < out2) out2 = i[0];
            System.out.println(out);
            System.out.println(out2);*/
        }


    @Override
    public Object part1() {
        /*Scanner inputScanner = new InputManipulation().getScannerFromFile(inputFile);
        long out = Long.MAX_VALUE, out2 = Long.MAX_VALUE;
        String initLine = inputScanner.nextLine();
        Scanner ss = new Scanner(initLine.substring(initLine.indexOf(':') + 2));

        ArrayList<Long> vals = new ArrayList<Long>();
        ArrayList<long[]> vals2 = new ArrayList<long[]>();

        while (ss.hasNextLong()) vals.add(ss.nextLong());

        for (int i = 0; i < vals.size(); i += 2)
            vals2.add(new long[] {vals.get(i), vals.get(i) + vals.get(i + 1) - 1});

        ss.close();
        inputScanner.nextLine();

        while (inputScanner.hasNextLine()) {
            inputScanner.nextLine(); // skip map title
            ArrayList<Long> newVals = new ArrayList<Long>();
            ArrayList<long[]> mapped = new ArrayList<long[]>();

            for (long i : vals) newVals.add(i);

            while (inputScanner.hasNextLine() && (initLine = inputScanner.nextLine()).length() > 0) {
                ss = new Scanner(initLine);
                long[] L = new long[3];
                for (int i = 0; i < 3; i++) L[i] = ss.nextLong();
                ss.close();
                final long dest = L[0], src = L[1], len = L[2];
                for (int i = 0; i < vals.size(); i++) {
                    long v = vals.get(i);
                    if (v >= src && v < src + len) newVals.set(i, v + dest - src);
                }

                for (int i = 0; i < vals2.size(); i++) {
                    long start = vals2.get(i)[0], end = vals2.get(i)[1];
                    if (start < src + len && end >= src) { // ranges overlap
                        long[] m = {start + dest - src, end + dest - src};
                        boolean hasUnmapped = false;
                        if (start < src) { // split if input range starts before source range
                            m[0] = dest;
                            vals2.get(i)[0] = start;
                            vals2.get(i)[1] = src - 1;
                            hasUnmapped = true;
                        }
                        if (end >= src + len) { // split if input range ends after source range
                            m[1] = dest + len - 1;
                            if (hasUnmapped) vals2.add(new long[]{src + len, end});
                            else {
                                vals2.get(i)[0] = src + len;
                                vals2.get(i)[1] = end;
                                hasUnmapped = true;
                            }
                        }
                        mapped.add(m);
                        if (!hasUnmapped) vals2.remove(i--);
                    }
                }
            }

            vals = newVals;
            vals2.addAll(mapped);

            inputScanner.close();
            for (long i : vals) if (i < out) out = i;
            for (long[] i : vals2) if (i[0] < out2) out2 = i[0];
            System.out.println(out);
            System.out.println(out2);
        }*/
        return null;
    }

    @Override
    public Object part2() {
        return null;
    }

    private ArrayList<Long> findPart1() {
        return null;
    }


}
