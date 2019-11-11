import java.sql.*;

public class EmailDatabase {
    private final String URL = "jdbc:postgresql://localhost:5433/Emaildatabase";
    private final String USER = "postgres";
    private final String PASSWORD = "root";


    public void saveNewEmployee(String name, String surname, String department) throws Exception {
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
    }

    public void changePassword(String email, String newPassword) throws Exception {

        String sql = "UPDATE email SET password=? WHERE email=?";

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newPassword);
        statement.setString(2, email);
        statement.executeUpdate();
        statement.close();
        connection.close();

    }

    public void changeCapacity(String email, int capacity) throws Exception {
        String sql = "UPDATE email SET capacity=? WHERE email = ?";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, capacity);
        statement.setString(2, email);
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void addAlternateEmail(String email, String alternateEmail) throws Exception {
        String getSql = "SELECT * FROM email WHERE email=?";
        String setSql = "INSERT INTO additions(employeeid, alternateemail) VALUES (?,?) ";

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement getStatement = connection.prepareStatement(getSql);
        PreparedStatement setStatement = connection.prepareStatement(setSql);
        getStatement.setString(1, email);

        ResultSet resultSet = getStatement.executeQuery();

        while (resultSet.next()) {
            setStatement.setInt(1, resultSet.getInt("employeeid"));
            setStatement.setString(2, alternateEmail);
            setStatement.executeUpdate();
        }
        resultSet.close();
        setStatement.close();
        getStatement.close();
        connection.close();

    }

    public boolean doesEmployeeExist(String name, String surname) throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM email WHERE firstname = ? AND surname = ?";


        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, surname);

        ResultSet resultSet = statement.executeQuery();
        boolean doesExist = resultSet.next();

        resultSet.close();
        statement.close();
        connection.close();
        return doesExist;
    }

    public boolean doesEmployeeExist(String email) throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "SELECT * FROM email WHERE email =?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, email);
        ResultSet set = statement.executeQuery();

        boolean employeeExists = set.next();

        set.close();
        statement.close();
        connection.close();
        return employeeExists;
    }

    public void displayInfo(String email) throws Exception {
        String sql = "SELECT * FROM email WHERE email =?";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setName(resultSet.getString("firstname"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setEmail(email);
            employee.setCapacity(resultSet.getInt("capacity"));
            employee.displayInfo();
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}


