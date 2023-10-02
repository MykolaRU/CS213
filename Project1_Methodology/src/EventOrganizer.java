import java.util.Scanner;
import java.util.Calendar;

public class EventOrganizer {
    private final EventCalendar eventCalendar;
    public static final int SIX_MONTHS = 6;

    public EventOrganizer() {
        this.eventCalendar = new EventCalendar();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inputDate = "", timeOfTheDay = "", building = "", department = "", email = "", duration = "";
        System.out.println("Event Organizer running...");
        System.out.println();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("Event Organizer terminated.");
                break;
            }

            String[] userInputArray = input.split(" "); //gets all user inputs
            String token = userInputArray[0];

            if (token.equals("A")||token.equals("R")) {
                inputDate = userInputArray[1];
                timeOfTheDay = userInputArray[2];
                building = userInputArray[3];
            }

            if (token.equals("A")){
                department = userInputArray[4];
                email = userInputArray[5];
                duration = userInputArray[6];
            }
            action(token, inputDate, timeOfTheDay, building, department, email, duration);
        }
        scanner.close();
    }

    private Event createEvent(String date, String timeOfTheDay, String building, String department, String email, String dur) {

        Date d = new Date(date);

        Timeslot timeslot = Timeslot.valueOf(timeOfTheDay.toUpperCase());
        Location location = Location.valueOf(building.toUpperCase());
        Department department1 = Department.valueOf(department.toUpperCase());
        int duration = Integer.parseInt(dur);

        return new Event(d, timeslot, location, new Contact(department1, email), duration);
    }

    private void action(String token, String inputDate, String timeOfTheDay, String building, String department, String email, String duration){
        switch (token) {
            case "A":
                A(inputDate, timeOfTheDay,building,department,email,duration);
                break;
            case "R":
                R(inputDate, timeOfTheDay, building, department, email, duration);
                break;
            case "P":
                if(eventCalendar.getNumEvents() != 0) eventCalendar.print();
                else System.out.println("Event calendar is empty!");
                break;
            case "PE":
                if(eventCalendar.getNumEvents() != 0) eventCalendar.printByDate();
                else System.out.println("Event calendar is empty!");
                break;
            case "PC":
                if(eventCalendar.getNumEvents() != 0) eventCalendar.printByCampus();
                else System.out.println("Event calendar is empty!");
                break;
            case "PD":
                if(eventCalendar.getNumEvents() != 0) eventCalendar.printByDepartment();
                else System.out.println("Event calendar is empty!");
                break;
            case "":
                break;
            default:
                System.out.println(token + " is an invalid command!");
        }
    }

    private void A(String inputDate, String timeOfTheDay, String building, String department, String email, String duration){
        if(!(checkTimeslot(timeOfTheDay))){System.out.println("Invalid time slot!"); return;}

        if(!(checkLocation(building))){System.out.println("Invalid location!"); return;}

        if(!checkDepartment(department, email)){System.out.println("Invalid contact information!"); return;}

        if(Integer.parseInt(duration)<30 || Integer.parseInt(duration)>120){System.out.println("Event duration must be at least 30 minutes and at most 120 minutes"); return;}

        Event newEvent = createEvent(inputDate, timeOfTheDay, building, department, email, duration);

        if(eventCalendar.contains(newEvent)){ System.out.println("The event is already on the calendar."); return;}

        if(!newEvent.getDate().isValid()){ System.out.println(newEvent.getDate().toString() + ": Invalid calendar date!"); return;}

        Calendar today = Calendar.getInstance();
        Calendar daten = Calendar.getInstance();
        daten.set(newEvent.getDate().getYear(), newEvent.getDate().getMonth()-1, newEvent.getDate().getDay());

        if(today.compareTo(daten) > 0){ System.out.println(newEvent.getDate().toString() + ": Event date must be a future date!"); return;}

        daten.add(Calendar.MONTH, -SIX_MONTHS);

        if(today.compareTo(daten) < 0){ System.out.println(newEvent.getDate().toString() + ": Event date must be within 6 months!"); return;}


        System.out.println("Event added to the calendar.");
        eventCalendar.add(newEvent);
    }

    private void R(String inputDate, String timeOfTheDay, String building, String department, String email, String duration){
        if(!(checkTimeslot(timeOfTheDay))){System.out.println("Invalid time slot!"); return;}

        if(!(checkLocation(building))){System.out.println("Invalid location!"); return;}

        Event newEvent = createEvent(inputDate, timeOfTheDay, building, department, email, duration);

        if(!eventCalendar.contains(newEvent)){ System.out.println("Cannot remove; event is not in the calendar! "); return;}

        if(!newEvent.getDate().isValid()){ System.out.println(newEvent.getDate().toString() + ": Invalid calendar date!"); return;}

        Calendar today = Calendar.getInstance();
        Calendar daten = Calendar.getInstance();
        daten.set(newEvent.getDate().getYear(), newEvent.getDate().getMonth()-1, newEvent.getDate().getDay());

        if(today.compareTo(daten) > 0){ System.out.println(newEvent.getDate().toString() + ": Event date must be a future date!"); return;}

        daten.add(Calendar.MONTH, -SIX_MONTHS);

        if(today.compareTo(daten) < 0){ System.out.println(newEvent.getDate().toString() + ": Event date must be within 6 months!"); return;}

        System.out.println("Event has been removed from the calendar!");
        eventCalendar.remove(newEvent);
    }

    private boolean checkDepartment(String department, String email){

        String[] emailArray = email.split("@");
        if(emailArray.length != 2) return false;
        if(!emailArray[1].equals("rutgers.edu")) return false;

        for(Department i : Department.values()){
            if(i.name().equalsIgnoreCase(department))
                return true;
        }
        return false;
    }

    private boolean checkLocation(String building){
        for(Location i : Location.values()){
            if(i.name().equalsIgnoreCase(building))
                return true;
        }
        return false;
    }

    private boolean checkTimeslot(String timeslot){
        for(Timeslot i : Timeslot.values()){
            if(i.name().equalsIgnoreCase(timeslot))
                return true;
        }
        return false;
    }

    public static void main(String args[]){

    }

}