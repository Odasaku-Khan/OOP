package OOPproject.test;

import OOPproject.main.datapart.DataRepository;
import OOPproject.main.humanpart.Admin;

public class testDataRepo {
    public static void main(String[] args) {
        // Pull existing data from the database
        DataRepository.pullDataFromDatabase();

        // Print loaded data summary
        System.out.println("Users loaded: " + DataRepository.getUsers().size());
        System.out.println("Courses loaded: " + DataRepository.getCourses().size());
        System.out.println("Books loaded: " + DataRepository.getBooks().size());
        System.out.println("Admins loaded: " + DataRepository.getAdmins().size());

        // Add a test Admin if no admins are present
        if (DataRepository.getAdmins().isEmpty()) {
            System.out.println("No admins found. Adding a test admin...");
            Admin testAdmin = new Admin("TestAdmin", "admin@example.com", "adminpass", 100000);
            DataRepository.addAdmin(testAdmin);

            System.out.println("Test admin added successfully.");
        }

        // Save changes back to the database
        DataRepository.saveTransactionDataToDB();

        System.out.println("Test complete. Changes saved.");
    }
}
