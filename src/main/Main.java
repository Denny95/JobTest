
package main;

import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.Timer;


public class Main {


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        
        System.out.println("Тестовое задание на должность Специалиста по автоматизации тестирования\n\n");
        
        Office office = new Office();

        int mounth = 0;
        int year = 0;
        Scanner in = new Scanner(System.in);
        
        while(mounth < 1 || mounth > 12){
            System.out.print("Введите месяц (01 - 12): ");
            mounth = in.nextInt();
          }
        
        while(year < 1970 || year > 2055){
            System.out.print("Введите год (1970 - 2055): ");
            year = in.nextInt();
          }
  
        String mounthString = "";
        if(mounth >= 1 && mounth <= 9)
            mounthString = "0" + String.valueOf(mounth);
        else
            mounthString = String.valueOf(mounth);
        
        office.generateCalendar(mounthString, String.valueOf(year));
        System.out.println("Генерация календаря прошла успешно!");
        
        office.generateEmployees();
        System.out.println("Генерация сотрудников прошла успешно!");
        
        Thread.sleep(2000);
        
        int choice = 0;
        
         while(choice != 9){
            System.out.print("Выберете пункт меню:\n"
                    + "1 -- Генерировать один час рабочего процесса\n"
                    + "2 -- Генерировать 5 часов рабочего процесса\n"
                    + "3 -- Генерировать день полный рабочего процесса\n"
                    + "4 -- Генерировать неделю полный рабочего процесса\n"
                    + "5 -- Вывести всех сотрудников\n"
                    + "6 -- Вывести всех сотрудников вместе с их рабочим графиком\n"
                    + "7 -- Вывести календарь\n"
                    + "8 -- Вывести все распоряжения\n"
                    
                    + "Ваш выбор: ");
            choice = in.nextInt();
            
            while(choice < 1 || choice > 8){
                System.out.print("Выберете снова: ");
                 choice = in.nextInt();
            }
            
            switch(choice){
                case 1:
                    office.doWorkHour();
                    Thread.sleep(3000);
                    break;                   
                case 2:
                    office.doWorkFiveHour();
                    Thread.sleep(3000);
                    break;
                case 3:
                    office.doWorkAllDay();
                    Thread.sleep(3000);
                    break;
                case 4:
                    office.doWorkAllWeek();
                    Thread.sleep(3000);
                    break;
                case 5:
                    office.getEmployeesToPrint();
                    Thread.sleep(3000);
                    break;
                case 6:
                    office.getEmployeesWithScheduleToPrint();
                    Thread.sleep(3000);
                    break;
                case 7:
                    office.getCalendarDataToPrint();
                    Thread.sleep(3000);
                    break;
                case 8:
                    office.getCommandsToPrint();
                    Thread.sleep(3000);
                    break;
            }
         }
    }
}
