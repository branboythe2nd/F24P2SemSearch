/**
 * Represents a node in a binary search tree (BST) that holds generic data
 * and a reference to a seminar object, as well as pointers to left and right
 * child nodes.
 * 
 * @author Brantson and Adarsh
 * @version 10/03/2024
 *
 * @param <T>
 *            the type of data stored in this node, must be Comparable
 */
public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Seminar seminar;

    /**
     * Constructs a new Node with the specified data, seminar, left, and right
     * child nodes.
     *
     * @param data
     *            the data to store in the node
     * @param seminar
     *            the seminar associated with the node
     * @param left
     *            the left child node
     * @param right
     *            the right child node
     */
    public Node(T data, Seminar seminar, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.seminar = seminar;
    }


    /**
     * Sets the left child of this node.
     *
     * @param node
     *            the node to set as the left child
     */
    public void setLeft(Node<T> node) {
        this.left = node;
    }


    /**
     * Retrieves the left child of this node.
     *
     * @return the left child node
     */
    public Node<T> getLeft() {
        return left;
    }


    /**
     * Sets the right child of this node.
     *
     * @param node
     *            the node to set as the right child
     */
    public void setRight(Node<T> node) {
        this.right = node;
    }


    /**
     * Retrieves the right child of this node.
     *
     * @return the right child node
     */
    public Node<T> getRight() {
        return right;
    }


    /**
     * Sets the data stored in this node.
     *
     * @param data
     *            the data to set in the node
     */
    public void setData(T data) {
        this.data = data;
    }


    /**
     * Retrieves the data stored in this node.
     *
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }


    /**
     * Retrieves the seminar associated with this node.
     *
     * @return the seminar object stored in this node
     */
    public Seminar getSeminar() {
        return seminar;
    }


    /**
     * Sets the seminar associated with this node.
     *
     * @param seminar
     *            the seminar to associate with this node
     */
    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }
}
