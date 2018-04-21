/*
 * https://leetcode.com/problems/3sum/description/
 */
 
 class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Set<Integer> partSum = new HashSet<Integer>();
        Set<OneRes> tmpSet = new HashSet<OneRes>();
        for(int i = 0; i < nums.length;i++){
            int aim = -nums[i];
            partSum.clear();
            for(int j=i+1;j<nums.length;j++){
                if(partSum.contains(aim-nums[j])){
                    int [] oneResArr={nums[i],nums[j],aim-nums[j]};
                    Arrays.sort(oneResArr);
                    OneRes oneRes = new OneRes(oneResArr);
                    tmpSet.add(oneRes);
                } else {
                    partSum.add(nums[j]);
                }
            }
        }
        for(Iterator<OneRes> iter=tmpSet.iterator();iter.hasNext();){
            OneRes oneRes=(OneRes)iter.next();
            res.add(Arrays.asList(oneRes.x, oneRes.y, oneRes.z));
        }
        return res;
    }
    
    class OneRes {
        int x;
        int y;
        int z;
        
        OneRes(int[] array){
            x=array[0];
            y=array[1];
            z=array[2];
        }
        
        public boolean equals(Object one){
            OneRes oneRes = (OneRes)one;
            return this.x == oneRes.x &&
                   this.y == oneRes.y &&
                   this.z == oneRes.z;
        }
        
        public int hashCode(){
            return 1*x + 2*y + 3*z;
        }
    }
}
