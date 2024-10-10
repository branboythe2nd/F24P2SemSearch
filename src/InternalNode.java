/**
 * The InternalNode class represents a non-leaf node in a binary tree. It holds
 * references
 * to two child nodes, the left and right children. This class is part of a
 * binary tree
 * structure used to manage seminars.
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public class InternalNode implements BinNode {

    private BinNode left;
    private BinNode right;

    /**
     * Constructs an InternalNode with the specified left and right child nodes.
     *
     * @param leftNode
     *            the left child node
     * @param rightNode
     *            the right child node
     */
    public InternalNode(BinNode leftNode, BinNode rightNode) {
        setLeft(leftNode);
        setRight(rightNode);
    }


    /**
     * Retrieves the left child of this internal node.
     *
     * @return the left child node
     */
    public BinNode getLeft() {
        return left;
    }


    /**
     * Sets the left child of this internal node.
     *
     * @param left
     *            the new left child node
     */
    public void setLeft(BinNode left) {
        this.left = left;
    }


    /**
     * Retrieves the right child of this internal node.
     *
     * @return the right child node
     */
    public BinNode getRight() {
        return right;
    }


    /**
     * Sets the right child of this internal node.
     *
     * @param right
     *            the new right child node
     */
    public void setRight(BinNode right) {
        this.right = right;
    }


    /**
     * Inserts a seminar into the tree. This method is not yet implemented in
     * the InternalNode
     * and will return null.
     *
     * @param sem
     *            the seminar to be inserted
     * @param x
     *            the x-coordinate of the seminar
     * @param y
     *            the y-coordinate of the seminar
     * @return null as the insertion is not implemented
     */
    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        return null;
    }
}
