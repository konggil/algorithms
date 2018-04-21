/*
 * https://leetcode.com/problems/permutations/description/
 */
 
 class Permute_46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        permuteRecur(0, nums, res);
        return res;
    }
    
    void permuteRecur(int index, int[] nums, List<List<Integer>> res){
        if(index == nums.length - 1){
            List<Integer> list = new LinkedList<Integer>();
            list.add(nums[index]);
            res.add(list);
            return;
        }
        
        permuteRecur(index+1, nums, res);
        
        int size = res.size();
        
        while(size>0){
            List<Integer> list = res.get(0);
            res.remove(0);
            int listSize = list.size();
            for(int i=0;i<=listSize;i++){
                List<Integer> newList = new LinkedList<>(list);
                newList.add(i, nums[index]);
                res.add(newList);
            }
            size--;
        }
    }
    
}
