public class LinkedList<T> {
    private class Node<T> {
        T       value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 长度
     */
    Integer N;

    /**
     * 根节点
     */
    Node    root;

    /**
     * 初始化根节点
     */
    public LinkedList() {
        this.root = new Node(null);
        N = 0;
    }

    /**
     * 插入
     * 
     * @param value
     */
    public void insert(T value) {
        // 新建结点
        Node<T> tNode = new Node<>(value);
        // 插入结点
        Node next = root.next;
        root.next = tNode;
        tNode.next = next;
        // 长度+1
        N++;
    }

    /**
     * 删除
     */
    public void remove() {
        if (N == 0) {
            return;
        }
        // 删除
        Node next = root.next;
        Node next1 = next.next;
        root.next = next1;
        // 长度-1
        N--;
    }

    public void printList() {
        Node curr = root.next;
        for (Integer i = 0; i < N; i++) {
            System.out.println(curr.value);
            curr = curr.next;
        }
        System.out.println("\n");
    }
}
