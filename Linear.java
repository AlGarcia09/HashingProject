import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.io.*;

public class Linear {

    static Hashtable<Integer, String> ht = new Hashtable<>(666511);
    static Hashtable<Integer, Integer> htCount = new Hashtable<>(66511);

    public static void linear(String word) {

        Integer value = 0;
        value = word.hashCode() % 666511;
        if (value < 0) {
            value *= -1;
        }
        while (ht.containsKey(value)) {
            htCount.put(value, htCount.get(value) + 1);
            value++;
        }

        htCount.put(value, 1 - 1);// count
        ht.put(value, word);// puts word into hash table
    }

    public void fileOutput(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Hash Table Contents:");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("words.txt");
        Scanner sc = new Scanner(file);
        long start = System.currentTimeMillis();
        while (sc.hasNext()) {
            String temp = sc.next();
            linear(temp);
        }
        System.out.println("All words have be sorted");
        System.out.println(ht);
        System.out.println("Cxollisions at each position: " + htCount);
        long end = System.currentTimeMillis();
        System.out.println("Total time to complete probing is " + (end - start) + "ms");

    }
}
