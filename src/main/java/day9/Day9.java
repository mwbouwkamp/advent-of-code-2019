package day9;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input9.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

//        input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        input = "1102,34915192,34915192,7,4,7,99,0";
//        input = "104,1125899906842624,99";
        numbers = InputUtils.getLongsFromCSVInput(input);


        System.out.println("Solution 9.1 " + new SolverDay9(numbers).solve().toString());





    }
}
