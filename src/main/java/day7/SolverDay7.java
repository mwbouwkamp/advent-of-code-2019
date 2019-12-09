package day7;

import utils.InputUtils;
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

    public int getMaxOutputWithFeedback() {
        return phaseSettingsCombinations.stream()
                .mapToInt(ps -> getThrusterOutputWithFeedback(numbers, ps))
                .max()
                .orElse(-1);
    }

    public int getThrusterOutputWithFeedback(List<Integer> numbers, List<Integer> phaseSettings) {
        IntComputer intComputerA = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings.get(0))
                .isHaltable()
                .name("A")
                .build();
        intComputerA.start();
        IntComputer intComputerB = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings.get(1))
                .isHaltable()
                .name("B")
                .build();
        intComputerB.start();
        IntComputer intComputerC = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings.get(2))
                .isHaltable()
                .name("C")
                .build();
        intComputerC.start();
        IntComputer intComputerD = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings.get(3))
                .isHaltable()
                .name("D")
                .build();
        intComputerD.start();
        IntComputer intComputerE = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(phaseSettings.get(4))
                .isHaltable()
                .name("E")
                .build();
        intComputerE.start();
        int resultE = 0;
        while(!intComputerA.hasTerminated()) {
            int resultA = intComputerA.runCycle(resultE);
            int resultB = intComputerB.runCycle(resultA);
            int resultC = intComputerC.runCycle(resultB);
            int resultD = intComputerD.runCycle(resultC);
            resultE = intComputerE.runCycle(resultD);
        }
        return resultE;
    }

    private class Pair {
        List<Integer> fst, snd;

        Pair(List<Integer> fst, List<Integer> snd) {
            this.fst = fst;
            this.snd = snd;
        }

    }
}
