
public class BinTree {
    private BinNode root;
    private int worldSize;
    private BinNode emptyNode;
    private DLList<Seminar> allSeminars;
    
    public BinTree(int size) {
        setWorldSize(size);
        emptyNode = new LeafNode();
        setRoot(emptyNode);
    }
    
    public void insert(int x, int y, Seminar sem) {
        root = insertHelp(root, sem, worldSize, worldSize);
    }
    
    private BinNode insertHelp(BinNode node, Seminar sem, int worldX, int worldY) {
        if (node instanceof LeafNode && ((LeafNode)node).getSemList() == null) {
            node = new LeafNode(sem.x(), sem.y());
            ((LeafNode)node).insert(sem);
            return node;
        }
        if (node instanceof LeafNode) {
            LeafNode leaf = ((LeafNode)node);
            node = createInternal(leaf, worldX/2, worldY/2);    
        }
        return node;
        
        
    }
    
    private InternalNode createInternal(LeafNode leaf, int worldX, int worldY) {
        if (worldX != worldY) {
            if (leaf.getxValue() >= worldX) {
                return new InternalNode(null, leaf);
            }
            else {
                return new InternalNode(leaf, null);
            }
        }
        else {
            if (leaf.getyValue() >= worldY) {
                return new InternalNode(null, leaf);
            }
            else {
                return new InternalNode(leaf, null);
            }
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
}
