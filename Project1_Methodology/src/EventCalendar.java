public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int GROW_AMOUNT = 4;
    public static final int NOT_FOUND = -1;

    public static void main(String args[]){ //just for testing right now
        Date d1 = new Date("12/22/2023");
        Timeslot t1 = Timeslot.AFTERNOON;
        Location l1 = Location.HLL114;
        Department de1 = Department.CS;
        Contact c1 = new Contact(de1,"exd1@gmail.com");
        int du1 = 30;
        Event e1 = new Event(d1,t1,l1,c1,du1);

        Date d2 = new Date("11/14/2021");
        Timeslot t2 = Timeslot.MORNING;
        Location l2 = Location.AB2225;
        Department de2 = Department.BAIT;
        Contact c2 = new Contact(de2,"exd1@gmail.com");
        int du2 = 40;
        Event e2 = new Event(d2,t2,l2,c2,du2);

        Date d3 = new Date("11/13/2021");
        Timeslot t3 = Timeslot.AFTERNOON;
        Location l3 = Location.BE_AUD;
        Department de3 = Department.ITI;
        Contact c3 = new Contact(de3,"exd1@gmail.com");
        int du3 = 25;
        Event e3 = new Event(d3,t3,l3,c3,du3);

        EventCalendar eventlist = new EventCalendar();

        eventlist.add(e1);
        eventlist.add(e2);
        eventlist.add(e3);


        eventlist.printByCampus();

        eventlist.remove(e3);

        eventlist.printByCampus();

    }

    public EventCalendar(){
        this.events = new Event[GROW_AMOUNT];
        this.numEvents = 0;
    }

    public Event[] getEvents(){
        return this.events;
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

        if(removeLoc == -1)
            return false;

        for(int i = removeLoc; i<(events.length - 1); i++){
            events[i] = events[i+1];
        }

        events[events.length - 1] = null;

        setNumEvents(getNumEvents()-1);

        return true;
    }
    public boolean contains(Event event) {
        return find(event) != -1;
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
                if(events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) == 1 ||
                ((events[j].getLocation().getCampus().compareTo(events[j+1].getLocation().getCampus()) == 0) &&
                (events[j].getLocation().getBuildingName().compareTo(events[j+1].getLocation().getBuildingName())==1))) {
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
                if(events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) == 1 ||
                ((events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName()) == 0) &&
                (events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j+1].getContact().getDepartment().getDepartmentName())==1))) {
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