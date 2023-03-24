import java.util.Random;

/**
 * @author zhoulei
 * @create 2023/3/10
 * @description:
 */
public class LeetCode142 {

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
class Solution{
    Random random;
    private ListNode head;

    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        ListNode curr = head;
        int cnt = 1;
        int conserveVal = head.val;
        while (curr != null) {
            int randomNum = random.nextInt(cnt) + 1;
            if (randomNum == cnt) {
                conserveVal = curr.val;
            }
            cnt++;
            curr = curr.next;
        }
        return conserveVal;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = new ListNode(0);
        slow.next = head;
        ListNode fast = slow.next.next;
        for (; fast != null && fast.next != null && slow != fast; ) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast == null) {
            return null;
        }

        ListNode start = new ListNode(0);
        start.next = head;
        for (; start != fast; ) {
            start = start.next;
            fast = fast.next.next;
        }
        return fast;
    }
}


