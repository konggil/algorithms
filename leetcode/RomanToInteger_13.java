/*
 * https://leetcode.com/problems/roman-to-integer/description/
 */

class RomanToInteger_13 {
    public int romanToInt(String s) {
        Map<Character,Integer> map=new HashMap<Character,Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int res=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='I'||c=='X'||c=='C'){
                if(i+1<s.length()){
                    char nextC=s.charAt(i+1);
                    if(c=='I'&&(nextC=='V'||nextC=='X')){
                        res-=1;
                    } else if(c=='X'&&(nextC=='L'||nextC=='C')){
                        res-=10;
                    } else if(c=='C'&&(nextC=='D'||nextC=='M')){
                        res-=100;
                    } else {
                        res+=map.get(c);
                    }
                } else {
                    res+=map.get(c);
                }
            }else if(map.get(c)!=null){
                res+=map.get(c);
            }
        }
        return res;
    }
}
