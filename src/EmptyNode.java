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
     * Helper method for inserting a seminar into the tree recursively. This
     * method splits
     * the world space and decides where to place the node based on the depth
     * and (x, y) values.
     *
     * @param node
     *            the current node being processed
     * @param sem
     *            the seminar to be inserted
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param worldX
     *            the size of the world in the x-direction
     * @param worldY
     *            the size of the world in the y-direction
     * @param depth
     *            the current depth in the tree
     * @param empty
     *            Empty Node
     * @return the updated node after insertion
     */
    @Override
    public BinNode insert(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth,
        BinNode empty) {
        LeafNode leaf = new LeafNode(sem.x(), sem.y());
        leaf.insert(sem);
        return leaf;
    }


    /**
     * helper method for delete
     * 
     * @param node
     *            root node
     * @param sem
     *            seminar to be removed
     * @param x
     *            starting x position
     * @param y
     *            starting y position
     * @param worldX
     *            world size x
     * @param worldY
     *            wold size y
     * @param depth
     *            depth level
     * @return
     */
    @Override
    public BinNode delete(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth) {
        return this;
    }


    /**
     * Helper method for the search function, which traverses the tree
     * recursively to find nodes
     * within the search radius.
     *
     * @param found
     *            the list of nodes found within the radius
     * @param node
     *            the current node being processed
     * @param x
     *            the x-coordinate to search from
     * @param y
     *            the y-coordinate to search from
     * @param radius
     *            the radius within which to search
     * @param depth
     *            the current depth in the tree
     * @param worldX
     *            the size of the world in the x-direction
     * @param worldY
     *            the size of the world in the y-direction
     * @param posX
     *            comparable x value
     * @param posY
     *            comparable y value
     * @return nodes traveled
     */
    @Override
    public int search(
        DLList<LeafNode> found,
        BinNode node,
        int x,
        int y,
        int radius,
        int depth,
        int worldX,
        int worldY,
        int posX,
        int posY) {
        return 1;

    }
}
