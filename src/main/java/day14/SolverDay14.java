package day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolverDay14 {
    List<Reaction> reactions;
    Reaction fuelReaction;

    public SolverDay14(List<Reaction> reactions) {
        this.reactions = reactions;
        this.fuelReaction = reactions.stream()
                .filter(r -> r.produces("FUEL"))
                .collect(Collectors.toList())
                .get(0);
    }

    public int solve2() {
        List<Reaction> resultingReactions = new ArrayList<>();
        while (!solved()) {
            Reaction toSynthesize = reactions.remove(0);
            if (toSynthesize.onlyOre()) {
                resultingReactions.add(toSynthesize);
                System.out.println(toSynthesize);
            } else {
                for (String reagent: toSynthesize.getIn().keySet()) {
                    if (!reagent.equals("ORE")) {
                        Reaction newReaction = new Reaction(toSynthesize);
                        if (resultingReactions.stream().filter(r -> r.getOut().containsKey(reagent)).count() > 0) {
                            newReaction.performReaction(resultingReactions, reagent);
                        } else {
                            newReaction.performReaction(reactions, reagent);
                        }
                        reactions.add(newReaction);
                    }
                }
            }
        }
        return resultingReactions.stream()
                .filter(r -> r.getOut().containsKey("FUEL"))
                .collect(Collectors.toList())
                .get(0)
                .getIn()
                .get("ORE");
    }

    private boolean solved() {
        return reactions.stream()
                .filter(r -> r.getOut().containsKey("FUEL"))
                .map(r -> r.getIn().keySet())
                .filter(r -> !r.equals("ORE"))
                .count() == 0;
    }


    public int solve() {
        List<Reaction> fringe = new ArrayList<>();
        fringe.add(fuelReaction);
        Reaction bestReaction = null;
        int oreBestReaction = 441082;
        while (fringe.size() > 0) {
            Collections.sort(fringe);
            Reaction toCheck = fringe.remove(fringe.size() - 1);
            if (toCheck.onlyOre()) {
                if (null == bestReaction || toCheck.numOre() < oreBestReaction) {
                    bestReaction = toCheck;
                    oreBestReaction = toCheck.numOre();
                    System.out.println(bestReaction + " " + oreBestReaction);
                    int finalOreBestReaction = oreBestReaction;
                    fringe = fringe.stream()
                            .filter(r -> !r.hasOre() || r.numOre() < finalOreBestReaction)
                            .collect(Collectors.toList());
                }
                else {
                    System.out.println("solution, but too large: " + toCheck.numOre());
                }
            }
            else {
                for (String reagent: toCheck.getIn().keySet()) {
                    if (!reagent.equals("ORE")) {
                        Reaction newFuelReaction = new Reaction(toCheck);
                        newFuelReaction.performReaction(reactions, reagent);
                        if (!newFuelReaction.hasOre() || newFuelReaction.numOre() < oreBestReaction) {
                            fringe.add(newFuelReaction);
                        }
                    }
                }
            }
        }
        return oreBestReaction;
    }

//    public int solve() {
//        System.out.println(fuelReaction);
//        while (!fuelReaction.onlyOre()) {
//            Reaction newFuelReaction = new Reaction();
//            newFuelReaction.getOut().put("FUEL", 1);
//            for (String reagent: fuelReaction.getIn().keySet()) {
//                if (reagent.equals("ORE")) {
//                    newFuelReaction.getIn().put("ORE", fuelReaction.getIn().get("ORE"));
//                }
//            }
//            for (String reagent: fuelReaction.getIn().keySet()) {
//                if (!reagent.equals("ORE")) {
//                    newFuelReaction.performReaction(reactions, reagent);
//                }
//            }
//            fuelReaction = newFuelReaction;
//            System.out.println(fuelReaction);
//        }
//        Reaction finalFuelReaction = fuelReaction;
//        return fuelReaction.getIn().keySet().stream()
//                .mapToInt(r -> finalFuelReaction.getIn().get(r))
//                .sum();
//    }

}
