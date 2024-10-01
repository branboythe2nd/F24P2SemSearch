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
        if (idTree.searchExact(s.id()) == null) {
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
        }
        for (Seminar s : sem) {
            idTree.delete(s.id(), id);
            costTree.delete(s.cost(), id);
            datesTree.delete(s.date(), id);
            for (String k : s.keywords()) {
                keywordsTree.delete(k, id);
            }
        }

    }
    public void searchID(int id)
    {
        DLList<Seminar> sem = idTree.searchExact(id);
        for (Seminar s : sem) {
             System.out.println(s.toString());
        }
    }
    public void searchKeyword(String key)
    {
        DLList<Seminar> sem = keywordsTree.searchExact(key);
        for (Seminar s : sem) {
             System.out.println(s.toString());
        }
    }
    public void searchRange(int low, int high)
    {
        DLList<Seminar> sem = costTree.searchRange(low,high);
        for (Seminar s : sem) {
             System.out.println(s.toString());
        }
    }
    public void searchRange(String low, String high)
    {
        DLList<Seminar> sem = datesTree.searchRange(low,high);
        for (Seminar s : sem) {
             System.out.println(s.toString());
        }
    }

}
