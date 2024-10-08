
public class EmptyNode implements BinNode {

    public EmptyNode() {
        
    }


    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        LeafNode leaf = new LeafNode(sem.x(), sem.y());
        leaf.getSemList().add(sem);
        return leaf;
    }
}
