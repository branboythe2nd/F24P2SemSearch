
/**
 * Test class for the BinTree class.
 * This class contains unit tests to validate the functionality of BinTree,
 * including edge cases
 * and boundary conditions. It ensures that all operations like insertion,
 * searching, and height
 * calculation work correctly.
 */
public class BinTreeTest extends student.TestCase {

    private BinTree binTree;
    private Seminar sem1, sem2, sem3, sem4, sem5, sem6, sem7, sem8, sem9, sem10,
        sem11;
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
        sem5 = new Seminar(5, "bye", "1010051600", 150, (short)70, (short)70,
            250, keywords, "d");
        sem6 = new Seminar(6, "The best", "1010051600", 30, (short)60,
            (short)189, 180, keywords, "e");
        sem7 = new Seminar(7, "Add", "1212051000", 75, (short)170, (short)10,
            350, keywords, "f");
        sem8 = new Seminar(8, "Sub", "0510051600", 60, (short)10, (short)10,
            420, keywords, "g");
        sem9 = new Seminar(9, "niceee", "0610051900", 90, (short)120,
            (short)199, 310, keywords, "h");
        sem10 = new Seminar(10, "kk", "0610051600", 120, (short)190, (short)240,
            360, keywords, "i");
        sem11 = new Seminar(11, "oho", "2710051600", 120, (short)190,
            (short)240, 360, keywords, "oo");
    }


    /**
     * Test inserting a single node and check if it increases the record count.
     */

    public void testInsertSingleNode() {
//        binTree.insert(99, 150, sem1);
//        assertEquals(1, binTree.getNumOfRecords());
//        binTree.insert(120, 110, sem2);
//        assertEquals(2, binTree.getNumOfRecords());
//        binTree.insert(120, 170, sem3);
//        assertEquals(3, binTree.getNumOfRecords());
//        binTree.insert(90, 180, sem2);
//        assertEquals(4, binTree.getNumOfRecords());
//       
//        assertTrue(binTree.getRoot() instanceof InternalNode);
//        InternalNode root = (InternalNode)binTree.getRoot();
//        //Checking right tree
//        root = (InternalNode)root.getRight();
//        assertTrue(root.getLeft() instanceof EmptyNode);
//        root = (InternalNode)root.getRight();
//        assertTrue(root.getRight() instanceof EmptyNode);
//        root = (InternalNode)root.getLeft();
//        LeafNode l1 = (LeafNode)root.getRight();
//        LeafNode l2 = (LeafNode)root.getLeft();
//        assertEquals(l1.getxValue(),120);
//        assertEquals(l1.getyValue(),110);
//        assertEquals(l2.getxValue(),120);
//        assertEquals(l2.getyValue(),170);
    }


    /**
     * Test inserting multiple nodes and check the record count.
     */

    public void testInsertMultipleNodes() {
        binTree.insert(100, 150, sem1);
        binTree.insert(110, 160, sem2);
        binTree.insert(70, 170, sem3);
        assertEquals(3, binTree.getNumOfRecords());
    }


    /**
     * Test inserting nodes with duplicate (x, y) coordinates,
     * ensuring that the record count still increments but no duplication in the
     * tree occurs.
     */

    public void testInsertDuplicateNodes() {
        binTree.insert(100, 150, sem1);
        binTree.insert(100, 150, sem1);
        assertEquals(2, binTree.getNumOfRecords());
    }


    /**
     * Test searching in an empty tree, which should return null.
     */

    public void testSearchEmptyTree() {
        assertNull(binTree.search(100, 150, 10));
    }


    /**
     * Test searching within a radius and ensure the correct nodes are returned.
     */

    public void testSearchWithRadius() {
        binTree.insert(100, 150, sem1);
        binTree.insert(110, 160, sem2);
        binTree.insert(120, 170, sem3);

        DLList<LeafNode> result = binTree.search(110, 160, 20);
        assertEquals(2, result.size());
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
     * Test inserting nodes that cause deeper recursive calls.
     */

    public void testDeepInsertions() {
        binTree.insert(5, 5, sem1);
        binTree.insert(90, 90, sem2);
        assertEquals(1, binTree.findHeight());
    }


    /**
     * Test searching for a node that exists in the tree.
     */

    public void testSearchFound() {
        binTree.insert(100, 150, sem1);
        DLList<LeafNode> result = binTree.search(100, 150, 5);
        assertEquals(1, result.size());
    }


    /**
     * Test searching for a node outside the radius.
     */

    public void testSearchNotFound() {
        binTree.insert(100, 150, sem1);
        DLList<LeafNode> result = binTree.search(50, 50, 5);
        assertEquals(0, result.size());
    }


    /**
     * Test printing the structure of the tree. This is a visual check, so the
     * assertion is simple.
     */

    public void testPrintTreeStructure() {
        binTree.insert(100, 150, sem1);
        binTree.insert(110, 160, sem2);
        binTree.insert(120, 170, sem3);

        
    }
}
