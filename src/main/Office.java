
package main;

import java.util.ArrayList;
import main.Employee;
import main.Calendar;

public class Office {
    String officeName;
    int numbEmployees;
    
    private int oneOrTwoPost; //0 -- только одна должность, 1 -- две должности  
    
    int idEmployeeValue = 0; //ID сотрудника, значение будет накапливаться с добавлением сотрудников
    String postName;
    Employee empl;
    ArrayList<Employee> employees = new ArrayList<>();
    
    Calendar calendar;
    
    int commandNumbInHour; //каждый час Директор дает одно или более одного распоряжения
    String commandName; //записывается название распоряжения
    int hoursToDoComandValue; //записывается значение кол-во  часов на выполнение распоряжения (1 или 2)
    Commands comm; //экземпляр класса Commands
    ArrayList<Commands> commands = new ArrayList<>(); // Лист для экземпляров Comm
    
    
    final String posts[] = {"Программист", "Дизайнер", "Тестеровщик", "Менеджер", "Директор", 
        "Бухгалтер", "Уборщик"};
    
    final String[] AllComands = {"Писать код", "Рисовать макет", "Тестировать программу", 
        "Продавать услуги", "Составить отчетность", "Выполнить уборку в офисе"};
    
    Office(){
        
    }
    
    
    
    public void generateEmployees(){
        this.numbEmployees = 10 + (int) (Math.random() * 90); 
//        this.numbEmployees = 10 + (int) (Math.random() * 30);
        
        for(int i = 0; i < this.numbEmployees; i++){
            this.idEmployeeValue++;
//В фирме должны быть хотя бы один Директор, Менеджер и Бухгалтер:
            if(i == 0)
                postName = "Директор";
            
            if(i == 1)
                postName = "Менеджер";
            
            if(i == 2)
                postName = "Бухгалтер";
            
            if(i > 3)
                postName = posts[(int) (Math.random() * posts.length)];
            
            empl = new Employee(idEmployeeValue, postName);
            oneOrTwoPost = 1 + (int) (Math.random() * 2);
            
            if( oneOrTwoPost == 1){
                if(empl.employeePost.equals("Директор") || empl.employeePost.equals("Бухгалтер")){
                    postName = "Менеджер";
                    empl.setEmployeePost2(postName);
                }
                if(empl.employeePost.equals("Программист")){
                    postName = posts[1 + (int) (Math.random() * 3)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Дизайнер")){
                    while(postName == "Дизайнер")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Тестеровщик")){
                    while(postName == "Тестеровщик")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
                
                if(empl.employeePost.equals("Менеджер")){
                    while(postName == "Менеджер")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    empl.setEmployeePost2(postName);
                }
            }
            employees.add(empl);
        }
    }   

    
    public void getEmployees(){
        for(int i = 0; i < numbEmployees; i++){
            System.out.println("ID:" + employees.get(i).getIdEmployee() + "  Priority post: " + employees.get(i).employeePost + 
                    "  Second post:" + employees.get(i).employeePost2); // + 
//                    "  isBusy:" + employees.get(i).isBusy);
        }
    }
    
    public void getCommands(){
        for(int i = 0; i < this.commands.size(); i++){
            System.out.println("#" + (i+1) + "  Command name: " + commands.get(i).command +
                    "  Hours to do: " + commands.get(i).hoursToDoComand +
                    "  Performer:" + commands.get(i).performer);
        }
    }
    
    public void generateCalendar(String mounth, String year){
        
        String startDate = "01 " + mounth + " " + year + " 00 00";
        this.calendar = new Calendar(startDate);
    }
    
    private void selectionPerformer(String neededPost, String commandNameValue){
        if(this.commandName == commandNameValue){
            for(int j = 0; j < numbEmployees; j++){
                if(this.employees.get(j).employeePost == neededPost || this.employees.get(j).employeePost2 == neededPost){
                    if(this.employees.get(j).isBusy == false){
//                        System.out.println(this.employees.get(j).employeePost + "  " + this.employees.get(j).getIdToString());
                        this.comm.setPerformer(this.employees.get(j).getIdToString());
                        this.employees.get(j).isBusy = true;
                        break;
                    }
                }
            }
        }
    }
    
    public void doWork(){
        

    for(int i = 0; i < this.numbEmployees; i++){
        if(this.employees.get(i).employeePost == "Директор" || this.employees.get(i).employeePost2 == "Директор"){
            int comandsNumbAtOnce; //количество распоряжений, изданных директором за один раз (значения: 1 и более)
            comandsNumbAtOnce = 1 + (int) (Math.random() * 3);
            
//            System.out.println("Директор №" + this.employees.get(i).getIdToString() + 
//                    "  comandsNumbAtOnce: " + comandsNumbAtOnce);
            
            for(int commandIndex = 0; commandIndex < comandsNumbAtOnce; commandIndex++){
                this.commandName =  this.AllComands[0 + (int) (Math.random() * 4)]; //директор выбирает распоряжение
                this.hoursToDoComandValue = 1 + (int) (Math.random() * 2); //длительность выполнения распоряжения

                this.comm = new Commands(commandName, hoursToDoComandValue);

                this.selectionPerformer("Программист", "Писать код");
                this.selectionPerformer("Дизайнер", "Рисовать макет");
                this.selectionPerformer("Тестеровщик", "Тестировать программу");
                this.selectionPerformer("Менеджер", "Продавать услуги");
                this.selectionPerformer("Бухгалтер", "Составить отчетность");
                this.selectionPerformer("Уборщик", "Выполнить уборку в офисе");

                if(this.comm.performer == null && this.comm.command != "Выполнить уборку в офисе")
                    this.comm.setPerformer("Фрилансер");
                
                this.commands.add(comm);
            }
        }
    }
        
    System.out.println("Process Date " + calendar.getProcessDate());
    calendar.nextHourDate();
    System.out.println("Process Date " + calendar.getProcessDate());
    calendar.nextWeekDate();
    System.out.println("Process Date " + calendar.getProcessDate());
        
    }
    
    
    
}
