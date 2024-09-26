
public class BST<T extends Comparable<T>> {
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
        root = insertHelp(root, value);
        numOfRecords++;
    }


    public Node<T> insertHelp(Node<T> root, T value)
    {
        if(root==null)
        {
            root = new Node<T>(value,null,null);
            return root;
        }
        int out = root.getData().compareTo(value);
        if(out > 0)
        {
            root.setLeft(new Node<T>(value, null, null));
        }
        else {
            root.setRight(new Node<T>(value, null, null));
        }
        return root;
        
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
