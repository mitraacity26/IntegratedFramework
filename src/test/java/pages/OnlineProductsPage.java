package pages;

import org.openqa.selenium.By;
import utility.BrowserDriver;

import static utility.Common.*;

public class OnlineProductsPage extends BrowserDriver {

    //Should use xpath to use common library functions
    public static String formalshoes_dropdown_xpath = "//h2[text()='Formal Shoes']/following-sibling::div/i[1]";

    public static String onlineProductsPage_link_LinkText = "//li[text()='Online Products']";


    public static void click_OnlineProducts_link() throws Exception {

        userClicksOnLocator(onlineProductsPage_link_LinkText);
        userPausesInSeconds("3");
//        Thread.sleep(3000);
    }

    public static void click_Products_dropdown() throws Exception {


        userClicksOnLocator(formalshoes_dropdown_xpath);
        userPausesInSeconds("3");
//        Thread.sleep(3000);
    }

//    public static void getText_formalshoes_firstvalue() throws Exception {
////      String formalshoesText =  driver.findElement(By.xpath(formalshoes_webtable_tr)).getText();
////      return formalshoesText;
//      user_verify_text_contains_on_locator(formalshoes_webtable_tr,"Classic Cheltenham");
//
//    }


}
