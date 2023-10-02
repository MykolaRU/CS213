/**
 * Represents an Event Organizer which uses an Event Calendar to perform various actions
 * like adding, removing, printing events based on user input. 
 * It validates the inputs and performs the corresponding actions.
 *
 * @authors Ethan, Mykola
 */
import java.util.Scanner;
import java.util.Calendar;

public class EventOrganizer {

    private final EventCalendar eventCalendar; // The Event Calendar instance used to perform various actions
    public static final int SIX_MONTHS = 6;
    public static final int MIN_DURATION = 30;
    public static final int MAX_DURATION = 120;

    /**
     * Initializes a new EventOrganizer with a new Event Calendar.
     */
    public EventOrganizer() {
        this.eventCalendar = new EventCalendar();
    }

    /**
     * Runs the Event Organizer, reads user input, and performs corresponding actions.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Event Organizer running...");

        // Infinite loop to keep the program running until the user decides to quit
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("Q")) {
                System.out.println("Event Organizer terminated.");
                break; // Exit the program
            }

            String[] userInputArray = input.trim().split("\\s+");
            String token = userInputArray[0];
            String inputDate, timeOfTheDay, building, department = "", email = "", duration = "";

            if (token.equals("A") || token.equals("R")) {
                inputDate = userInputArray[1].trim();
                timeOfTheDay = userInputArray[2].trim();
                building = userInputArray[3].trim();

                if (token.equals("A")) {
                    department = userInputArray[4].trim();
                    email = userInputArray[5].trim();
                    duration = userInputArray[6].trim();
                }

                action(token, inputDate, timeOfTheDay, building, department, email, duration);
            }
        }
        scanner.close();
    }

    /**
     * Creates an event object from the input strings.
     *
     * @param date The event date
     * @param timeOfTheDay The event time
     * @param building The event location
     * @param department The department hosting the event
     * @param email The contact email for the event
     * @param dur The event duration
     * @return A new Event object
     */
    private Event createEvent(String date, String timeOfTheDay, String building, String department, String email, String dur) {
        Date d = new Date(date);
        Timeslot timeslot = Timeslot.valueOf(timeOfTheDay.toUpperCase());
        Location location = Location.valueOf(building.toUpperCase());
        Department department1 = Department.valueOf(department.toUpperCase());
        int duration = Integer.parseInt(dur);

        return new Event(d, timeslot, location, new Contact(department1, email), duration);
    }

    /**
     * Calls the corresponding action method based on the token provided.
     *
     * @param token The action to be performed
     * @param inputDate The event date
     * @param timeOfTheDay The event time
     * @param building The event location
     * @param department The department hosting the event
     * @param email The contact email for the event
     * @param duration The event duration
     */
    private void action(String token, String inputDate, String timeOfTheDay, String building, String department, String email, String duration) {
        switch (token) {
            case "A":
                A(inputDate, timeOfTheDay, building, department, email, duration);
                break;
            case "R":
                R(inputDate, timeOfTheDay, building, department, email, duration);
                break;
            case "P":
                printEventCalendar("Event calendar is empty!");
                break;
            case "PE":
                printEventCalendarByDate("Event calendar is empty!");
                break;
            case "PC":
                printEventCalendarByCampus("Event calendar is empty!");
                break;
            case "PD":
                printEventCalendarByDepartment("Event calendar is empty!");
                break;
            case "":
                break; // Ignore empty input
            default:
                System.out.println(token + " is an invalid command!");
        }
    }

    /**
     * Adds an event to the event calendar after performing various validations.
     *
     * @param inputDate The event date
     * @param timeOfTheDay The event time
     * @param building The event location
     * @param department The department hosting the event
     * @param email The contact email for the event
     * @param duration The event duration
     */
    private void A(String inputDate, String timeOfTheDay, String building, String department, String email, String duration) {
        // Perform various validations before adding the event to the calendar
        if (!checkTimeslot(timeOfTheDay)) {
            System.out.println("Invalid time slot!");
            return;
        }

        if (!checkLocation(building)) {
            System.out.println("Invalid location!");
            return;
        }

        if (!checkDepartment(department, email)) {
            System.out.println("Invalid contact information!");
            return;
        }

        int durationInt = Integer.parseInt(duration.trim());
        if (durationInt < MIN_DURATION || durationInt > MAX_DURATION) {
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
            return;
        }

        Event newEvent = createEvent(inputDate, timeOfTheDay, building, department, email, duration);

        if (eventCalendar.contains(newEvent)) {
            System.out.println("The event is already on the calendar.");
            return;
        }

        if (!newEvent.getDate().isValid()) {
            System.out.println(newEvent.getDate().toString() + ": Invalid calendar date!");
            return;
        }

        // Check if the event date is a future date and within 6 months from the current date
        Calendar today = Calendar.getInstance();
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(newEvent.getDate().getYear(), newEvent.getDate().getMonth() - 1, newEvent.getDate().getDay());

        if (today.compareTo(eventDate) > 0) {
            System.out.println(newEvent.getDate().toString() + ": Event date must be a future date!");
            return;
        }

        eventDate.add(Calendar.MONTH, -SIX_MONTHS);

        if (today.compareTo(eventDate) < 0) {
            System.out.println(newEvent.getDate().toString() + ": Event date must be within 6 months!");
            return;
        }

        System.out.println("Event added to the calendar.");
        eventCalendar.add(newEvent);
    }

