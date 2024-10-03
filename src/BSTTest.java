

public class BSTTest extends student.TestCase {
    
    private BST<Integer> test;
    private String[] keywords = {"1", "2"};
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
    public void setUp()
    {
       test = new BST<Integer>();

       sem1 = new Seminar(1, "heeee", "0610051600", 60, (short) 100, (short) 150, 200, keywords, "a");
       sem2 = new Seminar(2, "woooww", "0610050900", 120, (short) 110, (short) 160, 300, keywords, "b");
       sem3 = new Seminar(3, "nice", "0710051600", 90, (short) 120, (short) 170, 150, keywords, "b");
       sem4 = new Seminar(4, "bye", "0611051600", 45, (short) 130, (short) 180, 400, keywords, "c");
       sem5 = new Seminar(5, "bye", "1010051600", 150, (short) 140, (short) 190, 250, keywords, "d");
       sem6 = new Seminar(6, "The best", "1010051600", 30, (short) 150, (short) 200, 180, keywords, "e");
       sem7 = new Seminar(7, "Add", "1212051000", 75, (short) 160, (short) 210, 350, keywords, "f");
       sem8 = new Seminar(8, "Sub", "0510051600", 60, (short) 170, (short) 220, 420, keywords, "g");
       sem9 = new Seminar(9, "niceee", "0610051900", 90, (short) 180, (short) 230, 310, keywords, "h");
       sem10 = new Seminar(10, "kk", "0610051600", 120, (short) 190, (short) 240, 360, keywords, "i");
       sem11 = new Seminar(11, "oho", "2710051600", 120, (short) 190, (short) 240, 360, keywords, "oo");

    }
    
    public void testInsertAndSearch()
    {
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
        test.print();
        
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
        
        assertEquals(12,test.getNumOfRecords());
        
        Node<Integer> root = test.getRoot();
        assertEquals((int)root.getData(),6);
        assertEquals(root.getSeminar(),sem6);
        //Right tree
        Node<Integer> rightNode = root.getRight();
        assertEquals((int)rightNode.getData(),7);
        assertEquals(rightNode.getSeminar(),sem7);
        
        rightNode = rightNode.getRight();
        assertEquals((int)rightNode.getData(),10);
        assertEquals(rightNode.getSeminar(),sem10);
        
        rightNode = rightNode.getLeft();
        assertEquals((int)rightNode.getData(),8);
        assertEquals(rightNode.getSeminar(),sem8);
        
        rightNode = rightNode.getRight();
        assertEquals((int)rightNode.getData(),9);
        assertEquals(rightNode.getSeminar(),sem9);
        rightNode = rightNode.getLeft();
        assertEquals((int)rightNode.getData(),9);
        assertEquals(rightNode.getSeminar(),sem9);       
        
        //Left tree 
        
        Node<Integer> leftNode = root.getLeft();
        assertEquals((int)leftNode.getData(),5);
        assertEquals(leftNode.getSeminar(),sem5);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(),4);
        assertEquals(leftNode.getSeminar(),sem4);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(),2);
        assertEquals(leftNode.getSeminar(),sem2);
        assertEquals((int)leftNode.getRight().getData(),3);
        assertEquals(leftNode.getRight().getSeminar(),sem3);
        leftNode = leftNode.getLeft();
        assertEquals((int)leftNode.getData(),1);
        assertEquals(leftNode.getSeminar(),sem1);
        
        BST<Integer> co = new BST<Integer>();
        co.insert(45, sem1);
        co.insert(30, sem2);
        co.insert(17, sem3);
        co.insert(30, sem4);
        root = co.getRoot();
        assertEquals((int)root.getData(),45);
        root = root.getLeft();
        assertEquals((int)root.getData(),30);
        root = root.getLeft();
        assertEquals((int)root.getData(),17);
        root = root.getRight();
        assertEquals((int)root.getData(),30);
        
        assertEquals(4,co.findHeight());
        
        
    }
    
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
        assertEquals((int) stillExist.getData(),7);
        
        test.delete(6, sem6.id());
        assertEquals((int)test.getRoot().getData(),5);
        assertEquals(test.getRoot().getSeminar(),sem5);
        
        test.delete(7, sem7.id());
        assertEquals((int)test.getRoot().getRight().getData(),10);
        
        test.delete(10, sem10.id());
        Node<Integer> root = test.getRoot(); 
        Node<Integer> curr = root.getRight();
        assertEquals((int)curr.getData(),9);
        assertEquals(curr.getSeminar(),sem9);
        assertEquals((int)curr.getLeft().getData(),8);
        assertEquals((int)curr.getLeft().getRight().getData(),9);
        assertNull(curr.getLeft().getRight().getRight());
        
        
        
        assertEquals(test.getNumOfRecords(),8);
        
        
    }
    
    public void testSearchRange() {
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
        
        DLList<Seminar> result = test.searchRange(5, 8);
        assertEquals(4, result.size());
        assertEquals(10,test.getNodesTraversed());
        
    }
    public void testPrint()
    {
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
        
        test.print();
        
       BST<Integer> empty = new BST<Integer>();
       empty.print();
        
       assertEquals(0,empty.findHeight());
        
    }
}
