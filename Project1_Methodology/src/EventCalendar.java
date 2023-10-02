public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int GROW_AMOUNT = 4;
    public static final int NOT_FOUND = -1;

    public EventCalendar(){
        this.events = new Event[GROW_AMOUNT];
        this.numEvents = 0;
    }

    public Event[] getEvents(){
        return this.events;
    }

    public void setEvents(Event[] events){
        this.events = events;
    }

    public int getNumEvents(){
        return this.numEvents;
    }

    public void setNumEvents(int num){
        this.numEvents = num;
    }

    private int find(Event event) {

        for(int i = 0; i<getNumEvents(); i++){
            if (event.getDate().toString().equals(events[i].getDate().toString()) && event.getStartTime().equals(events[i].getStartTime()) && event.getLocation().equals(events[i].getLocation()))
                return i;
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

        events[numEvents] = event;
        setNumEvents(getNumEvents()+1);


        return true;
    }
    public boolean remove(Event event) {
        int removeLoc = find(event);

        if(removeLoc == NOT_FOUND)
            return false;

        for(int i = removeLoc; i<(events.length - 1); i++){
            events[i] = events[i+1];
        }

        events[events.length - 1] = null;

        setNumEvents(getNumEvents()-1);

        return true;
    }
    public boolean contains(Event event) {
        return find(event) != NOT_FOUND;
    }
    public void print() {
        System.out.println("* Event calendar *");
        for (int i = 0; i<numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    } //print the array as is
    public void printByDate() {
        Event holder = null;
        for(int i = 0; i<numEvents; i++){
            for(int j = 0; j<numEvents-i-1; j++){
                if(events[j].getDate().compareTo(events[j+1].getDate()) == 1 ||
                ((events[j].getDate().compareTo(events[j+1].getDate()) == 0) &&
                (events[j].getStartTime().ConvertToMinutes() > events[j+1].getStartTime().ConvertToMinutes()))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by event date and start time *");
        for (int i = 0; i<numEvents; i++){
                System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    } //ordered by date and timeslot
    public void printByCampus() {
        Event holder = null;
        for(int i = 0; i<numEvents; i++){
            for(int j = 0; j<numEvents-i-1; j++){
                if(events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) > 0 ||
                ((events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) == 0) &&
                (events[j].getLocation().getBuildingName().compareTo(events[j+1].getLocation().getBuildingName())>0))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by campus and building *");
        for (int i = 0; i<numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    } //ordered by campus and building/room
    public void printByDepartment(){
        Event holder = null;
        for(int i = 0; i<numEvents; i++){
            for(int j = 0; j<numEvents-i-1; j++){
                if(events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) >0 ||
                ((events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) == 0) &&
                (events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName())>0))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by department *");
        for (int i = 0; i<numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    } //ordered by department done
}