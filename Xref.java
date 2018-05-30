/**
 * Xref will create a cross-reference of the file content by inserting the
 * filtered words into a tree. It also adds the omitted words into the hash 
 * table for future use.
 * @author  Veeran Kerai (010660296)
 */
import java.util.ArrayList;
public class Xref {
    private static ObjectBinaryTree tree = new ObjectBinaryTree();
    private static HashTable hashTable = new HashTable();
    /**
     * Creates the hash table from the omitted words content
     */
    public static void generateHashTable() {
        for (String line: Utils.omittedWords) {
            hashTable.insert(line);
        }
    }
    /**
     * Generates the index binary tree and ignores omitted words as per lab instruction.
     * Adds the word object into the tree with position and count
     */
    public static void generateIndex() {
        int lineCounter = 1;
        for (String line: Utils.gettyContent) {
            String[] words = line.replaceAll("[-,;.]", "").toLowerCase().split("\\s+"); //remove puncutation
            for (int i = 0; i < words.length; i++) {
                if (!hashTable.exists(words[i])) {
                    ObjectList position = new ObjectList();
                    position.addLast(lineCounter + "-" + (i + 1));
                    Word wordadd = new Word(words[i], position, 1);
                    getTree().insertBSTDup(wordadd);
                }
            }
            lineCounter++;
        }
    }
    /**
     * Prints the cross reference listing in alphabetical order by
     * traversing the tree through inTrav
     */
    public static void printXref() {
        Utils.println("-------------[CROSS REFERENCE LISTING]-------------");
        generateHashTable();
        generateIndex();
        ObjectTreeNode p = getTree().getRoot();
        getTree().inTrav(p);
    }
    /**
     * Getter for the binary tree
     * @return
     */
    public static ObjectBinaryTree getTree() {
        return tree;
    }
    /**
     * Getter for the hash table
     * @return
     */
    public static HashTable getHashTable() {
        return hashTable;
    }
}