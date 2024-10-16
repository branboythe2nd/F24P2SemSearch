import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test class for the BinTree class.
 * This class contains unit tests to validate the functionality of BinTree,
 * including edge cases
 * and boundary conditions. It ensures that all operations like insertion,
 * searching, and height
 * calculation work correctly.
 * 
 * @author Brantson and Adarsh
 * @version 10//10/2024
 */
public class BinTreeTest extends student.TestCase {

    private BinTree binTree;
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
    private String[] keywords = { "1", "2" };

    /**
     * Set up a new empty BinTree instance and initialize seminars before each
     * test.
     */
    public void setUp() {
        binTree = new BinTree(200); // Set a world size of 100 for tests

        // Initialize seminar objects
        sem1 = new Seminar(1, "heeee", "0610051600", 60, (short)99, (short)150,
            200, keywords, "a");
        sem2 = new Seminar(2, "woooww", "0610050900", 120, (short)120,
            (short)110, 300, keywords, "b");
        sem3 = new Seminar(3, "nice", "0710051600", 90, (short)120, (short)170,
            150, keywords, "b");
        sem4 = new Seminar(4, "bye", "0611051600", 45, (short)90, (short)180,
            400, keywords, "c");
        sem5 = new Seminar(5, "bye", "1010051600", 150, (short)45, (short)70,
            250, keywords, "d");
        sem6 = new Seminar(6, "The best", "1010051600", 30, (short)120,
            (short)170, 180, keywords, "e");
        sem7 = new Seminar(7, "Add", "1212051000", 75, (short)45, (short)70,
            350, keywords, "f");
        sem8 = new Seminar(8, "Sub", "0510051600", 60, (short)90, (short)150,
            420, keywords, "g");
        sem9 = new Seminar(9, "niceee", "0610051900", 90, (short)50, (short)80,
            310, keywords, "h");
        sem10 = new Seminar(10, "kk", "0610051600", 120, (short)100, (short)100,
            360, keywords, "i");
        sem11 = new Seminar(11, "oho", "2710051600", 120, (short)190,
            (short)240, 360, keywords, "oo");
    }


