/**
 * This enumeration represents different timeslots available.
 * Each timeslot has an associated hour and minute, representing the start time of the timeslot.
 * The enumeration provides methods to get these values and to convert the timeslot to minutes.
 *
 * @author Ethan, Mykola
 */
public enum Timeslot {

    // Enumeration constants representing the different timeslots available.
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18,30);

    // Constants
    public static final int MINUTES_IN_HOUR = 60; // Constant representing the number of minutes in an hour

    // Instance variables to hold the hour and minute of each timeslot
    private final int hour;
    private final int minute;

    /**
     * Constructor to initialize the hour and minute of each timeslot.
     *
     * @param hour The hour at which the timeslot starts.
     * @param minute The minute at which the timeslot starts.
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Method to get the hour of the timeslot.
     *
     * @return The hour at which the timeslot starts.
     */
    public int getHour(){
        return this.hour;
    }

    /**
     * Method to get the minute of the timeslot.
     *
     * @return The minute at which the timeslot starts.
     */
    public int getMinute(){
        return this.minute;
    }

    /**
     * Method to convert the timeslot to minutes.
     * This is done by converting the hour to minutes and adding the remaining minutes.
     *
     * @return The total minutes of the timeslot from the start of the day.
     */
    public int ConvertToMinutes() {
        // Converting time to minutes.
        return this.hour * MINUTES_IN_HOUR + this.minute;
    }
}
