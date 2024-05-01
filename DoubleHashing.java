import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DoubleHashing {
    private int size;
    private String[] hashtable;
    private int[] hashCounts;
    private int[] probeCounts;
    private boolean[] used;

    public DoubleHashing(int initialSize) {
        this.size = isPrime(initialSize) ? initialSize : nextPrime(initialSize);
        this.hashtable = new String[size];
        this.hashCounts = new int[size];
        this.probeCounts = new int[size];
        this.used = new boolean[size];
    }

  
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

   
    private int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    public int hashFunction1(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += c;
        }
        return hash % size;
    }

    public int hashFunction2(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash += (int) c * 31; 
        }
        return 1 + (hash % (size - 1)); 
    }

    public void insertItem(String key) {
        int index = hashFunction1(key);
        int step = hashFunction2(key);
        int probes = 0;

        while (probes < size) {
            if (!used[index]) {
                hashtable[index] = key;
                hashCounts[index]++;
                probeCounts[index] = probes + 1;
                used[index] = true;
                return;
            }
            index = (index + step) % size;
            probes++;
        }

        
        resizeAndRehash();
        insertItem(key); 
    }

    private void resizeAndRehash() {
        int newSize = size * 2; 
        String[] newHashtable = new String[newSize];
        int[] newHashCounts = new int[newSize];
        int[] newProbeCounts = new int[newSize];
        boolean[] newUsed = new boolean[newSize];

       
        for (int i = 0; i < size; i++) {
            if (used[i]) {
                String key = hashtable[i];
                int newIndex = hashFunction1(key);
                int newStep = hashFunction2(key);
                int newProbes = 0;

                while (newProbes < newSize) {
                    if (!newUsed[newIndex]) {
                        newHashtable[newIndex] = key;
                        newHashCounts[newIndex]++;
                        newProbeCounts[newIndex] = newProbes + 1;
                        newUsed[newIndex] = true;
                        break;
                    }
                    newIndex = (newIndex + newStep) % newSize;
                    newProbes++;
                }
            }
        }

        
        this.hashtable = newHashtable;
        this.hashCounts = newHashCounts;
        this.probeCounts = newProbeCounts;
        this.used = newUsed;
        this.size = newSize;
    }

    public void displayHash() {
        for (int i = 0; i < size; i++) {
            if (used[i]) {
                System.out.println(i + " - " + hashtable[i] + " (Probe Count: " + probeCounts[i] + ")");
            }
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial size of the hash table:");
        int initialSize = scanner.nextInt();
        scanner.close();

        DoubleHashing hash = new DoubleHashing(initialSize);
        hash.readKeysFromFile("words.txt");
        System.out.println("Words inserted into hash table:");
        hash.displayHash();
    }
}