    /**
     * Test insert of the bintree
     */
    public void testInsert() {
        // Insert the seminars into the binary tree
        binTree.insert(99, 150, sem1);
        assertEquals(1, binTree.getNumOfRecords());
        binTree.insert(120, 110, sem2);
        assertEquals(2, binTree.getNumOfRecords());
        binTree.insert(120, 170, sem3);
        assertEquals(3, binTree.getNumOfRecords());
        binTree.insert(90, 180, sem4);
        assertEquals(4, binTree.getNumOfRecords());
        binTree.insert(45, 70, sem5);
        assertEquals(5, binTree.getNumOfRecords());

        assertTrue(binTree.getRoot() instanceof InternalNode);
        InternalNode root = (InternalNode)binTree.getRoot();

        // Right tree
        root = (InternalNode)root.getRight();
        assertTrue(root.getLeft() instanceof EmptyNode);

        root = (InternalNode)root.getRight();
        assertTrue(root.getRight() instanceof EmptyNode);

        root = (InternalNode)root.getLeft();
        LeafNode l1 = (LeafNode)root.getRight();
        LeafNode l2 = (LeafNode)root.getLeft();
        assertEquals(l1.getxValue(), 120);
        assertEquals(l1.getyValue(), 170);
        assertEquals(l2.getxValue(), 120);
        assertEquals(l2.getyValue(), 110);
        binTree.insert(120, 170, sem6);
        assertTrue(l1.getSemList().contains(sem6));
        assertTrue(l1.getSemList().contains(sem3));

        // Left tree
        root = (InternalNode)binTree.getRoot();
        root = (InternalNode)root.getLeft();
        assertTrue(root.getLeft() instanceof LeafNode);

        l1 = (LeafNode)root.getLeft();
        assertEquals(l1.getxValue(), 45);
        assertEquals(l1.getyValue(), 70);

        root = (InternalNode)root.getRight();
        assertTrue(root.getLeft() instanceof EmptyNode);
        root = (InternalNode)root.getRight();
        assertTrue(root.getLeft() instanceof EmptyNode);
        root = (InternalNode)root.getRight();
        assertTrue(root.getLeft() instanceof EmptyNode);
        root = (InternalNode)root.getRight();

        assertTrue(root.getLeft() instanceof LeafNode);
        assertTrue(root.getRight() instanceof LeafNode);
        l1 = (LeafNode)root.getRight();
        l2 = (LeafNode)root.getLeft();
        assertEquals(l1.getxValue(), 90);
        assertEquals(l1.getyValue(), 180);
        assertEquals(l2.getxValue(), 99);
        assertEquals(l2.getyValue(), 150);
        binTree.delete(sem1);
        root = (InternalNode)binTree.getRoot();
        root = (InternalNode)root.getLeft();
        assertTrue(root.getLeft() instanceof LeafNode);

        l1 = (LeafNode)root.getLeft();
        assertEquals(l1.getxValue(), 45);
        assertEquals(l1.getyValue(), 70);

        l1 = (LeafNode)root.getRight();
        assertEquals(l1.getxValue(), 90);
        assertEquals(l1.getyValue(), 180);
        binTree.insert(45, 70, sem7);
        binTree.insert(90, 180, sem1);
        root = (InternalNode)binTree.getRoot();
        root = (InternalNode)root.getLeft();
        l1 = (LeafNode)root.getLeft();
        assertEquals(l1.getSemList().size(), 2);
        assertTrue(l1.getSemList().contains(sem7));
        assertTrue(l1.getSemList().contains(sem5));

        binTree.insert(90, 150, sem8);

        root = (InternalNode)binTree.getRoot();
        root = (InternalNode)root.getLeft();
        root = (InternalNode)root.getRight();
        root = (InternalNode)root.getRight();
        root = (InternalNode)root.getRight();
        root = (InternalNode)root.getRight();
        root = (InternalNode)root.getLeft();

        assertTrue(root.getLeft() instanceof EmptyNode);
        assertTrue(root.getRight() instanceof InternalNode);

        root = (InternalNode)root.getRight();
        root = (InternalNode)root.getLeft();
        l1 = (LeafNode)root.getRight();
        l2 = (LeafNode)root.getLeft();

        assertEquals(l1.getxValue(), 99);
        assertEquals(l1.getyValue(), 150);
        assertEquals(l2.getxValue(), 90);
        assertEquals(l2.getyValue(), 150);

        binTree.insert(50, 80, sem9);
        assertEquals(9, binTree.getNumOfRecords());
        root = (InternalNode)binTree.getRoot();
        root = (InternalNode)root.getLeft();
        root = (InternalNode)root.getLeft();
        l1 = (LeafNode)root.getLeft();
        l2 = (LeafNode)root.getRight();
        assertTrue(l2.getSemList().contains(sem9));
        assertTrue(l1.getSemList().contains(sem5));
        assertTrue(l1.getSemList().contains(sem7));

        binTree.insert(100, 100, sem10);
    }


    /**
     * Tests the bintree according to project specs
     */
    public void testProjectSPec() {
        binTree = new BinTree(4);
        Seminar s1 = new Seminar(11, "oho", "2710051600", 120, (short)1,
            (short)0, 360, keywords, "oo");
        Seminar s2 = new Seminar(11, "oho", "2710051600", 120, (short)1,
            (short)2, 360, keywords, "oo");
        Seminar s3 = new Seminar(11, "oho", "2710051600", 120, (short)2,
            (short)1, 360, keywords, "oo");
        binTree.insert(1, 0, s1);
        binTree.insert(1, 2, s2);
        binTree.insert(2, 1, s3);
        DLList<LeafNode> found = binTree.search(1, 1, 1);
        assertEquals(3, found.size());
        found = binTree.search(1, 3, 0);
        assertEquals(0, found.size());
        found = binTree.search(0, 2, 0);
        assertEquals(0, found.size());
        found = binTree.search(3, 3, 0);
        assertEquals(0, found.size());

    }


