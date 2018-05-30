import java.io.FileNotFoundException;
/**
 * Driver class for the BinaryTreeLab
 * @author Veeran Kerai (010660296)
 *
 */
public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        new Utils("csis.txt");
        Utils.runProgram("getty.txt");
        //Close program if user didn't 
        Utils.closeProgram();
    }
}