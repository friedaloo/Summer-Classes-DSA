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
    public String display(){
        StringBuilder sb=new StringBuilder();
        sb.append("<html>");
        sb.append("<h1>Waiting</h1>");
        Node<T> temp=front;
        while(temp!=null){
            sb.append(temp.data.toString());
            temp=temp.next;
        }
    sb.append("</html>");
    return sb.toString();

    }

}
