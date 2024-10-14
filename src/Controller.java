/**
 * The Controller class manages a graph structure representing artists and
 * songs.
 * It supports the command processor
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
     * 
     * @param worldSize
     *            the size of the world
     */
    public Controller(int worldSize) {
        setIdTree(new BST<Integer>());
        costTree = new BST<Integer>();
        datesTree = new BST<String>();
        keywordsTree = new BST<String>();
        locationTree = new BinTree(worldSize);
    }


    /**
     * Inserts seminars by breaking its respective parts and inserting them into
     * their respective trees
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

        DLList<Seminar> sem = getIdTree().searchExact(s.id());
        if (sem.isEmpty()) {
            getIdTree().insert(s.id(), s);
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
        DLList<Seminar> sem = getIdTree().searchExact(id);
        if (sem.isEmpty()) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
            return;
        }
        for (Seminar s : sem) {
            getIdTree().delete(s.id(), id);
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
        DLList<Seminar> sem = getIdTree().searchExact(id);
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
     * searches for the seminars within a given radius and point
     * 
     * @param x
     *            x value of the search center
     * @param y
     *            y value of the search center
     * @param radius
     *            radius of the circle with the x and y as the center
     */
    public void searchLocation(int x, int y, int radius) {
        DLList<LeafNode> found = locationTree.search(x, y, radius);
        // System.out.println(found);
        System.out.println("Seminars within " + radius + " units of " + x + ", "
            + y + ":");
        for (LeafNode l : found) {
            for (Seminar s : l.getSemList()) {
                System.out.println("Found a record with key value " + s.id()
                    + " at " + l.getxValue() + ", " + l.getyValue());
            }
        }
        System.out.println(locationTree.getNodesTraversed()
            + " nodes visited in this search");
    }


    /**
     * returns the ID tree
     */
    public void printId() {
        System.out.println("ID Tree:");
        getIdTree().print();
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


    public BST<Integer> getIdTree() {
        return idTree;
    }


    public void setIdTree(BST<Integer> idTree) {
        this.idTree = idTree;
    }

}
