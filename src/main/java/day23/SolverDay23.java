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
                    .phaseSetting2(-1)
                    .name(i + "")
                    .build();
            intComputer.setRunning();
            intComputer.start();
            intComputerMap.put(i, intComputer);
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (long i = 0; i < 50; i++) {
            getMessagesFromIntComputer(i);
        }
        Message message;
        while (true) {
            for (long i = 0; i < 50; i++) {
                getMessagesFromIntComputer(i);
            }
            message = messages.remove(0);
            if (message.address == 255L) {
                break;
            }
            IntComputer intComputer = intComputerMap.get(message.address);
            intComputer.receivePacket(message.x, message.y);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getMessagesFromIntComputer(message.address);
        }
        System.out.println("end");
        return message.y;
    }

    private void getMessagesFromIntComputer(Long address) {
        int numProcessed = numProcessedMap.get(address);
        List<Long> currentOutput;
        do {
            currentOutput = new ArrayList<>(intComputerMap.get(address).getIntComputerOutput());
        } while ((currentOutput.size() - numProcessed) % 3 != 0);
        for (int j = numProcessed; j < currentOutput.size(); j = j + 3) {
            Message newMessage = new Message(
                    currentOutput.get(j),
                    currentOutput.get(j + 1),
                    currentOutput.get(j + 2)
            );
            messages.add(newMessage);
        }
        System.out.println(messages.size() + " -> " + messages);
        numProcessedMap.put(address, currentOutput.size());
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
