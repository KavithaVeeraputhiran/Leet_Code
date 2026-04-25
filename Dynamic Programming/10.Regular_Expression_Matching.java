// if we konw the consand value at compile time
enum Result{
    TRUE,FALSE    // either trur or false
}

class Solution {
     Result[][] memo;
    public boolean isMatch(String s, String p) {
       memo = new Result[s.length()+1][p.length()+1];
       return find(0,0,s,p); //function call 
    }
    public boolean find(int i,int j,String s,String p)
    {
        if(memo[i][j]!= null)
        {
            return memo[i][j]==Result.TRUE;
        }
        boolean ans;
        if(j== p.length())     // if the length of 
        {
            ans = i == s.length();
        }
        else
        {
            boolean first_match=(i<s.length() && (p.charAt(j)==s.charAt(i)|| p.charAt(j)=='.'));
            
            
            // check the pattern is *  
            if(j+1<p.length() && p.charAt(j+1)=='*')
            {
                
                ans=(find(i,j+2,s,p)|| first_match && find(i+1,j,s,p));
            }
            else{
                // check the char at the next postion if next string match with the pattern like s=aa then p=aa this case checks here.
                ans = first_match && find(i+1,j+1,s,p);
            }
        }
        memo [i][j]=ans?Result.TRUE:Result.FALSE;
        return ans;
    }
}