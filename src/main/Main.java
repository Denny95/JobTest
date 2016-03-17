
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
    }
    
}
