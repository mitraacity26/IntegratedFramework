package pages;

import org.openqa.selenium.By;
import utility.BrowserDriver;

import static utility.Common.*;

public class HomePage extends BrowserDriver {

    //All the methods are using in-build functions from commons (like click, enter text etc.)


    //Should use xpath to use common library functions
    public static String hamburger_menu_xpath = "//*[@id=\"menuToggle\"]/input";


    public static void click_hamburger_menu() throws Exception {

        //driver.findElement(By.xpath(hamburger_menu_xpath)).click();
        userOpenURL("https://anupdamoda.github.io/AceOnlineShoePortal/index.html#");
        userMaximizeScreen();
        userClicksOnLocator(hamburger_menu_xpath);
    }





}
