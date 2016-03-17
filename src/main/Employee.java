
package main;

import main.Office;

public class Employee {
    
    private final int maxWorkingHoursInDay = 8;
    private final int maxWorkingHoursInWeek = 40;
    
    public String employeePost;
    public String employeePost2;
    public String employeePost3;
    int workingDay;
    int workingWeek;
    
    public void 
    
    Employee(){
        
    }
    
    Employee(String postName){
        employeePost = postName;
    }
    
    
    Employee(String postName, String postName2){
        employeePost = postName;
        employeePost2 = postName2;
    }
    
    public void setEmployeePost2(String postName){
        this.employeePost2 = postName;
    }
    
}
