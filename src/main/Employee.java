
package main;

import main.Calendar;
import main.EmployeeSchedule;

public class Employee {
    
    
    private int idEmployee;
    public String employeePost;
    public String employeePost2;
//    public String employeePost3;
    
    public int salaryPost = 0;
    
    public int salaryGet = 0;
            
    public EmployeeSchedule shedule;
    
    boolean isBusy = false;
    boolean isWorking = false;
    
    Employee(){
        
    }
    
    Employee(int idEmployee, String postName){
        this.idEmployee = idEmployee;
        this.employeePost = postName;
        
        this.shedule = new EmployeeSchedule();
        this.shedule.generateEmployeeShcedule();
    }
    
     Employee(int idEmployee, String postName, int postSalary){
        this.idEmployee = idEmployee;
        this.employeePost = postName;
        this.salaryPost = postSalary;
        
        this.shedule = new EmployeeSchedule();
        this.shedule.generateEmployeeShcedule();
    }
    
    
    Employee(int idEmployee, String postName, String postName2){
        this.idEmployee = idEmployee;
        this.employeePost = postName;
        this. employeePost2 = postName2;
        
        this.shedule = new EmployeeSchedule();
        this.shedule.generateEmployeeShcedule();
    }
    
     public void setEmployeePost2(String postName, int postSalary){
        this.employeePost2 = postName;
        this.salaryPost = postSalary;
    }
     
//    public void setEmployeePost2(String postName){
//        this.employeePost2 = postName;
//    }
    
         
//    public void setEmployeePost2(String postName, int postSalary){
//        this.employeePost2 = postName;
//        this.salaryPost2 = postSalary;
//    }
    
    public int getIdEmployee(){
        return this.idEmployee;
    }
    
    public String getIdToString(){
        return Integer.toString(this.idEmployee);
    }
    
    public void generateWorkStatus(){
        
    }
    
}
