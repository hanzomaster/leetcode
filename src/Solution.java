import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

final class Solution {
    private Solution() {
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = mergeTwoLists(list1, list2);
        System.out.println(listNode);
    }

    //region Two Sums (from an array of int, find pair that sum to the target)

    /**
     * <p>Given an array of integers {@code nums}&nbsp;and an integer {@code target}, return <em>indices of the two
     * numbers such that they add up to {@code target}</em>.</p>
     *
     * <p>You may assume that each input would have <strong><em>exactly</em> one solution</strong>, and you may not
     * use the <em>same</em> element twice.</p>
     *
     * <p>You can return the answer in any order.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [2,7,11,15], target = 9
     * <strong>Output:</strong> [0,1]
     * <strong>Explanation:</strong> Because nums[0] + nums[1] == 9, we return [0, 1].
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [3,2,4], target = 6
     * <strong>Output:</strong> [1,2]
     * </pre>
     *
     * <p><strong class="example">Example 3:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [3,3], target = 6
     * <strong>Output:</strong> [0,1]
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
     * <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
     * <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li>
     * <li><strong>Only one valid answer exists.</strong></li>
     * </ul>
     *
     * <p>&nbsp;</p>
     * <strong>Follow-up:&nbsp;</strong>Can you come up with an algorithm that is less than
     * <code>O(n<sup>2</sup>)</code>
     * <font face="monospace">&nbsp;</font>time complexity?
     */

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    //endregion

    //region Palindrome (check if number is the same if flip)

