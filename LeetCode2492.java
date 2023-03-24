import java.util.*;

/**
 * @author zhoulei
 * @create 2023/3/22
 * @description:
 */
public class LeetCode2492 {
    public static void main(String[] args) {
        new LeetCode2492().new Solution().minScore(4,new int [][]{{1,2,9},{2,3,6},{2,4,5},{1,4,7}});
    }
    class Solution {
        public int minScore(int n, int[][] roads) {
            //build map
            //int[0]:node int[1]:score
            Map<Integer, List<int[]>> map = new HashMap<>(16);

            for (int[] road : roads) {
                map.putIfAbsent(road[0],new ArrayList<int[]>());
                map.get(road[0]).add(new int []{road[1],road[2]});
                map.putIfAbsent(road[1],new ArrayList<int[]>());
                map.get(road[1]).add(new int []{road[0],road[2]});
            }

            int result = Integer.MAX_VALUE;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(1);
            for (;!queue.isEmpty();){
                Integer integer = queue.removeFirst();
                List<int[]> ints = map.get(integer);
                for (int[] anInt : ints) {
                    int temp = anInt[0];
                    int score = anInt[1];
                    result = Math.min(score,result);
                    if (temp == n){
                        return result;
                    }
                    queue.add(temp);
                }
            }
            return result;
        }
    }
}