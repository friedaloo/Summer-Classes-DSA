package queue;

public class MyQueue<T>{
    Node<T> front;
    Node<T> rear;

    public void enqueue(T data) {
        Node<T> node=new Node<>(data);
        if(rear==null){
            rear=front=node;
            return;
        }
    }
}
