
public class BST<T extends Comparable<T>> {
    private int numOfRecords;
    private Node<T> root;

    public BST() {
        setRoot(null);
        setNumOfRecords(0);
    }


    public DLList<Seminar> searchExact(T value) {
        DLList<Seminar> found = new DLList<Seminar>();
        searchHelp(root, value,found);
        return found;
    }
    
    private void searchHelp(Node<T> root, T value, DLList<Seminar> found){
        if(root == null)
        {
            return;
        }
        int c = root.getData().compareTo(value);
        if(c == 0 )
        {
            found.add(root.getSeminar());
        }
        if(c >= 0)
        {
            searchHelp(root.getLeft(),value, found);
        }
        if(c <= 0)
        {
            searchHelp(root.getRight(),value,found);            
        }
    }
    
    
    public Seminar searchRange(T lower, T higher) {
        return null;
    }


    public Seminar searchLocation(int x, int y, int radius) {
        return null;
    }


    public void insert(T key, Seminar value) {
        root = insertHelp(root, key, value);
        numOfRecords++;
    }


    private Node<T> insertHelp(Node<T> root, T key, Seminar value) {
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


    public void delete(T key) {
        root = deleteHelp(root, key);
        numOfRecords--;
    }


    private Node<T> deleteHelp(Node<T> root, T key) {
        if (root == null) {
            return null;
        }
        int out = root.getData().compareTo(key);
        if (out > 0) {
            root.setLeft(deleteHelp(root.getLeft(), key));
        }
        else if (out < 0) {
            root.setRight(deleteHelp(root.getRight(), key));
        }
        else {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            else if (root.getRight() == null) {
                return root.getLeft();
            }
            else {
                Node<T> max = findMax(root.getLeft());
                root.setData(max.getData());
                root.setLeft(deleteHelp(root.getLeft(), max.getData()));
            }
        }
        return root;
    }


    private Node<T> findMax(Node<T> root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
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