    /**
     * Test for adding and searching mutiple nodes at the same
     */
    public void testMultipleNodesAtSameLeaf() {
        binTree.insert(99, 150, sem1);
        assertEquals(1, binTree.getNumOfRecords());
        binTree.insert(120, 110, sem2);
        assertEquals(2, binTree.getNumOfRecords());
        binTree.insert(120, 170, sem3);
        assertEquals(3, binTree.getNumOfRecords());
        binTree.insert(90, 180, sem4);
        assertEquals(4, binTree.getNumOfRecords());
        binTree.insert(45, 70, sem5);
        assertEquals(5, binTree.getNumOfRecords());
        DLList<LeafNode> found = new DLList<LeafNode>();
        binTree.print();
        found = binTree.search(90, 180, 0);
        assertEquals(found.size(), 1);
        binTree.insert(90, 180, new Seminar(10, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(13, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(16, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(20, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));

    }


    /**
     * Testing search method with additional cases.
     */
    public void testSearch() {

        assertNull(binTree.search(100, 150, 10));

        binTree.insert(99, 150, sem1);
        binTree.insert(120, 110, sem2);
        binTree.insert(120, 170, sem3);
        binTree.insert(90, 180, sem4);

        assertTrue(binTree.search(75, 40, 5).isEmpty());

        binTree.insert(45, 70, sem5);

        DLList<LeafNode> found = binTree.search(100, 100, 50);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(120, l.getxValue());
            assertEquals(110, l.getyValue());
        }

        found = binTree.search(0, 0, 1000);
        assertEquals(5, found.size());

        found = binTree.search(120, 170, 0);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(120, l.getxValue());
            assertEquals(170, l.getyValue());
        }

        found = binTree.search(75, 175, 40);
        assertEquals(2, found.size());

        found = binTree.search(75, 175, 50);
        assertEquals(3, found.size());
        for (LeafNode l : found) {
            assertTrue((l.getxValue() == 99 && l.getyValue() == 150) || (l
                .getxValue() == 120 && l.getyValue() == 170) || (l
                    .getxValue() == 90 && l.getyValue() == 180));
        }

        found = binTree.search(45, 70, 0);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertTrue((l.getxValue() == 45 && l.getyValue() == 70));
        }
        found = binTree.search(99, 150, 0);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(99, l.getxValue());
            assertEquals(150, l.getyValue());
        }

