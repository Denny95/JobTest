
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.exit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.Employee;
import main.Calendar;

public class Office {
    String officeName;
    int numbEmployees;
    
    private int oneOrTwoPost; //0 -- только одна должность, 1 -- две должности  
    
    int idEmployeeValue = 0; //ID сотрудника, значение будет накапливаться с добавлением сотрудников
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
    
    final int salary[] = {25, 20, 16, 17, 40, 17, 3}; //почасовые ставки
    
    int salaryCostNeed; // Офис должен выплатить зарплату
    int salaryCostGive = 0; ///Офис выплатил зарплату
    
    int salaryCostFrilasersNeed = 0; // Офис должен выплатить фринасерам
    int salaryCostFrilasersGive = 0; // Офис выплатил фрилансерам
    
    private int commandCost; //стоимость выполнения определенного поручения (для фрилансеров)
    
    public Office(){
        
    }
    
    
    
    public void generateEmployees(){
        String postName = "";
        int postSalary = 0;
        
        this.numbEmployees = 10 + (int) (Math.random() * 90); 
//        this.numbEmployees = 10 + (int) (Math.random() * 30);
        
        for(int i = 0; i < this.numbEmployees; i++){
            this.idEmployeeValue++;
//В фирме должны быть хотя бы один Директор, Менеджер и Бухгалтер (поэтому генерируем их первыми):
            if(i == 0){
                postName = "Директор";
                for(int q = 0; q<this.posts.length; q++)
                    if(this.posts[q].equals(postName))
                        postSalary = this.salary[q];
            }
            if(i == 1){
                postName = "Менеджер";
                for(int q = 0; q<this.posts.length; q++)
                    if(this.posts[q].equals(postName))
                        postSalary = this.salary[q];
            }
            
            if(i == 2){
                postName = "Бухгалтер";
                for(int q = 0; q<this.posts.length; q++)
                    if(this.posts[q].equals(postName))
                        postSalary = this.salary[q];
            }
            
            if(i > 3){
                postName = posts[(int) (Math.random() * posts.length)]; //если цикл больше 3 генерируется случайная должность
                for(int q = 0; q<this.posts.length; q++)
                    if(this.posts[q].equals(postName))
                        postSalary = this.salary[q];
            }
            
            empl = new Employee(idEmployeeValue, postName, postSalary);
            oneOrTwoPost = 1 + (int) (Math.random() * 2); // 0 -- одна должность, 1 -- две должности
            
            if( oneOrTwoPost == 1){
                if(empl.employeePost.equals("Директор") || empl.employeePost.equals("Бухгалтер")){
                    postName = "Менеджер";
                    for(int q = 0; q<this.posts.length; q++)
                        if(this.posts[q].equals(postName))
                            postSalary += this.salary[q];
                                    
                    empl.setEmployeePost2(postName, postSalary);
                }
                if(empl.employeePost.equals("Программист")){
                    postName = posts[1 + (int) (Math.random() * 3)];
                    for(int q = 0; q<this.posts.length; q++)
                        if(this.posts[q].equals(postName))
                            postSalary += this.salary[q];
                    
                    empl.setEmployeePost2(postName, postSalary);
                }
                
                if(empl.employeePost.equals("Дизайнер")){
                    while(postName == "Дизайнер")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    for(int q = 0; q<this.posts.length; q++)
                        if(this.posts[q].equals(postName))
                            postSalary += this.salary[q];
                    
                    empl.setEmployeePost2(postName, postSalary);
                }
                
                if(empl.employeePost.equals("Тестеровщик")){
                    while(postName == "Тестеровщик")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    for(int q = 0; q<this.posts.length; q++)
                        if(this.posts[q].equals(postName))
                            postSalary += this.salary[q];
                    empl.setEmployeePost2(postName, postSalary);
                }
                
                if(empl.employeePost.equals("Менеджер")){
                    while(postName == "Менеджер")
                        postName = posts[0 + (int) (Math.random() * 4)];
                    for(int q = 0; q<this.posts.length; q++)
                        if(this.posts[q].equals(postName))
                            postSalary += this.salary[q];
                    empl.setEmployeePost2(postName, postSalary);
                }
            }
            employees.add(empl);
        }
    }

    
    private String getEmployeeToString(int index){
       
            return "ID: " + employees.get(index).getIdEmployee() 
                    + "  |Первый должность: " + employees.get(index).employeePost
                    + "  |Ставка: " + employees.get(index).salaryPost
                    + "  |Второй пост: " + employees.get(index).employeePost2 
//                    + "  |Ставка должность 2: " + employees.get(index).salaryPost2
                    + "  |Статус присутствия на работе: " + employees.get(index).isWorking
                    + "  |Стату занятости: " + employees.get(index).isBusy;
    }
    
