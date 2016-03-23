
package main;


public class Main {


    public static void main(String[] args) {
        
        Office office = new Office();
        
        office.generateEmployees();
        office.getEmployees();
        
        String mounth = "03";
        String year = "2016";
        office.generateCalendar(mounth, year);
        
        office.doWork();
        office.getCommands();
        
//        for(int i = 0; i<6; i++)
//            System.out.println(office.comands[0 + (int) (Math.random() * 6)]);
    }
}
