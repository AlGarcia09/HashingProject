//Alan Garcia, 04/09/24, creating a hashing project to display characters and strings in seperate chaining. 
/*Seperate chaining: In hashing there is a hash function that maps keys to some values. But these hashing functions may lead to a collision that is two or more keys are mapped to same value. Chain hashing avoids collision. The idea is to make each cell of hash table point to a linked list of records that have same hash function value.*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
//imports for reading files, exception, arraylist, and many more that can support any methods to diplay the output.
 
public class SeperateChaining {
    private final int size;
    private final ArrayList<String>[] hashtable;
    private final int[] hashCounts;
    private final int[] probeCounts;

    public SeperateChaining(int size) {
        this.size = size;
        this.hashtable = new ArrayList[size];
        this.hashCounts = new int[size];
        this.probeCounts = new int[size];
        for (int i =0; i < size; i++) {
            hashtable[i] = new ArrayList<>();
            hashCounts[i] = 0;
            probeCounts[i] = 0;
        }
    }

    public int hashFunction(String key) {
        int hash = 5381;
        for (char c : key.toCharArray()) {
            hash = ((hash << 5) + hash) + c; // hash * 33 + c
        }
        return Math.abs(hash) % size; // Ensure hash value is positive and within table size
    }
    //Creating the hash function

    public void insertItem(String key) {
        int index = hashFunction(key); // Find the hash index for key
        int probes = 1; // Initialize probe count for this insertion
        while (hashtable[index].contains(key)) {
            probes++;
            index = (index + 1) % size; // Linear probing
        }
        hashtable[index].add(key); // Insert key into hash table at that index
        hashCounts[index]++; // Increment hash count for this index
        probeCounts[index] += probes; // Increment probe count for this index
    }
    //Inserting items

    public void plotProbeCounts() {
        // long startTime = System.currentTimeMillis(); // Record start time
        int totalProbes = 0; // Initialize total probes
        int totalElements = 0; // Initialize total elements
        for (int i = 0; i < size; i++) {
            for (String s : hashtable[i]) {
                int index = hashFunction(s); // Calculate hash index for the string
                totalProbes += probeCounts[index]; // Increment total probes by probe counts of the corresponding index
                totalElements++;
            }
        }
        // long endTime = System.currentTimeMillis(); // Record end time
        // long elapsedTime = endTime - startTime; // Calculate elapsed time
        // System.out.println("Time taken for probe counting: " + elapsedTime + " milliseconds");
    
        for (char c = 'a'; c <= 'z'; c++) {
            int letterProbeCount = 0;
            int letterElementCount = 0;
            for (int i = 0; i < size; i++) {
                for (String s : hashtable[i]) {
                    if (s.charAt(0) == c) {
                        int index = hashFunction(s); // Calculate hash index for the string
                        letterProbeCount += probeCounts[index]; // Increment probe count for this character
                        letterElementCount++;
                    }
                }
            }
            if (letterElementCount > 0) {
                System.out.println(c + ": Probes = " + letterProbeCount);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            int letterProbeCount = 0;
            int letterElementCount = 0;
            for (int i = 0; i < size; i++) {
                for (String s : hashtable[i]) {
                    if (s.charAt(0) == c) {
                        int index = hashFunction(s); // Calculate hash index for the string
                        letterProbeCount += probeCounts[index]; // Increment probe count for this character
                        letterElementCount++;
                    }
                }
            }
            if (letterElementCount > 0) {
                System.out.println(c + ": Probes = " + letterProbeCount);
            }
        }
    
        //System.out.println("Total probes used: " + totalProbes);
        //double averageProbe = totalElements == 0 ? 0 : (double) totalProbes / totalElements;
        //System.out.println("Average probe: " + averageProbe);
    }            
    //Counting the probes.

    public void plotDistribution() {
        int[] distribution = new int[size]; // Array to store distribution of hash counts
        for (int i = 0; i < size; i++) {
            for (String s : hashtable[i]) {
                int index = hashFunction(s); // Calculate hash index for the string
                distribution[index]++; // Increment hash count for this index
            }
        }
    
        for (char c = 'a'; c <= 'z'; c++) {
            int letterDistribution = 0;
            for (int i = 0; i < size; i++) {
                for (String s : hashtable[i]) {
                    if (s.charAt(0) == c) {
                        int index = hashFunction(s); // Calculate hash index for the string
                        letterDistribution += distribution[index]; // Increment distribution count for this character
                    }
                }
            }
            if (letterDistribution > 0) {
                System.out.println(c + ": " + letterDistribution);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            int letterDistribution = 0;
            for (int i = 0; i < size; i++) {
                for (String s : hashtable[i]) {
                    if (s.charAt(0) == c) {
                        int index = hashFunction(s); // Calculate hash index for the string
                        letterDistribution += distribution[index]; // Increment distribution count for this character
                    }
                }
            }
            if (letterDistribution > 0) {
                System.out.println(c + ": " + letterDistribution);
            }
        }
    }
    

    public void displayHash() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("hash_table_contents.txt"))) {
            for (char c = 'a'; c <= 'z'; c++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(c).append(" - ");
                for (int i = 0; i < size; i++) {
                    for (String s : hashtable[i]) {
                        if (s.charAt(0) == c) {
                            stringBuilder.append(s).append(" --> ");
                        }
                    }
                }
                if (stringBuilder.length() > 4) { // Check if there are elements for this character
                    writer.println(stringBuilder.substring(0, stringBuilder.length() - 5)); // Remove the last " --> "
                }
            }
    
            for (char c = 'A'; c <= 'Z'; c++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(c).append(" - ");
                for (int i = 0; i < size; i++) {
                    for (String s : hashtable[i]) {
                        if (s.charAt(0) == c) {
                            stringBuilder.append(s).append(" --> ");
                        }
                    }
                }
                if (stringBuilder.length() > 4) { // Check if there are elements for this character
                    writer.println(stringBuilder.substring(0, stringBuilder.length() - 5)); // Remove the last " --> "
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readKeysFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                insertItem(trimmedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    //Reading files from the text file.

    public void writeOutputToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write hash table contents
            writer.println("Hash Table Contents:");
            // Iterate over lowercase letters
            for (char c = 'a'; c <= 'z'; c++) {
                writer.print(c + " - ");
                boolean firstItem = true;
                for (int i = 0; i < size; i++) {
                    for (String s : hashtable[i]) {
                        if (s.charAt(0) == c) {
                            if (!firstItem) {
                                writer.print(" --> ");
                            }
                            writer.print(s);
                            firstItem = false;
                        }
                    }
                }
                writer.println();
            }
    
            // Iterate over uppercase letters
            for (char c = 'A'; c <= 'Z'; c++) {
                writer.print(c + " - ");
                boolean firstItem = true;
                for (int i = 0; i < size; i++) {
                    for (String s : hashtable[i]) {
                        if (s.charAt(0) == c) {
                            if (!firstItem) {
                                writer.print(" --> ");
                            }
                            writer.print(s);
                            firstItem = false;
                        }
                    }
                }
                writer.println();
            }
    
            // Write distribution of hash counts
            writer.println("\nDistribution of Hash Counts:");
            int[] distribution = new int[size]; // Array to store distribution of hash counts
            for (int i = 0; i < size; i++) {
                for (String s : hashtable[i]) {
                    int index = hashFunction(s); // Calculate hash index for the string
                    distribution[index]++; // Increment hash count for this index
                }
            }
            for (int i = 0; i < size; i++) {
                writer.println(" Index " + i + ": " + distribution[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    //Creating an output text file.

    public void searchItem(String key) {
        long startTime = System.currentTimeMillis(); // Record start time
        int index = hashFunction(key); // Calculate hash index for the key
        int probes = 1; // Initialize probe count for this search
        for (String s : hashtable[index]) {
            if (s.equals(key)) {
                long endTime = System.currentTimeMillis(); // Record end time
                long elapsedTime = endTime - startTime; // Calculate elapsed time
                // System.out.println("Item found at index " + index);
                System.out.println("Time taken for search: " + elapsedTime + " milliseconds");
                System.out.println("Number of probes used: " + probes);
                return; // Item found, exit the method
            }
            probes++; // Increment probe count for each element checked
        }
        // If item is not found
        long endTime = System.currentTimeMillis(); // Record end time
        long elapsedTime = endTime - startTime; // Calculate elapsed time
        System.out.println("Item not found");
        System.out.println("Time taken for search: " + elapsedTime + " milliseconds");
        System.out.println("Number of probes used: " + probes);
    }

    public static void main(String[] args) {
        SeperateChaining hash = new SeperateChaining(78);


        hash.readKeysFromFile("words.txt");

        // hash.displayHash();

        System.out.println("Distribution of Hash Counts:");
        hash.plotDistribution();

        hash.writeOutputToFile("output.txt");
        System.out.println("Output written to file 'output.txt'");

        System.out.println("Probe Counts: ");
        
        hash.plotProbeCounts();

        hash.searchItem("pectoriloquous");
                
    }
}
