import main.java.common.Day;

import java.lang.reflect.InvocationTargetException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.


            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.


            for (int day = 1; day <= 25; day++) {
                System.out.println("\n" + "Day " + day + ":");
                Day instance = (Day) Class.forName("main.java.year24.days.Day" + day).getDeclaredConstructor().newInstance();
                instance.printSolution();
                System.out.println();
            }


    }
}