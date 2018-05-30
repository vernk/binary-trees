/**
 * Standard Word class object which will hold information about a word
 * such as the count, positions, and word itself. 
 * @author Veeran Kerai (010660296)
 *
 */
public class Word implements TreeComparable {
    private int wordCount;
    private String word;
    private ObjectList positionsList;
    /**
     * This constructor is used during binary tree creation
     * @param w
     * @param p
     * @param wc
     */
    public Word(String w, ObjectList p, int wc) {
        wordCount = wc;
        word = w;
        positionsList = p;
    }
    /**
     * This constructor is used for query's 
     * @param w
     */
    public Word(String w) {
        word = w;
        wordCount = 1;
        positionsList = new ObjectList();
    }
    /**
     * Getter for Word
     * @return
     */
    public String getWord() {
        return word;
    }
    /**
     * Getter for word count
     * @return
     */
    public int getWordCount() {
        return wordCount;
    }
    /**
     * Getter for position objectlist
     * @return
     */
    public ObjectList getPositions() {
        return positionsList;
    }
    /**
     * Our own implementation of compareTo
     */
    public int compareTo(Object o) {
        return word.compareTo(((Word) o).getWord());
    }
    /**
     * Our own operate implementation
     */
    public void operate(Object o) {
        ObjectListNode p = ((Word) o).getPositions().getFirstNode();
        wordCount++;
        positionsList.addLast(p);
    }
    /**
     * Our own visit method implementation
     */
    public void visit() {
        Utils.print(String.format("%-12s %-6d ", word, wordCount)); //prints during tree traversal (A-Z)
        ObjectListNode p = positionsList.getFirstNode();
        //Iterate through the object list
        while (p != null) {
            Utils.print(String.format("%s ", p.getInfo()));
            p = p.getNext(); //next node     
        }
        Utils.print("\n");
    }
}