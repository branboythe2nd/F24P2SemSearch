/**
 * BinNode interface
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public interface BinNode {
    /**
     * Insert method for each unique node
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
     * @return
     *         BinNode that is root
     */
    public BinNode insert(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth,
        BinNode empty);


    /**
     * delete method for unique nodes
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
     * @return BinNode that is root
     */
    public BinNode delete(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth);


    /**
     * search method for unique nodes
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
     * @return BinNode that is root
     */
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
        int posY);
}
