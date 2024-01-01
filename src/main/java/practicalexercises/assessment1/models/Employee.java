package practicalexercises.assessment1.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The {@code Employee} class represents an employee entity with attributes such as id, first name, last name, job title, salary, and start date
 *
 * <p>This class provides methods to access and modify the attributes of an employee,
 * along with a {@code toString()} method for easy representation of the employee details.</p>
 *
 * @author andre
 */
@Entity
// Employee Model:
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private double salary;
    private LocalDate startDate;

    /**
     *
     */
    public Employee() {
    }

    /**
     * Constructs a new {@code Employee} object with specified attribute values
     *
     * @param id The unique identifier for the employee ID
     * @param firstName The first name of the employee
     * @param lastName The last name of the employee
     * @param jobTitle The job title of the employee
     * @param salary The salary of the employee
     * @param startDate The start date of work of the employee
     */
    public Employee(int id, String firstName, String lastName, String jobTitle, double salary, LocalDate startDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.startDate = startDate;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *  
     * @param id
     * @param
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     *
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     *
     * @return
     */
    public double getSalary() {
        return salary;
    }

    /**
     *
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     *
     * @return
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return    "Id --------> " + id + ", \n"
                + "FirstName -> " + firstName + ",\n"
                + "LastName --> " + lastName + ", \n"
                + "JobTitle --> " + jobTitle + ", \n"
                + "Salary ----> " + salary + ", \n"
                + "StartDate -> " + startDate + "\n";
    }
    
}