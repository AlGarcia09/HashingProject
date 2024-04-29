import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Hashtable;

public class Final {

    static Hashtable<Integer, String> hash = new Hashtable<>();
    static Hashtable<Integer, Integer> hashCount = new Hashtable<>();

    public static void Qprobe(String word) {

        Integer key = 0;
        Integer value = 0;
        int count = 1;
        value = word.hashCode() % 666511;
        if (value < 0) {
            value *= -1;
        }
        key = value;
        while (hash.containsKey(key)) {
            hashCount.put(key, hashCount.get(key) + 1);
            key = value + (count * count);
            count++;

        }

        hashCount.put(key, 1);// count
        hash.put(key, word);// puts word into hash table
    }

    public static void writeTxt(Hashtable<Integer, String> map) {
        System.out.println("Writing txt");
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            map.forEach((key, value) -> {
                try {
                    myWriter.write(value + "\n");
                } catch (IOException e) {

                    e.printStackTrace();
                }
            });
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Failed to write to the file");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File txt = new File("words.txt");
        Scanner sc = new Scanner(txt);
        long start = System.currentTimeMillis();
        while (sc.hasNext()) {
            String tmp = sc.next();
            // System.out.println(tmp);
            Qprobe(tmp);
        }
        System.out.println();
        writeTxt(hash);
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println("Finished in " + (end - start) + "ms.");

    }
}