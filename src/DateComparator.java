import java.util.Comparator;

public class DateComparator implements Comparator<String> {

    @Override
    public int compare(String date1, String date2) {
        String year1 = date1.substring(4, 6);
        String year2 = date2.substring(4, 6);
        int yearComparison = year1.compareTo(year2);
        if (yearComparison != 0) {
            return yearComparison;
        }

        String month1 = date1.substring(0, 2);
        String month2 = date2.substring(0, 2); 
        int monthComparison = month1.compareTo(month2);
        if (monthComparison != 0) {
            return monthComparison;
        }

        String day1 = date1.substring(2, 4); 
        String day2 = date2.substring(2, 4); 
        int dayComparison = day1.compareTo(day2);
        if (dayComparison != 0) {
            return dayComparison;
        }

        String time1 = date1.substring(6, 10); 
        String time2 = date2.substring(6, 10); 
        int timeComparison = time1.compareTo(time2);
        if (timeComparison != 0) {
            return timeComparison;
        }

       return 0;
    }
}