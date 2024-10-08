import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class SemSearchTest extends TestCase {

    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testSampleIO() throws Exception {
        // Setting up all the parameters
        String[] args = new String[2];
        args[0] = "128";
        args[1] = "solutionTestData/input.txt";

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile("solutionTestData/output.txt");

        // Compare the two outputs
        assertFuzzyEquals(expectedOutput, actualOutput);

    }


//    /**
//     * Get code coverage of the class declaration.
//     */
//    public void testSimple() throws Exception {
//        // Setting up all the parameters
//        String[] args = new String[2];
//        args[0] = "128";
//        args[1] = "solutionTestData/test.txt";
//
//        // Invoke main method of our Graph Project
//        SemSearch.main(args);
//
//        String actualOutput = systemOut().getHistory();
//
//        // Expected output from file
//        String expectedOutput = readFile("solutionTestData/testOut.txt");
//
//        // Compare the two outputs
//        assertFuzzyEquals(expectedOutput, actualOutput);
//
//    }

}
