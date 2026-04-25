class Solution {
    public boolean canAliceWin(int n) {
    int flag =10 ;
    boolean alice=true;
    while(n>=flag)
    {
        n = n -flag;
        flag = flag -1;
        alice = !alice;
    
    if(flag<1)
    {
        break;
    }
    }
    return !alice;

    
    //return (n<10)?true:false;
    }
}