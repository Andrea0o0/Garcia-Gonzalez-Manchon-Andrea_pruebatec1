package practicalexercises.assessment1.services;

import java.util.List;
import java.util.Map;
import practicalexercises.assessment1.entities.ReturnMenu;
import practicalexercises.assessment1.models.Employee;
import practicalexercises.assessment1.persistence.PersistenceController;

/**
 * The {@code EmployeeService} class provides methods for managing employee data and acts as an intermediary for validations and persistence control
 * @author andre
 */
public class EmployeeService {

    Employee employee = new Employee();
    Validator validation = new Validator();
    PersistenceController controller = new PersistenceController();
    ReturnMenu menu = new ReturnMenu();

    /**
     * Prints a message informing that if the user writes Return in any way, ignoring the case, they will return to the menu at any time, cancelling the actual operation 
     */
    public void returnMessage() {
        System.out.println("** ENTER RETURN TO BACK TO THE MENU AND CANCEL OPERATION **");
    }

    /**
     *Prints the message of the attribute to be filled 
     * @param parameter The parameter for which the user is prompted to enter a value.
     */
    public void namesPrint(String parameter) {
        System.out.println("Enter the " + parameter + ":");
    }

    /**
     * This method is responsible for creating an employee object and connecting the validation of each parameter or attribute of the Employee class
     * @return The created {@code Employee } object.
     * @see Validator
     * @see Validator#stringValidator(java.lang.String) 
     * @see Validator#doubleValidator(java.lang.String) 
     * @see Validator#validationDate() 
     */
    public Employee createEmployee() {

        //ENTER NAME
        returnMessage();
        namesPrint("name");
        String name = validation.stringValidator("name");
        employee.setFirstName(name);

        // ENTER LASTNAME
        returnMessage();
        namesPrint("last name");
        employee.setLastName(validation.stringValidator("last name"));

        // ENTER JOB TITLE
        returnMessage();
        namesPrint("job title");
        employee.setJobTitle(validation.stringValidator("Job Title"));

        // ENTER SALARY
        returnMessage();
        namesPrint("salary");
        employee.setSalary(validation.doubleValidator("salary"));

        // ENTER START DATE
        returnMessage();
        namesPrint("date");
        employee.setStartDate(validation.validationDate());

        return employee;
    }

    /**
     *  Prompts the index to enter and connect with the index validation
     * @return The employee index validated by {@code Validation.intValidator(String index, int maxValue)}
     * @see Validator#intValidator(java.lang.String, int) 
     */
    public int byId() {
        returnMessage();
        System.out.println("Please enter the employee's index:");
        return validation.intValidator("index", Integer.MAX_VALUE);
    }

    /**
     * Modifies each one of the employee's attributes based on the user's input with the {@code Validation } class helps and allowing to update employee attributes  
     * @param employeeById The employee to be modified
     * @return The modified {@code Employee} object
     * @see Validator
     * @see Validator#stringValidator(java.lang.String) 
     * @see Validator#doubleValidator(java.lang.String) 
     * @see Validator#validationDate() 
     */
    public Employee modify(Employee employeeById) {
        System.out.println("This is the employee you are gonna modify:");
        System.out.println(employeeById.toString());
        System.out.println("Which parameter do you want to modify?");
        System.out.println("1. First Name:");
        System.out.println("2. Last Name:");
        System.out.println("3. Job Title:");
        System.out.println("4. Salary");
        System.out.println("5. Date");
        System.out.println("Enter 1-5 to choose the parameter:");
        returnMessage();
        int option = validation.intValidator("option", 5);
        switch (option) {
            case 1 -> {
                namesPrint("new name");
                String inputName = validation.stringValidator("name");
                System.out.println(inputName);
                employeeById.setFirstName(inputName);
            }
            case 2 -> {
                namesPrint("new last name");
                employeeById.setLastName(validation.stringValidator("last name"));
            }
            case 3 -> {
                namesPrint("new job title");
                employeeById.setJobTitle(validation.stringValidator("job title"));
            }
            case 4 -> {
                namesPrint("new salary");
                employeeById.setSalary(validation.doubleValidator("salary"));
            }
            case 5 -> {
                namesPrint("new date");
                employeeById.setStartDate(validation.validationDate());
            }
        }
        controller.modifyEmployee(employeeById);
        System.out.println("EMPLOYEE EDITED:");
        System.out.println(employeeById.toString());
        System.out.println("would you like to edit more parameters?");
        String continueOption = validation.yesOrNoValidator("Please enter YES or NO or Return to go back to the Menu");
        if (continueOption.equalsIgnoreCase("YES")) {
            modify(employeeById);
        } else {
            return employeeById;
        }
        return null;
    }

    /**
     *
     Prompts the user to enter a search parameter (job title, name, or last name) and calls method {@link Validator#stringValidator(String) to validate all the attributes} 
     * @param parameter The type of parameter to be entered (e.g., "job title", "name").
     * @return The entered search parameter.
     * @see Validator#stringValidator(String) 
     */
    public String bySearchJobNameLastName(String parameter) {
        returnMessage();
        namesPrint(parameter);
        return validation.stringValidator(parameter);
    }

    /**
     * With the employees Results from {@code PersistenceController} Prints the results of the search, including employees that matches or contains the same input from the attributes
     * @see PersistenceController#employeesByJobTitle(java.lang.String) 
     * @see PersistenceController#employeesByName(java.lang.String)  
     * @see PersistenceController#employeesByLastName(java.lang.String) 
     * @param employeesResultSearch A map containing list of {@code Employee} categorized as "MATCH" and "CONTAINS"
     */
    public void printResultSearch(Map<String, List<Employee>> employeesResultSearch) {
        if (employeesResultSearch.containsKey("MATCH")) {
            System.out.println("MATCH:");
            List<Employee> employeesMatch = employeesResultSearch.get("MATCH");
            for (Employee employeeM : employeesMatch) {
                System.out.println(employeeM.toString());
            }
        }
        if (employeesResultSearch.containsKey("CONTAINS")) {
            System.out.println("CONTAINS:");
            List<Employee> employeesContains = employeesResultSearch.get("CONTAINS");
            for (Employee employeeC : employeesContains) {
                System.out.println(employeeC.toString());
            }
        }
    }

    /**
     *Display a warning message which warns that the entire database is going to be deleted, also checks together with {@code Validator.yesOrNoValidator(String message)} that the input from user is valid and allows the operation to be cancelled with a NO or a RETURN
     */
    public void warningDelete() {
        String option = validation.yesOrNoValidator("Are you sure you want to delete ALL THE EMPLOYEES from the database? \nEnter YES to DELETE ALL the Database or No/Return to cancel and back to the menu");
        if (option.equalsIgnoreCase("NO")) {
            option = "RETURN";
            menu.menu(option);
        }
    }
}
