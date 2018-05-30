/**
 * This class is an implementation of a HashTable which uses a better optimized
 * hashing algorithm for this lab then Java's default String.hashCode() method
 * @author Veeran Kerai (010660296)
 *
 */
public class HashTable {
    private static final int TABLE_SIZE = 37;
    private static String[] hashTable = new String[TABLE_SIZE];
    //Collision tracking as per lab instructions
    private static int collisions = 0;
    private static int resolves = 0;
    private static int totalCollisions = 0;
    /**
     * This function will take a string an generate a hash key value for it according to the table size.
     * More information on the algorithm can be seen in the runtime menu output
     * @param s String
     * @return hash code
     */
    public int getHashCode(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++)
            sum += (int) s.charAt(i); //multiply by 31 also can work but produces 8 collisions
        int hashCode = (sum ^ 411) * 113; //many will work for 1 collision minimum such as 9627/91503, 611/408
        hashCode %= TABLE_SIZE;
        return hashCode;
    }
    /**
     * This will print the statistics of the hash table 
     * As per lab instructions: collisions, resolves, total collisions, load factor
     * As well as the description of how the hash function works
     */
    public static void printStats() {
        Utils.println("-----------[HASH TABLE STATS]-----------");
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (hashTable[i] == null)
                Utils.println(i + "\t");
            else
                Utils.println(i + "\t" + hashTable[i]);
        }
        float loadFactor = (float) Utils.omittedWords.size() / TABLE_SIZE;
        Utils.println("\nCollisions: " + collisions);
        Utils.println("Resolutions: " + resolves);
        Utils.println("Total Collisions: " + totalCollisions);
        Utils.println("HashTable Load Factor: " + loadFactor);
        Utils.println("\n[Hashing Algorithm Information]:" +
            "\nThe hashing algorithm used sums the characters of a string\n" +
            "Borrowed the foundation from Stegman textbook\n" +
            "However, where it differs is by using an XOR and multiplying that\n" +
            "number. Before this I used a prime number (31) to multiply the sum\n" +
            "However it would have a minimum collisions of 8, which was not good enough\n" +
            "So instead, I made a small class to optimize this method by changing the\n" +
            "numbers in the XOR and multiplier to minimize the collision count. \n" +
            "After many hours and trials the hash function produces a total collision\n" +
            "count of 1, which is extremely low and the lowest that was found from my\n" +
            "algorithm, therefore I believe this is the minimum perfect hash function\n" +
            "for the set of omitted words used in the lab (besides definining switch/case\n" +
            "for all the strings and pairing it to a key manually). "
        );
    }
    /** 
     * Inserts into the hash table using linear probing
     * @param s
     */
    public void insert(String s) {
        int probe = getHashCode(s);
        int hashValue = getHashCode(s);
        if (hashTable[hashValue] != null)
            collisions++;
        while (hashTable[probe] != null) {
            if (hashTable[probe] != hashTable[hashValue])
                resolves++;
            probe++;
            probe %= TABLE_SIZE;
        }
        if (hashTable[probe] == null)
            hashTable[probe] = s;
        totalCollisions = collisions + resolves;
    }
    /**
     * This function will lookup whether the value exists in the hash table 
     * @param s The string to lookup
     * @return true or false
     */
    public boolean exists(String s) {
        int hashValue = getHashCode(s);
        int probe = getHashCode(s);
        while (hashTable[probe] != null) {
            if (hashTable[hashValue].equals(s))
                return true;
            probe++;
            probe %= TABLE_SIZE;
        }
        return false;
    }
}