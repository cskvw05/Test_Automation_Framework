package stepdef;

import baseClass.BaseStepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class LoginStepDef  extends BaseStepDef {

    public LoginStepDef(){
        super();
    }
    @Given("user open the url")
    public void user_open_the_url() {
      utils.navigateToURL("https://www.amazon.in/ref=nav_logo",10);
    }

    @And("user get the title of the page")
    public void user_get_the_title_of_the_page() {
        String expected="Online Shopping site in India";
       String actual=utils.getPageTitle();
        System.out.println(actual);
       assertUtils.softAssertEquals(actual,expected,"validated");
    }




}
