import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test class for BST
 * 
 * @author Brantson and Adarsh
 * @version 10/03/2024
 */
public class BSTTest extends student.TestCase {

    private BST<Integer> test;
    private String[] keywords = { "1", "2" };
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem3;
    private Seminar sem4;
    private Seminar sem5;
    private Seminar sem6;
    private Seminar sem7;
    private Seminar sem8;
    private Seminar sem9;
    private Seminar sem10;
    private Seminar sem11;
    private Seminar sem12;

    /**
     * Called before every test method
     */
    public void setUp() {
        test = new BST<Integer>();

        sem1 = new Seminar(1, "heeee", "0610051600", 60, (short)100, (short)150,
            200, keywords, "a");
        sem2 = new Seminar(2, "woooww", "0610050900", 120, (short)110,
            (short)160, 300, keywords, "b");
        sem3 = new Seminar(3, "nice", "0710051600", 90, (short)120, (short)170,
            150, keywords, "b");
        sem4 = new Seminar(4, "bye", "0611051600", 45, (short)130, (short)180,
            400, keywords, "c");
        sem5 = new Seminar(5, "bye", "1010051600", 150, (short)140, (short)190,
            250, keywords, "d");
        sem6 = new Seminar(6, "The best", "1010051600", 30, (short)150,
            (short)200, 180, keywords, "e");
        sem7 = new Seminar(7, "Add", "1212051000", 75, (short)160, (short)210,
            350, keywords, "f");
        sem8 = new Seminar(8, "Sub", "0510051600", 60, (short)170, (short)220,
            420, keywords, "g");
        sem9 = new Seminar(9, "niceee", "0610051900", 90, (short)180,
            (short)230, 310, keywords, "h");
        sem10 = new Seminar(10, "kk", "0610051600", 120, (short)190, (short)240,
            360, keywords, "i");
        sem11 = new Seminar(11, "oho", "2710051600", 120, (short)190,
            (short)240, 360, keywords, "oo");
        sem12 = new Seminar(12, "oho", "2710051600", 120, (short)190,
            (short)240, 360, keywords, "oo");

    }


