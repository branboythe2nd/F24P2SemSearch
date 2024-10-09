
public class InternalNode implements BinNode{
    
    private BinNode left;
    private BinNode right;
    
    public InternalNode(BinNode leftNode, BinNode rightNode) {
        setLeft(leftNode);
        setRight(rightNode);           
    }
    public BinNode getLeft() {
        return left;
    }
    public void setLeft(BinNode left) {
        this.left = left;
    }
    public BinNode getRight() {
        return right;
    }
    public void setRight(BinNode right) {
        this.right = right;
    }
    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        return null;
    }
    
}