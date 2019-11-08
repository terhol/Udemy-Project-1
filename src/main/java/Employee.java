import org.apache.commons.lang3.RandomStringUtils;

public class Employee {

    private String name;
    private String surname;
    private String email;
    private String password;
    int capacity = 100;
    private String alternateEmail;

    public Employee(String name, String surname, String department){
        this.name = name;
        this.surname = surname;
        this.email = createEmail(name, surname, department);
        this.password = generatePassword();
    }

    private String createEmail(String name, String surname, String department) {
        String email;
        if(department.equals("")){
            email = String.format("%s.%s@company.com");
        }else {
            email = String.format("%s.%s@%s.company.com", name.toLowerCase(), surname.toLowerCase(), department);
        }
        return email;
    }

    private String generatePassword(){
        return RandomStringUtils.random(8, true, true);
    }

    public void setPassword(String password) {this.password = password; }

    public void setCapacity(int capacity) {this.capacity = capacity; }

    public String getAlternateEmail() { return alternateEmail;}

    public void setAlternateEmail(String alternateEmail) { this.alternateEmail = alternateEmail;}

    public String getName() { return name;}

    public String getSurname() { return surname;}

    public String getEmail() { return email;}

    public String getPassword() { return password;}

    public int getCapacity() {return capacity; }

}
