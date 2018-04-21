/*
 * https://leetcode.com/problems/multiply-strings/description/
 */
 
 class Multiply_43 {
    public String multiply(String num1, String num2) {
        int[] res = new int[230];
        int[] num1Int = new int[num1.length()];
        int i,j;
        for(i=0; i<res.length; i++) {
            res[i] = 0;
        }
        for(i=0; i<num1.length(); i++) {
            num1Int[i] = num1.charAt(num1.length()-1-i) - '0';
        }
        for(i=0; i<num2.length(); i++) {
            int digit = num2.charAt(num2.length()-1-i) - '0';
            int carry = 0;
            for(j=0; j<num1.length(); j++) {
                res[i+j] += num1Int[j] * digit + carry;
                if(res[i+j] >= 10) {
                    carry = res[i+j] / 10;
                    res[i+j] %= 10;
                } else {
                    carry = 0;
                }
            }
            while(carry != 0) {
                res[i+j] += carry % 10;
                if(res[i+j] >= 10) {
                    carry += res[i+j] / 10;
                    res[i+j] %= 10;
                } else {
                    carry = 0;
                }
                j++;
            }
        }
        StringBuilder resStr = new StringBuilder();
        for(i=res.length-1; i>=0 && res[i]==0; i--);
        for(; i>=0; i--) {
            resStr.append(res[i]);
        }
        if(resStr.length() == 0) {
            resStr.append(0);
        }
        return resStr.toString();
    }
}
