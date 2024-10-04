
/**
 * A generic Binary Search Tree (BST) class that stores records of type T.
 * 
 * @author Brantson and Adarsh
 * @version 10/03/2024
 * 
 * @param <T>
 *            the type of elements stored in the BST, which must be comparable.
 */
public class BST<T extends Comparable<T>> {

    private Node<T> root;
    private int numOfRecords;
    private int nodesTraversed;

    /**
     * Constructs an empty BST with no root node and zero records.
     */
    public BST() {
        setRoot(null);
        setNumOfRecords(0);
    }


    /**
     * Searches the tree for nodes with a value that exactly matches the
     * provided value.
     * 
     * @param value
     *            the value to search for in the tree.
     * @return a doubly linked list of Seminar objects associated with nodes
     *         that contain the specified value.
     */
    public DLList<Seminar> searchExact(T value) {
        DLList<Seminar> found = new DLList<Seminar>();
        searchHelp(root, value, found);
        return found;
    }


    /**
     * A helper method that recursively searches the tree for nodes with a value
     * that matches the provided value.
     * 
     * @param node
     *            the current node in the recursion.
     * @param value
     *            the value to search for.
     * @param found
     *            the list of Seminar objects found so far that match the search
     *            value.
     */
    private void searchHelp(Node<T> node, T value, DLList<Seminar> found) {
        if (node == null) {
            return;
        }
        int c = node.getData().compareTo(value);
        if (c > 0) {
            searchHelp(node.getLeft(), value, found);
        }
        else if (c < 0) {
            searchHelp(node.getRight(), value, found);
        }
        else {
            searchHelp(node.getLeft(), value, found);
            found.add(node.getSeminar());
        }
    }


    /**
     * Searches the tree for nodes with values within a given range [lower,
     * higher].
     * 
     * @param lower
     *            the lower bound of the search range.
     * @param higher
     *            the upper bound of the search range.
     * @return a doubly linked list of Seminar objects associated with nodes
     *         that fall within the specified range.
     */
    public DLList<Seminar> searchRange(T lower, T higher) {
        DLList<Seminar> found = new DLList<Seminar>();
        nodesTraversed = 0;
        searchHelpRange(root, lower, higher, found);
        return found;
    }


    /**
     * A helper method that recursively searches the tree for nodes within a
     * specified range.
     * 
     * @param node
     *            the current node in the recursion.
     * @param lower
     *            the lower bound of the search range.
     * @param higher
     *            the upper bound of the search range.
     * @param found
     *            the list of Seminar objects found so far within the range.
     */
    private void searchHelpRange(
        Node<T> node,
        T lower,
        T higher,
        DLList<Seminar> found) {
        if (node == null) {
            nodesTraversed++;
            return;
        }
        nodesTraversed++;
        int low = node.getData().compareTo(lower);
        int high = node.getData().compareTo(higher);
        if (low >= 0 && high < 0) {
            searchHelpRange(node.getLeft(), lower, higher, found);
            found.add(node.getSeminar());
            searchHelpRange(node.getRight(), lower, higher, found);
        }
        else if (high == 0) {
            searchHelpRange(node.getLeft(), lower, higher, found);
            found.add(node.getSeminar());
        }
        else if (low < 0) {
            searchHelpRange(node.getRight(), lower, higher, found);
        }
        else if (high > 0) {
            searchHelpRange(node.getLeft(), lower, higher, found);
        }
    }


    /**
     * Inserts a new node with the specified key and associated Seminar value
     * into the tree.
     * 
     * @param key
     *            the key value of the node to be inserted.
     * @param value
     *            the Seminar object associated with the node.
     */
    public void insert(T key, Seminar value) {
        root = insertHelp(root, key, value);
        numOfRecords++;
    }


    /**
     * A helper method that recursively inserts a new node into the tree.
     * 
     * @param node
     *            the current node in the recursion.
     * @param key
     *            the key value of the node to be inserted.
     * @param value
     *            the Seminar object associated with the node.
     * @return the newly inserted node.
     */
    private Node<T> insertHelp(Node<T> node, T key, Seminar value) {
        if (node == null) {
            node = new Node<T>(key, value, null, null);
            return node;
        }
        int out = node.getData().compareTo(key);
        if (out > 0) {
            node.setLeft(insertHelp(node.getLeft(), key, value));
        }
        else if (out < 0) {
            node.setRight(insertHelp(node.getRight(), key, value));
        }
        else {
            if (node.getLeft() == null) {
                node.setLeft(insertHelp(node.getLeft(), key, value));
            }
            else {
                insertHelp(node.getLeft(), key, value);
            }
        }
        return node;
    }


