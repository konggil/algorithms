/*
 * https://leetcode.com/problems/plus-one/description/
 */
 
 class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        int i;
        int[] res;
        for(i=0; i<digits.length; i++) {
            if(digits[i] != 9) {
                break;
            }
        }
        if(i == digits.length) {
            res = new int[digits.length+1];
            res[0] = 1;
            for(i=1; i<res.length; i++){
                res[i] = 0;
            }
        } else {
            res = new int[digits.length];
            int carry = 1;
            for(i=digits.length-1; i>=0; i--) {
                if(digits[i] + carry < 10) {
                    res[i] = digits[i] + carry;
                    i--;
                    break;
                }
                res[i] = 0;
            }
            for(; i>=0; i--){
                res[i] = digits[i];
            }      
        }
        return res;
    }
}
