/*
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
 
 class LetterCombinations_17 {
    public List<String> letterCombinations(String digits) {
        Map<Character,String> map = new HashMap<Character, String>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        List<String> res = new LinkedList<>();
        if(digits.length()==0){
            return res;
        }
        char[] tmpRes=new char[digits.length()];
        letterCombinationsRecur(res,tmpRes, 0,digits,map);
        return res;
    }
    
    void letterCombinationsRecur(List<String> res, char[] tmpRes, int index, String digits, Map<Character, String> map){
        if(index>=digits.length()){
            res.add(new String(tmpRes));
            return;
        }
        char c=digits.charAt(index);
        String value=map.get(c);
        for(int i=0;i<value.length();i++){
            tmpRes[index]=value.charAt(i);
            letterCombinationsRecur(res,tmpRes,index+1,digits,map);
        }
    }
}
