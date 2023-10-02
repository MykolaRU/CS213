public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    public static void main(String args[]){

    }

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
        else if (object ==null || !(object instanceof Event)) return false;  //checking if object is of class Event
        Event event = (Event) object;

        if ( date.equals(event.date)
                && startTime == event.startTime
                && location == event.location)
            return true;

        return false;
    }

    @Override
    public int compareTo(Event other) {   //comparing dates and time
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) return dateComparison;
        return this.startTime.compareTo(other.startTime);
    }

    @Override
    public String toString() {

        return String.format("[Event Date: %s] [Start: %s] [End: %s] @%s (%s, %s) [Contact: %s]",
                date, startTime.getTime(), calculateEndTime(), location, location.getBuildingName(), location.getCampus(), contact);
    }

    private String calculateEndTime(){
        int startTimeInMinutes = startTime.ConvertToMinutes();
        int endTimeInMinutes = startTimeInMinutes + duration;

        //converting back to hours
        int hour = endTimeInMinutes / 60;
        int minutes = endTimeInMinutes % 60;
        String partOfTheDay = "";

        if(hour<12) partOfTheDay = "am";
        else {
            partOfTheDay = "pm";
            hour = hour - 12;
        }
        if(minutes == 0)
            return Integer.toString(hour) + ":" + Integer.toString(minutes) + "0" + partOfTheDay;
        return Integer.toString(hour) + ":" + Integer.toString(minutes) + partOfTheDay;
    }
}