
// Q1: Partition Array into K Equal Sum Subsets

import java.util.Arrays;

public class PartitionKSubsets {
    public static boolean canPartitionKSubsets(int[] arr, int k) {
        int sum = Arrays.stream(arr).sum();
        if (sum % k != 0) return false;

        int target = sum / k;
        boolean[] visited = new boolean[arr.length];
        return canPartition(arr, visited, 0, k, 0, target);
    }

    private static boolean canPartition(int[] arr, boolean[] visited, int start, int k, int currSum, int target) {
        if (k == 0) return true;
        if (currSum == target) return canPartition(arr, visited, 0, k - 1, 0, target);

        for (int i = start; i < arr.length; i++) {
            if (!visited[i] && currSum + arr[i] <= target) {
                visited[i] = true;
                if (canPartition(arr, visited, i + 1, k, currSum + arr[i], target)) return true;
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 2};
        int k = 2;
        System.out.println(canPartitionKSubsets(arr, k)); // Output: true
    }
}


//Q2: Generate All Permutations of an Array

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void generatePermutations(int[] arr, boolean[] used, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == arr.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(arr[i]);
                generatePermutations(arr, used, temp, result);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    public static List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutations(arr, new boolean[arr.length], new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(permute(arr));
    }
}


//Q3: Unique Permutations with Duplicates

import java.util.*;

public class UniquePermutations {
    public static void generateUniquePermutations(int[] arr, boolean[] used, List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == arr.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i] || (i > 0 && arr[i] == arr[i - 1] && !used[i - 1])) continue;

            used[i] = true;
            temp.add(arr[i]);
            generateUniquePermutations(arr, used, temp, result);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    public static List<List<Integer>> permuteUnique(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        generateUniquePermutations(arr, new boolean[arr.length], new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }
}


//Q4. Subset Product Equals Target

public class SubsetProduct {
    public static boolean isSubsetProduct(int[] arr, int index, int product, int target) {
        if (product == target) return true;
        if (index >= arr.length || product > target) return false;

        return isSubsetProduct(arr, index + 1, product * arr[index], target) ||
                isSubsetProduct(arr, index + 1, product, target);
    }

    public static boolean subsetProductEqualsTarget(int[] arr, int target) {
        return isSubsetProduct(arr, 0, 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 2, 5, 4};
        int target = 16;
        System.out.println(subsetProductEqualsTarget(arr, target) ? "YES" : "NO");
    }
}


// Q5. N Queens Problem

public class NQueens {
    public static int countNQueensSolutions(int n) {
        int[] board = new int[n];
        return solveNQueens(board, 0, n);
    }

    private static int solveNQueens(int[] board, int row, int n) {
        if (row == n) return 1;

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                count += solveNQueens(board, row + 1, n);
            }
        }
        return count;
    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == row - i) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countNQueensSolutions(4)); // Output: 2
        System.out.println(countNQueensSolutions(1)); // Output: 1
    }
}
