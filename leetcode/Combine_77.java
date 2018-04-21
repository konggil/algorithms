/*
 * https://leetcode.com/problems/combinations/description/
 */
 
 class Combine_77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        int[] oneRes = new int[k];
        combineRecur(0,1,n,oneRes,res);
        return res;
    }
    
    void combineRecur(int index,int start, int n,int[] oneRes,List<List<Integer>> res){
        if(index==oneRes.length){
            List<Integer> tmpRes = new LinkedList<Integer>();
            for(int i:oneRes) {
                tmpRes.add(i);
            }
            res.add(tmpRes);
            return;
        }
        for(int i=start;i<=n-oneRes.length+index+1;i++) {
            oneRes[index]=i;
            combineRecur(index+1,i+1,n,oneRes,res);
        }
    }
}
