public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Constants for months and leap year rules
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(String date){
        String[] dateArray = date.split("/",3); //splitting the string into parts (month, day , year)
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);
    }

    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }

    @Override // may have to remove, not sure yet
    public String toString(){
        return (getMonth() + "/" + getDay() + "/" + getYear());
    }

    //checks if the date is a valid calendar date
    public boolean isValid(){
        if(month<1 || month>12) return false;

        int[] daysInMonth = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};

        if(month==2 && IsLeapYear(year)) //checking January for Leap Year
            if(day<1 || day>29) return false;
        else
            if (day != daysInMonth[month-1]) return false;

        if (year<1) return false;

        return true;
    }

    //checks if year is leap year
     private boolean IsLeapYear(int year){
         if (year % QUADRENNIAL!=0) return false;
         if(year % CENTENNIAL!=0) return true;
         return year % QUATERCENTENNIAL == 0;    //short form of if statement
    }

    @Override //Fixed
    public int compareTo(Date other) {
        if (this.year > other.year)
            return 1;
        if (this.year < other.year)
            return -1;

        if (this.month > other.month)
            return 1;
        if (this.month < other.month)
            return -1;

        if (this.day > other.day)
            return 1;
        if (this.day < other.day)
            return -1;

        return 0;
    }

}