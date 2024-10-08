
public class LeafNode implements BinNode{
    
    private int xValue;
    private int yValue;
    private DLList<Seminar> semList;
    
    public LeafNode(int x, int y) {
        xValue = x;
        yValue = y;
        semList = new DLList<Seminar>();
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public DLList<Seminar> getSemList() {
        return semList;
    }

    public void setSemList(DLList<Seminar> semList) {
        this.semList = semList;
    }
    @Override
    public BinNode insert(Seminar sem, int x, int y) {
        return new InternalNode(null, null);
    }

}
