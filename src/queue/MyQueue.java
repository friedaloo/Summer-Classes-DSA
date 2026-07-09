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
        rear.next=node;
        rear=node;
    }

    public T dequeue(){
        if(front==null){
            return null;
        }
        T data=front.data;
        front=front.next;

        if(front==null){ rear = null;}
        return data;
    }
}
