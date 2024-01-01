package practicalexercises.assessment1;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import practicalexercises.assessment1.models.Employee;
import practicalexercises.assessment1.persistence.PersistenceController;
import practicalexercises.assessment1.services.EmployeeService;

/**
 * The {@code Menu} class provides a text-based menu interface for interacting with employee data.
 *
 * <p>Options include adding employees, listing all employees, updating employee information, searching employees by job title, name, or last name, deleting employees, resetting the base data, and exiting the program.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * Menu.runMenu();
 * }1
 * </pre>
 *
 * @author andre
 */
public class Menu {

    /**
     * Runs the text-based for interacting with employee data so that it is more visual for the user as a menu
     */
    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        EmployeeService employee = new EmployeeService();
        PersistenceController controller = new PersistenceController();

        while (true) {
            // Display MENU options
            System.out.println("Menu:");
            System.out.println("1. Add employee");
            System.out.println("2. List of all employees");
            System.out.println("3. Update employee information");
            System.out.println("4. Search employee by Job title");
            System.out.println("5. Search employee by Name");
            System.out.println("6. Search employee by Last Name");
            System.out.println("7. Delete employee");
            System.out.println("8. Reset Base Data (Delete all employees)");
            System.out.println("9. Exit");

            int choice;
            System.out.print("Enter your choice: ");
            do {

                // Validation NUMBER int
                while (!scanner.hasNextInt()) {
                    System.out.println("This is not a number, please enter an valid number:");
                    scanner.next();
                }
                choice = scanner.nextInt();

                // Validation Range Options 1 to 9
                if (choice < 0 || choice > 9) {
                    System.out.println("Please enter a number between 0 and 9:");
                }

            } while (choice < 0 || choice > 9);
            switch (choice) {
                case 1 -> {
                    Employee employeeCreated = employee.createEmployee();
                    controller.createEmployee(employeeCreated);
                    System.out.println(" \n" + "EMPLOYEE CREATED:");
                    System.out.println(employeeCreated.toString());
                    System.out.println("Please remember this employee's id for later id lookups");
                    System.out.println("ID ---> " + employeeCreated.getId());
                }
                case 2 ->
                    controller.allEmployees();
                case 3 -> {
                    System.out.println("Modify employee");
                    int index = employee.byId();
                    Employee employeeById = controller.findEmployee(index);
                    while (employeeById == null) {
                        employeeById = controller.findEmployee(employee.byId());
                    }
                    employee.modify(employeeById);
                }
                case 4 -> {
                    String stringScannerJobTitle = employee.bySearchJobNameLastName("job title");
                    Map<String, List<Employee>> employeesByJobTitle = controller.employeesByJobTitle(stringScannerJobTitle);
                    employee.printResultSearch(employeesByJobTitle);
                    controller.restartingDataListAndMap();
                }
                case 5 -> {
                    String stringScannerName = employee.bySearchJobNameLastName("name");
                    Map<String, List<Employee>> employeesByName = controller.employeesByName(stringScannerName);
                    employee.printResultSearch(employeesByName);
                    controller.restartingDataListAndMap();
                }
                case 6 -> {
                    String stringScannerLastName = employee.bySearchJobNameLastName("last name");
                    Map<String, List<Employee>> employeesByLastName = controller.employeesByLastName(stringScannerLastName);
                    employee.printResultSearch(employeesByLastName);
                    controller.restartingDataListAndMap();
                }
                case 7 -> {
                    int index = employee.byId();
                    controller.deleteEmployee(index);
                }
                case 8 -> {
                    employee.warningDelete();
                    controller.deleteallEmployees();
                }
                case 9 -> {
                    System.out.println("9. Exit");
                    scanner.close();
                    System.exit(0);
                }
                default -> // Double Validation in case error last validation :)
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    /**
     * Main method to start the menu application
     * @param args 
     */
    public static void main(String[] args) {
        runMenu();
    }
}
