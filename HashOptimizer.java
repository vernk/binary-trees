/**
 * This class is not relevant to the lab, however,it helped me
 * optimize the hash algorithm to produce the minimum
 * collisions possible
 * @author Veeran Kerai (010660296)
 */
import java.util.Random;
public class HashOptimizer {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String names = "a\r\n" +
            "after\r\n" +
            "all\r\n" +
            "and\r\n" +
            "because\r\n" +
            "every\r\n" +
            "for\r\n" +
            "from\r\n" +
            "had\r\n" +
            "have\r\n" +
            "in\r\n" +
            "is\r\n" +
            "it\r\n" +
            "its\r\n" +
            "now\r\n" +
            "of\r\n" +
            "on\r\n" +
            "so\r\n" +
            "that\r\n" +
            "the\r\n" +
            "their\r\n" +
            "there\r\n" +
            "to\r\n" +
            "was\r\n" +
            "were\r\n" +
            "which\r\n" +
            "with";
        String lines[] = names.split("\\r?\\n");
        insert(lines);
    }
    public static void insert(String[] lines) {
        int totalCollisions = 0;
        int min = 100;
        int apair = 0;
        int bpair = 0;
        int cpair = 0;
        while (totalCollisions <= 10) {
            String[] hashTable = new String[37];
            int collisions = 0;
            int resolves = 0;
            Random rand = new Random();
            //int a = 0 + (int)(Math.random() * ((99999 - 0) + 1));
            //int b = 0 + (int)(Math.random() * ((99999-0) + 1));
            int a = rand.nextInt(999999);
            int b = rand.nextInt(99);
            int c = 1;
            //with or without primes doesnt matter
            //31 does not help in our case makes collisions worse
            //so we will use the sum only and XOR with a multiplier
            //then % table size to get the key index for the array
            while (!isPrime(a)) {
                a = rand.nextInt(1000);
            }
            while (!isPrime(b)) {
                b = rand.nextInt(1000);
            }
            for (int i = 0; i < 27; i++) {
                int probe = getHashCode(lines[i], a, b, c);
                int hashValue = getHashCode(lines[i], a, b, c);
                if (hashTable[hashValue] != null) {
                    collisions += 1;
                }
                while (hashTable[probe] != null) {
                    if (hashTable[probe] != hashTable[hashValue]) {
                        resolves += 1;
                    }
                    probe++;
                    probe %= 37;
                }
                if (hashTable[probe] == null) {
                    hashTable[probe] = lines[i];
                }
            }
            totalCollisions = (collisions + resolves);
            if (totalCollisions < min) {
                min = totalCollisions;
                apair = a;
                bpair = b;
                cpair = c;
            }
            System.out.println(totalCollisions + "(MIN: " + min + " | PAIRS : " + apair + " & " + bpair + " &" + cpair + ") Current: " + a + " & " + b + " & " + c);
            if (totalCollisions <= 0) {
                System.out.println("PAIRS: " + a + " & " + b + " & " + c);
                break;
            }
            totalCollisions = 0;
        }
    }
    public static int getHashCode(String s, int a, int b, int c) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++)
            sum += c * (int) s.charAt(i);
        int hashCode = (sum ^ a) * b;
        hashCode %= 37;
        return hashCode;
    }
    private static boolean isPrime(int inputNum) {
        if (inputNum <= 3 || inputNum % 2 == 0)
            return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
            divisor += 2; //iterates through all possible divisors
        return inputNum % divisor != 0; //returns true/false
    }
}