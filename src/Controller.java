/**
 * The Controller class manages a graph structure representing artists and
 * songs.
 * It supports inserting artists and songs, printing the graph, and removing
 * artists or songs.
 * 
 * @author Brantson and Adarsh
 * @version 09.05.2024
 */
public class Controller {
    private BST<Integer> idTree;
    private BST<Integer> costTree;
    private BST<String> datesTree;
    private BST<String> keywordsTree;
    private BinTree locationTree;

    /**
     * Constructs a Controller
     */
    public Controller(int worldSize) {
        idTree = new BST<Integer>();
        costTree = new BST<Integer>();
        datesTree = new BST<String>();
        keywordsTree = new BST<String>();
        locationTree = new BinTree(worldSize);
    }


    /**
     * Inserts an artist and song into the database and updates the graph.
     * If the artist or song is already present, the method will handle
     * duplicates.
     * Also checks and doubles the size of the hash table if necessary.
     *
     * @param s
     *            the seminar to be inserted
     * @param size
     *            the size of the world
     */
    public void insert(Seminar s, String size) {
        int worldSize = Integer.parseInt(size);
        if (s.x() < 0 || s.x() >= worldSize || s.y() < 0 || s
            .y() >= worldSize) {
            System.out.println("Insert FAILED - Bad x, y coordinates: " + s.x()
                + ", " + s.y());
            return;
        }

        DLList<Seminar> sem = idTree.searchExact(s.id());
        if (sem.isEmpty()) {
            idTree.insert(s.id(), s);
            costTree.insert(s.cost(), s);
            datesTree.insert(s.date(), s);
            locationTree.insert(s.x(), s.y(), s);
            for (String word : s.keywords()) {
                keywordsTree.insert(word, s);
            }
            System.out.println("Successfully inserted record with ID " + s
                .id());
            System.out.println(s.toString());

        }
        else {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + s.id());
        }

    }


    /**
     * Deletes the id from all trees
     * 
     * @param id
     *            id to be deleted
     */
    public void delete(int id) {
        DLList<Seminar> sem = idTree.searchExact(id);
        if (sem.isEmpty()) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
            return;
        }
        for (Seminar s : sem) {
            idTree.delete(s.id(), id);
            costTree.delete(s.cost(), id);
            datesTree.delete(s.date(), id);
            for (String k : s.keywords()) {
                keywordsTree.delete(k, id);
            }
        }
        System.out.println("Record with ID " + id
            + " successfully deleted from the database");

    }


    /**
     * searches for the given ID
     * 
     * @param id
     *            id to be found
     */
    public void searchID(int id) {
        DLList<Seminar> sem = idTree.searchExact(id);
        if (sem.isEmpty()) {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Found record with ID " + id + ":");
            for (Seminar s : sem) {
                System.out.println(s.toString());
            }
        }
    }


    /**
     * searches for the given Keyword
     * 
     * @param key
     *            keyword to be found
     */
    public void searchKeyword(String key) {
        DLList<Seminar> sem = keywordsTree.searchExact(key);
        System.out.println("Seminars matching keyword " + key + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
    }


    /**
     * searches for the given cost range
     * 
     * @param low
     *            the lower bound
     * @param high
     *            the upper bound
     */
    public void searchRange(int low, int high) {
        DLList<Seminar> sem = costTree.searchRange(low, high);
        System.out.println("Seminars with costs in range " + low + " to " + high
            + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
        System.out.println(costTree.getNodesTraversed()
            + " nodes visited in this search");
    }


    /**
     * searches for the given dates
     * 
     * @param low
     *            lower date
     * @param high
     *            upper date
     */
    public void searchRange(String low, String high) {
        DLList<Seminar> sem = datesTree.searchRange(low, high);
        System.out.println("Seminars with dates in range " + low + " to " + high
            + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
        System.out.println(datesTree.getNodesTraversed()
            + " nodes visited in this search");
    }


    /**
     * returns the ID tree
     */
    public void printId() {
        System.out.println("ID Tree:");
        idTree.print();
    }


    /**
     * returns the Keyword tree
     */
    public void printKeyword() {
        System.out.println("Keyword Tree:");
        keywordsTree.print();
    }


    /**
     * returns the Cost tree
     */
    public void printCost() {
        System.out.println("Cost Tree:");
        costTree.print();
    }


    /**
     * returns the Dates tree
     */
    public void printDates() {
        System.out.println("Date Tree:");
        datesTree.print();
    }
    
    /**
     * returns the Dates tree
     */
    public void printLocation() {
        System.out.println("Location Tree:");
        locationTree.print();
    }

}
