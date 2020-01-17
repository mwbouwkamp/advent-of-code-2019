package day23;

import utils.IntComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class SolverDay23 {

    private List<Long> numbers;
    Map<Long, IntComputer> intComputerMap;
    Map<Long, Point> coordinates;
    Map<Long, Integer> numProcessedMap;
    List<Message> messages;

    public SolverDay23(List<Long> numbers) {
        this.intComputerMap = new HashMap<>();
        this.coordinates = new HashMap<>();
        this.messages = new ArrayList<>();
        this.numProcessedMap = new HashMap<>();
        for (long i = 0; i < 50; i++) {
            numProcessedMap.put(i, 0);
        }
        this.numbers = numbers;
    }

    public long solveDay23_1() {
        for (long i = 0; i < 50; i++) {
            IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                    .inputCode(-1)
                    .phaseSetting(i)
                    .name(i + "")
                    .build();
            intComputer.setRunning();
            intComputer.start();
            intComputerMap.put(i, intComputer);
        }
        for (long i = 0; i < 50; i++) {
            messages.add(new Message(i, -1, -1));
        }
        boolean val255;
        Message message;
        while (true) {
            message = messages.remove(0);
            val255 = message.address == 255L;
            if (val255) {
                break;
            }
            IntComputer intComputer = intComputerMap.get(message.address);
            intComputer.setInputCode(message.x);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int numProcessed = numProcessedMap.get(message.address);
            for (int j = numProcessed; j < intComputer.getIntComputerOutput().size(); j = j + 3) {
                Message newMessage = new Message(
                        intComputer.getIntComputerOutput().get(j),
                        intComputer.getIntComputerOutput().get(j + 1),
                        intComputer.getIntComputerOutput().get(j + 2)
                );
                messages.add(newMessage);
            }
            System.out.println(messages.size() + " -> " + messages);
            numProcessedMap.put(message.address, intComputer.getIntComputerOutput().size());
        }
        System.out.println("end");
        return message.y;
    }

    private class Message {
        long address;
        long x;
        long y;

        public Message(long address, long x, long y) {
            this.address = address;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "A: " + address + "; x: " + x + "; y: " + y;
        }
    }
}
