package pemcom;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
        System.out.print("How many elements to add? ");
        int count = input.nextInt();

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter element " + i + ": ");
            String elem = input.next();
            queue.add(elem);
        }
        System.out.println("Elements successfully added to the Queue.");
    }

    // TODO 2: Displays the elements in the Queue
    static void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("Queue contents: " + queue);
    }

    // TODO 3: Generates possible permutations
    static void generatePermutations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to arrange (r): ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid number of elements!");
            return;
        }

        String[] elements = queue.toArray(new String[0]);
        Queue<String> permQueue = new LinkedList<>();

        // Add initial single elements
        for (String item : elements) {
            permQueue.add(item);
        }

        int totalPermutations = 0;

        System.out.println("\nGenerated Permutations:");
        while (!permQueue.isEmpty()) {
            String current = permQueue.poll();

            if (current.length() == r) {
                System.out.println(current);
                totalPermutations++;
            } else {
                for (String item : elements) {
                    if (!current.contains(item)) {
                        permQueue.add(current + item);
                    }
                }
            }
        }

        System.out.println("Total Permutations: " + totalPermutations);
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to select (r): ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid number of elements, Please try Again!");
            return;
        }

        String[] elements = queue.toArray(new String[0]);

        // Helper state to track combination string and next allowed starting index
        class CombState {
            String combo;
            int nextIndex;

            CombState(String combo, int nextIndex) {
                this.combo = combo;
                this.nextIndex = nextIndex;
            }
        }

        Queue<CombState> combWorkingQueue = new LinkedList<>();

        // Add initial elements with their respective next available indices
        for (int i = 0; i < elements.length; i++) {
            combWorkingQueue.add(new CombState(elements[i], i + 1));
        }

        int totalCombinations = 0;

        System.out.println("\nGenerated Combinations:");
        while (!combWorkingQueue.isEmpty()) {
            CombState state = combWorkingQueue.poll();

            if (state.combo.length() == r) {
                System.out.println(state.combo);
                totalCombinations++;
            } else {
                for (int i = state.nextIndex; i < elements.length; i++) {
                    combWorkingQueue.add(new CombState(state.combo + elements[i], i + 1));
                }
            }
        }

        System.out.println("Total Combinations: " + totalCombinations);
    }
}