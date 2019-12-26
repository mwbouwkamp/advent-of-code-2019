package day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactionTest {

    @Test
    void onlyOre_should_return_true() {
        Reaction reaction = new Reaction("1 ORE => 1 FUEL");
        assertTrue(reaction.onlyOre());
    }

    @Test
    void onlyOre_should_return_false() {
        Reaction reaction = new Reaction("1 ORE, 1 OTHER => 1 FUEL");
        assertFalse(reaction.onlyOre());
    }

    @Test
    void removeReagentsThatArePresentAtBothSides_should_remove_nothing() {
        Reaction reaction = new Reaction("1 IN => 1 OUT");
        reaction.removeReagentsThatArePresentAtBothSides();
        Reaction reactionExpected = new Reaction("1 IN => 1 OUT");
        assertEquals(reactionExpected.toString(), reaction.toString());
    }

    @Test
    void removeReagentsThatArePresentAtBothSides_should_remove_reagent() {
        Reaction reaction = new Reaction("1 IN, 1 OTHER => 1 OUT, 2 IN");
        reaction.removeReagentsThatArePresentAtBothSides();
        Reaction reactionExpected = new Reaction("1 OTHER => 1 OUT, 1 IN");
        assertEquals(reactionExpected.toString(), reaction.toString());
    }

    @Test
    void removeReagentsThatArePresentAtBothSides_should_remove_product() {
        Reaction reaction = new Reaction("2 IN => 1 OUT, 1 IN");
        reaction.removeReagentsThatArePresentAtBothSides();
        Reaction reactionExpected = new Reaction("1 IN => 1 OUT");
        assertEquals(reactionExpected.toString(), reaction.toString());
    }

}