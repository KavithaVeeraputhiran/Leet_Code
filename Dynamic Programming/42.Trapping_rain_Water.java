class Solution {
    public int trap(int[] height) {
       int n= height.length;
        if (n==0) return 0;
        // initlize the matrix to store the leftMax and rightMax
        int[] leftMax = new int[n];
        int[] rightMax= new int[n];
        //store the index of the firstin left and last in right 
        leftMax[0]=height[0];
        rightMax[n-1]=height[n-1];
        // iterate and find the left max
        for(int i=1;i<n;i++){
            leftMax[i]=Math.max(leftMax[i-1],height[i]);
        }
        // iterate and calculate the rightMax
        for(int i=n-2;i>=0;i--)
        {
            rightMax[i]=Math.max(rightMax[i+1],height[i]);
        }
        int weight=0;
        //find the totalWater stored 
        for(int i=0;i<n;i++)
        {
            weight+=Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return weight;
    }
}