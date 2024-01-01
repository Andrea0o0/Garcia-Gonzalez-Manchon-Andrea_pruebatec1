package practicalexercises.assessment1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import practicalexercises.assessment1.entities.ReturnMenu;

/**
 *The {@code Validator} class provides methods for validating and obtaining user inputs (int, double, String, LocalDate)
 * <p>This class include methods for validating integers, double, String, yes/no responses, dates (LocalDate). It also contains methods to handle the validation</p>
 * <p>Usage example: </p>
 * <pre>
 *  {@code
 * Validator validator = new Validator();
 * String validatedString = validator.stringValidator("parameter");
 * Double validatedDouble = validator.doubleValidator("parameter");
 * int validatedInt = validator.intValidator("parameter", maxValue);
 * String yesOrNoResponse = validator.yesOrNoValidator("Message text");
 * LocalDate validatedDate = validator.validationDate();
 * }
 * </pre>
 * @author andre
 */
public class Validator {

    Scanner scanner = new Scanner(System.in);
    ReturnMenu returnMenu = new ReturnMenu();
    private String returnInput;

    // The regex pattern that allows to test the letters, accents, spaces, hyphen and apostroph
    Pattern pattern = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ'\\s-]+$");

    /**
     * Default constructor for the {@code Validator} class to call it without parameters
     */
    public Validator() {
    }

    /**
     *
     * @param returnInput The return Input value used to be stored throughout the entire class to use return to menu
     */
    public Validator(String returnInput) {
        this.returnInput = returnInput;
    }

    /**
     * Validates and retrieves a user-entered string for the specified parameter
     * @param parameter The type of parameter/attribute from {@code Employee} for which the user is prompted to enter a value
     * @return The validated user input string
     * @see ReturnMenu#menu
     */
    public String stringValidator(String parameter) {
        String validateInput;
        while (true) {
            validateInput = scanner.nextLine();

            Matcher matcher = pattern.matcher(validateInput);

            if (matcher.matches()) {
                break;
            } else if (!validateInput.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid " + parameter + ":");
            }
        }
        this.returnInput = validateInput;
        returnMenu.menu(returnInput);
        return validateInput;
    }

