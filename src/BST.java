
public class BST<T extends Comparable<T>> {
    private int numOfRecords;
    private int nodesTraversed;
    private Node<T> root;

    public BST() {
        setRoot(null);
        setNumOfRecords(0);
    }


    public DLList<Seminar> searchExact(T value) {
        DLList<Seminar> found = new DLList<Seminar>();
        searchHelp(root, value, found);
        return found;
    }


    private void searchHelp(Node<T> root, T value, DLList<Seminar> found) {
        if (root == null) {
            return;
        }
        int c = root.getData().compareTo(value);
        if (c > 0) {
            searchHelp(root.getLeft(), value, found);
        }
        else if (c < 0) {
            searchHelp(root.getRight(), value, found);
        }
        else {
            searchHelp(root.getLeft(), value, found);
            found.add(root.getSeminar());
        }
    }


    public DLList<Seminar> searchRange(T lower, T higher) {
        DLList<Seminar> found = new DLList<Seminar>();
        searchHelpRange(root, lower, higher, found, 0);
        return found;
    }


    private int searchHelpRange(
        Node<T> root,
        T lower,
        T higher,
        DLList<Seminar> found, int nodes) {
        if (root == null) {
            return 0;
        }
        int low = root.getData().compareTo(lower);
        int high = root.getData().compareTo(higher);
        if (low >= 0 && high <= 0) {
            searchHelpRange(root.getLeft(), lower, higher, found, nodes + 1);
            found.add(root.getSeminar());
            searchHelpRange(root.getRight(), lower, higher, found, nodes + 1);
        }
        else if (low < 0) {
            searchHelpRange(root.getRight(), lower, higher, found, nodes + 1);
        }
        else if (high > 0) {
            searchHelpRange(root.getLeft(), lower, higher, found, nodes + 1);
        }
        return nodes;

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
        else if (out < 0) {
            root.setRight(insertHelp(root.getRight(), key, value));
        }
        else {
            if (root.getLeft() == null) {
                root.setLeft(insertHelp(root.getLeft(), key, value));
            }
            else {
                insertHelp(root.getLeft(), key, value);
            }
        }
        return root;

    }


    public void delete(T key, int id) {
        root = deleteHelp(root, key, id);
        numOfRecords--;
    }


    private Node<T> deleteHelp(Node<T> root, T key, int id) {
        if (root == null) {
            return null;
        }
        int out = root.getData().compareTo(key);
        if (out > 0) {
            root.setLeft(deleteHelp(root.getLeft(), key, id));
        }
        else if (out < 0) {
            root.setRight(deleteHelp(root.getRight(), key, id));
        }
        else {
            if (root.getSeminar().id() == id) {
                if (root.getLeft() == null) {
                    return root.getRight();
                }
                else if (root.getRight() == null) {
                    return root.getLeft();
                }
                else {
                    Node<T> max = findMax(root.getLeft());
                    root.setData(max.getData());
                    root.setSeminar(max.getSeminar());
                    root.setLeft(deleteHelp(root.getLeft(), max.getData(), max
                        .getSeminar().id()));
                }
            }
            else {
                deleteHelp(root.getRight(), key, id);
            }
        }
        return root;
    }


    public int findHeight() {
        if (this.getNumOfRecords() == 0) {
            return 0;
        }
        else {
            return findHeightHelp(root);
        }
    }


    private int findHeightHelp(Node<T> root) {
        if (root == null) {
            return 0;
        }
        int right = findHeightHelp(root.getLeft());
        int left = findHeightHelp(root.getRight());

        if (left > right) {
            return left + 1;
        }
        else {
            return right + 1;
        }

    }


    public void print() {
        if (numOfRecords == 0) {
            System.out.println("This tree is Empty");
        }
        else {
            int height = this.findHeight();
            printHelp(root, height, 0);
            System.out.println("Number of Records: " + numOfRecords);
        }

    }


    private void printHelp(Node<T> root, int h, int level) {
        if (root == null) {
            for (int i = 0; i < (h - level); i++) {
                System.out.print("    ");
            }
            System.out.println("(null)");
            return;
        }

        printHelp(root.getLeft(), h, level + 1);

        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("\\");
        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("(" + root.getData() + ")");
        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("/");
        printHelp(root.getRight(), h, level + 1);
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
    
    public int getNodesTraversed() {
        return nodesTraversed;
    }
}