    public void getEmployeesToPrint(){
        for(int i = 0; i < numbEmployees; i++){
            System.out.println(this.getEmployeeToString(i));
//            this.employees.get(i).shedule.getEmployeeSheduleOfWeek();
        }
    }
    
    public void getEmployeesWithScheduleToPrint(){
        for(int i = 0; i < numbEmployees; i++){
            System.out.println(this.getEmployeeToString(i));
            this.employees.get(i).shedule.getEmployeeSheduleOfWeek();
        }
    }
    
    private String getCommandsToString(int index){
        return "#" + (index+1) + "  Указание: " + commands.get(index).command
                + "  Время на выполнение: " + commands.get(index).hoursToDoComand
                + "  ID исполнителя:" + commands.get(index).performer;
    }
    
    public void getCommandsToPrint(){
        for(int i = 0; i < this.commands.size(); i++){
            System.out.println(this.getCommandsToString(i));
        }
    }
    
    private String getCalendarDataToSring(){
        return "Дата начала периода работы: " + calendar.getProcessStartDate() + 
                "\nДата окончания периода работы: " + calendar.getProcessFinishtDate() +
                "\nТекущая дата: " + calendar.getProcessDate() + "  День недели: " + calendar.getProcessDate().getDayOfWeek();
    }
    
    public void getCalendarDataToPrint(){
        System.out.println(this.getCalendarDataToSring());
    }
    
    public void generateCalendar(String mounth, String year){
        
        String startDate = "01 " + mounth + " " + year + " 00 00";
        this.calendar = new Calendar(startDate);
        
        this.generateWorkStatusForEmployee();
    }
    
    private void selectionPerformer(String neededPost, String commandNameValue){
        if(this.commandName == commandNameValue){
            for(int j = 0; j < numbEmployees; j++){               
                if(this.employees.get(j).employeePost == neededPost || this.employees.get(j).employeePost2 == neededPost){   
                    if(this.employees.get(j).isBusy == false && this.employees.get(j).isWorking == true){
//                        System.out.println(this.employees.get(j).employeePost + "  " + this.employees.get(j).getIdToString());
                        this.comm.setPerformer(this.employees.get(j).getIdToString());
                        this.employees.get(j).isBusy = true;
                        break;
                    }
                }
            }
        }
    }

    private int generateCommandCostToFrilans(String commandNameValue){
        int commandCost = 0;
        if(commandNameValue.equals("Писать код"))
            commandCost = this.salary[0];
        if(commandNameValue.equals("Рисовать макет"))
            commandCost = this.salary[1];
        if(commandNameValue.equals("Тестировать программу"))
            commandCost = this.salary[2];
        if(commandNameValue.equals("Продавать услуги"))
            commandCost = this.salary[3];
        if(commandNameValue.equals("Составить отчетность"))
            commandCost = this.salary[5];
//        System.out.print("commandCost " + commandCost);
        return commandCost;
    }
    
    
    private void doWork(){
    for(int i = 0; i < this.numbEmployees; i++){
        if(this.employees.get(i).employeePost == "Директор" || this.employees.get(i).employeePost2 == "Директор"){
            if(this.employees.get(i).isWorking == true){
                int comandsNumbAtOnce; //количество распоряжений, изданных директором за один раз (значения: 1 и более)
                comandsNumbAtOnce = 1 + (int) (Math.random() * 3);

    //            System.out.println("Директор №" + this.employees.get(i).getIdToString() + 
    //                    "  comandsNumbAtOnce: " + comandsNumbAtOnce);

                for(int commandIndex = 0; commandIndex < comandsNumbAtOnce; commandIndex++){
                    int indexAllCommand = 1 + (int) (Math.random() * 6);
                    this.commandName =  this.AllComands[(indexAllCommand - 1)]; //директор выбирает распоряжение
                    
                    this.hoursToDoComandValue = 1 + (int) (Math.random() * 2); //длительность выполнения распоряжения

                    this.comm = new Commands(commandName, hoursToDoComandValue);

                    this.selectionPerformer("Программист", "Писать код");
                    this.selectionPerformer("Дизайнер", "Рисовать макет");
                    this.selectionPerformer("Тестеровщик", "Тестировать программу");
                    this.selectionPerformer("Менеджер", "Продавать услуги");
                    this.selectionPerformer("Бухгалтер", "Составить отчетность");
                    this.selectionPerformer("Уборщик", "Выполнить уборку в офисе");

                    if(this.comm.performer == null && this.comm.command != "Выполнить уборку в офисе"){
                        this.comm.setPerformer("Фрилансер");
                        this.salaryCostFrilasersNeed = this.salaryCostFrilasersNeed + this.generateCommandCostToFrilans(commandName);
//                        System.out.println("Офис должен выплатить фрилансерам: " + this.salaryCostFrilasersNeed);
                    }

                    this.commands.add(comm);
                }
            }
        }
    }
    System.out.print("Текущая дата " + calendar.getProcessDate());
    System.out.println("  Текущий день недели " + calendar.getProcessDate().getDayOfWeek()); 
        
}
    
