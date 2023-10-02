public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Constants for months and leap year rules
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int JANUARY_DAYS = 31;
    public static final int FEBRUARY_DAYS  = 28;
    public static final int FEBRUARY_LEAP_YEAR_DAYS  = 29;
    public static final int MARCH_DAYS  = 31;
    public static final int APRIL_DAYS  = 30;
    public static final int MAY_DAYS  = 31;
    public static final int JUNE_DAYS  = 30;
    public static final int JULY_DAYS  = 31;
    public static final int AUGUST_DAYS  = 31;
    public static final int SEPTEMBER_DAYS  = 30;
    public static final int OCTOBER_DAYS  = 31;
    public static final int NOVEMBER_DAYS  = 30;
    public static final int DECEMBER_DAYS  = 31;
    public static final int JANUARY = 1;
    public static final int DECEMBER = 12;
    public static final int FIRST_DAY_OF_MONTH = 1;
    public static final int CURRENT_YEAR = 2023;


    public Date(String date){
        String[] dateArray = date.split("/"); //splitting the string into parts (month, day , year)
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

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(int month){
        this.year = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    @Override // may have to remove, not sure yet
    public String toString(){
        return (getMonth() + "/" + getDay() + "/" + getYear());
    }

    //checks if the date is a valid calendar date
    public boolean isValid(){
        if(month<JANUARY || month>DECEMBER) return false;

        int[] daysInMonth = new int[] {JANUARY_DAYS ,FEBRUARY_DAYS ,MARCH_DAYS ,APRIL_DAYS ,MAY_DAYS ,JUNE_DAYS ,JULY_DAYS ,AUGUST_DAYS ,SEPTEMBER_DAYS ,OCTOBER_DAYS ,NOVEMBER_DAYS ,DECEMBER_DAYS };

        if(month==2 && IsLeapYear(year)) { //checking January for Leap Year
            return day >= FIRST_DAY_OF_MONTH && day <= FEBRUARY_LEAP_YEAR_DAYS;
        }

        if (day > daysInMonth[month-1] || day < FIRST_DAY_OF_MONTH) return false;

        if (year<CURRENT_YEAR) return false;

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