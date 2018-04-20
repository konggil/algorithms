/*
 * https://leetcode.com/problems/binary-watch/description/
 */
 
 class ReadBinaryWatch_401 {
    public List<String> readBinaryWatch(int num) {
        List<String> res=new LinkedList<>();
        int[] ts=new int[]{0,0,0,0,0,0,0,0,0,0};
        readBinaryWatchRecur(num,9,ts,res);
        return res;
    }
    
    void readBinaryWatchRecur(int leftNum,int index,int[] ts,List<String> res){
        if(leftNum==0){
            convertToTimeStr(ts,res);
            return;
        }
        for(int i=index;i>=leftNum-1;i--){
            ts[i]=1;
            readBinaryWatchRecur(leftNum-1,i-1,ts,res);
            ts[i]=0;
        }
        
    }
    
    void convertToTimeStr(int[] ts,List<String> res){
        int hour=ts[0]*8+ts[1]*4+ts[2]*2+ts[3];
        if(hour>11)
            return;
        int min=ts[4]*32+ts[5]*16+ts[6]*8+ts[7]*4+ts[8]*2+ts[9];
        if(min>59)
            return;
        res.add(hour+(min<10?":0":":")+min);
    }
}
