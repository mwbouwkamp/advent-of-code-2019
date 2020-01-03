package day9;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input9.txt").get(0);

//        input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
//        input = "1102,34915192,34915192,7,4,7,99,0";
//        input = "104,1125899906842624,99";
//        input = "109,-1,4,1,99"; //-1
//        input = "109,-1,104,1,99"; //outputs 1
//        input = "109,-1,204,1,99";  //outputs 109
//        input = "109,1,9,2,204,-6,99"; //outputs 204
//        input = "109,1,109,9,204,-6,99"; // outputs 204
//        input = "109,1,209,-1,204,-106,99"; // outputs 204
//        input = "109,1,3,3,204,2,99"; // outputs the input
//        input = "109,1,203,2,204,2,99"; //outputs the input

        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        System.out.println("Solution 9.1 " + new SolverDay9(numbers).solve().toString());
    }
}
