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
     * Inserts a seminar into this leaf node (unused, returns null).
     *
     * @param sem
     *            the seminar to be inserted
     * @param x
     *            the x-coordinate of the seminar
     * @param y
     *            the y-coordinate of the seminar
     * @return null (this method is not implemented for LeafNode)
     */
    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        return null; // Not used in LeafNode
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
}
