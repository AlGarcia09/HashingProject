import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class Linear3 {

    static Hashtable<Integer, String> ht = new Hashtable<>(666501);
    static Hashtable<Integer, Integer> htCount = new Hashtable<>(666501);

    public static void linear(String word) {

        Integer value = 0;
        value = word.hashCode() % 666501;
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

    public static String search(Integer key) {
        return ht.get(key);

    }

    public static void writeTxt(Hashtable<Integer, String> map) {
        System.out.println("Writing txt");
        try {
            FileWriter myWriter = new FileWriter("Output.txt");
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

        File file = new File("words.txt");
        Scanner sc = new Scanner(file);
        long start = System.currentTimeMillis();
        while (sc.hasNext()) {
            String temp = sc.next();
            linear(temp);
        }
        System.out.println("All words have be sorted");
        // System.out.println(ht);
        // System.out.println("Cxollisions at each position: " + htCount);
        long end = System.currentTimeMillis();
        System.out.println("Total time to complete probing is " + (end - start) + "ms");

        int i = 0;
        long searchStart = System.nanoTime();
        Integer probes = 0;
        while (i < 1) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        long searchEnd = System.nanoTime();
        System.out.println("Total time to complete 1 searchs is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);

        i = 0;
        searchStart = System.nanoTime();
        probes = 0;
        while (i < 10) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println("Total time to complete 10 searchs is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);

        i = 0;
        searchStart = System.nanoTime();
        probes = 0;
        while (i < 20) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println("Total time to complete 20 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);

        i = 0;

        searchStart = System.nanoTime();
        probes = 0;
        while (i < 30) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println("Total time to complete 30 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);

        i = 0;
        searchStart = System.nanoTime();
        probes = 0;
        while (i < 40) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println("Total time to complete 40 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);

        i = 0;
        searchStart = System.nanoTime();
        probes = 0;
        while (i < 50) {
            Integer num = (int) (Math.random() * ht.size() + 1);
            if (ht.containsKey(num)) {
                System.out.println(search(num));
                probes += htCount.get(num);
                i++;
            }

        }
        searchEnd = System.nanoTime();
        System.out.println("Total time to complete 50 search is " + (searchEnd - searchStart) + "ns");
        System.out.println("The total number of probes used is " + probes);
    }
}
