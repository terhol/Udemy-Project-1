public class Main {

    public static void main(String[] args) {

        try {

            HRInterface.startApplication();

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }

    }
}
