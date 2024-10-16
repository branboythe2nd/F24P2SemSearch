/**
 * The LeafNode class represents a leaf node in a binary search tree. Each leaf
 * node holds
 * a list of seminars that share the same (x, y) coordinates. This class
 * implements the
 * BinNode interface, which allows for insertion and basic node management.
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public class LeafNode implements BinNode {

    private int xValue;
    private int yValue;
    private DLList<Seminar> semList;

    /**
     * Constructs a LeafNode with the specified (x, y) coordinates and
     * initializes
     * an empty list of seminars.
     *
     * @param x
     *            the x-coordinate of the leaf node
     * @param y
     *            the y-coordinate of the leaf node
     */
    public LeafNode(int x, int y) {
        xValue = x;
        yValue = y;
        semList = new DLList<Seminar>();
    }


    /**
     * Inserts a seminar into the list of seminars associated with this leaf
     * node.
     *
     * @param s
     *            the seminar to be added to the leaf node
     */
    public void insert(Seminar s) {
        boolean inserted = false;
        for (Seminar sem : semList) {
            int i = semList.lastIndexOf(sem);

            if (s.id() < sem.id()) {

                semList.add(i, s);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            semList.add(s);
        }

    }


    /**
     * Retrieves the x-coordinate of this leaf node.
     *
     * @return the x-coordinate of the node
     */
    public int getxValue() {
        return xValue;
    }


    /**
     * Sets the x-coordinate of this leaf node.
     *
     * @param xValue
     *            the new x-coordinate to be set
     */
    public void setxValue(int xValue) {
        this.xValue = xValue;
    }


    /**
     * Retrieves the y-coordinate of this leaf node.
     *
     * @return the y-coordinate of the node
     */
    public int getyValue() {
        return yValue;
    }


    /**
     * Sets the y-coordinate of this leaf node.
     *
     * @param yValue
     *            the new y-coordinate to be set
     */
    public void setyValue(int yValue) {
        this.yValue = yValue;
    }


    /**
     * Retrieves the list of seminars associated with this leaf node.
     *
     * @return the list of seminars stored in this leaf node
     */
    public DLList<Seminar> getSemList() {
        return semList;
    }


    /**
     * Sets the list of seminars associated with this leaf node.
     *
     * @param list
     *            the new list of seminars to be set
     */
    public void setSemList(DLList<Seminar> list) {
        semList = list;
    }


    /**
     * Generates a string containing the IDs of all seminars stored in this leaf
     * node.
     *
     * @param list
     *            the list of seminars to retrieve IDs from
     * @return a string containing the IDs of all seminars in the list
     */
    public String idList(DLList<Seminar> list) {
        String result = "";
        for (Seminar s : list) {
            result += " ";
            result += s.id();
        }
        return result;
    }


    /**
     * Returns a string representation of the leaf node, including the number of
     * seminars
     * stored and their IDs.
     *
     * @return a string representing the leaf node and its contents
     */
    @Override
    public String toString() {
        return "(Leaf with " + getSemList().size() + " objects:" + idList(
            getSemList()) + ")";
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
        if (sem.x() == this.getxValue() && sem.y() == this.getyValue()) {
            this.insert(sem);
            return this;
        }
        InternalNode iNode = new InternalNode(empty, empty);
        for (Seminar s : this.getSemList()) {
            iNode = (InternalNode)iNode.insert(iNode, s, x, y, worldX, worldY,
                depth, empty);
        }
        iNode = (InternalNode)iNode.insert(iNode, sem, x, y, worldX, worldY,
            depth, empty);

        return iNode;
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
        for (Seminar s : this.getSemList()) {
            if (s.id() == sem.id()) {
                this.getSemList().remove(sem);
                break;
            }
        }
        if (this.getSemList().size() == 0) {
            EmptyNode iNode = new EmptyNode();
            return iNode;
        }
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
        int xsquare = (x - this.getxValue()) * (x - this.getxValue());
        int ysquare = (y - this.getyValue()) * (y - this.getyValue());
        int rsquare = radius * radius;

        if (this.getxValue() == x && this.getyValue() == y) {
            found.add(this);
        }
        else if ((xsquare + ysquare) <= rsquare) {
            found.add(this);
        }
        return 1;

    }
}
