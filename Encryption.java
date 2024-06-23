import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Encrypt {
    Queue<Integer> queue = new LinkedList<>();

    void addAsciiValuesToQueue(String s) {
        // Split the statement into sentences using regex for sentence boundaries
        String[] sentences = s.split("(?<=[.!?])\\s*");

        // Loop through each sentence
        for (String sentence : sentences) {
//            System.out.println("\nSentence: " + sentence + "\n");
            // Split the sentence into letters and add their ASCII values to the queue
            char[] letters = sentence.toCharArray();

            for (char letter : letters) {
                int asciiValue = letter;
                asciiValue = (int) Math.pow(((asciiValue * 2) + 54), 2);
                queue.add(asciiValue);
            }
        }

        // Print the encryption contents of the queue
        System.out.print("\nE Msg: ");
        for (int val : queue) {
//            System.out.print(val + " ");
            System.out.print(val);
        }
    }

    Encrypt(String s) {
        addAsciiValuesToQueue(s);
    }

    Queue<Integer> getQueue() {
        return queue;
    }
}



class Decrypt {
    Queue<Integer> queue;
    Queue<Integer> decryptedQueue = new LinkedList<>();

    Decrypt(Queue<Integer> queue) {
        this.queue = queue;
        replaceElementsInQueue();
    }

    void replaceElementsInQueue() {
        // Access and modify each element in the original queue
        while (!queue.isEmpty()) {
            int asciiValue = queue.poll();
            int newAsciiValue = (int) ((Math.pow(asciiValue, 0.5) - 54) / 2);
            decryptedQueue.add(newAsciiValue);
        }

/*        // Print the modified contents of the queue
        System.out.print("\nD value: ");
        for (int val : decryptedQueue) {
            int character = (int) val;
            System.out.print(character + " ");
        }           */

        System.out.print("\nD Msg: ");
        for (int val : decryptedQueue) {
            char character = (char) val;
            System.out.print(character);
        }
    }
}

public class Encryption {
    private static boolean isValidInput(String input) {
        // Check if input is empty
        if (input.isEmpty()) {
            return false;
        }

        // Limit input length
        if (input.length() > 1000) {
            return false; // Example limit, adjust as necessary
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your statement: ");
        String s = sc.nextLine();
        sc.close();

        if (!isValidInput(s)) {
            System.out.println("Invalid input. Please enter a valid statement.");
            return;
        }

        Encrypt encrypt = new Encrypt(s);
        System.out.println();
        Decrypt decrypt = new Decrypt(encrypt.getQueue());
    }
}