    /**
     * tests the insert and search method
     */
    public void testInsertAndSearch() {
        test.insert(sem6.id(), sem6);
        test.insert(sem5.id(), sem5);
        test.insert(sem7.id(), sem7);
        test.insert(sem10.id(), sem10);
        test.insert(sem4.id(), sem4);
        test.insert(sem2.id(), sem2);
        test.insert(sem1.id(), sem1);
        test.insert(sem8.id(), sem8);
        test.insert(sem3.id(), sem3);
        test.insert(sem9.id(), sem9);
        test.insert(sem9.id(), sem9);
        test.insert(sem9.id(), sem9);

        DLList<Seminar> result = test.searchExact(1);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem1));
        result = test.searchExact(2);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem2));
        result = test.searchExact(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem3));
        result = test.searchExact(4);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem4));
        result = test.searchExact(5);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem5));
        result = test.searchExact(6);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem6));
        result = test.searchExact(7);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem7));
        result = test.searchExact(8);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem8));
        result = test.searchExact(9);
        assertEquals(3, result.size());
        assertTrue(result.contains(sem9));
        result = test.searchExact(10);
        assertEquals(1, result.size());
        assertTrue(result.contains(sem10));

        assertEquals(12, test.getNumOfRecords());

        Node<Integer> root = test.getRoot();
        assertEquals((int)root.getData(), 6);
        assertEquals(root.getSeminar(), sem6);
        // Right tree
        Node<Integer> rightNode = root.getRight();
        assertEquals((int)rightNode.getData(), 7);
        assertEquals(rightNode.getSeminar(), sem7);

        rightNode = rightNode.getRight();
        assertEquals((int)rightNode.getData(), 10);
        assertEquals(rightNode.getSeminar(), sem10);

        rightNode = rightNode.getLeft();
        assertEquals((int)rightNode.getData(), 8);
        assertEquals(rightNode.getSeminar(), sem8);

        rightNode = rightNode.getRight();
        assertEquals((int)rightNode.getData(), 9);
        assertEquals(rightNode.getSeminar(), sem9);
        rightNode = rightNode.getLeft();
        assertEquals((int)rightNode.getData(), 9);
        assertEquals(rightNode.getSeminar(), sem9);

        // Left tree

        Node<Integer> leftNode = root.getLeft();
        assertEquals((int)leftNode.getData(), 5);
        assertEquals(leftNode.getSeminar(), sem5);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(), 4);
        assertEquals(leftNode.getSeminar(), sem4);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(), 2);
        assertEquals(leftNode.getSeminar(), sem2);
        assertEquals((int)leftNode.getRight().getData(), 3);
        assertEquals(leftNode.getRight().getSeminar(), sem3);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(), 1);
        assertEquals(leftNode.getSeminar(), sem1);

        BST<Integer> co = new BST<Integer>();
        co.insert(45, sem1);
        co.insert(30, sem2);
        co.insert(17, sem3);
        co.insert(30, sem4);
        root = co.getRoot();
        assertEquals((int)root.getData(), 45);
        root = root.getLeft();
        assertEquals((int)root.getData(), 30);
        root = root.getLeft();
        assertEquals((int)root.getData(), 17);
        root = root.getRight();
        assertEquals((int)root.getData(), 30);

        assertEquals(4, co.findHeight());

    }


    /**
     * Tests the remove and search
     */
    public void testRemoveAndSearch() {
        test.insert(sem6.id(), sem6);
        test.insert(sem5.id(), sem5);
        test.insert(sem7.id(), sem7);
        test.insert(sem10.id(), sem10);
        test.insert(sem4.id(), sem4);
        test.insert(sem2.id(), sem2);
        test.insert(sem1.id(), sem1);
        test.insert(sem8.id(), sem8);
        test.insert(sem3.id(), sem3);
        test.insert(sem9.id(), sem9);
        test.insert(sem9.id(), sem9);
        test.insert(sem11.id(), sem11);

        test.delete(7, 69);
        Node<Integer> stillExist = test.getRoot().getRight();
        assertEquals((int)stillExist.getData(), 7);

        test.delete(6, sem6.id());
        assertEquals((int)test.getRoot().getData(), 5);
        assertEquals(test.getRoot().getSeminar(), sem5);

        test.delete(7, sem7.id());
        assertEquals((int)test.getRoot().getRight().getData(), 10);

        test.delete(10, sem10.id());
        Node<Integer> root = test.getRoot();
        Node<Integer> curr = root.getRight();
        assertEquals((int)curr.getData(), 9);
        assertEquals(curr.getSeminar(), sem9);
        assertEquals((int)curr.getLeft().getData(), 8);
        assertEquals((int)curr.getLeft().getRight().getData(), 9);
        assertNull(curr.getLeft().getRight().getRight());

        assertEquals(test.getNumOfRecords(), 8);

    }


    /**
     * tests the delete function
     */
    public void testRemove() {
        test.insert(20, sem6);
        test.insert(40, sem5);
        test.insert(50, sem7);
        test.insert(10, sem10);
        test.insert(10, sem4);
        test.insert(40, sem2);

        Node<Integer> root = test.getRoot();
        assertEquals(40, (int)root.getRight().getLeft().getData());
        assertEquals(10, (int)root.getLeft().getLeft().getData());

        test.delete(40, sem2.id());
        assertEquals(test.getNumOfRecords(), 5);
        DLList<Seminar> found = test.searchExact(40);
        assertEquals(found.size(), 1);
        assertNull(root.getRight().getLeft());
        test.delete(10, sem4.id());
        assertEquals(test.getNumOfRecords(), 4);
        found = test.searchExact(10);
        assertEquals(found.size(), 1);
        assertNull(root.getLeft().getLeft());

        BST<Integer> empty = new BST<Integer>();
        empty.insert(sem6.id(), sem6);
        empty.insert(sem7.id(), sem7);
        empty.insert(sem8.id(), sem8);
        empty.insert(sem9.id(), sem9);
        empty.insert(sem10.id(), sem10);
        empty.insert(sem11.id(), sem11);

        empty.delete(11, sem11.id());

        assertEquals(empty.getNumOfRecords(), 5);
        root = empty.getRoot();
        assertNull(root.getRight().getRight().getRight().getRight().getRight());
        empty = new BST<Integer>();
        empty.insert(40, sem6);
        empty.insert(50, sem7);
        empty.insert(32, sem9);
        empty.insert(30, sem8);

        empty.insert(29, sem10);
        empty.insert(33, sem11);
        empty.insert(31, sem1);
        empty.insert(28, sem2);

        empty.delete(32, sem9.id());
        assertEquals(31, (int)empty.getRoot().getLeft().getData());

        empty = new BST<Integer>();
        assertEquals(0, empty.findHeight());
        empty.print();
        empty.insert(20, sem6);
        empty.insert(30, sem7);
        empty.insert(10, sem8);
        empty.insert(15, sem9);
        empty.insert(9, sem10);
        empty.insert(sem11.id(), sem11);
        empty.delete(10, sem8.id());
        assertNotNull(empty.getRoot().getLeft().getRight());

    }


    /**
     * Tests search range
     */
    public void testSearchRange() {
        test.insert(sem6.id(), sem6);
        test.insert(sem5.id(), sem5);
        test.insert(sem7.id(), sem7);
        test.insert(sem10.id(), sem10);
        test.insert(sem4.id(), sem4);
        test.insert(sem2.id(), sem2);
        test.insert(sem1.id(), sem1);
        test.insert(sem11.id(), sem11);
        test.insert(sem9.id(), sem9);
        test.insert(sem8.id(), sem8);
        test.insert(sem3.id(), sem3);
        // test.insert(sem9.id(), sem9);

        DLList<Seminar> result = test.searchRange(5, 8);
        assertEquals(4, result.size());
        assertEquals(11, test.getNodesTraversed());
        result = test.searchRange(9, 13);
        assertEquals(3, result.size());
        assertEquals(10, test.getNodesTraversed());
        result = test.searchRange(0, 13);
        assertEquals(11, result.size());
        assertEquals(23, test.getNodesTraversed());

    }


    /**
     * Tests search range
     */
    public void testSearchRange2() {
        test.insert(sem6.id(), sem6);
        test.insert(sem7.id(), sem7);
        test.insert(sem10.id(), sem10);
        test.insert(sem4.id(), sem4); // ID 4

        DLList<Seminar> result = test.searchRange(5, 8);
        assertEquals(2, result.size()); // We expect 2 results: sem6 and sem7
        assertTrue(result.contains(sem6)); // sem6 should be in the result
        assertTrue(result.contains(sem7)); // sem7 should be in the result
        assertFalse(result.contains(sem10));
        assertEquals(7, test.getNodesTraversed());

    }


    /**
     * Tests the print method
     * 
     * @throws IOException
     */
    public void testPrint() throws IOException {
        test.insert(sem6.id(), sem6);
        assertEquals(1, test.findHeight());
        test.insert(sem5.id(), sem5);
        test.insert(sem7.id(), sem7);
        assertEquals(2, test.findHeight());
        test.insert(sem10.id(), sem10);
        test.insert(sem4.id(), sem4);
        test.insert(sem2.id(), sem2);
        test.insert(sem1.id(), sem1);
        assertEquals(5, test.findHeight());
        test.insert(sem8.id(), sem8);
        test.insert(sem3.id(), sem3);
        test.insert(sem9.id(), sem9);
        test.insert(sem9.id(), sem9);
        test.insert(sem11.id(), sem11);

        test.print();

        BST<Integer> empty = new BST<Integer>();
        assertEquals(0, empty.findHeight());
        empty.print();
        empty.insert(sem6.id(), sem6);
        empty.insert(sem7.id(), sem7);
        empty.insert(sem8.id(), sem8);
        empty.insert(sem9.id(), sem9);
        empty.insert(sem10.id(), sem10);
        empty.insert(sem11.id(), sem11);
        Node<Integer> root = empty.getRoot();
        assertEquals((int)root.getData(), 6);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 7);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 8);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 9);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 10);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 11);
        assertNull(root.getLeft());
        assertNull(root.getRight());

        assertEquals(6, empty.findHeight());
        assertEquals(6, test.findHeight());
        empty.print();
        empty = new BST<Integer>();
        empty.insert(sem11.id(), sem11);
        empty.insert(sem10.id(), sem10);
        empty.insert(sem9.id(), sem9);
        empty.insert(sem8.id(), sem8);
        empty.insert(sem7.id(), sem7);
        empty.insert(sem6.id(), sem6);
        root = empty.getRoot();
        assertEquals((int)root.getData(), 11);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 10);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 9);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 8);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 7);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 6);
        assertNull(root.getLeft());
        assertNull(root.getRight());

        empty.print();
        int[] expected = { 0, 1, 2, 3, 4, 5 };
        int count = 0;
        assertEquals(empty.getLevels().size(), 6);
        for (int l : empty.getLevels()) {
            assertEquals(l, expected[count]);
            count++;
        }
        String actualOutput = systemOut().getHistory();
        String expectedOutput = readFile("solutionTestData/bstprint.txt");
        assertFuzzyEquals(actualOutput, expectedOutput);
    }


    /**
     * More edge cases for delete
     */
    public void testDeleteM() {
        BST<Integer> empty = new BST<Integer>();
        empty.insert(sem11.id(), sem11);
        empty.insert(sem10.id(), sem10);
        empty.insert(sem9.id(), sem9);
        empty.insert(sem8.id(), sem8);
        empty.insert(sem7.id(), sem7);
        empty.insert(sem6.id(), sem6);
        Node<Integer> root = empty.getRoot();
        assertEquals((int)root.getData(), 11);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 10);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 9);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 8);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 7);
        assertNull(root.getRight());
        root = root.getLeft();
        assertEquals((int)root.getData(), 6);
        assertNull(root.getLeft());
        assertNull(root.getRight());

        empty.delete(6, sem6.id());
        DLList<Seminar> found = empty.searchExact(6);
        assertEquals(found.size(), 0);
        root = empty.getRoot();
        assertNull(root.getLeft().getLeft().getLeft().getLeft().getLeft());

        empty = new BST<Integer>();
        assertEquals(0, empty.findHeight());
        empty.print();
        empty.insert(sem6.id(), sem6);
        empty.insert(sem7.id(), sem7);
        empty.insert(sem8.id(), sem8);
        empty.insert(sem9.id(), sem9);
        empty.insert(sem10.id(), sem10);
        empty.insert(sem11.id(), sem11);
        root = empty.getRoot();
        assertEquals((int)root.getData(), 6);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 7);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 8);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 9);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 10);
        assertNull(root.getLeft());
        root = root.getRight();
        assertEquals((int)root.getData(), 11);
        assertNull(root.getLeft());
        assertNull(root.getRight());

        empty.delete(11, 11);
        found = empty.searchExact(11);
        assertEquals(found.size(), 0);
        root = empty.getRoot();
        assertNull(root.getRight().getRight().getRight().getRight().getRight());
        empty.delete(10, 10);
        found = empty.searchExact(10);
        assertEquals(found.size(), 0);
        root = empty.getRoot();
        assertNull(root.getRight().getRight().getRight().getRight());
        empty.delete(9, 9);
        found = empty.searchExact(9);
        assertEquals(found.size(), 0);
        root = empty.getRoot();
        assertNull(root.getRight().getRight().getRight());

    }


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
     * Tests the more rigid edge cases for print
     */
    public void testPrint1() {
        // Insert nodes
        test.insert(20, sem1); // Root node with key 20
        test.insert(10, sem2); // Left child
        test.insert(30, sem3); // Right child

        // Capture the printed output
        test.print();

        String expectedOutput = "(null)\r\n" + "    \\\r\n" + "    (10)\r\n"
            + "    /\r\n" + "(null)\r\n" + "        \\\r\n" + "        (20)\r\n"
            + "        /\r\n" + "(null)\r\n" + "    \\\r\n" + "    (30)\r\n"
            + "    /\r\n" + "(null)\r\n" + "Number of Records: 3\n";

        // Check the printed output (compare to the expected output)
        assertEquals(systemOut().getHistory(), expectedOutput);
    }


    /**
     * Tests print again
     */
    public void testPrint2() {
        // Insert nodes to create a deeper right subtree
        test.insert(20, sem1); // Root node with key 20
        test.insert(30, sem2); // Right child
        test.insert(40, sem3); // Right child of 30

        // Capture the printed output
        test.print();

        String expectedOutput = "        (null)\r\n" + "            \\\r\n"
            + "            (20)\r\n" + "            /\r\n" + "    (null)\r\n"
            + "        \\\r\n" + "        (30)\r\n" + "        /\r\n"
            + "(null)\r\n" + "    \\\r\n" + "    (40)\r\n" + "    /\r\n"
            + "(null)\r\n" + "Number of Records: 3\n";

        // Check the printed output (compare to the expected output)
        assertEquals(systemOut().getHistory(), expectedOutput);
    }

}
