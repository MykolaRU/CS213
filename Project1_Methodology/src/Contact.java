public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }
    public boolean isValid() {
        if (department==null || email ==null) return false;

        return email.endsWith("@rutgers.edu");
    }

    @Override
    public String toString() {
        return department + ", " + email;
    }

    public Department getDepartment(){
        return this.department;
    }

    public String getEmail(){
        return this.getEmail();
    }

    public void setDepartment(Department newDepartment){
        this.department = newDepartment;
    }

    public void setEmail(String email){
        this.email = email;
    } //setter
}