import java.util.Arrays;

/**
 * @author zhoulei
 * @create 2023/3/8
 * @description:
 */
public class LeetCode875 {
}
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //二分法
        int right = Arrays.stream(piles).max().getAsInt();
        int left = Arrays.stream(piles).min().getAsInt();

        for (;left<=right;){
            int mid = left+(right-left)/2;

            if (canEatAll(piles,h,mid)){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    public boolean canEatAll(int [] piles,int h,int k){
        int cnt = 0;
        for(int pile : piles){
            int currH = pile%k == 0 ? pile/k : pile/k+1;
            cnt += currH;
            if (cnt>h){
                return false;
            }
        }
        return true;
    }
}
