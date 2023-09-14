package org.ait.phonebook;

import org.ait.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        // precondition: if user should be logged out
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        // click on Login link - a:nth-child(4) - css
        app.getUser().clickOnLoginLinK();
    }

    @Test
    public void existedUserRegistrationNegativeTest() {
        // enter email - [placeholder='Email'] - css
        app.getUser().fillLoginRegistrationForm(new User().setEmail("student@gmail.com")
                .setPassword("Student1$"));
        // click on Registration button - //button[text()='Registration'] - xpath
        app.getUser().clickOnRegistrationButton();
        // assert alert is appeared
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
}
