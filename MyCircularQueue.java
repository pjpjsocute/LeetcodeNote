public class MyCircularQueue {

    private int[] myCircularQueue;

    int           size;

    int           root;

    int           last;

    int           capacity;

    public MyCircularQueue(int k) {
        this.myCircularQueue = new int[k];
        this.size = 0;
        this.root = 0;
        this.last = -1;
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        int inx = (last + 1) % capacity;
        myCircularQueue[inx] = value;
        last++;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        root++;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : myCircularQueue[root%capacity];
    }

    public int Rear() {
        return isEmpty() ? -1 : myCircularQueue[last%capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == myCircularQueue.length;
    }
}
