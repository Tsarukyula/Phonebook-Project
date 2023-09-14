package org.ait.phonebook;

import org.ait.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests extends TestBase {

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
    public void loginPositiveTest() {
        // enter email - [placeholder='Email'] - css
        app.getUser().fillLoginRegistrationForm(new User().setEmail("student@gmail.com")
                .setPassword("Student1$"));
        // click on login button
        app.getUser().clickOnLoginButton();
        // assert Sign out button present
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void loginNegativeWithoutEmailTest() {
        // enter email - [placeholder='Email'] - css
        app.getUser().fillLoginRegistrationForm(new User() //setEmail("student@gmail.com")
                .setPassword("Student1$"));
        // click on login button
        app.getUser().clickOnLoginButton();
        // assert Sign out button present
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void loginPositiveTestWithScreencast() throws IOException, AWTException {
        app.getUser().deleteScreencast();
        app.getUser().startRecording();

        app.getUser().fillLoginRegistrationFormForScreenCast(new User().setEmail("student@gmail.com")
                .setPassword("Student1$"));

        app.getUser().clickOnLoginButton();
        app.getUser().pause(4000);

        app.getUser().stopRecording();
    }

}
