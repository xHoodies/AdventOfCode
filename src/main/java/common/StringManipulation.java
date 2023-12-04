package main.java.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringManipulation {
    /**
     * Input filePath after src\main\java\ and filename.
     */


    public List<String> partitionStringByComma(String input) {
        return new ArrayList<String>(Arrays.asList(input
                .substring(1, input.length() - 1)
                .split(",")));
    }

    public List<String> removeEmptyAndNull(List<String> input) {
        input.removeAll(Arrays.asList(" ", null));
        return input;
    }

}
