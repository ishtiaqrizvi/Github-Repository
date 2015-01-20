
package mybean;
/**
 * @author Ishtiaq
 */

public class Bean {
    private String UserName;
    private String Password;
    private String Name;
    
    public String getUserName() {
        return UserName;
    }
     public void setUserName(String UserName){
         this.UserName=UserName;
     }
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name=Name;
    }

}