    /**
     * Deletes the node with the specified key and ID from the tree.
     * 
     * @param key
     *            the key value of the node to be deleted.
     * @param id
     *            the ID of the Seminar object associated with the node to be
     *            deleted.
     */
    public void delete(T key, int id) {
        root = deleteHelp(root, key, id);
        numOfRecords--;
    }


    /**
     * A helper method that recursively deletes a node from the tree.
     * 
     * @param node
     *            the current node in the recursion.
     * @param key
     *            the key value of the node to be deleted.
     * @param id
     *            the ID of the Seminar object associated with the node to be
     *            deleted.
     * @return the node after deletion.
     */
    private Node<T> deleteHelp(Node<T> node, T key, int id) {
        if (node == null) {
            return null;
        }
        int out = node.getData().compareTo(key);
        if (out > 0) {
            node.setLeft(deleteHelp(node.getLeft(), key, id));
        }
        else if (out < 0) {
            node.setRight(deleteHelp(node.getRight(), key, id));
        }
        else {
            if (node.getSeminar().id() == id) {
                if (node.getLeft() == null) {
                    return node.getRight();
                }
                else if (node.getRight() == null) {
                    return node.getLeft();
                }
                else {
                    Node<T> max = findMax(node.getLeft());
                    node.setData(max.getData());
                    node.setSeminar(max.getSeminar());
                    node.setLeft(deleteHelp(node.getLeft(), max.getData(), max
                        .getSeminar().id()));
                }
            }
            else {
                deleteHelp(node.getRight(), key, id);
            }
        }
        return node;
    }


    /**
     * Finds the height of the tree.
     * 
     * @return the height of the tree, or 0 if the tree is empty.
     */
    public int findHeight() {
        if (this.getNumOfRecords() == 0) {
            return 0;
        }
        else {
            return findHeightHelp(root);
        }
    }


    /**
     * A helper method that recursively calculates the height of the tree.
     * 
     * @param node
     *            the current node in the recursion.
     * @return the height of the subtree rooted at the given node.
     */
    private int findHeightHelp(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int right = findHeightHelp(node.getLeft());
        int left = findHeightHelp(node.getRight());

        return Math.max(left, right) + 1;
    }


    /**
     * Prints the tree's structure and the number of records.
     * If the tree is empty, prints a message indicating that.
     */
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


    /**
     * A helper method that recursively prints the structure of the tree.
     * 
     * @param node
     *            the current node in the recursion.
     * @param h
     *            the total height of the tree.
     * @param level
     *            the current level in the tree.
     */
    private void printHelp(Node<T> node, int h, int level) {
        if (node == null) {
            for (int i = 0; i < (h - level); i++) {
                System.out.print("    ");
            }
            System.out.println("(null)");
            return;
        }

        printHelp(node.getLeft(), h, level + 1);

        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("\\");
        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("(" + node.getData() + ")");
        for (int i = 0; i < (h - level); i++) {
            System.out.print("    ");
        }
        System.out.println("/");
        printHelp(node.getRight(), h, level + 1);
    }


    /**
     * finds the max value in a subtree
     * 
     * @param node
     *            the root node
     * @return the max value/node
     */
    private Node<T> findMax(Node<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }


    /**
     * Retrieves the root node of the binary search tree.
     *
     * @return the root node of the tree
     */
    public Node<T> getRoot() {
        return root;
    }


    /**
     * Sets the root node of the binary search tree.
     *
     * @param node
     *            the node to set as the root of the tree
     */
    public void setRoot(Node<T> node) {
        root = node;
    }


    /**
     * Retrieves the number of records in the binary search tree.
     *
     * @return the number of records currently stored in the tree
     */
    public int getNumOfRecords() {
        return numOfRecords;
    }


    /**
     * Sets the number of records in the binary search tree.
     *
     * @param numOfRecords
     *            the number of records to set for the tree
     */
    public void setNumOfRecords(int numOfRecords) {
        this.numOfRecords = numOfRecords;
    }


    /**
     * Retrieves the number of nodes traversed during the last range search.
     *
     * @return the number of nodes traversed in the tree
     */
    public int getNodesTraversed() {
        return nodesTraversed;
    }
}
