
package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import main.Calendar;

public class EmployeeSchedule {
    private final int maxWorkingHoursInDay = 8; //максимальное число часов, которое может отработать сотрудник в день
    private final int maxWorkingHoursInWeek = 40; //максимальное число часов, которое может отработать сотрудник в неделю

    private LocalTime employeeStartWorking; //Начало рабочего дня сотрудника
    private LocalTime employeeFinishWorking; //Конец рабочего дня сотрудника
    
    public LocalTime[][] shceduleOfWeek; //Массив рабочего графика определенного сотрудника [6][2]: пример [0][0] - понедельник, начало рабочего дня
    
    int workPeriodOfDay = 0;
    int workPeriodOfWeek = 0;
    
    public Calendar caledar;

    public EmployeeSchedule() {
    }
    
    public void generateEmployeeShcedule(){
        this.employeeStartWorking = LocalTime.of(0, 0);
        this.employeeFinishWorking = LocalTime.of(0, 0);
        
        shceduleOfWeek = new LocalTime[7][2];
        int j;
        boolean isWorkPeriodTrue = true;
        
        for(int i = 0; i < 7; i++){
            j = 0;
            this.workPeriodOfDay = 0; //сбрасываем время отработанных часов при начале следующего дня

            if(isWorkPeriodTrue == false){
                this.shceduleOfWeek[i][j] = LocalTime.of(0, 0); //записываем начало рабочего дня сотрудника
                this.shceduleOfWeek[i][j+1] = LocalTime.of(0, 0);
            }
            else{
                if(i <= 4) //рабочие дни
                    while(this.workPeriodOfDay < 5){
                        this.randomWorkingTimeInWorkDay();
                        if(this.workPeriodOfDay >= 5)
                            break;
                    }
                if(i > 4) // выходные
                    while(this.workPeriodOfDay < 3){
                        this.randomWorkingTimeInWeekends();
                        if(this.workPeriodOfDay >= 3)
                            break;
                    }

    //                    System.out.println(this.employeeStartWorking + " " + this.employeeFinishWorking);
                this.shceduleOfWeek[i][j] = this.employeeStartWorking; //записываем начало рабочего дня сотрудника
                this.shceduleOfWeek[i][j+1] = this.employeeFinishWorking; //записываем конец рабочего дня сотрудника
            
                this.periodOfWorkingHoursOfWeek();
                if(this.workPeriodOfWeek > maxWorkingHoursInWeek){
                    isWorkPeriodTrue = false;
                }
            }
        }
        
    }
    
    private void randomWorkingTimeInWorkDay(){

            this.employeeStartWorking = LocalTime.of((9 + (int) (Math.random() * 8)), 0); // от 9 до 18
            this.employeeFinishWorking = LocalTime.of(((this.employeeStartWorking.getHour() + 1) + (int) (Math.random() * (19 - this.employeeStartWorking.getHour()))) , 0);

            this.periodOfWorkingHoursOfDay(); //накапливаем рабочие часы сотрудника за каждый день до конца недели

    }
    
    private void randomWorkingTimeInWeekends(){
        while(this.workPeriodOfDay < this.maxWorkingHoursInDay){
            this.employeeStartWorking = LocalTime.of((10 + (int) (Math.random() * 4)) , 0); // от 10 до 15
            this.employeeFinishWorking = LocalTime.of(((this.employeeStartWorking.getHour() + 1 ) + (int) (Math.random() * (16 - this.employeeStartWorking.getHour()))) , 0);

            this.periodOfWorkingHoursOfDay(); //накапливаем рабочие часы сотрудника за каждый день до конца недели
            if(this.workPeriodOfDay < this.maxWorkingHoursInDay)
                break;
        }
    }
    
    private void periodOfWorkingHoursOfDay(){ //вычислить период, тоесть отработанные часы сотрудником в день
        this.workPeriodOfDay = this.employeeFinishWorking.getHour() - this.employeeStartWorking.getHour();
    }
    
    private void periodOfWorkingHoursOfWeek(){
        this.workPeriodOfWeek += this.workPeriodOfDay;
    }
    
    public void getEmployeeSheduleOfWeek(){
        System.out.println("Понедельник:  с " + this.shceduleOfWeek[0][0] + " до " + this.shceduleOfWeek[0][1] +
                "\nВторник: с " + this.shceduleOfWeek[1][0] + " до " + this.shceduleOfWeek[1][1] +
                "\nСреда: с " + this.shceduleOfWeek[2][0] + " до: " + this.shceduleOfWeek[2][1] +
                "\nЧетверг: с " + this.shceduleOfWeek[3][0] + " до " + this.shceduleOfWeek[3][1] +
                "\nПятница: с " + this.shceduleOfWeek[4][0] + " до: " + this.shceduleOfWeek[4][1] +
                "\nСуббота: с " + this.shceduleOfWeek[5][0] + " до " + this.shceduleOfWeek[5][1] +
                "\nВоскресенье: с " + this.shceduleOfWeek[6][0] + " до " + this.shceduleOfWeek[6][1]);
        
        System.out.println("Всего часов в неделю: " + this.workPeriodOfWeek);
    }
}
