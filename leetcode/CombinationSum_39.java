/*
 * https://leetcode.com/problems/combination-sum/description/
 */
 
 class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Set<String> memo=new HashSet<String>();
        int[] tmpRes = new int[candidates.length];
        for(int i=0;i<tmpRes.length;i++){
            tmpRes[i]=0;
        }
        combinationSumRecur(0, candidates, tmpRes, target, res, memo);
        return res;
    }
    
    boolean combinationSumRecur(int index, int[] candidates, int[] tmpRes, int target, List<List<Integer>> res, Set<String> memo) {
        if(target==0){
            List<Integer> oneRes = new LinkedList<Integer>();
            for(int i=0;i<tmpRes.length;i++){
                for(int j=tmpRes[i];j>0;j--){
                    oneRes.add(candidates[i]);
                }
            }
            res.add(oneRes);
            return true;
        }
        if(index==candidates.length){
            return false;
        }
  /*      if(memo.contains(index+"_"+target)){
            return false;
        }*/
        for(int i=0;target-i*candidates[index]>=0;i++){
            tmpRes[index]=i;
            if(combinationSumRecur(index+1,candidates,tmpRes,target-i*candidates[index],res,memo)==false) {
                memo.add(index+"_"+target);
            }
        }
        tmpRes[index]=0;
        if(memo.contains(index+"_"+target)){
            return false;
        } else {
            return true;
        }
    }
}
