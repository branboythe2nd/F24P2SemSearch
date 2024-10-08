
public class BinTree {
    private BinNode root;
    private int worldSize;
    private BinNode emptyNode;
    private int numOfRecords;

    public BinTree(int size) {
        setWorldSize(size);
        emptyNode = new EmptyNode();
        setRoot(emptyNode);
    }


    public void insert(int x, int y, Seminar sem) {
        root = insertHelp(root, sem, worldSize, worldSize, 0);
        numOfRecords++;
    }


    private BinNode insertHelp(
        BinNode node,
        Seminar sem,
        int worldX,
        int worldY,
        int depth) {
        if (node instanceof EmptyNode) {
            return ((EmptyNode)node).insert(sem, worldX, worldY);
        }
        if (node instanceof LeafNode) {
            LeafNode temp = (LeafNode)node;
            if (sem.x() == temp.getxValue() && sem.y() == temp.getyValue()) {
                ((LeafNode)node).getSemList().add(sem);
                return node;
            }
            node = ((LeafNode)node).insert(null, worldX, worldY);
            ((InternalNode)node).setLeft(emptyNode);
            ((InternalNode)node).setRight(emptyNode);
            for (Seminar s : temp.getSemList()) {
                if (depth % 2 == 0) {
                    insertHelp(node, s, worldX / 2, worldY, depth + 1);
                }
                else {
                    insertHelp(node, s, worldX, worldY / 2, depth + 1);
                }
            }
        }
        if (node instanceof InternalNode) {
            if (depth % 2 == 0) {
                if (sem.x() >= worldX) {
                    ((InternalNode)node).setRight(insertHelp(
                        ((InternalNode)node).getRight(), sem, worldX / 2,
                        worldY, depth + 1));
                }
                else {
                    ((InternalNode)node).setLeft(insertHelp(((InternalNode)node)
                        .getLeft(), sem, worldX / 2, worldY, depth + 1));
                }
            }
            else {
                if (sem.y() >= worldY) {
                    ((InternalNode)node).setRight(insertHelp(
                        ((InternalNode)node).getRight(), sem, worldX, worldY
                            / 2, depth + 1));
                }
                else {
                    ((InternalNode)node).setLeft(insertHelp(((InternalNode)node)
                        .getLeft(), sem, worldX, worldY / 2, depth + 1));
                }
            }
        }
        return node;

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
        if (numOfRecords == 0) {
            System.out.println("This tree is Empty");
        }
        else {
            int height = this.findHeight();
            printHelp(root, height, 0);
        }
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
            for (int i = 0; i < (h - level); i++) {
                System.out.print("    ");
            }
            System.out.println("(E)");
            return;
        }
        if (node instanceof InternalNode) {
            for (int i = 0; i < (h - level); i++) {
                System.out.print("    ");
            }
            System.out.println("(I)");
            return;
        }
        if (node instanceof LeafNode) {
            for (int i = 0; i < (h - level); i++) {
                System.out.print("    ");
            }
            System.out.println("(Leaf with " + ((LeafNode)node).getSemList()
                .size() + " objects: " + ((LeafNode)node).getSemList()
                    .toString());
            return;
        }
        printHelp(((InternalNode)node).getRight(), h, level + 1);
        printHelp(((InternalNode)node).getLeft(), h, level + 1);
    }


    public void search() {

    }


    public int getWorldSize() {
        return worldSize;
    }


    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }


    public void setRoot(BinNode node) {
        root = node;
    }


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
}
