package practicalexercises.assessment1;

/**
 * The {@code Assessment1} class serves as the entry point for the assessment application, but also doesn't store to much code
 * It runs the main menu, allowing users to interact with employee data through the menu options, more user-friendly :)
 *
 *
 * <p>The class directly invokes the {@code runMenu} method from the {@code Menu} class to initiate the text-based menu interface.</p>
 *
 * @author andre
 */
public class Assessment1 {

    /**
     * The main method to start the assessment application
     * @param args
     */
    public static void main(String[] args) {
        Menu.runMenu();
    }
}
