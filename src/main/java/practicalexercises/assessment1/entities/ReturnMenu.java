package practicalexercises.assessment1.entities;

import practicalexercises.assessment1.Menu;

/**
 *
 * @author andre
 */
public class ReturnMenu{
    Menu menu = new Menu();

    /**
     *
     */
    public ReturnMenu() {
    }
    
    /**
     *
     * @param returnInput
     */
    public void menu (String returnInput){
        if(returnInput.equalsIgnoreCase("RETURN")){
                menu.runMenu();
            }
    }
    
}

