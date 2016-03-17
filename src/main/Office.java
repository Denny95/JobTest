
package main;

import java.util.ArrayList;
import main.Employee;
import main.Calendar;

public class Office {
    String officeName;
    int numbEmployees;
    
//    int numbProgrammers;
//    int numbDesigners;
//    int numbTesters;
//    int numbManagers;
//    int numbDirectors;
//    int numbAccountants;
//    int numbCleaner;
    
    private int oneOrTwoPost; //0 -- только одна должность, 1 -- две должности  
    
    String postName;
    Employee empl;
    ArrayList<Employee> employees = new ArrayList<>();
    
    Calendar calendar = new Calendar();
    
    
    String post[] = {"Программист", "Дизайнер", "Тестеровщик", "Менеджер", "Директор", 
        "Бухгалтер", "Уборщик"};
    
    Office(){
        
    }
    
    
    
    public void generateEmployees(){
        this.numbEmployees = 10 + (int) (Math.random() * 90);  
        
        for(int i = 0; i < this.numbEmployees; i++){
//В фирме должны быть хотя бы один Директор, Менеджер и Бухгалтер:
            if(i == 0)
                postName = "Директор";
            
            if(i == 1)
                postName = "Менеджер";
            
            if(i == 2)
                postName = "Бухгалтер";
            
            if(i > 3)
                postName = post[(int) (Math.random() * post.length)];
            
            empl = new Employee(postName);
            oneOrTwoPost = 1 + (int) (Math.random() * 2);
            
            if( oneOrTwoPost == 1){
                if(empl.employeePost.equals("Директор") || empl.employeePost.equals("Бухгалтер")){
                    postName = "Менеджер";
                    empl.setEmployeePost2(postName);
                }
                if(empl.employeePost.equals("Программист")){
                    postName = post[1 + (int) (Math.random() * 3)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Дизайнер")){
                    while(postName == "Дизайнер")
                        postName = post[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Тестеровщик")){
                    while(postName == "Тестеровщик")
                        postName = post[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Менеджер")){
                    while(postName == "Менеджер")
                        postName = post[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
            }
            employees.add(empl);
        }
    }   

    
    public void getEmployees(){
        for(int i = 0; i < numbEmployees; i++){
            System.out.println("#" + (i+1) + "  Priority post: " + employees.get(i).employeePost + 
                    "  Second post:" + employees.get(i).employeePost2);
        }
    }
    
    public void generateCalendar(String mounth, String year){
        
        String startDate = "01 " + mounth + " " + year + " 00 00";
        this.calendar = new Calendar(startDate);
    }
    
    public void doWork(){
        calendar.nextHourDate();
        System.out.println("Process Date " + calendar.getProcessDate());
        calendar.nextWeekDate();
        System.out.println("Process Date " + calendar.getProcessDate());
    }
    
    
    
}
