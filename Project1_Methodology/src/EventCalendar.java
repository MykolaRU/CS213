public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private int find(Event event) {
        final int NOT_FOUND = -1;
        for(int i = 0; i<events.length; i++){
            if (event.equals(events[i])) return i;
        }
        return NOT_FOUND;
    } //search an event in the list
    private void grow() {
        final int GROW_AMOUNT = 4;
        Event[] largerevents = new Event[events.length + GROW_AMOUNT];

        for(int i = 0; i<events.length; i++){
            largerevents[i]=events[i];
        }

        events = largerevents;
    } //increase the capacity by 4
    public boolean add(Event event) {
        if(numEvents == events.length)
            grow();

        for(int i = (events.length - 1); i>0; i--){
            if(events[i] != null){
                events[i+1] = event;
                return true;
            }
        }
        return false;
    }
    public boolean remove(Event event) {
        int removeLoc = find(event);

        for(int i = removeLoc; i<(events.length - 1); i++){
            events[i] = events[i+1];
        }

        events[events.length - 1] = null;

        return true;
    }
    public boolean contains(Event event) {
        return find(event) != -1;
    }
    public void print() {
        for(int i=0; i< events.length; i++){
            System.out.println(events[i]);
        }
    } //print the array as is
    public void printByDate() {

    } //ordered by date and timeslot
    public void printByCampus() {

    } //ordered by campus and building/room
    public void printByDepartment(){

    } //ordered by department
}