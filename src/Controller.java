import java.util.Iterator;

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

    /**
     * Constructs a Controller with the given length for hash tables and graph.
     *
     * @param length
     *            the initial size of the hash tables and the graph
     */
    public Controller() {
        idTree = new BST<Integer>();
        costTree = new BST<Integer>();
        datesTree = new BST<String>();
        keywordsTree = new BST<String>();
    }


    /**
     * Inserts an artist and song into the database and updates the graph.
     * If the artist or song is already present, the method will handle
     * duplicates.
     * Also checks and doubles the size of the hash table if necessary.
     *
     * @param artist
     *            the name of the artist to insert
     * @param song
     *            the name of the song to insert
     */
    public void insert(Seminar s) {
        DLList<Seminar> sem = idTree.searchExact(s.id());
        if (sem.isEmpty()) {
            idTree.insert(s.id(), s);
            costTree.insert(s.cost(), s);
            datesTree.insert(s.date(), s);
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


    public void delete(int id) {
        DLList<Seminar> sem = idTree.searchExact(id);
        if (sem.isEmpty()) {
            System.out.println("REMOVE FAILED ID DOES NOT EXIST");
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
        System.out.println("Record with ID "+id+ " successfully deleted from the database");

    }


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


    public void searchKeyword(String key) {
        DLList<Seminar> sem = keywordsTree.searchExact(key);
        System.out.println("Seminars matching keyword " + key + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
    }


    public void searchRange(int low, int high) {
        DLList<Seminar> sem = costTree.searchRange(low, high);
        System.out.println("Seminars with costs in range " + low + " to " + high + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
        System.out.println(costTree.getNodesTraversed() + " nodes visited in this search");
    }


    public void searchRange(String low, String high) {
        DLList<Seminar> sem = datesTree.searchRange(low, high);
        System.out.println("Seminars with dates in range " + low + " to " + high + ":");
        for (Seminar s : sem) {
            System.out.println(s.toString());
        }
        System.out.println(datesTree.getNodesTraversed() + " nodes visited in this search");
    }


    public void printId() {
        System.out.println("ID Tree:");
        idTree.print();
    }


    public void printKeyword() {
        System.out.println("Keyword Tree:");
        keywordsTree.print();
    }


    public void printCost() {
        System.out.println("Cost Tree:");
        costTree.print();
    }


    public void printDates() {
        System.out.println("Date Tree:");
        datesTree.print();
    }

}
