/**
 * Represents a date object, containing day, month, and year,
 * and provides functionalities to validate the date, determine leap years, 
 * and compare two Date objects.
 *
* @author Ethan, Mykola
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    // Constants representing the number of years and days for leap year calculations
    // and the number of days in each month
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

    /**
     * Initializes a new Date object from a string representation in the form MM/DD/YYYY.
     *
     * @param date a string representation of the date
     */
    public Date(String date){
        String[] dateArray = date.split("/");
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);
    }

    /**
     * Retrieves the day of the month.
     *
     * @return the day of the month
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Retrieves the month of the year.
     *
     * @return the month of the year
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Retrieves the year.
     *
     * @return the year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Updates the year of the date.
     *
     * @param year the new year
     */
    public void setYear(int year){
        this.year = year;
    }

    /**
     * Updates the month of the date.
     *
     * @param month the new month
     */
    public void setMonth(int month){
        this.month = month;
    }

    /**
     * Updates the day of the month.
     *
     * @param day the new day of the month
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * Returns a string representation of the Date object in the format MM/DD/YYYY.
     *
     * @return a string representation of the date
     */
    @Override
    public String toString(){
        return (getMonth() + "/" + getDay() + "/" + getYear());
    }

    /**
     * Validates the date to ensure it corresponds to a valid calendar date, considering the month, day, and leap years.
     *
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid(){
        if(month < JANUARY || month > DECEMBER) return false;

        int[] daysInMonth = new int[] {JANUARY_DAYS, FEBRUARY_DAYS, MARCH_DAYS, APRIL_DAYS, MAY_DAYS, JUNE_DAYS, JULY_DAYS, AUGUST_DAYS, SEPTEMBER_DAYS, OCTOBER_DAYS, NOVEMBER_DAYS, DECEMBER_DAYS};

        if(month == 2 && IsLeapYear(year)) {
            return day >= FIRST_DAY_OF_MONTH && day <= FEBRUARY_LEAP_YEAR_DAYS;
        }

        if (day > daysInMonth[month-1] || day < FIRST_DAY_OF_MONTH) return false;

        if (year < CURRENT_YEAR) return false;

        return true;
    }

    /**
     * Determines if the given year is a leap year according to the Gregorian calendar.
     *
     * @param year the year to be checked
     * @return true if the year is a leap year, false otherwise
     */
    private boolean IsLeapYear(int year){
        if (year % QUADRENNIAL != 0) return false;
        if(year % CENTENNIAL != 0) return true;
        return year % QUATERCENTENNIAL == 0;
    }

    /**
     * Compares this Date object to another Date object for ordering.
     *
     * @param other the other Date object to compare to
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Date other) {
        if (this.year > other.year) return 1;
        if (this.year < other.year) return -1;
        if (this.month > other.month) return 1;
        if (this.month < other.month) return -1;
        if (this.day > other.day) return 1;
        if (this.day < other.day) return -1;
        return 0;
    }
}
