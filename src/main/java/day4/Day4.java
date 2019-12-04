package day4;

public class Day4 {

    public static void main(String[] args) {
        int lower = 359282;
        int higher = 820401;
        System.out.println("Solution 4.1: " + new SolverDay4().getValidPasswords(lower, higher).size());

        System.out.println("Solution 4.2: " + new SolverDay4().getValidPasswordsPart2(lower, higher).size());
    }
}
