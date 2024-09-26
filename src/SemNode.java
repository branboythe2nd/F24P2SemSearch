
public class SemNode<T> {
    private Seminar seminar;
    private T value;
    
    public SemNode(T data, Seminar seminar)
    {
        value = data;
        this.seminar = seminar;
    }
    
    public void setValue(T data) {
        this.value = data;
    }


    public T getValue() {
        return value;
    }


    public Seminar getSeminar() {
        return seminar;
    }


    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }
}
