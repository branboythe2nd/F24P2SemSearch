import java.util.Comparator;

public class BST<T> {
    private int numOfRecords;
    private Node<T> root;
    private Comparator<T> comparator;
    
    public BST(Comparator<T> comparator) {
        setRoot(null);
        setNumOfRecords(0);
        this.comparator = comparator;
    }
    
    public void searchExact(T value) {
        
    }
    
    public void searchRange(T lower, T higher) {
        
    }
    
    public void searchLocation(int x, int y, int radius) {
        
    }
    
    public void insert(T value) {
        if (comparator.compare(root.getData(),value)>0) {
            
        }
    }
    
    public void delete(int ID) {
        
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int getNumOfRecords() {
        return numOfRecords;
    }

    public void setNumOfRecords(int numOfRecords) {
        this.numOfRecords = numOfRecords;
    }
}
