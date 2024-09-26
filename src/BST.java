
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


    public void insert(T key, Seminar value) {
        root = insertHelp(root, key, value);
        numOfRecords++;
    }


    public Node<T> insertHelp(Node<T> root, T key, Seminar value) {
        if (root == null) {
            root = new Node<T>(key, value, null, null);
            return root;
        }
        int out = root.getData().compareTo(key);
        if (out > 0) {
            root.setLeft(insertHelp(root.getLeft(), key, value));
        }
        else {
            root.setRight(insertHelp(root.getRight(), key, value));
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
