/**
 Represents an Event Calendar holding a list of events, provides functionalities to add, remove, search,
 and print events in different orders.

 @authors Ethan, Mykola
 */
public class EventCalendar {
    private Event[] events; // Array holding the list of events
    private int numEvents; // Current number of events in the array

    // Constants to represent the amount by which the events array grows and a value representing a not found state
    public static final int GROW_AMOUNT = 4;
    public static final int NOT_FOUND = -1;

    /**
     Initializes a new EventCalendar with an events array of size GROW_AMOUNT and no events.
     */
    public EventCalendar() {
        this.events = new Event[GROW_AMOUNT];
        this.numEvents = 0;
    }

    /**
     Retrieves the events in the calendar.

     @return Array of events in the calendar
     */
    public Event[] getEvents() {
        return this.events;
    }

    /**
     Sets the events in the calendar.

     @param events Array of events to be set in the calendar
     */
    public void setEvents(Event[] events) {
        this.events = events;
    }

    /**
     Retrieves the number of events in the calendar.

     @return Number of events in the calendar
     */
    public int getNumEvents() {
        return this.numEvents;
    }

    /**
     Sets the number of events in the calendar.

     @param num The number of events to be set in the calendar
     */
    public void setNumEvents(int num) {
        this.numEvents = num;
    }

    /**
     Searches for an event in the list of events.

     @param event The event to be found
     @return Index of the event in the events array if found, NOT_FOUND constant otherwise
     */
    private int find(Event event) {
        for (int i = 0; i < getNumEvents(); i++) {
            if (event.getDate().toString().equals(events[i].getDate().toString())
                    && event.getStartTime().equals(events[i].getStartTime())
                    && event.getLocation().equals(events[i].getLocation()))
                return i;
        }
        return NOT_FOUND;
    }

    /**
     Increases the capacity of the events array by the GROW_AMOUNT.
     */
    private void grow() {
        Event[] largerEvents = new Event[events.length + GROW_AMOUNT];

        for (int i = 0; i < events.length; i++) {
            largerEvents[i] = events[i];
        }

        events = largerEvents;
    }

    /**
     Adds a new event to the events array.

     @param event The event to be added
     @return true if the event is successfully added, false otherwise
     */
    public boolean add(Event event) {
        if (numEvents == events.length)
            grow();

        events[numEvents] = event;
        setNumEvents(getNumEvents() + 1);

        return true;
    }

    /**
     Removes an event from the events array.

     @param event The event to be removed
     @return true if the event is successfully removed, false otherwise
     */
    public boolean remove(Event event) {
        int removeLoc = find(event);

        if (removeLoc == NOT_FOUND)
            return false;

        for (int i = removeLoc; i < (events.length - 1); i++) {
            events[i] = events[i + 1];
        }

        events[events.length - 1] = null;

        setNumEvents(getNumEvents() - 1);

        return true;
    }

    /**
     Checks if an event is present in the events array.

     @param event The event to be checked
     @return true if the event is present in the events array, false otherwise
     */
    public boolean contains(Event event) {
        return find(event) != NOT_FOUND;
    }

    /**
     * Prints the events array as is.
     */
    public void print() {
        System.out.println("* Event calendar *");
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     Prints the events array ordered by event date and start time.
     */
    public void printByDate() {
        Event holder;
        for (int i = 0; i < numEvents; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                if (events[j].getDate().compareTo(events[j + 1].getDate()) == 1 ||
                        ((events[j].getDate().compareTo(events[j + 1].getDate()) == 0)
                                && (events[j].getStartTime().ConvertToMinutes() > events[j + 1].getStartTime().ConvertToMinutes()))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by event date and start time *");
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     Prints the events array ordered by campus and building/room.
     */
    public void printByCampus() {
        Event holder;
        for (int i = 0; i < numEvents; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                if (events[j].getLocation().getCampus().compareTo(events[j + 1].getLocation().getCampus()) > 0 ||
                        ((events[j].getLocation().getCampus().compareTo(events[j + 1].getLocation().getCampus()) == 0)
                                && (events[j].getLocation().getBuildingName().compareTo(events[j + 1].getLocation().getBuildingName()) > 0))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by campus and building *");
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     Prints the events array ordered by department.
     */
    public void printByDepartment() {
        Event holder;
        for (int i = 0; i < numEvents; i++) {
            for (int j = 0; j < numEvents - i - 1; j++) {
                if (events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j + 1].getContact().getDepartment().getDepartmentName()) > 0 ||
                        ((events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j + 1].getContact().getDepartment().getDepartmentName()) == 0)
                                && (events[j].getContact().getDepartment().getDepartmentName().compareTo(events[j + 1].getContact().getDepartment().getDepartmentName()) > 0))) {
                    holder = events[j];
                    events[j] = events[j + 1];
                    events[j + 1] = holder;
                }
            }
        }

        System.out.println("* Event calendar by department *");
        for (int i = 0; i < numEvents; i++) {
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }
}
