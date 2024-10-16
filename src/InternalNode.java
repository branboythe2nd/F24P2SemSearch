/**
 * The InternalNode class represents a non-leaf node in a binary tree. It holds
 * references
 * to two child nodes, the left and right children. This class is part of a
 * binary tree
 * structure used to manage seminars.
 * 
 * @author Brantson and Adarsh
 * @version 10/10/2024
 */
public class InternalNode implements BinNode {

    private BinNode left;
    private BinNode right;

    /**
     * Constructs an InternalNode with the specified left and right child nodes.
     *
     * @param leftNode
     *            the left child node
     * @param rightNode
     *            the right child node
     */
    public InternalNode(BinNode leftNode, BinNode rightNode) {
        setLeft(leftNode);
        setRight(rightNode);
    }


    /**
     * Retrieves the left child of this internal node.
     *
     * @return the left child node
     */
    public BinNode getLeft() {
        return left;
    }


    /**
     * Sets the left child of this internal node.
     *
     * @param left
     *            the new left child node
     */
    public void setLeft(BinNode left) {
        this.left = left;
    }


    /**
     * Retrieves the right child of this internal node.
     *
     * @return the right child node
     */
    public BinNode getRight() {
        return right;
    }


    /**
     * Sets the right child of this internal node.
     *
     * @param right
     *            the new right child node
     */
    public void setRight(BinNode right) {
        this.right = right;
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
        if (depth % 2 == 0) {
            if (sem.x() >= x + worldX / 2) {
                this.setRight(this.getRight().insert(this.getRight(), sem, x
                    + worldX / 2, y, worldX / 2, worldY, depth + 1, empty));
            }
            else {
                this.setLeft(this.getLeft().insert(this.getLeft(), sem, x, y,
                    worldX / 2, worldY, depth + 1, empty));
            }
        }
        else {
            if (sem.y() >= y + worldY / 2) {
                this.setRight(this.getRight().insert(this.getRight(), sem, x, y
                    + worldY / 2, worldX, worldY / 2, depth + 1, empty));
            }
            else {
                this.setLeft(this.getLeft().insert(this.getLeft(), sem, x, y,
                    worldX, worldY / 2, depth + 1, empty));
            }
        }
        return this;
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
        if (depth % 2 == 0) {
            if (sem.x() >= x + worldX / 2) {
                this.setRight(this.getRight().delete(this.getRight(), sem, x
                    + worldX / 2, y, worldX / 2, worldY, depth + 1));
            }
            else {
                this.setLeft(this.getLeft().delete(this.getLeft(), sem, x, y,
                    worldX / 2, worldY, depth + 1));
            }
        }
        else {
            if (sem.y() >= y + worldY / 2) {
                this.setRight(this.getRight().delete(this.getRight(), sem, x, y
                    + worldY / 2, worldX, worldY / 2, depth + 1));
            }
            else {
                this.setLeft(this.getLeft().delete(this.getLeft(), sem, x, y,
                    worldX, worldY / 2, depth + 1));
            }
        }
        if (this.getLeft() instanceof EmptyNode && this
            .getRight() instanceof EmptyNode) {
            return new EmptyNode();
        }
        else if (this.getLeft() instanceof LeafNode && this
            .getRight() instanceof EmptyNode) {
            return this.getLeft();
        }
        else if (this.getRight() instanceof LeafNode && this
            .getLeft() instanceof EmptyNode) {
            return this.getRight();
        }
        else {
            return this;
        }
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
        int sum = 1;
        if (depth % 2 == 0) {
            if ((x - radius) < posX + worldX / 2) {
                sum = sum + this.getLeft().search(found, this.getLeft(), x, y,
                    radius, depth + 1, worldX / 2, worldY, posX, posY);
            }
            if ((x + radius >= posX + worldX / 2)) {
                sum = sum + this.getRight().search(found, this.getRight(), x, y,
                    radius, depth + 1, worldX / 2, worldY, posX + worldX / 2,
                    posY);
            }
        }
        else {
            if ((y - radius) < posY + worldY / 2) {
                sum = sum + this.getLeft().search(found, this.getLeft(), x, y,
                    radius, depth + 1, worldX, worldY / 2, posX, posY);
            }
            if ((y + radius) >= posY + worldY / 2) {
                sum = sum + this.getRight().search(found, this.getRight(), x, y,
                    radius, depth + 1, worldX, worldY / 2, posX, posY + worldY
                        / 2);
            }
        }
        return sum;

    }
}
