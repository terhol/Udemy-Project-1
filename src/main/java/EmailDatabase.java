

import java.sql.*;

public class EmailDatabase {
    private final String URL = "jdbc:postgresql://localhost:5433/Emaildatabase";
    private final String USER = "postgres";
    private final String PASSWORD = "root";

   public boolean tryConnection() throws Exception{
       Connection connection = DriverManager.getConnection(URL, USER,PASSWORD);
       boolean isValid = connection.isValid(5);
       connection.close();
       return isValid;
   }

   public void saveNewEmployee(String name, String surname, String department) throws Exception {
       boolean employeeExists = doesEmployeeExist(name, surname);
       if (!employeeExists) {
           Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
           Employee employee = new Employee(name, surname, department);
           String sql = "INSERT INTO email(firstname, surname, email, password, capacity) VALUES (?,?,?,?,?)";
           PreparedStatement statement = connection.prepareStatement(sql);

           statement.setString(1, employee.getName());
           statement.setString(2, employee.getSurname());
           statement.setString(3, employee.getEmail());
           statement.setString(4, employee.getPassword());
           statement.setInt(5, employee.getCapacity());

           statement.executeUpdate();

           statement.close();
           connection.close();

       } else {
System.out.println("Employee with this name and surname already exists - please contact support.");
       }
   }

   public void changePassword(String email, String newPassword)throws Exception{
       String sql = "UPDATE email SET password=? WHERE email=?";
//TODO: Decide if employee exists
       Connection connection = DriverManager.getConnection(URL, USER,PASSWORD);
       PreparedStatement statement = connection.prepareStatement(sql);
       statement.setString(1, newPassword);
       statement.setString(2, email);
       statement.executeUpdate();
       statement.close();
       connection.close();

   }

   private boolean doesEmployeeExist(String name, String surname) throws Exception{
       Connection connection = DriverManager.getConnection(URL, USER,PASSWORD);
       String sql = "SELECT * FROM email WHERE firstname = ? AND surname = ?";

       PreparedStatement statement = connection.prepareStatement(sql);
       statement.setString(1, name);
       statement.setString(2, surname);

       ResultSet resultSet = statement.executeQuery();
       boolean doesExist = resultSet.next();

       statement.close();
       connection.close();
       return doesExist;
   }
}
