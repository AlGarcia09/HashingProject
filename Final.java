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
    static int totalProbes = 0;

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

    public static void writeCountTxt(Hashtable<Integer, Integer> map) {
        System.out.println("Writing txt");
        try {
            FileWriter myWriter = new FileWriter("outputC.txt");
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

    public static String Search(Integer key) {
        return hash.get(key);
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
        // final Integer totalProbes;
        hashCount.forEach((key, value) -> {

            totalProbes += value;
        });
        System.out.println("total probes: " + totalProbes);
        System.out.println();
        writeTxt(hash);
        writeCountTxt(hashCount);
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println("Finished in " + (end - start) + "ms.");
        System.out.println();
        int i = 0;

        long searchStart = System.nanoTime();
        Integer probes = 0;
        while (i < 10) {
            Integer num = (int) (Math.random() * hash.size() + 1);
            if (hash.containsKey(num)) {
                System.out.println(Search(num));
                probes += hashCount.get(num);
                i++;
            }

        }
        long searchEnd = System.nanoTime();
        System.out.println();
        System.out.println("Total time to complete 10 searchs is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
        System.out.println();

        i = 0;

        searchStart = System.nanoTime();
        probes = 0;
        while (i < 20) {
            Integer num = (int) (Math.random() * hash.size() + 1);
            if (hash.containsKey(num)) {
                System.out.println(Search(num));
                probes += hashCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println();
        System.out.println("Total time to complete 20 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
        System.out.println();

        i = 0;

        searchStart = System.nanoTime();
        probes = 0;
        while (i < 30) {
            Integer num = (int) (Math.random() * hash.size() + 1);
            if (hash.containsKey(num)) {
                System.out.println(Search(num));
                probes += hashCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println();
        System.out.println("Total time to complete 30 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
        System.out.println();

        i = 0;

        searchStart = System.nanoTime();
        probes = 0;
        while (i < 40) {
            Integer num = (int) (Math.random() * hash.size() + 1);
            if (hash.containsKey(num)) {
                System.out.println(Search(num));
                probes += hashCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println();
        System.out.println("Total time to complete 40 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
        System.out.println();

        i = 0;

        searchStart = System.nanoTime();
        probes = 0;
        while (i < 50) {
            Integer num = (int) (Math.random() * hash.size() + 1);
            if (hash.containsKey(num)) {
                System.out.println(Search(num));
                probes += hashCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println();
        System.out.println("Total time to complete 50 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
        System.out.println();
    }
}