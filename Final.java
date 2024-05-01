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

    static Hashtable<Integer, String> hash = new Hashtable<>(933102); // 933151, 666511, 933102, 666501
    static Hashtable<Integer, Integer> hashCount = new Hashtable<>(933102); // Values for hashing size
    static int totalProbes = 0;

    public static void Qprobe(String word) { // Quadratic probes the data into a hashtable by giving it a value and
                                             // assigning it to that index, if taken, the value will search the next
                                             // number exponentially

        Integer key = 0;
        Integer value = 0;
        int count = 1;
        value = word.hashCode() % 933102; // Ensures value doesn't overtake table size
        if (value < 0) {
            value *= -1;
        }
        key = value;
        while (hash.containsKey(key)) {
            hashCount.put(key, hashCount.get(key) + 1);// Counts the amount of times probed to.
            key = value + (count * count);
            count++;

        }

        hashCount.put(key, 1);// count
        hash.put(key, word);// puts word into hash table
    }

    public static void writeTxt(Hashtable<Integer, String> map) { // Outputs the results to a txt file in order to save
                                                                  // time on individual print statments
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

    public static void writeCountTxt(Hashtable<Integer, Integer> map) { // Writes probe count to a file
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

    public static String Search(Integer key) { // Searches for a key in the hash map
        return hash.get(key);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File txt = new File("words.txt"); // Initialized the data file
        Scanner sc = new Scanner(txt);
        long start = System.currentTimeMillis(); // Starts program timer
        while (sc.hasNext()) { // Runs through the entire file to hash all data
            String tmp = sc.next();
            Qprobe(tmp);
        }

        hashCount.forEach((key, value) -> {// Reports total probes

            totalProbes += value;
        });
        long end = System.currentTimeMillis(); // Ends timer
        System.out.println("total probes: " + totalProbes);// reports total probes
        System.out.println();
        writeTxt(hash);
        writeCountTxt(hashCount); // Outputs to files
        System.out.println();
        System.out.println("Finished in " + (end - start) + "ms.");// outputs time
        System.out.println();
        int i = 0;

        long searchStart = System.nanoTime(); // For each amount, runs through a set amount of random searches and
                                              // returns the time taken, words searched, and probes used
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
        long t1 = searchEnd - searchStart;
        int p1 = probes;

        i = 0;

        searchStart = System.nanoTime();// For each amount, runs through a set amount of random searches and returns the
                                        // time taken, words searched, and probes used
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
        long t2 = searchEnd - searchStart;
        int p2 = probes;
        i = 0;

        searchStart = System.nanoTime();// For each amount, runs through a set amount of random searches and returns the
                                        // time taken, words searched, and probes used
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
        long t3 = searchEnd - searchStart;
        int p3 = probes;
        i = 0;

        searchStart = System.nanoTime();// For each amount, runs through a set amount of random searches and returns the
                                        // time taken, words searched, and probes used
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
        long t4 = searchEnd - searchStart;
        int p4 = probes;
        i = 0;

        searchStart = System.nanoTime();// For each amount, runs through a set amount of random searches and returns the
                                        // time taken, words searched, and probes used
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
        long t5 = searchEnd - searchStart;
        int p5 = probes;

        System.out.println(p1);// Ease of access for probe and timer information
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);

    }
}