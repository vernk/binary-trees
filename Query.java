/**
 * This class allows the user to query the binary search index for a word. 
 * It uses the default searchBST function found in the BinaryTree class
 * @author Veeran Kerai (010660296)
 */
import java.util.Scanner;
public class Query {
    private static boolean querying = true;
    /**
     * Query prompt for the user
     */
    public static void startQuery() {
        Utils.println("\n----------------[WORD SEARCH]-----------------");
        while (querying) {
            Scanner sc = new Scanner(System.in);
            Utils.println("\nEnter a word to search (-1 to return to menu): ");
            String input = sc.nextLine();
            Utils.printInput(input);
            search(input);
            if (input.equals("-1"))
                querying = false;
        }
    }
    /**
     * Search function which will search the binary tree and the hash table for the
     * inputted word, if it is omitted it will result in an error. 
     * @param word
     */
    private static void search(String word) {
        if (!Xref.getHashTable().exists(word)) {
            ObjectTreeNode result = Xref.getTree().searchBST(new Word(word.toLowerCase()));
            if (result != null) {
                Word retrieved = (Word) result.getInfo();
                Utils.print(String.format("%-12s %-6d ", retrieved.getWord(), retrieved.getWordCount()));
                ObjectListNode p = retrieved.getPositions().getFirstNode();
                while (p != null) {
                    Utils.print(String.format("%s ", p.getInfo()));
                    p = p.getNext();
                }
            } else {
                Utils.println("Error: The word could not be found.");
            }
        } else {
            Utils.println("Erorr: This word is omitted.");
        }
    }
}