package day20part2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InputProcessor {

    public List<String> processInner(List<String> input) {
        List<String> inputInner = new ArrayList<>(input);
        for (int y = 0; y < inputInner.size(); y++) {
            for (int x = 0; x < inputInner.get(y).length(); x++) {
                if (inputInner.get(y).charAt(x) == 'A' || inputInner.get(y).charAt(x) == 'Z'){
                    Point locationExitLabel = new NeighbourExplorer().getNeighboursBasedOnUpperCase(inputInner, new Point(x, y)).get(0);
                    if (inputInner.get(locationExitLabel.y).charAt(locationExitLabel.x) == 'A' || inputInner.get(locationExitLabel.y).charAt(locationExitLabel.x) == 'Z') {
                        char[] firstLine = inputInner.get(y).toCharArray();
                        firstLine[x] = '#';
                        inputInner.set(y, new String(firstLine));
                        char[] secondLine = inputInner.get(locationExitLabel.y).toCharArray();
                        secondLine[locationExitLabel.x] = '#';
                        inputInner.set(locationExitLabel.y, new String(secondLine));
                    }
                }
            }
        }
        return inputInner;
    }

    public List<String> processOuter(List<String> input) {
        List<String> inputOuter = new ArrayList<>(input);
        for (int y = 0; y < 2; y++) {
            inputOuter.set(y, inputOuter.get(y).replaceAll("[A-Z]", "#"));
        }
        for (int y = inputOuter.size() - 2; y < inputOuter.size(); y ++) {
            inputOuter.set(y, inputOuter.get(y).replaceAll("[A-Z]", "#"));
        }
        for (int y = 2; y < inputOuter.size() - 2;y ++) {
            char[] line = inputOuter.get(y).toCharArray();
            for (int x = 0; x < 2; x++) {
                if (Character.isUpperCase(line[x])) {
                    line[x] = '#';
                }
            }
            for (int x = inputOuter.get(y).length() - 2; x < inputOuter.get(y).length(); x++) {
                if (Character.isUpperCase(line[x])) {
                    line[x] = '#';
                }
            }
            inputOuter.set(y, new String(line));
        }
        return inputOuter;
    }


}
