package stepDefinition;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.BrowserDriver;

import static pages.HomePage.*;
import static pages.OnlineProductsPage.*;

//All the methods are using functions from respective pages
public class OnlineProductsStep extends BrowserDriver {
    @Given("User navigates to the Online products page")
    public void user_navigates_to_the_Online_product_page() throws Exception {
        click_hamburger_menu();
        click_OnlineProducts_link();


    }
    @When("User clicks on Formal Shoes drop down")
    public void user_clicks_on_Formal_Shoes_drop_down() throws Exception {
        click_Products_dropdown();
    }
    @Then("User should be able to view the Products")
    public void user_should_be_able_to_view_the_Products() throws Exception {
        //-getText_formalshoes_firstvalue();
        //assertEquals("   Classic Cheltenham",getText_formalshoes_firstvalue());
    }

}
