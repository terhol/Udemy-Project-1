public class Main {

    public static void main(String[] args){

        EmailDatabase database = new EmailDatabase();

        try{
            System.out.println(database.tryConnection());
            database.saveNewEmployee("John", "Smith", "accountant");

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
