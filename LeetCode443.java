import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zhoulei
 * @create 2023/3/2
 * @description:
 */
public class LeetCode443 {
    public static void main(String[] args) {

    }
    public int compress(char[] chars) {
        //use two point to ensure the repeat characters window
        int left = 0;
        int write = 0;
        //find the consecutive window
        for(;left<chars.length;){
            char target = chars[left];
            int windowsPoint = left;
            int cnt = 0;
            for (;windowsPoint<chars.length && chars[windowsPoint] == target;){
                cnt ++;
                windowsPoint++;
            }
            left = windowsPoint;
            chars[write++] = target;
            //replace the chars
            if (cnt == 1){
                continue;
            }
            for (char c : String.valueOf(cnt).toCharArray()) {
                chars[write++] = c;
            }
        }
        return write;
    }

}

