
public class ControllerTest extends student.TestCase {
    
    private Controller test;
    private String[] keywords;
    public void setUp(){
        test = new Controller(4);
        keywords = new String[] {"hhh", "ssss"};
    }
    
    public void testInsert()
    {
        // Initial conditions: the tree should have no records
        assertEquals(test.getIdTree().getNumOfRecords(), 0);
        
        // Attempt to search for a Seminar that doesn't exist
        DLList<Seminar> found = test.getIdTree().searchExact(11);
        assertTrue(found.isEmpty());

        // Insert a valid Seminar into the tree
        test.insert(new Seminar(11, "oho", "2710051600", 120, (short)1,
            (short)0, 3, keywords, "oo"), "4");  // World size 4
        assertEquals(test.getIdTree().getNumOfRecords(), 1);

        // Check if the Seminar was inserted correctly
        found = test.getIdTree().searchExact(11);
        assertFalse(found.isEmpty());

        // Try inserting the same Seminar again and check that the insert fails
        test.insert(new Seminar(11, "oho", "2710051600", 120, (short)1,
            (short)0, 3, keywords, "oo"), "4");
        assertEquals(test.getIdTree().getNumOfRecords(), 1);  // Should still be 1 because insertion failed

        // Try inserting a Seminar with invalid coordinates (x or y out of bounds)
        test.insert(new Seminar(12, "invalid", "2710051600", 120, (short)-1,
            (short)0, 3, keywords, "oo"), "4");  // x < 0
        assertEquals(test.getIdTree().getNumOfRecords(), 1);  // Insertion should fail

        test.insert(new Seminar(13, "invalid", "2710051600", 120, (short)1,
            (short)5, 3, keywords, "oo"), "4");  // y >= world size
        assertEquals(test.getIdTree().getNumOfRecords(), 1);  // Insertion should fail

        // Finally, insert another valid Seminar
        test.insert(new Seminar(14, "valid", "2710051600", 120, (short)2,
            (short)2, 3, keywords, "oo"), "4");
        assertEquals(test.getIdTree().getNumOfRecords(), 2);  // Now there should be 2 records
    }

    public void testSearch()
    {
        test.searchID(69);
        String output = systemOut().getHistory();
        String expected = "Search FAILED -- There is no record with ID 69\n";
        assertEquals(output,expected);
        test.insert(new Seminar(11, "oho", "2710051600", 120, (short)1, 
            (short)0, 3, keywords, "oo"), "4");
        test.searchID(69);
        output = systemOut().getHistory();
        expected = "Search FAILED -- There is no record with ID 69\n"
            +"Successfully inserted record with ID 11\r\n"
            + "ID: 11, Title: oho\r\n"
            + "Date: 2710051600, Length: 120, X: 1, Y: 0, Cost: 3\r\n"
            + "Description: oo\r\n"
            + "Keywords: hhh, ssss\r\n"
            + "Search FAILED -- There is no record with ID 69\n"
            ;
        assertEquals(output,expected);
        test.searchID(11);
        output = systemOut().getHistory();
        expected = "Search FAILED -- There is no record with ID 69\n"
            +"Successfully inserted record with ID 11\r\n"
            + "ID: 11, Title: oho\r\n"
            + "Date: 2710051600, Length: 120, X: 1, Y: 0, Cost: 3\r\n"
            + "Description: oo\r\n"
            + "Keywords: hhh, ssss\r\n"
            + "Search FAILED -- There is no record with ID 69\n"
            +"Found record with ID 11:\n"
            +"ID: 11, Title: oho\r\n"
            + "Date: 2710051600, Length: 120, X: 1, Y: 0, Cost: 3\r\n"
            + "Description: oo\r\n"
            + "Keywords: hhh, ssss\n"
            ;
        assertEquals(output,expected);
    }
    

}
