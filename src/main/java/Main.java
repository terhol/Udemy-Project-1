public class Main {

    public static void main(String[] args){

        EmailDatabase database = new EmailDatabase();

        try{
            database.addAlternateEmail("john.smith@accountant.company.com", "aaa@a.com");
        }
        catch(Exception e){
            ExceptionHandler.handleException(e);
        }

    }
}
