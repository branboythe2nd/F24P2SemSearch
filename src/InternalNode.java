
public class InternalNode implements BinNode{
    
    private LeafNode left;
    private LeafNode right;
    
    public InternalNode(LeafNode leftNode, LeafNode rightNode) {
        setLeft(leftNode);
        setRight(rightNode);
    }
    public LeafNode getLeft() {
        return left;
    }
    public void setLeft(LeafNode left) {
        this.left = left;
    }
    public LeafNode getRight() {
        return right;
    }
    public void setRight(LeafNode right) {
        this.right = right;
    }
    @Override
    public boolean isLeaf() {
        // TODO Auto-generated method stub
        return false;
    }

}
