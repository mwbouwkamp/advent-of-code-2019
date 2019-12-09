package day8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void onesTimesTwosLayerWithFewestZeros() {
    }

    @Test
    void getLayerWithFewestZeros_should_return_0() {
        List<List<Integer>> layer1 = createLayerWithAllOnes();
        List<List<Integer>> layer2 = createLayerWithAllZeros();
        List<List<Integer>> layer3 = createListWithAllOnesButOneZero();
        List<List<List<Integer>>> image = makeImage(layer1, layer2, layer3);
        List<List<Integer>> layerFewestZeros = new Image(image).getLayerWithFewestZeros();
        assertEquals(0, new Image().countNumbers(layerFewestZeros, 0));
    }

    @Test
    void getLayerWithFewestZeros_should_return_1() {
        List<List<Integer>> layer1 = createLayerWithAllZeros();
        List<List<Integer>> layer3 = createListWithAllOnesButOneZero();
        List<List<List<Integer>>> image = makeImage(layer1, layer3, layer3);
        List<List<Integer>> layerFewestZeros = new Image(image).getLayerWithFewestZeros();
        assertEquals(1, new Image().countNumbers(layerFewestZeros, 0));
    }

    @Test
    void countNumbers_should_return9() {
        List<List<Integer>> layer = createLayerWithAllZeros();
        assertEquals(9, new Image().countNumbers(layer, 0));
    }

    @Test
    void countNumbers_should_return0() {
        List<List<Integer>> layer = createLayerWithAllZeros();
        assertEquals(0, new Image().countNumbers(layer, 1));
    }

    @Test
    void countNumbers_should_return1() {
        List<List<Integer>> layer = createListWithAllOnesButOneZero();
        assertEquals(1, new Image().countNumbers(layer, 0));
    }

    private List<List<Integer>> createLayerWithAllZeros() {
        List<Integer> row1 = makeRow(0, 0, 0);
        List<Integer> row2 = makeRow(0, 0, 0);
        List<Integer> row3 = makeRow(0, 0, 0);
        return makeLayer(row1, row2, row3);
    }

    private List<List<Integer>> createLayerWithAllOnes() {
        List<Integer> row1 = makeRow(1, 1, 1);
        List<Integer> row2 = makeRow(1, 1, 1);
        List<Integer> row3 = makeRow(1, 1, 1);
        return makeLayer(row1, row2, row3);
    }

    private List<List<Integer>> createListWithAllOnesButOneZero() {
        List<Integer> row1 = makeRow(1, 1, 1);
        List<Integer> row2 = makeRow(1, 0, 1);
        List<Integer> row3 = makeRow(1, 1, 1);
        return makeLayer(row1, row2, row3);
    }

    private List<Integer> makeRow(int i, int j, int k) {
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        list.add(k);
        return list;
    }

    private List<List<Integer>> makeLayer(List<Integer> i, List<Integer> j, List<Integer> k) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        list.add(k);
        return list;
    }

    private List<List<List<Integer>>> makeImage(List<List<Integer>> i, List<List<Integer>> j, List<List<Integer>> k) {
        List<List<List<Integer>>> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        list.add(k);
        return list;
    }

}