    /**
     * Removes an event from the event calendar after performing various validations.
     *
     * @param inputDate The event date
     * @param timeOfTheDay The event time
     * @param building The event location
     * @param department The department hosting the event
     * @param email The contact email for the event
     * @param duration The event duration
     */
    private void R(String inputDate, String timeOfTheDay, String building, String department, String email, String duration) {
        // Perform various validations before removing the event from the calendar
        if (!checkTimeslot(timeOfTheDay)) {
            System.out.println("Invalid time slot!");
            return;
        }

        if (!checkLocation(building)) {
            System.out.println("Invalid location!");
            return;
        }

        Event newEvent = createEvent(inputDate, timeOfTheDay, building, department, email, duration);

        if (!newEvent.getDate().isValid()) {
            System.out.println(newEvent.getDate().toString() + ": Invalid calendar date!");
            return;
        }

        // Check if the event date is a future date and within 6 months from the current date
        Calendar today = Calendar.getInstance();
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(newEvent.getDate().getYear(), newEvent.getDate().getMonth() - 1, newEvent.getDate().getDay());

        if (today.compareTo(eventDate) > 0) {
            System.out.println(newEvent.getDate().toString() + ": Event date must be a future date!");
            return;
        }

        eventDate.add(Calendar.MONTH, -SIX_MONTHS);

        if (today.compareTo(eventDate) < 0) {
            System.out.println(newEvent.getDate().toString() + ": Event date must be within 6 months!");
            return;
        }

        if (!eventCalendar.contains(newEvent)) {
            System.out.println("Cannot remove; event is not in the calendar! ");
            return;
        }

        System.out.println("Event has been removed from the calendar!");
        eventCalendar.remove(newEvent);
    }

    /**
     * Validates the department and email.
     *
     * @param department The department hosting the event
     * @param email The contact email for the event
     * @return true if valid department and email, false otherwise
     */
    private boolean checkDepartment(String department, String email) {
        String[] emailArray = email.split("@");
        if (emailArray.length != 2) return false;
        if (!emailArray[1].equals("rutgers.edu")) return false;

        for (Department i : Department.values()) {
            if (i.name().equalsIgnoreCase(department))
                return true;
        }
        return false;
    }

    /**
     * Validates the location.
     *
     * @param building The event location
     * @return true if valid location, false otherwise
     */
    private boolean checkLocation(String building) {
        for (Location i : Location.values()) {
            if (i.name().equalsIgnoreCase(building))
                return true;
        }
        return false;
    }

    /**
     * Validates the timeslot.
     *
     * @param timeslot The event time
     * @return true if valid timeslot, false otherwise
     */
    private boolean checkTimeslot(String timeslot) {
        for (Timeslot i : Timeslot.values()) {
            if (i.name().equalsIgnoreCase(timeslot))
                return true;
        }
        return false;
    }

    /**
     * Prints the event calendar.
     *
     * @param emptyMessage The message to be printed if the calendar is empty.
     */
    private void printEventCalendar(String emptyMessage) {
        if (eventCalendar.getNumEvents() != 0) eventCalendar.print();
        else System.out.println(emptyMessage);
    }

    /**
     * Prints the event calendar ordered by date.
     *
     * @param emptyMessage The message to be printed if the calendar is empty.
     */
    private void printEventCalendarByDate(String emptyMessage) {
        if (eventCalendar.getNumEvents() != 0) eventCalendar.printByDate();
        else System.out.println(emptyMessage);
    }

    /**
     * Prints the event calendar ordered by campus.
     *
     * @param emptyMessage The message to be printed if the calendar is empty.
     */
    private void printEventCalendarByCampus(String emptyMessage) {
        if (eventCalendar.getNumEvents() != 0) eventCalendar.printByCampus();
        else System.out.println(emptyMessage);
    }

    /**
     * Prints the event calendar ordered by department.
     *
     * @param emptyMessage The message to be printed if the calendar is empty.
     */
    private void printEventCalendarByDepartment(String emptyMessage) {
        if (eventCalendar.getNumEvents() != 0) eventCalendar.printByDepartment();
        else System.out.println(emptyMessage);
    }
}
