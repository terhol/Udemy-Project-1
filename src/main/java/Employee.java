import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@NoArgsConstructor
public class Employee {

    private String name;
    private String surname;
    private String email;
    private String password;
    private int capacity = 100;
    private String alternateEmail;

    public Employee(String name, String surname, String department) {
        this.name = name;
        this.surname = surname;
        this.email = createEmail(name, surname, department);
        this.password = generatePassword();
    }

    private String createEmail(String name, String surname, String department) {
        String email = String.format("%s.%s@%s.company.com", name.toLowerCase(), surname.toLowerCase(), department);
        return email;
    }

    private String generatePassword() {
        return RandomStringUtils.random(8, true, true);
    }

    public void displayInfo() {
        System.out.println(String.format("Employee:%s %s. Email: %s. Email capacity: %d", name, surname, email, capacity));
    }


}
