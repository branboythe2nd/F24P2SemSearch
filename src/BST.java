

public class BST<T> {
    private int numOfRecords;
    private Node<T> root;
    
    public BST() {
        setRoot(null);
        setNumOfRecords(0);
    }
    
    public void searchExact(T value) {
        
    }
    
    public void searchRange(T lower, T higher) {
        
    }
    
    public void searchLocation(int x, int y, int radius) {
        
    }
    
    public void insert(T value) {
        
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
