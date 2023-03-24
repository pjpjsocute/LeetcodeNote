import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoulei
 * @create 2023/3/3
 * @description:
 */
public class LeetCode28 {
    /**
     * we can get two different way to  match the string
     * first , a simple way by sliding window
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        //validate
        if (haystack == null || needle.length() > haystack.length()){
            return -1;
        }

        int leftPointer = 0;
        int rightPointer = needle.length()-1;

        while(rightPointer<haystack.length()){
            String substring = haystack.substring(leftPointer, rightPointer+1);
            if (substring.equals(needle)){
                return leftPointer;
            }
            leftPointer++;
            rightPointer++;
        }
        return -1;
    }

    /**
     * now we can use kmp algorithm，a prefix matching algorithm
     *
     */

    public int strStr(String haystack, String needle){
        //in first method,we can find that we need match all the substring if it's not match.
        //some message have been lost: the prefix of the last string we have compared.
        //we can start with the same prefix string to match,so that the time can be saved
        // we can store the same prefix in a array or list, so we called kmp algorithm
        int length = haystack.length();
        int length1 = needle.length();
        int i=0;
        int j = 0;
        int [] next = new int [length1];
        getNext(needle,next);
        while (i < length && j < length1)
        {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j))
            {
                i++;
                j++;
            }
            else {
                j = next[j];
            }
        }

        if (j == length1){
            return i - j;
        }
        else {
            return -1;
        }
    }

    void getNext(String p, int [] next)
    {
        next[0] = -1;
        int i = 0, j = -1;

        while (i < (p.length())){
            if (j == -1 || p.charAt(i)==p.charAt(j)) {
                ++i;
                ++j;
                next[i] = j;
            }
            else {
                j = next[j];
            }
        }
    }
}
