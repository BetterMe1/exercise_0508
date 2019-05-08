package exercise.exercise_0508;

/*
数组中的逆序对
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
题目保证输入的数组中没有的相同的数字
数据范围：
	对于%50的数据,size<=10^4
	对于%75的数据,size<=10^5
	对于%100的数据,size<=2*10^5
 */
public class Solution {
    int count = 0;
    public int InversePairs(int [] array) {
        if(array != null && array.length != 0){
            //数组创建在merge中会每次都开辟一此存放数组的空间，数据量大时会消耗大量时间，因此在一开始就创建好。
            int[] tmpArr = new int[array.length];
            mergeSort(array,0,array.length-1,tmpArr);
        }
        return count;
    }
    private void mergeSort(int[] array, int start,int end,int[] tmpArr){
        if(start >= end){
            return;
        }
        int mid = (start+end)>>1;
        mergeSort(array,start,mid,tmpArr);
        mergeSort(array,mid+1,end,tmpArr);
        merge(array,start,mid,end,tmpArr);//合并
    }
    private void merge(int[] array,int start,int mid,int end,int[] tmpArr) {
        int tmpIndex = start;//tmpArr的下标
        int start2 = mid + 1;//第二个归并段
        int i = start;
        while (start <= mid && start2 <= end) {
            if (array[start] > array[start2]) {
                tmpArr[tmpIndex++] = array[start++];
                count = (count + end - start2+1) % 1000000007;//逆序对
            } else {
                tmpArr[tmpIndex++] = array[start2++];
            }
        }
        if (start2 <= end) {
            System.arraycopy(array, start2, tmpArr, tmpIndex, end - start2 + 1);
        }
        if (start <= mid) {
            System.arraycopy(array, start, tmpArr, tmpIndex, mid - start + 1);
        }
        System.arraycopy(tmpArr, i, array, i, end - i + 1);
    }
}

/*
2. 两数相加
给出两个 非空 的链表用来表示两个非负的整数。
其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int d = 0;//进位
        while(cur1 != null || cur2 != null){
            int num1 = cur1 == null ? 0 : cur1.val;
            int num2 = cur2 == null ? 0 : cur2.val;
            int sum = num1+num2+d;
            cur.next = new ListNode(sum%10);
            d = sum/10;
            cur = cur.next;
            cur1 = cur1 == null ? cur1 :cur1.next;
            cur2 = cur2 == null ? cur2 :cur2.next;
        }
        if(d>0){
            cur.next = new ListNode(d);
        }
        return head.next;
    }
}*/

/*
8. 字符串转换整数 (atoi)
请你来实现一个 atoi 函数，使其能将字符串转换成整数。
首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
则你的函数不需要进行转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
如果数值超过这个范围，qing返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
示例 1:
输入: "42"
输出: 42
示例 2:
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     因此返回 INT_MIN (−231) 。
 */
/*
class Solution {
    public int myAtoi(String str) {
        str = str.trim();
        int len = str.length();
        if(len==0 || str == null){
            return 0;
        }
        int i=0;
        long result = 0;
        boolean flag = true;
        if(str.charAt(i) == '+'){
            i++;
        }else if(str.charAt(i) == '-'){
            i++;
            flag = false;
        }else if('0'>str.charAt(i)|| str.charAt(i)>'9'){
            return 0;
        }
        int count = 0;
        for(; i<len; i++){
            char a = str.charAt(i);
            if('0'<=a && a<='9'){
                result = result*10 +(a-'0');
                if(result != 0){
                    count++;
                }
                if(count > 10){
                    return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }else{
                break;
            }
        }
        result = flag ? result : -result;
        if(result > (long)Integer.MAX_VALUE ||
                result < (long)Integer.MIN_VALUE){
            return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return (int)result;
    }
}
*/

/*
15. 三数之和
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组。
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

/*
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0,len=nums.length; i<len-2; i++){
            if(i==0 ||(i>0 && nums[i] != nums[i-1])){
                int j=i+1;
                int k=len-1;
                while(j < k){
                    if(nums[j] + nums[k] == -nums[i]){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        result.add(tmp);
                        j++;
                        k--;
                        while(j<k && nums[k] == nums[k+1]){
                            k--;
                        }
                        while(j<k && nums[j] == nums[j-1]){
                            j++;
                        }
                    }else if(nums[j] + nums[k] < -nums[i]){
                        j++;
                        while(j<k && nums[j] == nums[j-1]){
                            j++;
                        }
                    }else{
                        k--;
                        while(j<k && nums[k] == nums[k+1]){
                            k--;
                        }
                    }
                }
            }
        }
        return result;
    }
}
*/
