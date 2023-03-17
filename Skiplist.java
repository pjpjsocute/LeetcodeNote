import java.util.Arrays;
import java.util.Random;

public class Skiplist {
    private class Node {
        Integer value;
        Node[]  next;

        public Node(Integer value, Integer maxLevel) {
            this.value = value;
            this.next = new Node[maxLevel];
        }

        public Node getNodeByNextLevel(Integer level) {
            return this.next[level];
        }

        public void setNodeByNextLevel(Integer level, Node nextNode) {
            this.next[level] = nextNode;
        }
    }

    private Node                 root;

    private static final Float   LEVEL_TO_GENERATE_PROBABILITY = 0.5F;

    private Integer              level;

    private static final Integer MAX_LEVEL                     = 32;

    private final Random         random;

    public Skiplist() {
        this.root = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        this.level = 0;
        this.random = new Random();
    }

    public boolean search(int target) {
        Node currNode = root;
        for (Integer i = this.level - 1; i >= 0; i--) {
            for (; currNode.getNodeByNextLevel(i) != null && currNode.getNodeByNextLevel(i).value < target;) {
                currNode = currNode.getNodeByNextLevel(i);
            }
        }
        currNode = currNode.getNodeByNextLevel(0);

        if (currNode != null && currNode.value == target) {
            return true;
        }
        return false;
    }

    public void add(int target) {
        /**
         * 拿到当前最大的 小于输入值的结点 判断当前新节点的插入层数 插入结点
         */
        Node[] preNode = new Node[MAX_LEVEL];
        Arrays.fill(preNode, root);
        Node currNode = root;
        for (Integer i = this.level - 1; i >= 0; i--) {
            for (; currNode.getNodeByNextLevel(i) != null && currNode.getNodeByNextLevel(i).value < target;) {
                currNode = currNode.getNodeByNextLevel(i);
            }
            preNode[i] = currNode;
        }
        // 新节点的层数
        int currLevel = getLevel();
        // 跳表的层数
        this.level = Math.max(currLevel, level);

        Node newNode = new Node(target, this.level);
        for (int i = 0; i < currLevel; i++) {
            newNode.setNodeByNextLevel(i, preNode[i].getNodeByNextLevel(i));
            preNode[i].setNodeByNextLevel(i, newNode);
        }

    }

    public boolean erase(int target) {
        /**
         * 拿到当前层数中小于target的最大值 判断值是否存在：不存在，返回false 如果存在，则删除 维护跳表层数
         */
        Node[] preNode = new Node[MAX_LEVEL];
        Arrays.fill(preNode, root);
        Node currNode = root;
        for (Integer i = this.level - 1; i >= 0; i--) {
            for (; currNode.getNodeByNextLevel(i) != null && currNode.getNodeByNextLevel(i).value < target;) {
                currNode = currNode.getNodeByNextLevel(i);
            }
            preNode[i] = currNode;
        }

        currNode = currNode.getNodeByNextLevel(0);
        if (currNode == null || currNode.value != target) {
            return false;
        }

        for (Integer i = 0; i < this.level; i++) {
            preNode[i].setNodeByNextLevel(i, currNode.getNodeByNextLevel(i));
        }

        for (; this.level > 1 && this.root.getNodeByNextLevel(level - 1) == null;) {
            this.level--;
        }
        return true;
    }

    private int getLevel() {
        int level = 1;
        for (; random.nextFloat() < LEVEL_TO_GENERATE_PROBABILITY;) {
            level++;
        }
        return Math.max(level, MAX_LEVEL);
    }
}
