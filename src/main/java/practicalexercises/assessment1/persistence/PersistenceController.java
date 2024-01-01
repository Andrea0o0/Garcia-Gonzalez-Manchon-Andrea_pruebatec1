package practicalexercises.assessment1.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import practicalexercises.assessment1.models.Employee;
import practicalexercises.assessment1.persistence.exceptions.NonexistentEntityException;

/**
 * The {@code PersistenceController} class manages the interaction with the persistence layer, providing methods to create, read, update, and delete employee data among others
 *
 * <p>This class interfaces with the {@code EmployeeJpaController} for performing CRUD operations on the employee entities stored in the database (employee)</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * {@code
 * PersistenceController controller = new PersistenceController();
 * controller.createEmployee(employee);
 * controller.findEmployee(employeeId);
 * controller.allEmployees();
 * controller.modifyEmployee(updatedEmployee);
 * controller.deleteEmployee(employeeId);
 * controller.deleteAllEmployees();
 * controller.employeesByJobTitle(jobTitleInput);
 * controller.employeesByName(nameInput);
 * controller.employeesByLastName(lastNameInput);
 * controller.restartingDataListAndMap();
 * }
 * </pre>
 *
 * <p>The class also includes methods for searching employees by job title, name, or last name, as well as resetting data lists and maps to avoid accumulation of search results.</p>
 *
 * @author andre
 */
public class PersistenceController {

    EmployeeJpaController employeeJpa = new EmployeeJpaController();

    /**
     * Creates a new employee in the database
     * @param employee The employee object from {@code Employee} class to be created
     * @see Employee
     */
    public void createEmployee(Employee employee) {
        employeeJpa.create(employee);
    }

    /**
     * Finds an employee by their unique identifier (ID) - id attribute
     * @param id The ID of the employee in the database
     * @see Employee#id
     * @return The found employee or null if not found by the id
     */
    public Employee findEmployee(int id) {
        return employeeJpa.findEmployee(id);
    }

    /**
     *  It is an internal method to help and use in other methods, so it is only coded once, to simplify the code
     * @return the list of all employees
     */
    public List<Employee> listEmployees() {
        return employeeJpa.findEmployeeEntities();
    }

    /**
     * Prints on the screen one by one, all the employees in the database
     * @return The list of all employees
     * @see Employee
     */
    public List<Employee> allEmployees() {
        List<Employee> employees = listEmployees();
        for (int i = 0; i < employees.size(); i++) {
            System.out.println("Employee " + (i + 1) + ": \n" + employees.get(i));
        }
        return employees;
    }

