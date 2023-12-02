package main.java.common;

public abstract class Day {
    public static final String DEFAULT_DELIMITER = "\n";
    protected final int year;
    protected final int day;
    protected int example = 0;

    private Object solutionPart1;
    private Object solutionPart2;

    public Day(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public void printSolution() {
        solutionPart1 = part1();
        solutionPart2 = part2();
        System.out.println(solutionPart1);
        System.out.println(solutionPart2);
    }

    public abstract Object part1();

    public abstract Object part2();

}