    private void generateTimeComplCommand(){
        for(int i = 0;  i < this.commands.size(); i++){
            this.commands.get(i).hoursToDoComand --;
            
            if(this.commands.get(i).hoursToDoComand < 0)
                this.commands.get(i).hoursToDoComand = 0;
            
            if(this.commands.get(i).hoursToDoComand == 0){
                for(int j = 0; j < numbEmployees; j++){
                    if(this.commands.get(i).performer.equals(this.employees.get(j).getIdToString()) ){
                        this.employees.get(j).isBusy = false;
                    }
                }
            }
        }
    }
    
    public void doWorkHour(){
                
        this.generateTimeComplCommand();
        
        this.doWork();
        this.generateWorkStatusForEmployee();
        this.generateFrilansersSalaryGive(this.calendar.getProcessDate());
        this.generateEmployeeSalaryGive(this.calendar.getProcessDate());
        this.calendar.nextHourDate();   
    }
    
    public void doWorkFiveHour() throws FileNotFoundException{
        for(int i =0; i < 5; i++){
            this.generateTimeComplCommand();
            
            this.doWork();
            this.generateWorkStatusForEmployee();
            this.generateFrilansersSalaryGive(this.calendar.getProcessDate());
            this.generateEmployeeSalaryGive(this.calendar.getProcessDate());
            
            this.calendar.nextHourDate();
            this.doMounthReport();
        }
    }
    
    public void doWorkAllDay() throws FileNotFoundException{ 
        if(this.calendar.getProcessDate().getDayOfWeek().toString() != "SATURDAY" || 
                this.calendar.getProcessDate().getDayOfWeek().toString() != "SUNDAY" ){
             for(int i =0; i < 10; i++){
                this.generateTimeComplCommand();

                this.doWork();
                this.generateWorkStatusForEmployee();
                this.generateFrilansersSalaryGive(this.calendar.getProcessDate());
                this.generateEmployeeSalaryGive(this.calendar.getProcessDate());
                
                this.calendar.nextHourDate(); 
                this.doMounthReport();
            }
        }
        else
            for(int i =0; i < 6; i++){
                this.generateTimeComplCommand();
      
                this.doWork();
                this.generateWorkStatusForEmployee();
                this.generateFrilansersSalaryGive(this.calendar.getProcessDate());
                this.generateEmployeeSalaryGive(this.calendar.getProcessDate());
                
                this.calendar.nextHourDate(); 
                this.doMounthReport();
            }
    }
    
    public void doWorkAllWeek() throws FileNotFoundException{
        int nextWeekDate = this.calendar.getProcessDate().plusWeeks(1).getDayOfMonth();
        while(this.calendar.getProcessDate().getDayOfMonth() != nextWeekDate){
            this.generateTimeComplCommand();
                  
            this.doWork();
            this.generateWorkStatusForEmployee();
            this.generateFrilansersSalaryGive(this.calendar.getProcessDate());
            this.generateEmployeeSalaryGive(this.calendar.getProcessDate());
            
            this.calendar.nextHourDate(); 
            this.doMounthReport();       
        }
    }
    