    /**
     * Modify the employee who receives the method in the database or throws exception message in case don't work
     * @param employee The {@code Employee} object to modify
     */
    public void modifyEmployee(Employee employee) {
        try {
            employeeJpa.edit(employee);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete the employee found by the ID received or throws exception message in case don't work
     * @param id The {@code Employee} id attribute (unique identifier (ID)) to delete
     */
    public void deleteEmployee(int id) {
        try {
            employeeJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete all the employees from the database, be careful with this method, because will reset the database
     */
    public void deleteallEmployees() {
        try {
            List<Employee> employees = listEmployees();
            List<Integer> indexes = new ArrayList<>();
            for (Employee employee : employees) {
                indexes.add(employee.getId());
            };
            for (Integer id : indexes) {
                employeeJpa.destroy(id);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Intern method of the persistence to trim and remove double spaces from the string received
     * @param string2Validate String to validate
     * @return String validated
     */
    public String stringValidation(String string2Validate) {
        // Validation to trim and/or remove unnecessary spaces from a String
        // TRIM spaces
        string2Validate.trim();

        // REMOVE double spaces between words
        string2Validate.replaceAll("\\s+", " ");
        return string2Validate;
    }

    // COMMON INFO FOR SEARCH BY
    // CREATE a List to insert the list of Employees that fit with the parameter filter by (JobTitle, Name, Last Name)
    // 1º MATCH: exact input parameter without keep in mind lowerCase, or UpperCase
    List<Employee> matchEmployees = new ArrayList<>();
    // 2º CONTAINS: NOT exactly input but contains all the input inside parameter (JobTitle, Name, Last Name)
    List<Employee> containsEmployees = new ArrayList<>();

    BiConsumer<String, Employee> addList = (type, employee) -> {
        if (type.equalsIgnoreCase("MATCH")) {
            matchEmployees.add(employee);
        } else if (type.equalsIgnoreCase("CONTAINS")) {
            containsEmployees.add(employee);
        }
    };

    Map<String, List<Employee>> filterEmployees = new HashMap<>();

    /**
     * Final common step on the methods: {@code: 
     *  PersistenceController controller = new PersistenceController();
     * controller.employeesByJobTitle(jobTitleInput);
     * controller.employeesByName(nameInput);
     * controller.employeesByLastName(lastNameInput);}
     */
    public void searchReturn() {
        if (!matchEmployees.isEmpty()) {
            filterEmployees.put("MATCH", matchEmployees);
        }
        if (!containsEmployees.isEmpty()) {
            filterEmployees.put("CONTAINS", containsEmployees);
        }

        if (matchEmployees.isEmpty() && containsEmployees.isEmpty()) {
            System.out.println("Sorry there's no results for you search");
        }
    }

    /**
     * Evaluates if exists any MATCH coincidence or CONTAINS any similar JobTitle input
     * @param jobTitle String input to look for coincidence inside the database with the Job Title
     * @return The coincidences in a Map with the String key: MATCH/CONTAINS in case there are, and the List of {@code Employee} with the results for each case
     * @see Employee#jobTitle
     */
    public Map<String, List<Employee>> employeesByJobTitle(String jobTitle) {
        jobTitle = stringValidation(jobTitle).toLowerCase();
        for (Employee employee : listEmployees()) {
            // String repeated several times, better to declare it and give it a simplified use
            String employeeJobTitle = stringValidation(employee.getJobTitle()).toLowerCase();
            // MATCH EXACT input
            if (employeeJobTitle.equals(jobTitle)) {
                addList.accept("MATCH", employee);
            } // CONTAINS same input
            else if (employeeJobTitle.contains(jobTitle)) {
                addList.accept("CONTAINS", employee);
            }

        }
        searchReturn();
        return filterEmployees;

    }

    /**
     * Evaluates if exists any MATCH coincidence or CONTAINS any similar the first name input
     * @param firstName String input to look for coincidence inside the database with the first name
     * @return The coincidences in a Map with the String key: MATCH/CONTAINS in case there are, and the List of {@code Employee} with the results for each case
     * @see Employee#firstName
     */
    public Map<String, List<Employee>> employeesByName(String firstName) {
        firstName = stringValidation(firstName).toLowerCase();
        for (Employee employee : listEmployees()) {
            // String repeated several times, better to declare it and give it a simplified use
            String employeeName = stringValidation(employee.getFirstName()).toLowerCase();
            // MATCH EXACT input
            if (employeeName.equals(firstName)) {
                addList.accept("MATCH", employee);
            } // CONTAINS same input
            else if (employeeName.contains(firstName)) {
                addList.accept("CONTAINS", employee);
            }

        }
        searchReturn();
        return filterEmployees;

    }

    /**
     * Evaluates if exists any MATCH coincidence or CONTAINS any similar the last name input
     * @param lastName String input to look for coincidence inside the database with the last name
     * @return The coincidences in a Map with the String key: MATCH/CONTAINS in case there are, and the List of {@code Employee} with the results for each case
     * @see Employee#lastName
     */
    public Map<String, List<Employee>> employeesByLastName(String lastName) {
        lastName = stringValidation(lastName).toLowerCase();
        for (Employee employee : listEmployees()) {
            // String repeated several times, better to declare it and give it a simplified use
            String employeeLastName = stringValidation(employee.getLastName()).toLowerCase();
            // MATCH EXACT input
            if (employeeLastName.equals(lastName)) {
                addList.accept("MATCH", employee);
            } // CONTAINS same input
            else if (employeeLastName.contains(lastName)) {
                addList.accept("CONTAINS", employee);
            }

        }
        searchReturn();
        return filterEmployees;

    }

    /**
     * Resets data lists and maps used to filtering employee search results
     */
    public void restartingDataListAndMap() {
        // TO NO ACCUMULATE INFO on the same output, it's necessary to delete info Match and Contains each running method
        matchEmployees.clear();
        containsEmployees.clear();
        filterEmployees.clear();
    }
}

