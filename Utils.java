/**
 * This class manages the menu and file reading for the lab, it also
 * combines writing to console and csis.txt file to save lots of time
 * @author Veeran Kerai (010660296) 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class Utils {
    static PrintWriter pw;
    static boolean running = true;
    //Only used to store file contents so we don't have to read the files multiple times in the future 
    static ArrayList < String > gettyContent = new ArrayList < > ();
    static ArrayList < String > omittedWords = new ArrayList < > ();
    /**
     * Constructor which initializes the PrintWriter for the filename
     * @param fileName
     */
    public Utils(String fileName) {
        try {
            pw = new PrintWriter(new java.io.FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Main program menu for user interaction 
     * @param fileName
     * @throws FileNotFoundException
     */
    public static void runProgram(String fileName)
    throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        runGetty(fileName);
        getOmittedWords("omittedwords.txt");
        while (running) {
            println("\n---------------------[BINARY TREE LAB MENU]---------------------");
            println("1: Output/build cross refrenced listing tree (A-Z) [Must run first]");
            println("2: Querying/word search system");
            println("3: Hash table statistics & details");
            println("0: Exit program");
            println("----------------------------------------------------------------");
            println("Enter choice: ");
            int choice = sc.nextInt();
            pw.print(choice);
            switch (choice) {
                case 1:
                    Xref.printXref();
                    break;
                case 2:
                    Query.startQuery();
                    break;
                case 3:
                    HashTable.printStats();
                    break;
                case 0:
                    running = false;
            }
        }
    }
    /**
     * Extracts the getty.txt line by line into an arraylist for use by Xref.java to create
     * the binary tree index. Also outputs the text content with line number as per lab instruction
     * @param fileName
     * @throws FileNotFoundException
     */
    private static void runGetty(String fileName)
    throws FileNotFoundException {
        Scanner content = new Scanner(new File(fileName));
        int lineNum = 1;
        println("--------------------OUTPUT (" + fileName + ")---------------------");
        while (content.hasNext()) {
            String line = content.nextLine();
            println("[" + lineNum + "]\t" + line);
            gettyContent.add(line);
            lineNum++;
        }
        content.close();
    }
    /**
     * Extracts all the omitted words from omittedwords.txt into an arraylist for use by Xref.java to
     * create the hash table used in the lab. 
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    private static void getOmittedWords(String fileName)
    throws FileNotFoundException {
        Scanner content = new Scanner(new File(fileName));
        while (content.hasNext()) {
            String line = content.nextLine();
            omittedWords.add(line);
        }
        println("\n(" + omittedWords.size() + " omitted words added from " + fileName + ")");
        content.close();
    }
    /**
     * Prints line with spacing to both 
     * @param str
     */
    public static void println(String str) {
        System.out.println(str);
        pw.println(str);
    }
    /**
     * Prints without new line
     * @param str
     */
    public static void print(String str) {
        System.out.print(str);
        pw.print(str);
    }
    /**
     * Prints the user input to the txt file only
     * @param str
     */
    public static void printInput(String str) {
        pw.print(str);
    }
    /**
     * Closes the print writer
     */
    public static void closeProgram() {
        pw.close();
    }
}