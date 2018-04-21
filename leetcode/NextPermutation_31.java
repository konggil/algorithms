/*
 * https://leetcode.com/problems/next-permutation/description/
 */

class NextPermutation_31 {
    public void nextPermutation(int[] nums) {
        int i = nums.length-2, j = nums.length-1,tmp;
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i>=0){
            while(nums[j] <= nums[i]){
                j--;
            }
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        } 
        i++;
        j = nums.length-1;
        while(i <= j){
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
}
