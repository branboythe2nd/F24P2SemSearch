
public class LeafNode implements BinNode{
    
    private int xValue;
    private int yValue;
    private DLList<Seminar> semList;
    
    public LeafNode() {
        xValue = -1;
        yValue = -1;
        semList = null;
    }
    
    public LeafNode(int x, int y) {
        xValue = x;
        yValue = y;
        semList = new DLList<Seminar>();
    }

    public void insert(Seminar seminar) {
        semList.add(seminar);
    }
    
    public void delete() {
        // TODO Auto-generated method stub
        
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
    public boolean isLeaf() {
        return true;
    }

}
