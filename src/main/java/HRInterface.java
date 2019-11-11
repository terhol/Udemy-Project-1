import java.util.Scanner;

public class HRInterface {
    private static EmailDatabase database = new EmailDatabase();
    private static Scanner keyboard = new Scanner(System.in);
    private static String currentEmployee;

    public static void startApplication() throws Exception {
        System.out.println("Do you want to add new employee or edit existing employee?");
        System.out.println("1: Add new employee.");
        System.out.println("2: Edit existing employee.");
        System.out.println("0: Quit application.");

        String input = keyboard.next();

        if (input.equals("1")) {
            addNewEmployee();
        } else if (input.equals("2")) {
            editEmployee();
        } else if (input.equals("0")) {
            System.out.println("Application is quitting now.");
        } else {
            System.out.println("Please type in 0, 1 or 2 only.");
            startApplication();

        }
    }

    private static void addNewEmployee() throws Exception {
        System.out.println("Type first name:");
        String firstName = keyboard.next();
        System.out.println("Type surname:");
        String surname = keyboard.next();
        System.out.println("Type department (sales, development, accounting), alternatively leave blank:");
        String department = keyboard.next();

        if (!database.doesEmployeeExist(firstName, surname)) {
            database.saveNewEmployee(firstName, surname, department);
            System.out.println("Employee saved successfully.");
        } else {
            System.out.println("Employee with same name exists. Please contact support for further help.");

        }
    }

    private static void editEmployee() throws Exception {
        System.out.println("Which employee do you want to edit? Please type email:");
        currentEmployee = keyboard.next();
        if (!database.doesEmployeeExist(currentEmployee)) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("What do you want to do?");
            System.out.println("1: Change password.");
            System.out.println("2: Change email capacity.");
            System.out.println("3. Set alternate email.");
            System.out.println("4. Show information (name, email, capacity)");
            System.out.println("0. Quit.");
            String choice = keyboard.next();

            switch (choice) {
                case "1":
                    changePassword();
                    break;
                case "2":
                    changeCapacity();
                    break;
                case "3":
                    setAlternateEmail();
                    break;
                case "4":
                    database.displayInfo(currentEmployee);
                    break;
                case "0":
                    System.out.println("Application is quitting.");
                    break;
                default:
                    System.out.println("Please make a valid choice.");
                    editEmployee();


            }
        }
    }

    private static void changePassword() throws Exception {
        System.out.println("Type new password:");
        String newPassword = keyboard.next();
        database.changePassword(currentEmployee, newPassword);
        System.out.println("Password changed successfully.");
    }

    private static void changeCapacity() throws Exception {
        System.out.println("Type new capacity:");
        String capacity = keyboard.next();
        try {
            int newCapacity = Integer.parseInt(capacity);
            database.changeCapacity(currentEmployee, newCapacity);
            System.out.println("Capacity changed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please use numbers only.");
            changeCapacity();
        }
    }

    private static void setAlternateEmail() throws Exception {
        System.out.println("Type alternate email:");
        String alternateEmail = keyboard.next();
        database.addAlternateEmail(currentEmployee, alternateEmail);
        System.out.println("Alternate email added.");
    }
}






