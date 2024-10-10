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
        if (node instanceof EmptyNode) {
            LeafNode leaf = new LeafNode(sem.x(), sem.y());
            leaf.insert(sem);
            return leaf;
        }
        if (node instanceof LeafNode) {
            LeafNode copy = (LeafNode)node;
            if (sem.x() == copy.getxValue() && sem.y() == copy.getyValue()) {
                copy.insert(sem);
                return copy;
            }
            InternalNode iNode = new InternalNode(emptyNode, emptyNode);
            for (Seminar s : copy.getSemList()) {
                iNode = (InternalNode)insertHelp(iNode, s, x, y, worldX, worldY,
                    depth);
            }
            iNode = (InternalNode)insertHelp(iNode, sem, x, y, worldX, worldY,
                depth);

            return iNode;
        }
        if (node instanceof InternalNode) {
            InternalNode i = (InternalNode)node;
            if (depth % 2 == 0) {
                if (sem.x() >= x + worldX / 2) {
                    i.setRight(insertHelp(i.getRight(), sem, x + worldX / 2, y,
                        worldX / 2, worldY, depth + 1));
                }
                else {
                    i.setLeft(insertHelp(i.getLeft(), sem, x, y, worldX / 2,
                        worldY, depth + 1));
                }
            }
            else {
                if (sem.y() >= y + worldY / 2) {
                    i.setRight(insertHelp(i.getRight(), sem, x, y + worldY / 2,
                        worldX, worldY / 2, depth + 1));
                }
                else {
                    i.setLeft(insertHelp(i.getLeft(), sem, x, y, worldX, worldY
                        / 2, depth + 1));
                }
            }

            return i;

        }
        return node;

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
        searchHelp(found, root, x, y, radius, 0, worldSize / 2, worldSize / 2);
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
     */
    public void searchHelp(
        DLList<LeafNode> found,
        BinNode node,
        int x,
        int y,
        int radius,
        int depth,
        int worldX,
        int worldY) {
        if (node instanceof EmptyNode) {
            nodesTraversed++;
            return;
        }

        if (node instanceof LeafNode) {
            LeafNode copy = (LeafNode)node;

            int xsquare = (x - copy.getxValue()) * (x - copy.getxValue());
            int ysquare = (y - copy.getyValue()) * (y - copy.getyValue());
            int rsquare = radius * radius;

            if ((xsquare + ysquare) <= rsquare) {
                found.add(copy);
            }
            nodesTraversed++;
            return;
        }

        if (node instanceof InternalNode) {
            InternalNode i = (InternalNode)node;
            nodesTraversed++;

            if (depth % 2 == 0) {
                if ((x - radius) <= worldX) {
                    searchHelp(found, i.getLeft(), x, y, radius, depth + 1,
                        worldX - worldX / 2, worldY);
                }
                if ((x + radius > worldX)) {
                    searchHelp(found, i.getRight(), x, y, radius, depth + 1,
                        worldX + worldX / 2, worldY);
                }
            }
            else {
                if ((y - radius) <= worldY) {
                    searchHelp(found, i.getLeft(), x, y, radius, depth + 1,
                        worldX, worldY - worldY / 2);
                }
                if ((y + radius) > worldY) {
                    searchHelp(found, i.getRight(), x, y, radius, depth + 1,
                        worldX, worldY + worldY / 2);
                }
            }

        }

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

        if (node == null || node instanceof EmptyNode) {

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

        if (node instanceof LeafNode) {
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
