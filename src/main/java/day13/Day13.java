package day13;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input13.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        List<Block> blocks = new SolverDay13().getBlocksFromInput(numbers);

        System.out.println(blocks.stream().map(Block::getType).filter(t -> t == 2).count());

        numbers.set(0, 2L);
        blocks = new SolverDay13().getBlocksFromInput(numbers);

        System.out.println(blocks.stream().map(Block::getType).filter(t -> t == 2).count());

        for (Block block: blocks) {
            if (block.getType() == 3L) {
                System.out.println("PADDLE: " + block.getX() + " " + block.getY());
            }
        }
        for (Block block: blocks) {
            if (block.getType() == 4L) {
                System.out.println("BALL: " + block.getX() + " " + block.getY());
            }
        }




    }
}
