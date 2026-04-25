class Solution {
    public char findKthBit(int n, int k) {
        // Base case: when n = 1, the string is "0"
        if (n == 1) {
            return '0';
        }
        
        // Calculate the length and middle position
        int length = (1 << n) - 1;  // 2^n - 1
        int mid = length / 2 + 1;
        
        // Case 1: k is at the middle position
        if (k == mid) {
            return '1';
        }
        
        // Case 2: k is in the first half - recurse on n-1
        if (k < mid) {
            return findKthBit(n - 1, k);
        }
        
        // Case 3: k is in the second half - map back and invert
        // Position in first half: length - k + 1
        char bit = findKthBit(n - 1, length - k + 1);
        return (bit == '0') ? '1' : '0';
    }
}