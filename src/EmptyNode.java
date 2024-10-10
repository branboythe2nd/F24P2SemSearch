/**
 * The EmptyNode class represents an empty node in the binary tree.
 * This class implements the BinNode interface and serves as a placeholder
 * for leaf and internal nodes that are yet to be populated.
 * When an insertion is made on an EmptyNode, it transitions into a LeafNode.
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public class EmptyNode implements BinNode {

    /**
     * Constructs an EmptyNode. This constructor does not require any parameters
     * as it simply initializes the empty node.
     */
    public EmptyNode() {

    }


    /**
     * Inserts a seminar into the empty node. Upon insertion, this EmptyNode
     * transitions into a LeafNode containing the seminar at the given (x, y)
     * coordinates.
     *
     * @param sem
     *            the seminar to be inserted
     * @param x
     *            the x-coordinate of the seminar
     * @param y
     *            the y-coordinate of the seminar
     * @return a new LeafNode containing the inserted seminar
     */
    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        LeafNode leaf = new LeafNode(sem.x(), sem.y());
        leaf.getSemList().add(sem);
        return leaf;
    }
}
