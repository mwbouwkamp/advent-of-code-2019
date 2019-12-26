package day14;

import java.util.*;
import java.util.stream.Collectors;

public class Reaction implements Comparable<Reaction> {
    private Map<String, Integer> in, out;

    public Reaction(Reaction reaction) {
        this.in = new HashMap<>(reaction.getIn());
        this.out = new HashMap<>(reaction.getOut());
    }

    public Reaction(String input) {
        String[] inOut = input.split(" => ");
        this.in = getReagentsFromInput(inOut[0]);
        this.out = getReagentsFromInput(inOut[1]);
    }

    private Map<String, Integer> getReagentsFromInput(String s1) {
        return Arrays.stream(s1.split(", "))
                .map(s -> s.split(" "))
                .collect(Collectors.toMap(a -> a[1], a-> Integer.parseInt(a[0])));
    }

    public void performReaction(List<Reaction> reactions, String reagent) {
        Reaction synthesis = getSynthesis(reactions, reagent);
        int required = in.get(reagent);
        int provided = synthesis.getOut().get(reagent);
        int numReactions = required % provided == 0
                ? required / provided
                : required / provided + 1;
        int remaining = numReactions * provided - required;
        if (remaining > 0) {
            addProduct(reagent, remaining);
        }
        removeReagent(reagent, in);
        for (String synthesisReagent: synthesis.getIn().keySet()) {
            addReagent(numReactions, synthesisReagent, synthesis);
            removeReagentsThatArePresentAtBothSides();
        }
    }

    private void removeReagent(String reagent, Map<String, Integer> reagentMap) {
        reagentMap.remove(reagent);
    }

    private void addProduct(String reagent, int remaining) {
        int amount = out.containsKey(reagent)
                ? out.get(reagent) + remaining
                : remaining;
        out.put(reagent, amount);
    }

    public void removeReagentsThatArePresentAtBothSides() {
        List<String> atBothSides = new ArrayList<>(in.keySet());
        atBothSides.retainAll(out.keySet());
        for (String reagent: atBothSides) {
            int differenceInOut = in.get(reagent) - out.get(reagent);
            if (differenceInOut > 0) {
                in.put(reagent, in.get(reagent) - out.get(reagent));
                removeReagent(reagent, out);
            } else if (differenceInOut < 0) {
                out.put(reagent, out.get(reagent) + in.get(reagent));
                removeReagent(reagent, in);
            } else {
                removeReagent(reagent, out);
                removeReagent(reagent, in);
            }
        }
    }

    private void addReagent(int numReactions, String reagent, Reaction synthesis) {
        int newReagentAmount = in.containsKey(reagent)
                ? in.get(reagent) + numReactions * synthesis.getIn().get(reagent)
                : numReactions * synthesis.getIn().get(reagent);
        in.put(reagent, newReagentAmount);
    }

    private Reaction getSynthesis(List<Reaction> reactions, String reagent) {
        return reactions.stream()
                    .filter(r -> r.produces(reagent))
                    .findAny()
                    .orElse(null);
    }

    public boolean produces(String product) {
        return out.keySet().contains(product);
    }

    public Map<String, Integer> getIn() {
        return in;
    }

    public Map<String, Integer> getOut() {
        return out;
    }

    public boolean onlyOre() {
        return in.keySet().stream()
                .filter(r -> !r.equals("ORE"))
                .count() == 0;
    }

    public boolean hasOre(){
        return in.containsKey("ORE");
    }

    public int numOre() {
        return in.get("ORE");
    }

    @Override
    public String toString() {
        return getString(in) + " -> " + getString(out);
    }

    private String getString(Map<String, Integer> map) {
        return map.keySet().stream()
                .map(r -> map.get(r) + " " + r)
                .reduce((a, b) -> a + " + " + b)
                .orElse(null);
    }

    @Override
    public int compareTo(Reaction reaction) {
        int thisOre = hasOre() ? 1 : 0;
        int otherOre = reaction.hasOre() ? 1 : 0;
        return (reaction.getIn().size() - otherOre) - (in.size() - thisOre);
    }
}
