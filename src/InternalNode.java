
public class InternalNode implements BinNode{
    
    private BinNode left;
    private BinNode right;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public InternalNode(BinNode leftNode, BinNode rightNode,int x,int y,int width, int height) {
        setLeft(leftNode);
        setRight(rightNode);
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);          
        
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
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
}