    public void doReport() throws FileNotFoundException{
        String fileName = "Отчет_" + this.calendar.getProcessDate().getYear() 
                + "-" + this.calendar.getProcessDate().getMonth()
                + "-" + this.calendar.getProcessDate().getDayOfMonth()
                + ".txt";
        System.out.print(fileName);
        File file = new File(fileName);
         
      try (FileOutputStream outStream = new FileOutputStream(file);) {

          byte[] b = "ОБШИЕ ДАННЫЕ".getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = ("Всего выполненно работ: " + this.commands.size()).getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = ("Выплачено сотрудникам: " + this.salaryCostGive).getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = ("Выплачено фрилансерам: " + this.salaryCostFrilasersGive).getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = ("Всего выплаченно: " + (this.salaryCostFrilasersGive + this.salaryCostFrilasersGive)).getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = ("Всего выплаченно: " + (this.salaryCostGive + this.salaryCostFrilasersGive)).getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          b = "\r\n-------------------------".getBytes();
          outStream.write(b);
          
          b = "\r\nДАННЫЕ ПО СОТРУДНИКАМ".getBytes();
          outStream.write(b);
          b = "\r\n".getBytes();outStream.write(b);
          
          for(int i = 0; i < numbEmployees; i++){
              b = ("ID: " + this.employees.get(i).getIdToString()
                      + "  Должность 1: " + this.employees.get(i).employeePost
                      + "  Должность 2: " + this.employees.get(i).employeePost2
                      + "  Ставка:" + this.employees.get(i).salaryPost
                      + "  Часы в неделю:" + this.employees.get(i).shedule.workPeriodOfWeek
                      + "\r\nВыплаченно: " + this.employees.get(i).salaryGet
                      + "  Выполенные распоряжения:\r\n" 
                      + this.getEmployeeComleteCommands(this.employees.get(i).getIdToString())).getBytes();
              outStream.write(b);
              b = "\r\n".getBytes();outStream.write(b);
          }
          
          

         outStream.close();
         JOptionPane.showMessageDialog(null, "Запись в файл прошла успешно!");
      } catch (IOException exc) {
          JOptionPane.showMessageDialog(null, "Ошибка при записи в файл!\n", "Information", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    private void doMounthReport() throws FileNotFoundException{
        if(this.calendar.getProcessDate().getDayOfMonth() == this.calendar.getProcessFinishtDate().getDayOfMonth() &&
                this.calendar.getProcessDate().getMonthValue() == this.calendar.getProcessFinishtDate().getMonthValue()){
            this.doReport();
            System.out.println("\nРабота офиса в течении месяца выполнена!");
            exit(0);
        }
    }
    
    private String getEmployeeComleteCommands(String idEmpl){
        String commandsCompl = "";
        for(int i = 0; i < this.commands.size(); i++){
            if(idEmpl.equals(this.commands.get(i).performer))
                commandsCompl += "\r\n--" + this.commands.get(i).command + "\n";
        }
        return commandsCompl;
    }
    
    private void forGenerateWorkStatus(Employee empl, int startHour, int finishHour, String dayOfWeek){
         if(this.calendar.getProcessDate().getDayOfWeek().toString() == dayOfWeek){
               if(this.calendar.getProcessDate().getHour() >= startHour
                       &&
                       this.calendar.getProcessDate().getHour() <= finishHour){
                   empl.isWorking = true;
               }
               else
                   empl.isWorking = false;
         }
    }
    
    private void generateWorkStatusForEmployee(){ //выбор статауса на работе ли сотрудник в соответвии с его графиком
        for(int i = 0; i < numbEmployees; i++){
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[0][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[0][1].getHour() , "MONDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[1][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[1][1].getHour() , "TUESDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[2][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[2][1].getHour() , "WEDNESDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[3][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[3][1].getHour() , "THURSDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[4][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[4][1].getHour() , "FRIDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[5][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[5][1].getHour() , "SATURDAY");
            
            this.forGenerateWorkStatus(this.employees.get(i), this.employees.get(i).shedule.shceduleOfWeek[6][0].getHour(), 
                    this.employees.get(i).shedule.shceduleOfWeek[6][1].getHour() , "SUNDAY");

        }
    }
    
            
    private void  generateEmployeeSalaryGive(LocalDateTime processDate){
        int salaryCostGiveNow = 0;
        int salaryCostGiveNowOneEmpl = 0;
        if(processDate.getDayOfWeek().toString() == "SUNDAY" &&
                processDate.getHour() == 15){
            for(int i = 0; i < this.employees.size(); i++){
                 salaryCostGiveNowOneEmpl += 
                         this.employees.get(i).shedule.workPeriodOfWeek * this.employees.get(i).salaryPost;
                 salaryCostGiveNow += salaryCostGiveNowOneEmpl;
                 this.employees.get(i).salaryGet += salaryCostGiveNowOneEmpl;
                 this.salaryCostGive += salaryCostGiveNowOneEmpl;
                 salaryCostGiveNowOneEmpl = 0;
            }
            
            System.out.println("Зарплата сотрудникам выплачена: " + salaryCostGiveNow);
        }
    }
    
    private void generateFrilansersSalaryGive(LocalDateTime processDate){
        int salaryCostGiveNow = 0;
         if(processDate.getDayOfWeek().toString() != "SATURDAY" || 
                processDate.getDayOfWeek().toString() != "SUNDAY" ){
             if(processDate.getHour() == 18){
                 salaryCostGiveNow = this.salaryCostFrilasersNeed;
                 this.salaryCostFrilasersGive += this.salaryCostFrilasersNeed;
                 System.out.println("Оплата фрилансерам проведена и составляет: " + salaryCostGiveNow);
                 this.salaryCostFrilasersNeed = 0;
             }
         }
         else{
             if(processDate.getHour() == 15){
                 salaryCostGiveNow = this.salaryCostFrilasersNeed;
                 this.salaryCostFrilasersGive += this.salaryCostFrilasersNeed;
                 System.out.println("Оплата фрилансерам проведена и составляет: " + salaryCostGiveNow);
                 this.salaryCostFrilasersNeed = 0;
             }
         }      
    }

}
