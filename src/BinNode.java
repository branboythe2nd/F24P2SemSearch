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
     * @param sem
     *            seminar to be added
     * @param x
     *            seminar x value
     * @param y
     *            seminar y value
     * @return
     *         BinNode that is root
     */
    public BinNode insert(Seminar sem, int x, int y);
}
