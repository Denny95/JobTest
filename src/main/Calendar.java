
package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Calendar {
    
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    
    private LocalTime officeStartTimeInWorkDay = LocalTime.of(9, 0); //В рабочие дни офис работает с 9:00 утра
    private LocalTime officeFinishTimeInWorkDay = LocalTime.of(19, 0); //В рабочие дни офис работает до 19:00 утра
    
    private LocalTime officeStartTimeInWeekend = LocalTime.of(10, 0); //В выходные дни офис работает с 10:00 утра
    private LocalTime officeFinishTimeInWeekend = LocalTime.of(16, 0); //В рабочие дни офис работает до 16:00 утра;;;
    
    private LocalDateTime processDate = null;
    
    public LocalDateTime scheduleEmployeeDate;
    public LocalDateTime officeDate;
    
    public Calendar(){
        
    }
    
    public Calendar(String startDate){
        
        try {
 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH mm");
            this.startDate = LocalDateTime.parse(startDate, formatter);
        } catch (Exception exc) {
            System.out.println(exc);
        }
          
        this.finishDate = this.startDate.plusMonths(01);
        
        if(this.startDate.getDayOfWeek().toString() == "SATURDAY" 
                || this.startDate.getDayOfWeek().toString() == "SUNDAY"){
            this.processDate = LocalDateTime.of(this.startDate.toLocalDate(), officeStartTimeInWeekend);
        }
        else{
            this.processDate = LocalDateTime.of(this.startDate.toLocalDate(), officeStartTimeInWorkDay);
        }
        
        
        
        System.out.println("Дата начала периода работы офиса: " + this.startDate);
        System.out.println("День недели: " + this.startDate.getDayOfWeek());
        System.out.println("Дата завершения периода работы офиса: " + this.finishDate);
        System.out.println("День недели: " + this.finishDate.getDayOfWeek());
               
        System.out.println("Текущая дата рабочего процесса: " + this.processDate);
        System.out.println("День недели: " + this.processDate.getDayOfWeek());
    }
    
    
    public LocalDateTime getProcessDate(){
        return this.processDate;
    }
    
    public LocalDateTime getProcessStartDate(){
        return this.startDate;
    }
    
    public LocalDateTime getProcessFinishtDate(){
        return this.finishDate;
    }
    
    private void generateChooseHour(){
        if(this.startDate.getDayOfWeek().toString() == "SATURDAY"){
            if(this.processDate.getHour() == this.officeFinishTimeInWeekend.getHour()){
                this.processDate = this.processDate.plusDays(1);
                this.processDate = LocalDateTime.of(this.processDate.toLocalDate(), this.officeStartTimeInWeekend);
            }
        }
        if(this.startDate.getDayOfWeek().toString() == "SUNDAY"){
            if(this.processDate.getHour() == this.officeFinishTimeInWeekend.getHour()){
                this.processDate = this.processDate.plusDays(1);
                this.processDate = LocalDateTime.of(this.processDate.toLocalDate(), officeStartTimeInWorkDay);
            }
        }
        
        if(this.startDate.getDayOfWeek().toString() == "FRIDAY"){
            if(this.processDate.getHour() == this.officeFinishTimeInWorkDay.getHour()){
                this.processDate = this.processDate.plusDays(1);
                this.processDate = LocalDateTime.of(this.processDate.toLocalDate(), this.officeStartTimeInWeekend);
            }
        }
        
        if(this.startDate.getDayOfWeek().toString() != "SATURDAY"
                || this.startDate.getDayOfWeek().toString() != "SUNDAY"){
            if(this.processDate.getHour() == this.officeFinishTimeInWorkDay.getHour()){
                this.processDate = this.processDate.plusDays(1);
                this.processDate = LocalDateTime.of(this.processDate.toLocalDate(), this.officeStartTimeInWorkDay);
            }
        }
    }
    
    public void nextHourDate(){
        this.processDate = this.processDate.plusHours(1);
        
        this.generateChooseHour();
    }
    
    
    public void nextWeekDate(){
        this.processDate = this.processDate.plusWeeks(1);
    }
    
    public void generateEmployeeSchedule(){
        
    }
    
}
