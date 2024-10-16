/**
 * The BinTree class implements a 2-dimensional binary search tree with the
 * ability to insert, search,
 * and manage seminars by their (x, y) coordinates. The tree supports nodes of
 * three types:
 * EmptyNode, LeafNode, and InternalNode. It maintains a dynamic structure and
 * allows searching
 * for nodes within a specified radius.
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public class BinTree {
    private BinNode root;
    private int worldSize;
    private BinNode emptyNode;
    private int numOfRecords;
    private int nodesTraversed;

    /**
     * Constructs an empty BinTree with the specified world size. The root is
     * initialized as an EmptyNode.
     *
     * @param size
     *            the size of the world in which the tree operates
     */
    public BinTree(int size) {
        setWorldSize(size);
        emptyNode = new EmptyNode();
        setRoot(emptyNode);
        setNodesTraversed(0);
    }


    /**
     * Inserts a seminar into the binary search tree based on its (x, y)
     * coordinates.
     * Each insertion increases the record count.
     *
     * @param x
     *            the x-coordinate of the seminar
     * @param y
     *            the y-coordinate of the seminar
     * @param sem
     *            the seminar to be inserted into the tree
     */
    public void insert(int x, int y, Seminar sem) {
        root = insertHelp(root, sem, 0, 0, worldSize, worldSize, 0);
        numOfRecords++;
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
     * @return the updated node after insertion
     */
    private BinNode insertHelp(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth) {

        if (node instanceof InternalNode) {
            InternalNode i = (InternalNode)node;
            return i.insert(node, sem, x, y, worldX, worldY, depth, emptyNode);

        }
        if (node instanceof LeafNode) {
            LeafNode copy = (LeafNode)node;
            return copy.insert(node, sem, x, y, worldX, worldY, depth,
                emptyNode);
        }
        else {
            EmptyNode empty = (EmptyNode)node;
            return empty.insert(node, sem, x, y, worldX, worldY, depth,
                emptyNode);
        }

    }


    /**
     * Delete function for BinTree
     * 
     * @param sem
     *            seminar to be removed
     */
    public void delete(Seminar sem) {
        root = deleteHelp(root, sem, 0, 0, worldSize, worldSize, 0);
        numOfRecords--;
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
    private BinNode deleteHelp(
        BinNode node,
        Seminar sem,
        int x,
        int y,
        int worldX,
        int worldY,
        int depth) {
        if (node instanceof InternalNode) {
            InternalNode i = (InternalNode)node;
            return i.delete(node, sem, x, y, worldX, worldY, depth);

        }
        if (node instanceof LeafNode) {
            LeafNode copy = (LeafNode)node;
            return copy.delete(node, sem, x, y, worldX, worldY, depth);

        }
        else {
            EmptyNode empty = (EmptyNode)node;
            return empty.delete(node, sem, x, y, worldX, worldY, depth);
        }
    }


    /**
     * Searches for seminars within a specified radius of the given (x, y)
     * coordinates.
     *
     * @param x
     *            the x-coordinate to search from
     * @param y
     *            the y-coordinate to search from
     * @param radius
     *            the radius within which to search
     * @return a list of leaf nodes found within the search radius, or null if
     *         the tree is empty
     */
    public DLList<LeafNode> search(int x, int y, int radius) {
        if (root instanceof EmptyNode) {
            return null;
        }
        DLList<LeafNode> found = new DLList<LeafNode>();
        setNodesTraversed(0);
        nodesTraversed = searchHelp(found, root, x, y, radius, 0, worldSize,
            worldSize, 0, 0);
        return found;
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
     * @return nodes traversed
     */
    public int searchHelp(
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
        if (node instanceof EmptyNode) {
            EmptyNode empty = (EmptyNode)node;
            return empty.search(found, node, x, y, radius, depth, worldX,
                worldY, posX, posY);

        }

        if (node instanceof LeafNode) {
            LeafNode copy = (LeafNode)node;
            return copy.search(found, node, x, y, radius, depth, worldX, worldY,
                posX, posY);

        }

        else {
            InternalNode i = (InternalNode)node;
            return i.search(found, node, x, y, radius, depth, worldX, worldY,
                posX, posY);

        }

    }


    /**
     * Finds the height of the tree.
     * 
     * @return the height of the tree, or 0 if the tree is empty.
     */
    public int findHeight() {
        return findHeightHelp(root);

    }


    /**
     * A helper method that recursively calculates the height of the tree.
     * 
     * @param node
     *            the current node in the recursion.
     * @return the height of the subtree rooted at the given node.
     */
    private int findHeightHelp(BinNode node) {
        if (node instanceof LeafNode || node instanceof EmptyNode) {
            return 0;
        }
        int right = findHeightHelp(((InternalNode)node).getLeft());
        int left = findHeightHelp(((InternalNode)node).getRight());

        return Math.max(left, right) + 1;
    }


    /**
     * Prints the tree's structure and the number of records.
     * If the tree is empty, prints a message indicating that.
     */
    public void print() {
        int height = this.findHeight();
        printHelp(root, height, 0);

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
    private void printHelp(BinNode node, int h, int level) {

        if (node instanceof EmptyNode) {

            for (int i = 0; i < (level); i++) {
                System.out.print("    ");
            }
            System.out.println("(E)");
            return;
        }

        if (node instanceof InternalNode) {
            for (int i = 0; i < (level); i++) {
                System.out.print("    ");
            }
            System.out.println("(I)");

            printHelp(((InternalNode)node).getRight(), h, level + 1);
            printHelp(((InternalNode)node).getLeft(), h, level + 1);

            return;
        }

        else {
            for (int i = 0; i < (level); i++) {
                System.out.print("    ");
            }
            System.out.println(((LeafNode)node).toString());
            return;
        }
    }


    /**
     * Retrieves the size of the world in which the binary tree operates.
     *
     * @return the world size
     */
    public int getWorldSize() {
        return worldSize;
    }


    /**
     * Sets the world size for the binary tree.
     *
     * @param worldSize
     *            the size of the world to set
     */
    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }


    /**
     * Sets the root of the binary tree to the specified node.
     *
     * @param node
     *            the new root node
     */
    public void setRoot(BinNode node) {
        root = node;
    }


    /**
     * Retrieves the root node of the binary tree.
     *
     * @return the root node
     */
    public BinNode getRoot() {
        return root;
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
     * Retrieves the number of nodes traversed during the last search operation.
     *
     * @return the number of nodes traversed
     */
    public int getNodesTraversed() {
        return nodesTraversed;
    }


    /**
     * Sets the number of nodes traversed during the last search operation.
     *
     * @param nodesTraversed
     *            the number of nodes traversed
     */
    public void setNodesTraversed(int nodesTraversed) {
        this.nodesTraversed = nodesTraversed;
    }
}
