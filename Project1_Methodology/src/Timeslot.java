public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18,30);

    private final int hour;
    private final int minute;
    public static final int MINUTES_IN_HOUR = 60;
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public int ConvertToMinutes(){           //converting time to minutes
        return this.hour * MINUTES_IN_HOUR + this.minute;
    }


}
