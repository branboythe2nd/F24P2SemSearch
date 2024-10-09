
public class BinTree {
    private BinNode root;
    private int worldSize;
    private BinNode emptyNode;
    private int numOfRecords;
    private int nodesTraversed;

    public BinTree(int size) {
        setWorldSize(size);
        emptyNode = new EmptyNode();
        setRoot(emptyNode);
        setNodesTraversed(0);
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
            LeafNode leaf = new LeafNode(sem.x(),sem.y());
            leaf.insert(sem);
            return leaf;
        }
        if(node instanceof LeafNode)
        {
            LeafNode copy = (LeafNode)node;
            if(sem.x()==copy.getxValue() && sem.y()==copy.getyValue())
            {
                copy.insert(sem);
                return copy;
            }
            InternalNode iNode = new InternalNode(emptyNode,emptyNode,0,0,worldX,worldY);
            for(Seminar s: copy.getSemList())
            {
                iNode = (InternalNode)insertHelp(iNode,s,worldX,worldY,depth);
            }
            iNode = (InternalNode)insertHelp(iNode,sem,worldX,worldY,depth);
            
            return iNode;
        }
        if(node instanceof InternalNode)
        {
            InternalNode i = (InternalNode)node;
            if (depth % 2 == 0) { 
                i.setWidth(worldX/2);
                if (sem.x() >= worldX / 2) {
                    i.setRight(insertHelp(i.getRight(), sem, worldX / 2, worldY, depth + 1));
                    i.setX(worldX/2);
                } else {
                    i.setLeft(insertHelp(i.getLeft(), sem, worldX / 2, worldY, depth + 1));
                    i.setX(0);
                }
            } else { 
                i.setHeight(worldY/2);
                if (sem.y() >= worldY / 2) {
                    i.setRight(insertHelp(i.getRight(), sem, worldX, worldY / 2, depth + 1));
                    i.setY(worldY/2);
                } else {
                    i.setLeft(insertHelp(i.getLeft(), sem, worldX, worldY / 2, depth + 1));
                    i.setX(0);
                }
            }
            
            return i;
            
        }
        return node;

    }

    public DLList<LeafNode> search(int x, int y, int radius) {
        if (root instanceof EmptyNode) {
            return null;
        }
        DLList<LeafNode> found = new DLList<LeafNode>();
        setNodesTraversed(0);
        searchHelp(found, root, x, y, radius); 
        return found;
    }

    public void searchHelp(DLList<LeafNode> found, BinNode root, int x, int y, int radius) {
        if (root instanceof EmptyNode) {
            nodesTraversed++;
            return;
        }

        if (root instanceof LeafNode) {
            LeafNode copy = (LeafNode) root;

            int xsquare = (x - copy.getxValue()) * (x - copy.getxValue());
            int ysquare = (y - copy.getyValue()) * (y - copy.getyValue());
            int rsquare = radius * radius;

            if ((xsquare + ysquare) <= rsquare) {
                found.add(copy);  
            }
            nodesTraversed++;  
            return;
        }

        if (root instanceof InternalNode) {
            InternalNode i = (InternalNode) root;
            
            if(withinBox(i,x,y,radius))
            {
                nodesTraversed++;
                searchHelp(found, i.getLeft(), x, y, radius);
                searchHelp(found, i.getRight(), x, y, radius);                
            }
            
            
        }
    }
    private boolean withinBox(InternalNode n, int x, int y, int radius)
    {
        int xBox = x- radius;
        int yBox = y-radius;
        int height = 2*radius +1;
        int width = 2*radius + 1;        
        int nX = n.getX();
        int nY = n.getY();
        int nH = n.getHeight();
        int nW = n.getWidth();
        
        if(xBox + width < nX || nX+nW < xBox)
        {   
            return false;
        }
        if(yBox +height <nY || nY+nH<yBox)
        {
            return false;
        }
        
        return true;
        
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
     * @param node the current node in the recursion.
     * @param h the total height of the tree.
     * @param level the current level in the tree.
     */
    private void printHelp(BinNode node, int h, int level) {
        // Handle EmptyNode
        if (node == null || node instanceof EmptyNode) {
            // Print EmptyNode (E)
            for (int i = 0; i < (level); i++) {
                System.out.print("    "); // Indentation based on level
            }
            System.out.println("(E)");
            return;
        }

        // Handle InternalNode (I)
        if (node instanceof InternalNode) {
            for (int i = 0; i < (level); i++) {
                System.out.print("    "); // Indentation based on level
            }
            System.out.println("(I)");

            // Recursively print the left and right children
            printHelp(((InternalNode) node).getRight(), h, level + 1);
            printHelp(((InternalNode) node).getLeft(), h, level + 1);
            
            return;
        }

        // Handle LeafNode
        if (node instanceof LeafNode) {
            for (int i = 0; i < (level); i++) {
                System.out.print("    "); // Indentation based on level
            }
            System.out.println(((LeafNode)node).toString());
            return;
        }
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


    public int getNodesTraversed() {
        return nodesTraversed;
    }


    public void setNodesTraversed(int nodesTraversed) {
        this.nodesTraversed = nodesTraversed;
    }
}
