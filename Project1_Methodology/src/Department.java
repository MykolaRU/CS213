/**
 Enumeration of the various departments within the organization.
 Each department has an associated full name.

 @authors Ethan, Mykola
 */
public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private final String fullName;

    /**
     * Constructs a new Department enum with the specified full name.
     *
     * @param fullName the full name of the department
     */
    Department(String fullName) {
        this.fullName = fullName;
    }

    /**
     Returns a string representation of this department, which is its full name.

     @return the full name of the department
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     Retrieves the full name of the department.

     @return the full name of the department
     */
    public String getDepartmentName(){
        return this.fullName;
    }
}
