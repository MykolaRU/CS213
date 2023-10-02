public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }
    public Department getDepartment(){
        return this.department;
    }

    public String getEmail(){
        return this.email;
    }

    public void setDepartment(Department newDepartment){
        this.department = newDepartment;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public boolean isValid(String email, String department) {
        String[] emailArray = email.split("@");
        if(emailArray.length != 2) return false;
        if(!emailArray[1].equals("rutgers.edu")) return false;

        for(Department i : Department.values()){
            if(i.name().equalsIgnoreCase(department))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return department + ", " + email;
    }
}