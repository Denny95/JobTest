
package main;


public class Commands {
    String command; //Название распоряжения
    String performer = null; //Исполнитель этого распоряжения
    String commandPriority;
    
    int hoursToDoComand = 0;  // Сотрудник(либо фрилансер) за 1 или 2 часа выполняет распоряжение
    
    Commands(){
        
    }
    
    Commands(String command){
        this.command = command;
    }
    
    Commands(String command, int hoursToDoComand){
        this.command = command;
        this.hoursToDoComand = hoursToDoComand;
    }
    
    Commands(String command, int hoursToDoComand, String performer){
        this.command = command;
        this.hoursToDoComand = hoursToDoComand;
        this.performer = performer;
    }
    
    public void setPerformer(String performer){
        this.performer = performer;
    }
}
