public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    private final String buildingName;
    private final String campus;

    Location(String buildingName, String campus) {
        this.buildingName = buildingName;
        this.campus = campus;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getCampus() {
        return campus;
    }
}
