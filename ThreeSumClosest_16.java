/*
 * https://leetcode.com/problems/3sum-closest/description/
 */


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res=target, minus = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]-target>minus){
                return res;
            }
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j=i+1, k=nums.length-1;
            while(j<k) {
                int sum=nums[i]+nums[j]+nums[k];
                if(sum==target){
                    return target;
                }
                if(Math.abs(sum-target)<minus){
                    res=sum;
                    minus=Math.abs(sum-target);
                }
                if(sum<target){
                    do{
                        j++;
                    }while(j<k&&nums[j]==nums[j-1]);
                }
                if(sum>target){
                    do{
                        k--;
                    }while(j<k&&nums[k]==nums[k+1]);
                }
            }
        }
        return res;
    }
}
