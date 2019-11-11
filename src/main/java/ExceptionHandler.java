import java.sql.SQLException;

public class ExceptionHandler {

    public static void handleException(Exception e) {
        if (e instanceof SQLException) {
            SQLException sqlException = (SQLException) e;
            System.out.println("Error code: " + sqlException.getErrorCode());
            System.out.println("SQL state: " + sqlException.getSQLState());
        }
        System.out.println("SQL message: " + e.getMessage());
        System.out.println("Stack trace: ");
        e.printStackTrace();
    }
}
