package day23;

import utils.IntComputer;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
                    .inputCode(-1L)
                    .phaseSetting(i)
                    .phaseSetting2(-1L)
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
        long prevYNat = Long.MIN_VALUE;
        while (true) {
            for (long i = 0; i < 50; i++) {
                getMessagesFromIntComputer(i);
            }
            message = messages.remove(0);
            System.out.print(message + " | ");
            if (message.receivedFrom255) {
                if (message.y == prevYNat) {
                    killAll();
                    System.out.println("\nFrom NAT: " + message.y);
                    return message.y; //15089 too high
                } else {
                    prevYNat = message.y;
                }
            }
            if (message.address == 255L) {
                System.out.println();
                messages = messages.stream()
                        .filter(m -> m.address != 255L)
                        .collect(Collectors.toList());
                messages.add(new Message(0, message.x, message.y, true));
                if (messages.size() == 0) {
                    System.out.println("EMPTY");
                }
                message = messages.remove(0);
                System.out.print(message + " | ");
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
            if (newMessage.address == 255L) {
                messages = messages.stream()
                        .filter(m -> m.address != 255L)
                        .collect(Collectors.toList());
            }
            messages.add(newMessage);
        }
        numProcessedMap.put(address, currentOutput.size());
    }

    private void killAll() {
        for (IntComputer intcomputer: intComputerMap.values()) {
            intcomputer.kill();
        }
    }

    private class Message {
        long address;
        long x;
        long y;
        boolean receivedFrom255;

        public Message(long address, long x, long y) {
            this.address = address;
            this.x = x;
            this.y = y;
            this.receivedFrom255 = false;
        }

        public Message(long address, long x, long y, boolean receivedFrom255) {
            this.address = address;
            this.x = x;
            this.y = y;
            this.receivedFrom255 = receivedFrom255;
        }

        @Override
        public String toString() {
            return "A: " + address + "; x: " + x + "; y: " + y;
        }
    }
}