    /**
     * Validates and retrieves a user-entered double for the specified parameter, can't be negative also
     * @param parameter The type of parameter/attribute from {@code Employee} for which the user is prompted to enter a value
     * @return The validated user input double
     * @see ReturnMenu#menu
     */
    public Double doubleValidator(String parameter) {
        Double validateInput;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid " + parameter + " :");
                this.returnInput = scanner.next();
                if (pattern.matcher(returnInput).matches()) {
                    returnMenu.menu(returnInput);
                }
            }
            validateInput = scanner.nextDouble();
            if (validateInput < 0) {
                System.out.println("Your " + parameter + " can't be negative, please enter valid " + ": ");
            }
        } while (validateInput < 0);

        return validateInput;
    }

    /**
     * Validates and retrieves a user-entered integer for the specified parameter
     * @param parameter The type of parameter/attribute from {@code Employee} for which the user is prompted to enter a value
     * @param maxValue The maximum allowed value for the integer
     * @return The validated user input int
     * @see ReturnMenu#menu
     */
    public int intValidator(String parameter, int maxValue) {
        int validateInput;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid " + parameter + ":");
                this.returnInput = scanner.next();
                if (pattern.matcher(returnInput).matches()) {
                    returnMenu.menu(returnInput);
                }
            }
            validateInput = scanner.nextInt();
            if (validateInput < 0) {
                System.out.println("Your " + parameter + " can't be negative, please enter valid " + parameter + ": ");
            }
            if (validateInput > maxValue) {
                System.out.println("Your " + parameter + " can't be higher than " + maxValue + ", please enter a valid " + parameter + ":");
            }
        } while (validateInput < 0 || validateInput > maxValue);

        return validateInput;
    }

    /**
     * Validates and retrieves a user-entered yes/no response and also the Return Option to back to the menu 
     * @param messageText The message text prompting the user to inform from the input value before entered
     * @return The validated yes/no response
     * @see ReturnMenu#menu
     */
    public String yesOrNoValidator(String messageText) {
        String continueOption;
        System.out.println(messageText);
        do {
            continueOption = scanner.nextLine();
            returnMenu.menu(continueOption);
            if (!continueOption.equalsIgnoreCase("YES") && !continueOption.equalsIgnoreCase("NO")) {
                System.out.println("Invalid input. Please enter YES, NO or RETURN.");
            }
        } while (!continueOption.equalsIgnoreCase("YES") && !continueOption.equalsIgnoreCase("NO"));
        return continueOption;
    }

    /**
     * Creates a Map with the specified month and the number of days in that month
     * @param month The name of the month
     * @param daysMonth The number of days in the month
     * @return A map with the month as the key and the number of days as value
     */
    public Map<String, Integer> creatingMap(String month, Integer daysMonth) {
        Map<String, Integer> months = new HashMap<>();
        months.put(month, daysMonth);
        return months;
    }

    /**
     * Validates and retrieves user-entered data based on specific constraints, like the company's date creation and the start date of work employee must be before the future, that is, at most, today's date
     * <<p>The method ensures that the entered date is within a valid range of years,
     * months, and days, considering the current date, the company's creation date,
     * and leap years.</p>
     * @return The validated user input date
     * @see ReturnMenu#menu
     */
    public LocalDate validationDate() {
        // Let's assume that the company is the same age as me, and that the employees can only have started at most 23 years ago, in 2000
        int firstYear = 2000;
        int firstMonth = 8;
        int firstDay = 25;

        int year = 0, month = 0, day = 0;
        List<Map<String, Integer>> listMonths = new ArrayList<>();
        listMonths.add(creatingMap("January", 31));
        listMonths.add(creatingMap("February", 28));
        listMonths.add(creatingMap("March", 31));
        listMonths.add(creatingMap("April", 30));
        listMonths.add(creatingMap("May", 31));
        listMonths.add(creatingMap("June", 30));
        listMonths.add(creatingMap("July", 31));
        listMonths.add(creatingMap("August", 31));
        listMonths.add(creatingMap("September", 30));
        listMonths.add(creatingMap("October", 31));
        listMonths.add(creatingMap("November", 30));
        listMonths.add(creatingMap("December", 31));

        int nowYear = LocalDateTime.now().getYear();
        int nowMonth = LocalDateTime.now().getMonthValue();
        int nowDay = LocalDateTime.now().getDayOfMonth();
        int lapYear = 2024;

        System.out.println("Enter the year:");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Hi, please enter a valid year and now try with a number ;): ");
                this.returnInput = scanner.next();
                returnMenu.menu(returnInput);
            }
            year = scanner.nextInt();
            if (year > nowYear || year < firstYear) {
                System.out.println("Invalid year, must be a year between "
                        + firstYear + " and " + (nowYear));
                System.out.println("Please enter a valid range year: ");
            }
        } while (year > nowYear || year < firstYear);

        System.out.print("Enter the month: ");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Hi, please enter a valid month and now try with a number ;): ");
                this.returnInput = scanner.next();
                returnMenu.menu(returnInput);
            }
            month = scanner.nextInt();
            // CASE 1 : Smaller year
            // EXAMPLE --- Year Input-> 2020 < 2023 <-Actual Year
            if (year < nowYear) {
                // Month MUST BE Higher than 0 and Lower than 12 months -- Example: NOT VALID 13 months or -2 months
                // Opposite condition to print invalid input message
                if (month <= 0 || month > 12) {
                    System.out.println("Invalid input. Please enter a valid month (1-12): ");
                }
            }
            // Invalid month, only possible from 1-12
            if (month < 1 || month > 12) {
                System.out.println(month > 12 ? "You created new months with your " + month + " digit, but until they become a reality, please insert a valid month: " : "Negative month, I'm sorry for you, but enter a valid month please: ");
            }
            // CASE 2: same year, has to be equal or higher month to be valid date booking flight
            // Opposite condition to print invalid input message 
            if (year == nowYear && month > nowMonth) {
                System.out.println("Invalid input, the employee can't start after the actual date");
                System.out.println("Actual date : " + "MONTH-> " + nowMonth + " YEAR-> " + nowYear);
                System.out.println("Your date   : " + "MONTH-> " + month + " YEAR-> " + year);
                System.out.println("Please enter a valid month until month " + nowMonth + " or until " + new ArrayList<>(listMonths.get((nowMonth - 1)).keySet()).get(0) + ":");
            }
            if (year == firstYear && month < firstMonth) {
                System.out.println("Invalid input, the employee can't start before the company's creation");
                System.out.println("Company's creation date : " + "MONTH-> " + firstMonth + " YEAR-> " + firstYear);
                System.out.println("Your date               : " + "MONTH-> " + month + " YEAR-> " + year);
                System.out.println("Please enter a valid month from month " + firstMonth + " or from " + new ArrayList<>(listMonths.get((firstMonth - 1)).keySet()).get(0) + ":");
            }
        } while // True 1: Smaller Year AND Month between 1-12
                ((year < nowYear && (month <= 0 || month > 12))
                // True 2: Same Year Actual Date - AND - Same Month or lower -> Actual month: 5, only valid 1,2,3,4,5 month
                || (year == nowYear && (month > nowMonth || month > 12))
                // True 3: Same Year Creation - AND - Same Month or Older -> Actual month: 10, only valid 10,11,12 month
                || (year == firstYear && (month < firstMonth || month > 12)));

        System.out.print("Enter the day: ");
        //                                                        Convert to List to get the days (value) parameter with the index and no the String Key  
        int maxDays = ((year - lapYear) % 4 == 0 && month == 2) ? 29 : (new ArrayList<>(listMonths.get((month - 1)).values()).get(0));
        String monthString = new ArrayList<>(listMonths.get((month - 1)).keySet()).get(0);
        String nowMonthString = new ArrayList<>(listMonths.get((nowMonth - 1)).keySet()).get(0);
        String firstMonthString = new ArrayList<>(listMonths.get((firstMonth - 1)).keySet()).get(0);
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Hi, please enter a valid day and now try with a number ;): ");
                this.returnInput = scanner.next();
                returnMenu.menu(returnInput);
            }
            day = scanner.nextInt();
            if (day > 0 && day <= maxDays) {
                // CASE same year AND month as NOW, DAY: Has to be smaller or same day
                if (year == nowYear && month == nowMonth && day > nowDay) {
                    System.out.println("You have to enter a day smaller than " + day + " because the employee hasn't been able to start work in the future");
                    System.out.println("Date employee : " + "DAY-> " + day + " MONTH-> " + month + "/" + monthString + " YEAR-> " + year);
                    System.out.println("Actual Date   : " + "DAY-> " + nowDay + " MONTH-> " + nowMonth + "/" + nowMonthString + " YEAR-> " + nowYear);
                    System.out.println("Please enter a valid day: ");
                }
                // CASE same year AND month as CREATION COMPANY, DAY: Has to be higher or same day
                if (year == firstYear && month == firstMonth && day < firstDay) {
                    System.out.println("You have to enter the same or older than " + firstDay + " because the employee couldn't start working before company's date creation");
                    System.out.println("Date employee           : " + "DAY-> " + day + " MONTH-> " + month + "/" + monthString + " YEAR-> " + year);
                    System.out.println("Company's Creation Date : " + "DAY-> " + firstDay + " MONTH-> " + firstMonth + "/" + firstMonthString + " YEAR-> " + firstYear);
                    System.out.println("Please enter a valid day: ");
                }
            } // Day has to be inside 1 and the maximum days in the month
            else {
                // Convert to List to get the MONTH (key) parameter with the index and no the String Key 
                System.out.println("Oops, there's " + maxDays + " days on " + monthString);
                System.out.println(day < 1 ? "It seems you don't know the negative days on month only exists in our mood" : "Keep dreaming, there are't that many days in the month");
                System.out.println("Enter new day inside 1 and " + maxDays + ":");
            }
        } while ( // TRUE 1: Same year, same month as NOW, same or smaller day 
                (year == nowYear && month == nowMonth && day > nowDay)
                || // TRUE 2: Same year, same month as CREATION COMPANY, same or older day 
                (year == firstYear && month == firstMonth && day < firstDay)
                || // COMMON CONDITION on TRUE1 & TRUE2 -> MUST BE: More than 0 and less than the max day of the input Month
                (day < 1 || day > maxDays));

        return LocalDate.of(year, month, day);
    }

}
