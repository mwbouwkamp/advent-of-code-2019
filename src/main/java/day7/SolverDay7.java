package day7;

import utils.IntComputer;

import java.util.ArrayList;
import java.util.List;

public class SolverDay7 {

    List<List<Integer>> phaseSettingsCombinations;
    List<Integer> numbers;

    public SolverDay7(List<Integer> numbers, int phaseSettingsSize, int offset) {
        this.numbers = numbers;
        phaseSettingsCombinations = new ArrayList<>();
        initPhaseSettingsCombinations(phaseSettingsSize, offset);
    }

    private void initPhaseSettingsCombinations(int phaseSettingsSize, int offset) {
        List<Pair> fringe = new ArrayList<>();
        for (int i = 0; i < phaseSettingsSize; i++) {
            List<Integer> used = new ArrayList<>();
            used.add(i + offset);
            List<Integer> toUse = new ArrayList<>();
            for (int j = 0; j < phaseSettingsSize; j++) {
                if (j != i) {
                    toUse.add(j + offset);
                }
            }
            fringe.add(new Pair(used, toUse));
        }
        while (fringe.size() > 0) {
            Pair toCheck = fringe.remove(0);
            if (toCheck.fst.size() == phaseSettingsSize) {
                phaseSettingsCombinations.add(toCheck.fst);
            } else {
                for (int i = 0; i < toCheck.snd.size(); i++) {
                    List<Integer> newToUse = new ArrayList<>(toCheck.snd);
                    List<Integer> newUsed = new ArrayList<>(toCheck.fst);
                    int toMove = newToUse.remove(i);
                    newUsed.add(toMove);
                    Pair newFringeItem = new Pair(newUsed, newToUse);
                    fringe.add(newFringeItem);
                }
            }
        }
    }

    public int getMaxOutput() {
        return phaseSettingsCombinations.stream()
                .mapToInt(ps -> getThrusterOutput(numbers, ps))
                .max()
                .orElse(-1);
    }

    public int getMaxOutputWithFeedback() {
        int[] phaseSettings = new int[]{9,8,7,6,5};
//        int[] phaseSettings = new int[]{9,7,8,5,6};

        IntComputer intComputerA = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings[0])
                .isHaltable()
                .name("A")
                .build();
        intComputerA.start();
        IntComputer intComputerB = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings[0])
                .isHaltable()
                .name("B")
                .build();
        intComputerB.start();
        IntComputer intComputerC = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings[0])
                .isHaltable()
                .name("C")
                .build();
        intComputerC.start();
        IntComputer intComputerD = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings[0])
                .isHaltable()
                .name("D")
                .build();
        intComputerD.start();
        IntComputer intComputerE = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings[0])
                .isHaltable()
                .name("E")
                .build();
        intComputerE.start();
        int resultA = 0;
        int resultB = 0;
        int resultC = 0;
        int resultD = 0;
        int resultE = 0;
        for (int i = 0; i < 5; i++) {
            System.out.print("--- Cycle: " + i);

            updateIntComputer(intComputerA, resultE);
            waitForResult(intComputerA);
            resultA = intComputerA.getIntComputerOutput().get(intComputerA.getIntComputerOutput().size() - 1);

            updateIntComputer(intComputerB, resultA);
            waitForResult(intComputerB);
            resultB = intComputerB.getIntComputerOutput().get(intComputerB.getIntComputerOutput().size() - 1);

            updateIntComputer(intComputerC, resultB);
            waitForResult(intComputerC);
            resultC = intComputerC.getIntComputerOutput().get(intComputerC.getIntComputerOutput().size() - 1);

            updateIntComputer(intComputerD, resultC);
            waitForResult(intComputerD);
            resultD = intComputerD.getIntComputerOutput().get(intComputerD.getIntComputerOutput().size() - 1);

            updateIntComputer(intComputerE, resultD);
            waitForResult(intComputerE);
            resultE = intComputerE.getIntComputerOutput().get(intComputerE.getIntComputerOutput().size() - 1);
        }
        return resultE;
    }

    private void waitForResult(IntComputer intComputerA) {
        synchronized (intComputerA) {
            try {
                intComputerA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateIntComputer(IntComputer intComputer, int inputCode) {
        intComputer.setInputCode(inputCode);
        intComputer.setRunning();
    }

    private IntComputer initializeIntComputer(int phaseSetting, int result, String name) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(result)
                .phaseSetting(phaseSetting)
                .isHaltable()
                .name(name)
                .build();
        intComputer.start();
        return intComputer;
    }

    public int getThrusterOutput(List<Integer> numbers, List<Integer> phaseSettings) {
        int amplifierInput = 0;
        for (int phaseSetting: phaseSettings) {
            IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                    .inputCode(amplifierInput)
                    .phaseSetting(phaseSetting)
                    .build();
            intComputer.start();
            intComputer.setRunning();
            intComputer.waitForIntcomputer();
            amplifierInput = intComputer.getIntComputerOutput().get(0);
        }
        return amplifierInput;
    }

    private class Pair {
        List<Integer> fst, snd;

        Pair(List<Integer> fst, List<Integer> snd) {
            this.fst = fst;
            this.snd = snd;
        }

    }
}
