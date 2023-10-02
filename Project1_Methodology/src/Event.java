public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    public static final int NOON = 12;
    public static final int MINUTES_IN_HOUR = 60;


    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    public Date getDate(){
        return this.date;
    }

    public Timeslot getStartTime(){
        return this.startTime;
    }

    public Location getLocation(){
        return this.location;
    } //getter

    public Contact getContact(){
        return this.contact;
    }

    public int getDuration(){
        return this.duration;
    }

    public void setDate(Date newDate){
        this.date = newDate;
    }

    public void setStartTime(Timeslot newStartTime){
        this.startTime = newStartTime;
    }

    public void setLocation(Location newLocation){
        this.location = newLocation;
    }

    public void setContact(Contact newContact){
        this.contact = newContact;
    }

    public void setDuration(int newDuration){
        this.duration = newDuration;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        else if (!(object instanceof Event)) return false;  //checking if object is of class Event
        Event event = (Event) object;

        return date.equals(event.date)
                && startTime == event.startTime
                && location == event.location;
    }

    @Override
    public int compareTo(Event other) {   //comparing dates and time
        if (this.date.compareTo(other.getDate()) > 0) return 1;
        if (this.date.compareTo(other.getDate()) < 0) return -1;

        if(this.startTime.ConvertToMinutes()>other.getStartTime().ConvertToMinutes()) return 1;
        if(this.startTime.ConvertToMinutes()<other.getStartTime().ConvertToMinutes()) return -1;

        return 0;
    }

    @Override
    public String toString() {

        return String.format("[Event Date: %s] [Start: %s] [End: %s] @%s (%s, %s) [Contact: %s]",
                date, startTime(), calculateEndTime(), location, location.getBuildingName(), location.getCampus(), contact);
    }

    private String calculateEndTime(){
        int startTimeInMinutes = startTime.ConvertToMinutes();
        int endTimeInMinutes = startTimeInMinutes + duration;

        //converting back to hours
        int hour = endTimeInMinutes / MINUTES_IN_HOUR;
        int minutes = endTimeInMinutes % MINUTES_IN_HOUR;
        String partOfTheDay = "";

        if(hour<NOON) partOfTheDay = "am";
        else {
            partOfTheDay = "pm";
            if(hour != NOON) hour = hour - NOON;
        }
        if(minutes == 0)
            return hour + ":" + minutes + "0" + partOfTheDay;
        return hour + ":" + minutes + partOfTheDay;
    }

    private String startTime(){
        int hour = startTime.getHour();
        int minutes = startTime.getMinute();

        String partOfTheDay = "";

        if(hour<NOON) partOfTheDay = "am";
        else {
            partOfTheDay = "pm";
            if(hour != NOON) hour = hour - NOON;
        }

        if(minutes == 0)
            return (hour + ":" + minutes + "0" + partOfTheDay);
        return Integer.toString(hour) + ":" + Integer.toString(minutes) + partOfTheDay;
    }
}