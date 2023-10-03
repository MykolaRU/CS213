/**
 * Represents a location where an event can be held.
 * This enum contains predefined locations, each associated with a building name and a campus.
 *
 * @authors Ethan, Mykola
 */
public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    // The building name where the event is located
    private final String buildingName;

    // The campus where the event is located
    private final String campus;

    /**
     * Constructs a new Location with the specified building name and campus.
     *
     * @param buildingName The building name where the event is located
     * @param campus The campus where the event is located
     */
    Location(String buildingName, String campus) {
        this.buildingName = buildingName;
        this.campus = campus;
    }

    /**
     * Retrieves the building name of this location.
     *
     * @return The building name of this location
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Retrieves the campus of this location.
     *
     * @return The campus of this location
     */
    public String getCampus() {
        return campus;
    }
}
