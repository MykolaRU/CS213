public enum Timeslot {
    MORNING("10:30am"),
    AFTERNOON("2:00pm"),
    EVENING("6:30pm");

    private final String time;

    Timeslot(String time) {
        this.time = time;
    }
    public String getTime(){
        return this.time;
    }

    public int ConvertToMinutes(){           //converting time to minutes from String
        return switch (this) {
            case MORNING -> 10 * 60 + 30;     //this is 10:30AM
            case AFTERNOON -> 14 * 60;         //this is 2:00PM
            case EVENING -> 18 * 60 + 30;    //this is 6:30PM
            default -> -1;
        };
    }


}
