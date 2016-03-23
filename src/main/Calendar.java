
package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Calendar {
    
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    
    private LocalDateTime processDate; 
    
    public Calendar(){
        
    }
    
    public Calendar(String startDate){
        
        try {
 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH mm");
            this.startDate = LocalDateTime.parse(startDate, formatter);
//            System.out.println(this.startDate);
        } catch (Exception exc) {
            System.out.println(exc);
        }
          
        this.finishDate = this.startDate.plusMonths(01);
        this.processDate = this.startDate;
        
        System.out.println("Start Date:" + this.startDate);
        System.out.println("Finish Date:" + this.finishDate);
    }
    
    public LocalDateTime getProcessDate(){
        return this.processDate;
    }
    
    public void nextHourDate(){
        this.processDate = this.processDate.plusHours(1);
    }
    
    public void nextDayForGenerateEmployee(){
        
    }
    
    public void nextWeekDate(){
        this.processDate = this.processDate.plusWeeks(1);
    }
    
        
    
    
}
