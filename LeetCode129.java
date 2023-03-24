import java.util.HashMap;

/**
 * @author zhoulei
 * @create 2023/3/14
 * @description:
 */
public class LeetCode129 {
    int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        dfs(root,0);
        return sum;
    }

    private void dfs(TreeNode curr,int tempSum){
        if (curr == null){
            return;
        }
        tempSum = 10*tempSum+curr.val;
        if(curr.left == null && curr.right == null){
            sum+=tempSum;
            return;
        }
        dfs(curr.left,tempSum);
        dfs(curr.right,tempSum);
    }
}

