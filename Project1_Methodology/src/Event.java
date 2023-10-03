/**
 Represents an event with associated date, start time, location, contact, and duration.
 Provides functionalities to compare events, convert time, and generate string representation of an event.

 @authors Ethan, Mykola
 */
public class Event implements Comparable<Event> {
    private Date date; // Date of the event
    private Timeslot startTime; // Starting time of the event
    private Location location; // Location of the event
    private Contact contact; // Contact for the event, includes department name and email
    private int duration; // Duration of the event in minutes

    // Constants to represent noon and minutes in an hour
    public static final int NOON = 12;
    public static final int MINUTES_IN_HOUR = 60;

    /**
     Initializes a new Event with the specified date, start time, location, contact, and duration.

     @param date      The date of the event
     @param startTime The starting time of the event
     @param location  The location of the event
     @param contact   The contact for the event
     @param duration  The duration of the event in minutes
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     Retrieves the date of the event.

     @return The date of the event
     */
    public Date getDate() {
        return this.date;
    }

    /**
     Retrieves the start time of the event.

     @return The start time of the event
     */
    public Timeslot getStartTime() {
        return this.startTime;
    }

    /**
     Retrieves the location of the event.

     @return The location of the event
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     Retrieves the contact for the event.

     @return The contact for the event
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     Retrieves the duration of the event in minutes.

     @return The duration of the event
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     Updates the date of the event.

     @param newDate The new date for the event
     */
    public void setDate(Date newDate) {
        this.date = newDate;
    }

    /**
     Updates the start time of the event.

     @param newStartTime The new start time for the event
     */
    public void setStartTime(Timeslot newStartTime) {
        this.startTime = newStartTime;
    }

    /**
     Updates the location of the event.

     @param newLocation The new location for the event
     */
    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }

    /**
     Updates the contact for the event.

     @param newContact The new contact for the event
     */
    public void setContact(Contact newContact) {
        this.contact = newContact;
    }

    /**
     Updates the duration of the event.

     @param newDuration The new duration of the event
     */
    public void setDuration(int newDuration) {
        this.duration = newDuration;
    }

    /**
     Compares this Event object to another object for equality based on date, start time, and location.

     @param object The object to be compared for equality with this Event
     @return true if the specified object is equal to this Event; false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if(object.getClass() != Event.class) return false;
        Event event = (Event) object;

        if (!this.getDate().toString().equals(event.getDate().toString())) return false;
        if (!this.getStartTime().toString().equals(event.getStartTime().toString())) return false;
        if (!this.getLocation().toString().equals(event.getLocation().toString())) return false;
        if (!this.getContact().toString().equals(event.getContact().toString())) return false;
        return this.getDuration() == event.getDuration();
    }

    /**
     Compares this Event to another Event for order. Returns a negative integer, zero, or a positive integer
     as this Event is less than, equal to, or greater than the specified Event.

     @param other The Event to be compared
     @return a negative integer, zero, or a positive integer as this Event is less than, equal to, or greater than the specified Event.
     */
    @Override
    public int compareTo(Event other) {
        if (this.date.compareTo(other.getDate()) > 0) return 1;
        if (this.date.compareTo(other.getDate()) < 0) return -1;

        if (this.startTime.ConvertToMinutes() > other.getStartTime().ConvertToMinutes()) return 1;
        if (this.startTime.ConvertToMinutes() < other.getStartTime().ConvertToMinutes()) return -1;

        return 0;
    }

    /**
     Returns a string representation of this Event in a specific format.

     @return a string representation of this Event
     */
    @Override
    public String toString() {
        return String.format("[Event Date: %s] [Start: %s] [End: %s] @%s (%s, %s) [Contact: %s]",
                date, startTime(), calculateEndTime(), location, location.getBuildingName(), location.getCampus(), contact);
    }

    /**
     Calculates the end time of the event.

     @return a string representation of the end time of the event.
     */
    private String calculateEndTime() {
        int startTimeInMinutes = startTime.ConvertToMinutes();
        int endTimeInMinutes = startTimeInMinutes + duration;
        int hour = endTimeInMinutes / MINUTES_IN_HOUR;
        int minutes = endTimeInMinutes % MINUTES_IN_HOUR;
        String partOfTheDay = "";

        if (hour < NOON) partOfTheDay = "am";
        else {
            partOfTheDay = "pm";
            if (hour != NOON) hour = hour - NOON;
        }
        if (minutes == 0)
            return hour + ":" + minutes + "0" + partOfTheDay;
        return hour + ":" + minutes + partOfTheDay;
    }

    /**
     Formats the start time of the event.

     @return a string representation of the start time of the event.
     */
    private String startTime() {
        int hour = startTime.getHour();
        int minutes = startTime.getMinute();
        String partOfTheDay = "";

        if (hour < NOON) partOfTheDay = "am";
        else {
            partOfTheDay = "pm";
            if (hour != NOON) hour = hour - NOON;
        }

        if (minutes == 0)
            return (hour + ":" + minutes + "0" + partOfTheDay);
        return Integer.toString(hour) + ":" + Integer.toString(minutes) + partOfTheDay;
    }

    /** TEST CASES FOR equals() METHOD */
    public static void main(String[] args) {
        // Test for isValid() method
        testIdenticalEvents();
        testDifferentDates();
        testDifferentTimeslot();
        testDifferentLocations();
        testDifferentDepartment();
        testDifferentEmail();
        testDifferentDuration();

    }

    /** Test case 1 */
    private static void testIdenticalEvents(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        boolean expectedOutput = true;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #1: Two events with the same attributes are equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 2 */
    private static void testDifferentDates(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/2/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #2: Two events with different dates are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 3 */
    private static void testDifferentTimeslot(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.AFTERNOON, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #3: Two events with different timeslots are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 4 */
    private static void testDifferentLocations(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #3: Two events with different locations are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 5 */
    private static void testDifferentDepartment(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.BAIT, "cs@rutgers.edu"), 60);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #3: Two events with different departments are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 6 */
    private static void testDifferentEmail(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "dd@rutgers.edu"), 60);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #3: Two events with different emails are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }

    /** Test case 7 */
    private static void testDifferentDuration(){
        Event event1 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "cs@rutgers.edu"), 60);
        Event event2 = new Event(new Date("1/1/2020"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "dd@rutgers.edu"), 55);
        boolean expectedOutput = false;
        boolean actualOutput = event1.equals(event2);
        System.out.println("Test case #3: Two events with different durations are not equal");
        testResult(event1,event2,expectedOutput,actualOutput);
    }
    /** Check if a given test case PASS or FAIL */
    private static void testResult(Event event1, Event event2, boolean expectedOutput, boolean actualOutput){
        System.out.println(event1.toString());
        System.out.println(event2.toString() + ": " + (expectedOutput == actualOutput));
    }

}
