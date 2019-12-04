package day4;

public class Main {

    public static void main(String[] args) {
        int lower = 359282;
        int higher = 820401;
        System.out.println("Solution 4.1: " + new Solver().getValidPasswords(lower, higher).size());

        System.out.println("Solution 4.2: " + new Solver().getValidPasswordsPart2(lower, higher).size());
    }
}
