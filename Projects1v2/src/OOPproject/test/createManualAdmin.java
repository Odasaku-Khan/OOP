package OOPproject.test;
import OOPproject.main.datapart.*;
import OOPproject.main.humanpart.Admin;
public class createManualAdmin {
	public static void main(String[] args) {
        // Pull existing data from database (if any)
        
        DataRepository.saveTransactionDataToDB();
        DataRepository.pullDataFromDatabase();
        // Create a new Admin user
        Admin newAdmin = new Admin("Admin1", "admin@email.com", "Admin1", 100000);

        // Add the new Admin to the DataRepository
        DataRepository.addAdmin(newAdmin);

        // Save the new Admin to the database
        DataRepository.saveTransactionDataToDB();

        System.out.println("Admin created and saved successfully.");
    }
}
