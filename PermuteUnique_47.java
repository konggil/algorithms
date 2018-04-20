/*
 * https://leetcode.com/problems/permutations-ii/description/
 */
 
class PermuteUnique_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        int[] tmpRes = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            used[i]=false;
        }
        Set<OneRes> res = new HashSet<>();
        permuteUniqueRecur(0, nums, tmpRes, used, res);
        List<List<Integer>> resList = new LinkedList<>();
        for(Iterator iter=res.iterator();iter.hasNext();){
            OneRes oneRes = (OneRes)iter.next();
            resList.add(oneRes.toList());
        }
        return resList;
    }
    
    void permuteUniqueRecur(int index, int[] nums, int[] tmpRes, boolean[] used, Set<OneRes> res){
        if(index == nums.length){        
            res.add(new OneRes(nums,tmpRes));
            return;
        }
        for(int j=0;j<nums.length;j++){
            if(used[j]==true){
                continue;
            }
            used[j]=true;
            tmpRes[index]=j;
            permuteUniqueRecur(index+1, nums, tmpRes, used, res);
            used[j]=false;
        }
    }
    
    class OneRes{
        int[] data;
        
        OneRes(int[] nums, int[] tmpRes){
            data = new int[nums.length];
            for(int i=0;i<nums.length;i++){
                data[i]=nums[tmpRes[i]];
            }
        }
        
        public boolean equals(Object one){
            OneRes newOne = (OneRes)one;
            for(int i=0;i<data.length;i++){
                if(data[i]!=newOne.data[i])
                    return false;
            }
            return true;
        }
        
        public int hashCode(){
            int sum=0;
            for(int i=0;i<data.length;i++){
                sum+=data[i];
            }
            return sum;
        }
        
        List<Integer> toList(){
            List<Integer> list = new LinkedList<>();
            for(int i=0;i<data.length;i++){
                list.add(data[i]);
            }
            return list;
        }
    }
    
}
