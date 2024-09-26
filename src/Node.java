
public class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;;
        this.left = left;
        this.right = right;
    }


    public void setLeft(Node<T> node) {
        this.left = node;
    }


    public Node<T> getLeft() {
        return left;
    }


    public void setRight(Node<T> node) {
        this.right = node;
    }


    public Node<T> getRight() {
        return right;
    }


    public void setData(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }
}
