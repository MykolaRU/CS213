/**
 * The Contact class represents a contact entity associated with a specific department 
 * and has a valid email address.
 * This class provides methods to access and modify the department and email of a contact,
 * and to validate the provided email and department.
 *
 * @authors Ethan, Mykola
 */
public class Contact {

    private Department department;
    private String email;

    /**
     * Constructs a new Contact object with the specified department and email.
     *
     * @param department the department to which the contact belongs
     * @param email the email address of the contact
     */
    public Contact(Department department, String email) {
        this.department = department;
        this.email = email;
    }

    /**
     * Retrieves the department of the contact.
     *
     * @return the department to which the contact belongs
     */
    public Department getDepartment() {
        return this.department;
    }

    /**
     * Retrieves the email address of the contact.
     *
     * @return the email address of the contact
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Updates the department of the contact.
     *
     * @param newDepartment the new department to which the contact will be associated
     */
    public void setDepartment(Department newDepartment) {
        this.department = newDepartment;
    }

    /**
     * Updates the email address of the contact.
     *
     * @param email the new email address of the contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Validates the provided email and department.
     * The email is valid if it is in the format local_part@rutgers.edu, and the department 
     * is valid if it exists in the Department enum.
     *
     * @param email the email address to validate
     * @param department the department name to validate
     * @return true if the email and department are valid, false otherwise
     */
    public boolean isValid(String email, String department) {
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
     * Returns a string representation of the contact object, 
     * in the format "department, email".
     *
     * @return a string representation of the contact
     */
    @Override
    public String toString() {
        return department + ", " + email;
    }
}