        found = binTree.search(120, 110, 0);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(120, l.getxValue());
            assertEquals(110, l.getyValue());
        }

        found = binTree.search(200, 200, 5);
        assertTrue(found.isEmpty());

        found = binTree.search(99, 99, 0);
        assertTrue(found.isEmpty());
        found = binTree.search(102, 102, 0);
        assertTrue(found.isEmpty());
        found = binTree.search(130, 140, 0);
        assertTrue(found.isEmpty());

        found = binTree.search(99, 150, 0);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(99, l.getxValue());
            assertEquals(150, l.getyValue());
        }

        found = binTree.search(99, 149, 0);
        assertTrue(found.isEmpty());

        found = binTree.search(99, 151, 0);
        assertTrue(found.isEmpty());

        found = binTree.search(99, 151, 1);
        assertEquals(1, found.size());

        found = binTree.search(98, 150, 0);
        assertTrue(found.isEmpty());

        found = binTree.search(100, 150, 0);
        assertTrue(found.isEmpty());

        found = binTree.search(100, 150, 1);
        assertEquals(1, found.size());

        found = binTree.search(99, 150, 1);
        assertEquals(1, found.size());
        for (LeafNode l : found) {
            assertEquals(99, l.getxValue());
            assertEquals(150, l.getyValue());
        }

        found = binTree.search(99, 150, 100);
        assertTrue(found.size() > 1);
        found = binTree.search(100, 25, 20);
        assertTrue(found.isEmpty());
    }


    /**
     * Test the height of an empty tree.
     */

    public void testEmptyTreeHeight() {
        assertEquals(0, binTree.findHeight());
    }


    /**
     * Test the height of a tree with multiple nodes inserted.
     */

    public void testTreeHeight() {
        binTree.insert(100, 150, sem1);
        binTree.insert(110, 160, sem2);
        binTree.insert(120, 170, sem3);
        assertEquals(4, binTree.findHeight());
    }


    /**
     * Test searching for a node that exists in the tree.
     */

    /**
     * Test printing the structure of the tree. This is a visual check, so the
     * assertion is simple.
     * 
     * @throws IOException
     */

    public void testPrintTreeStructure() throws IOException {

        binTree.insert(100, 150, sem1);
        binTree.insert(110, 160, sem2);
        binTree.insert(120, 170, sem3);
        binTree.print();
        binTree = new BinTree(200);
        binTree.insert(99, 150, sem1);
        assertEquals(1, binTree.getNumOfRecords());
        binTree.insert(120, 110, sem2);
        assertEquals(2, binTree.getNumOfRecords());
        binTree.insert(120, 170, sem3);
        assertEquals(3, binTree.getNumOfRecords());
        binTree.insert(90, 180, sem4);
        assertEquals(4, binTree.getNumOfRecords());
        binTree.insert(45, 70, sem5);
        assertEquals(5, binTree.getNumOfRecords());
        binTree.print();
        String actualOutput = systemOut().getHistory();
        String expectedOutput = "(I)\r\n" + "    (I)\r\n" + "        (I)\r\n"
            + "            (E)\r\n" + "            (I)\r\n"
            + "                (Leaf with 1 objects: 3)\r\n"
            + "                (Leaf with 1 objects: 2)\r\n" + "        (E)\r\n"
            + "    (Leaf with 1 objects: 1)\r\n" + "(I)\r\n" + "    (I)\r\n"
            + "        (I)\r\n" + "            (E)\r\n" + "            (I)\r\n"
            + "                (Leaf with 1 objects: 3)\r\n"
            + "                (Leaf with 1 objects: 2)\r\n" + "        (E)\r\n"
            + "    (I)\r\n" + "        (I)\r\n" + "            (I)\r\n"
            + "                (I)\r\n" + "                    (I)\r\n"
            + "                        (Leaf with 1 objects: 4)\r\n"
            + "                        (Leaf with 1 objects: 1)\r\n"
            + "                    (E)\r\n" + "                (E)\r\n"
            + "            (E)\r\n" + "        (Leaf with 1 objects: 5)\n";
        assertEquals(actualOutput, expectedOutput);
    }


    /**
     * Tests delete method in bintree
     */
    public void testDelete() {

        binTree.insert(99, 150, sem1);
        assertEquals(1, binTree.getNumOfRecords());
        binTree.delete(sem1);
        assertEquals(0, binTree.getNumOfRecords());
        assertTrue(binTree.getRoot() instanceof EmptyNode);
        binTree.insert(99, 150, sem1);
        binTree.insert(120, 110, sem2);
        assertEquals(2, binTree.getNumOfRecords());
        binTree.insert(120, 170, sem3);
        assertEquals(3, binTree.getNumOfRecords());
        binTree.insert(90, 180, sem4);
        assertEquals(4, binTree.getNumOfRecords());
        binTree.insert(45, 70, sem5);
        assertEquals(5, binTree.getNumOfRecords());

        binTree.delete(sem5);
        DLList<LeafNode> found = binTree.search(45, 70, 0);
        assertTrue(found.isEmpty());
        Seminar s = new Seminar(11, "oho", "2710051600", 120, (short)45,
            (short)70, 360, keywords, "oo");
        binTree.insert(45, 70, s);
        binTree.insert(45, 70, sem5);
        binTree.delete(sem5);
        found = binTree.search(45, 70, 0);
        assertEquals(found.size(), 1);
        found = binTree.search(120, 110, 0);
        assertEquals(found.size(), 1);
        binTree.delete(sem1);
        found = binTree.search(99, 150, 0);
        assertEquals(found.size(), 0);
        found = binTree.search(90, 180, 0);
        assertEquals(found.size(), 1);
        binTree.insert(99, 150, sem1);
        binTree.insert(90, 180, new Seminar(10, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(13, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(16, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        binTree.insert(90, 180, new Seminar(20, "oho", "2710051600", 120,
            (short)90, (short)180, 360, keywords, "oo"));
        found = binTree.search(90, 180, 0);
        System.out.println("look herreeeeeeeee");
        assertEquals(found.get(0).getSemList().size(), 5);
        binTree.delete(sem4);
        found = binTree.search(90, 180, 0);
        assertEquals(found.get(0).getSemList().size(), 4);
    }

}
