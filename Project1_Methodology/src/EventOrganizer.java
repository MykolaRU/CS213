import java.util.Scanner;

public class EventOrganizer {
    private EventCalendar eventCalendar;
    public EventOrganizer(){
        this.eventCalendar = new EventCalendar();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an event: ");
        String input = "";

        while (true) {          //gets user input
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            }
        }
        String[] userInputArray = input.split(" "); //splits input into separate strings
        String token = userInputArray[0].toUpperCase();
        String inputDate = userInputArray[1];
        String timeOfTheDay = userInputArray[2];
        String building = userInputArray[3];
        String department = userInputArray[4];
        String email = userInputArray[5];
        String duration = userInputArray[6];

        switch (token) {
            case "A":
                eventCalendar.add(CreateEvent(inputDate, timeOfTheDay,
                    building,department,email,duration));
                break;
            case "R":
                
        }
    }

        //this method will record user input and create an Event object
        public Event CreateEvent( String date, String timeOfTheDay,
                String building, String department, String email, String dur) {

            timeOfTheDay = timeOfTheDay.toUpperCase();

            Date d = new Date(date);
            //retrieving and setting enum classes with user input
            Timeslot timeslot = Timeslot.valueOf(timeOfTheDay.toUpperCase());
            Location location = Location.valueOf(building.toUpperCase());
            Department department1 = Department.valueOf(department.toUpperCase());

            int duration = Integer.parseInt(dur); // converting userInput string duration to int

            if (d.isValid()) {  //checks if date is valid
                Event event = new Event(new Date(date),
                        timeslot, location, new Contact(department1, email), duration);

                return event;     //returns event
            }
            return null;
        }


        }






}
