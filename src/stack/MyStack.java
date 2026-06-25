package stack;

@SuppressWarnings("unchecked")
public class MyStack<T> {
    private Object[] data;
    private int top;

    public MyStack() {
        data = new Object[16];
        top = -1;
    }

    public void push(T element) {
        if (top == data.length - 1) {
            grow();
        }
        data[++top] = element;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void grow() {
        Object[] newArray = new Object[data.length * 2];
        System.arraycopy(data, 0, newArray, 0, data.length);
        data = newArray;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T element = (T) data[top];
        data[top--] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) data[top];
    }
    public void clear()
        {
            data = new Object[16];
            top = -1;
        }
}