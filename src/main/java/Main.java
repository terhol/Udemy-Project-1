public class Main {

    public static void main(String[] args){

        EmailDatabase database = new EmailDatabase();

        try{
            System.out.println(database.tryConnection());
            database.saveNewEmployee("Samantha", "Smith", "accountant");
            database.changePassword("john.smith@accountant.company.com", "1234567");

        }
        catch(Exception e){
            ExceptionHandler.handleException(e);
        }

    }
}
