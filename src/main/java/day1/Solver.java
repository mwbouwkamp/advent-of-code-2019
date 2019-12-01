package day1;

import java.util.List;

public class Solver {
    public long calculateTotalFuel(List<Long> numbers) {
        return numbers.stream()
                .map(this::calculateFuel)
                .reduce(Long::sum)
                .orElse(-1L);
    }

    public long calculateTotalFuelIncludingFuelForFuelTransport(List<Long> numbers) {
        return numbers.stream()
                .map(i -> getAdditionalFuelRequired(calculateFuel(i)))
                .reduce(Long::sum)
                .orElse(-1L);
    }

    public long calculateFuel(long i) {
        long fuel = i / 3 - 2;
        return fuel > 0 ? fuel : 0;
    }

    public long getAdditionalFuelRequired(long fuelRequired) {
        long addedFuel = calculateFuel(fuelRequired);
        return (fuelRequired == 0) ? 0 : fuelRequired + getAdditionalFuelRequired(addedFuel);
    }



}