    /**
     * <p>Given an integer {@code x}, return {@code true}<em> if </em>{@code x}<em> is a </em><span
     * data-keyword="palindrome-integer"><em><strong>palindrome</strong></em></span><em>, and
     * </em>{@code false}<em> otherwise</em>.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> x = 121
     * <strong>Output:</strong> true
     * <strong>Explanation:</strong> 121 reads as 121 from left to right and from right to left.
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> x = -121
     * <strong>Output:</strong> false
     * <strong>Explanation:</strong> From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * </pre>
     *
     * <p><strong class="example">Example 3:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> x = 10
     * <strong>Output:</strong> false
     * <strong>Explanation:</strong> Reads 01 from right to left. Therefore it is not a palindrome.
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li><code>-2<sup>31</sup>&nbsp;&lt;= x &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
     * </ul>
     *
     * <p>&nbsp;</p>
     * <strong>Follow up:</strong> Could you solve it without converting the integer to a string?
     */

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (x == rev || x == rev / 10); // the second rev / 10 because the condition in the while loop is x > rev.
        // If it is while (x < rev) then the last condition has to be x / 10 == rev
    }
    //endregion

    //region Roman to Integer (convert Roman to Integer)

    /**
     * <p>Roman numerals are represented by seven different symbols:&nbsp;{@code I}, {@code V}, {@code X}, {@code L},
     * {@code C}, {@code D} and {@code M}.</p>
     *
     * <pre>
     * <strong>Symbol</strong>       <strong>Value</strong>
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000</pre>
     *
     * <p>For example,&nbsp;{@code 2} is written as {@code II}&nbsp;in Roman numeral, just two ones added together.
     * {@code 12} is written as&nbsp;{@code XII}, which is simply {@code X + II}. The number {@code 27} is
     * written as {@code XXVII}, which is {@code XX + V + II}.</p>
     *
     * <p>Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is
     * not {@code IIII}. Instead, the number four is written as {@code IV}. Because the one is before the five we
     * subtract it making four. The same principle applies to the number nine, which is written as {@code IX}. There
     * are six instances where subtraction is used:</p>
     *
     * <ul>
     * <li>{@code I} can be placed before {@code V} (5) and {@code X} (10) to make 4 and 9.&nbsp;</li>
     * <li>{@code X} can be placed before {@code L} (50) and {@code C} (100) to make 40 and 90.&nbsp;</li>
     * <li>{@code C} can be placed before {@code D} (500) and {@code M} (1000) to make 400 and 900.</li>
     * </ul>
     *
     * <p>Given a roman numeral, convert it to an integer.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "III"
     * <strong>Output:</strong> 3
     * <strong>Explanation:</strong> III = 3.
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "LVIII"
     * <strong>Output:</strong> 58
     * <strong>Explanation:</strong> L = 50, V= 5, III = 3.
     * </pre>
     *
     * <p><strong class="example">Example 3:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "MCMXCIV"
     * <strong>Output:</strong> 1994
     * <strong>Explanation:</strong> M = 1000, CM = 900, XC = 90 and IV = 4.
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li>{@code 1 <= s.length <= 15}</li>
     * <li>{@code s} contains only&nbsp;the characters {@code ('I', 'V', 'X', 'L', 'C', 'D', 'M')}.</li>
     * <li>It is <strong>guaranteed</strong>&nbsp;that {@code s} is a valid roman numeral in the range {@code [1,
     * 3999]}.</li>
     * </ul>
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> romanNumber = new HashMap<>();
        romanNumber.put('I', 1);
        romanNumber.put('V', 5);
        romanNumber.put('X', 10);
        romanNumber.put('L', 50);
        romanNumber.put('C', 100);
        romanNumber.put('D', 500);
        romanNumber.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && romanNumber.get(s.charAt(i)) < romanNumber.get(s.charAt(i + 1))) {
                result -= romanNumber.get(s.charAt(i)); // IV
            } else result += romanNumber.get(s.charAt(i));
        }

        return result;
    }
    //endregion

    //region Longest common prefix in an array of strings

    /**
     * <p>Write a function to find the longest common prefix string amongst an array of strings.</p>
     *
     * <p>If there is no common prefix, return an empty string {@code ""}.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> strs = ["flower","flow","flight"]
     * <strong>Output:</strong> "fl"
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> strs = ["dog","racecar","car"]
     * <strong>Output:</strong> ""
     * <strong>Explanation:</strong> There is no common prefix among the input strings.
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li>{@code 1 <= strs.length <= 200}</li>
     * <li>{@code 0 <= strs[i].length <= 200}</li>
     * <li>{@code strs[i]} consists of only lowercase English letters.</li>
     * </ul>
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        // * [info] - find the min length of all strings (this will also be the max of the prefix
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int start = 1;
        int end = minLen;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (isCommonPrefix(strs, middle)) start = middle + 1; // proceed to right half
            else end = middle - 1; // proceed to the left half
        }
        return strs[0].substring(0, (start + end) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        String prefixCandidate = strs[0].substring(0, len);
        for (String str : strs) {
            if (!str.startsWith(prefixCandidate)) return false;
        }
        return true;
    }
    //endregion

    //region Valid Parentheses

    /**
     * <p>Given a string {@code s} containing just the characters {@code '('}, {@code ')'}, <code>'{'</code>, <code>'
     * }'</code>, {@code '['} and {@code ']'}, determine if the input string is valid.</p>
     *
     * <p>An input string is valid if:</p>
     *
     * <ol>
     * <li>Open brackets must be closed by the same type of brackets.</li>
     * <li>Open brackets must be closed in the correct order.</li>
     * <li>Every close bracket has a corresponding open bracket of the same type.</li>
     * </ol>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "()"
     * <strong>Output:</strong> true
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "()[]{}"
     * <strong>Output:</strong> true
     * </pre>
     *
     * <p><strong class="example">Example 3:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> s = "(]"
     * <strong>Output:</strong> false
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
     * <li>{@code s} consists of parentheses only {@code '()[]{}'}.</li>
     * </ul>
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // push the corresponding close parentheses to the stack to ensure the position is right when we pop
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
                // * when all the 3 conditions above are false, then this character will be a closing bracket
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
    //endregion

    //region Merge two sorted lists

    /**
     * <p>You are given the heads of two sorted linked lists {@code list1} and {@code list2}.</p>
     *
     * <p>Merge the two lists into one <strong>sorted</strong> list. The list should be made by splicing together the
     * nodes of the first two lists.</p>
     *
     * <p>Return <em>the head of the merged linked list</em>.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height:
     * 302px;" />
     * <pre>
     * <strong>Input:</strong> list1 = [1,2,4], list2 = [1,3,4]
     * <strong>Output:</strong> [1,1,2,3,4,4]
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> list1 = [], list2 = []
     * <strong>Output:</strong> []
     * </pre>
     *
     * <p><strong class="example">Example 3:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> list1 = [], list2 = [0]
     * <strong>Output:</strong> [0]
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li>The number of nodes in both lists is in the range {@code [0, 50]}.</li>
     * <li>{@code -100 <= Node.val <= 100}</li>
     * <li>Both {@code list1} and {@code list2} are sorted in <strong>non-decreasing</strong> order.</li>
     * </ul>
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
        return list1 == null ? list2 : list1;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //endregion

    //region Remove duplicates from a sorted array

    /**
     * <p>Given an integer array {@code nums} sorted in <strong>non-decreasing order</strong>, remove the
     * duplicates
     * <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>
     * such that each unique element appears only <strong>once</strong>. The <strong>relative order</strong> of the
     * elements should be kept the <strong>same</strong>. Then return <em>the number of unique elements in
     * </em>{@code nums}.</p>
     *
     * <p>Consider the number of unique elements of {@code nums} to be {@code k}, to get accepted, you need
     * to do the following things:</p>
     *
     * <ul>
     * <li>Change the array {@code nums} such that the first {@code k} elements of {@code nums}
     * contain the unique elements in the order they were present in {@code nums} initially. The remaining
     * elements of {@code nums} are not important as well as the size of {@code nums}.</li>
     * <li>Return {@code k}.</li>
     * </ul>
     *
     * <p><strong>Custom Judge:</strong></p>
     *
     * <p>The judge will test your solution with the following code:</p>
     *
     * <pre>
     * int[] nums = [...]; // Input array
     * int[] expectedNums = [...]; // The expected answer with correct length
     *
     * int k = removeDuplicates(nums); // Calls your implementation
     *
     * assert k == expectedNums.length;
     * for (int i = 0; i &lt; k; i++) {
     * assert nums[i] == expectedNums[i];
     * }
     * </pre>
     *
     * <p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [1,1,2]
     * <strong>Output:</strong> 2, nums = [1,2,_]
     * <strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [0,0,1,1,1,2,2,3,3,4]
     * <strong>Output:</strong> 5, nums = [0,1,2,3,4,_,_,_,_,_]
     * <strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
     * <li>{@code -100 <= nums[i] <= 100}</li>
     * <li>{@code nums} is sorted in <strong>non-decreasing</strong> order.</li>
     * </ul>
     */
    public int removeDuplicates(int[] nums) {
        int j = 1;
        // ? loop from the second element because the first one is always unique
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
    //endregion

    //region Remove element

    /**
     * <p>Given an integer array {@code nums} and an integer {@code val}, remove all occurrences of {@code val} in
     * {@code nums}
     * <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>
     * . The order of the elements may be changed. Then return <em>the number of elements in </em>{@code nums}<em>
     *     which are not equal to
     * </em>{@code val}.</p>
     *
     * <p>Consider the number of elements in {@code nums} which are not equal to {@code val} be {@code k}, to get
     * accepted, you need to do the following things:</p>
     *
     * <ul>
     * <li>Change the array {@code nums} such that the first {@code k} elements of {@code nums} contain the elements
     * which are not equal to {@code val}. The remaining elements of {@code nums} are not important as well as the
     * size of {@code nums}.</li>
     * <li>Return {@code k}.</li>
     * </ul>
     *
     * <p><strong>Custom Judge:</strong></p>
     *
     * <p>The judge will test your solution with the following code:</p>
     *
     * <pre>
     * int[] nums = [...]; // Input array
     * int val = ...; // Value to remove
     * int[] expectedNums = [...]; // The expected answer with correct length.
     * // It is sorted with no values equaling val.
     *
     * int k = removeElement(nums, val); // Calls your implementation
     *
     * assert k == expectedNums.length;
     * sort(nums, 0, k); // Sort the first k elements of nums
     * for (int i = 0; i &lt; actualLength; i++) {
     * assert nums[i] == expectedNums[i];
     * }
     * </pre>
     *
     * <p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>
     *
     * <p>&nbsp;</p>
     * <p><strong class="example">Example 1:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [3,2,2,3], val = 3
     * <strong>Output:</strong> 2, nums = [2,2,_,_]
     * <strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * </pre>
     *
     * <p><strong class="example">Example 2:</strong></p>
     *
     * <pre>
     * <strong>Input:</strong> nums = [0,1,2,2,3,0,4,2], val = 2
     * <strong>Output:</strong> 5, nums = [0,1,4,0,3,_,_,_]
     * <strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
     * Note that the five elements can be returned in any order.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     * </pre>
     *
     * <p>&nbsp;</p>
     * <p><strong>Constraints:</strong></p>
     *
     * <ul>
     * <li>{@code 0 <= nums.length <= 100}</li>
     * <li>{@code 0 <= nums[i] <= 50}</li>
     * <li>{@code 0 <= val <= 100}</li>
     * </ul>
     */
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
    //endregion
}
