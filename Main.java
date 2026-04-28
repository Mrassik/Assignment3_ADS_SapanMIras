import java.util.*;

public class Main {
    // Task 1

    public static int[] twoSum(int[] nums, int target) {
        // HashMap to store the number and its index
        HashMap<Integer, Integer> map = new HashMap<>();

        // Loop through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If the complement exists in the map, return the indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // Otherwise, add the number and its index to the map
            map.put(nums[i], i);
        }

        // If no solution found, return an empty array (although the problem guarantees a solution)
        return new int[] {};
    }

    // Task 2
    public static int firstUniqChar(String s) {
        // HashMap to store the frequency of characters
        HashMap<Character, Integer> map = new HashMap<>();

        // Loop through the string to count frequencies
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Loop through the string again to find the first unique character
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;  // Return the index of the first unique character
            }
        }

        // If no unique character is found, return -1
        return -1;
    }
    // Task 3
    public static boolean isIsomorphic(String s, String t) {
        // If the strings are of different lengths, they can't be isomorphic
        if (s.length() != t.length()) {
            return false;
        }

        // HashMaps to store character mappings
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();

        // Loop through the characters of both strings
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if the character from 's' is already mapped to another character
            if (mapS.containsKey(charS)) {
                if (mapS.get(charS) != charT) {
                    return false;  // The mapping is inconsistent
                }
            } else {
                mapS.put(charS, charT);
            }

            // Similarly, check the mapping for 't'
            if (mapT.containsKey(charT)) {
                if (mapT.get(charT) != charS) {
                    return false;  // The mapping is inconsistent
                }
            } else {
                mapT.put(charT, charS);
            }
        }

        // If no inconsistencies are found, the strings are isomorphic
        return true;
    }
    // Task 4
    public static boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();

        // Continue the process until n becomes 1 or we find a cycle
        while (n != 1) {
            if (seen.contains(n)) {
                return false;  // Cycle detected, not a happy number
            }
            seen.add(n);
            n = getSumOfSquares(n);  // Calculate sum of squares of digits
        }

        return true;  // n is a happy number
    }

    // Helper function to calculate sum of squares of digits
    private static int getSumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;  // Get the last digit
            sum += digit * digit;  // Add the square of the digit
            n /= 10;  // Remove the last digit
        }
        return sum;
    }

    // Task 5
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    // Task 6
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        // Recurse through the left and right subtrees, adding 1 for the current node
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Return the maximum depth between the two subtrees
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Task 7
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // Helper function to check if two trees are mirrors of each other
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    // Task 8
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return longestConsecutiveHelper(root, null, 0);
    }

    // Helper function to calculate the longest consecutive path
    private int longestConsecutiveHelper(TreeNode node, TreeNode parent, int length) {
        if (node == null) return length;

        // If the current node is consecutive from its parent
        length = (parent != null && node.val == parent.val + 1) ? length + 1 : 1;

        int left = longestConsecutiveHelper(node.left, node, length);
        int right = longestConsecutiveHelper(node.right, node, length);

        // Return the longest path from the left and right child
        return Math.max(length, Math.max(left, right));
    }

    // Task 9
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        // Process the array using the 3-way partitioning
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap nums[low] and nums[mid], increment low and mid
                swap(nums, low++, mid++);
            } else if (nums[mid] == 1) {
                // Move mid to the next element
                mid++;
            } else {
                // Swap nums[mid] and nums[high], decrement high
                swap(nums, mid, high--);
            }
        }
    }

    // Task 10
    public void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private void quickSortHelper(int[] nums, int low, int high) {
        if (low < high) {
            int pi = partition(nums, low, high);
            quickSortHelper(nums, low, pi - 1);
            quickSortHelper(nums, pi + 1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;
    }

    // Task 11
    public void mergeSort(int[] nums) {
        if (nums.length < 2) return;
        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(nums, left, right);
    }

    private void merge(int[] nums, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        while (i < left.length) nums[k++] = left[i++];
        while (j < right.length) nums[k++] = right[j++];
    }

    // Task 12
    public void heapSort(int[] nums) {
        int n = nums.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // Extract elements one by one from the heap
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (max element) with the last element
            swap(nums, 0, i);

            // Call heapify on the reduced heap
            heapify(nums, i, 0);
        }
    }

    private void heapify(int[] nums, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < n && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, n, largest);
        }
    }


    // Helper function to swap two elements
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
    }
}