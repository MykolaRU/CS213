public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int GROW_AMOUNT = 4;
    public static final int NOT_FOUND = -1;

    private int find(Event event) {

        for(int i = 0; i<events.length; i++){
            if (event.equals(events[i])) return i;
        }
        return NOT_FOUND;
    } //search an event in the list
    private void grow() {
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
        Event holder = null;
        for(int i = 0; i<events.length; i++){
            for(int j = 0; j<events.length-i-1; j++){
                if(events[j].getDate().compareTo(events[j+1].getDate()) == 1 ||
                ((events[j].getDate().compareTo(events[j+1].getDate()) == 0) &&
                (events[j].getStartTime().ConvertToMinutes() > events[j+1].getStartTime().ConvertToMinutes()))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }
    } //ordered by date and timeslot
    public void printByCampus() {
        Event holder = null;
        for(int i = 0; i<events.length; i++){
            for(int j = 0; j<events.length-i-1; j++){
                if(events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) == 1 ||
                ((events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) == 0) &&
                (events[j].getLocation().getBuildingName().compareTo(events[j+1].getLocation().getBuildingName())==1))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }
    } //ordered by campus and building/room
    public void printByDepartment(){
        Event holder = null;
        for(int i = 0; i<events.length; i++){
            for(int j = 0; j<events.length-i-1; j++){
                if(events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) == 1 ||
                ((events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) == 0) &&
                (events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName())==1))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }
    } //ordered by department done
}