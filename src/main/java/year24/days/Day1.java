package main.java.year24.days;

import main.java.common.StringManipulation;
import main.java.year24.Day2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends Day2024 {

    public static void main(String[] args) {
        new Day1().printSolution();
    }

    @Override
    public Object part1() {
        return getDistanceBetweenSortedLists(getListsOfDestinations());
    }

    @Override
    public Object part2() {
        return getSimilarityScore(getListsOfDestinations());
    }

    public Day1() {
        super(1);
    }

    private List<List<Integer>> getListsOfDestinations() {
        return new StringManipulation().partitionStringByWhitespaceAndComma(filePathInput + "day1InputTest.txt", 2);
    }

    private Integer getDistanceBetweenSortedLists(List<List<Integer>> listsOfDestinations) {
        listsOfDestinations.forEach(Collections::sort);

        List<Integer> list1 = listsOfDestinations.get(0);
        List<Integer> list2 = listsOfDestinations.get(1);

        return calculateSum(list1, list2);
    }

    private Integer getSimilarityScore(List<List<Integer>> listsOfDestinations) {
        int score = 0;

        List<Integer> list1 = listsOfDestinations.get(0);
        List<Integer> list2 = listsOfDestinations.get(1);

        for (int number : list1) {
            int numberOfOccurrences = Collections.frequency(list2, number);

            score += number * numberOfOccurrences;
        }

        return score;
    }

    private static Integer calculateSum(List<Integer> list1, List<Integer> list2) {
        List<Integer> differences = new ArrayList<>();
        Integer sum = 0;

        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("The two lists must have the same size");
        }

        for (int i = 0; i < list1.size(); i++) {
            differences.add(Math.abs(list1.get(i) - list2.get(i)));
            sum += differences.get(i);
        }

        return sum;
    }
}
