package org.ait.phonebook.framework;

import org.ait.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLoginLinK() {
        click(By.cssSelector("a:nth-child(4)"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//a[.='LOGIN']"));
    }

    public void clickOnRegistrationButton() {
        click(By.xpath("//button[text()='Registration']"));
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        // enter password - [placeholder='Password'] - css
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    }

    public void fillLoginRegistrationFormForScreenCast(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        pause(1000);
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        pause(1000);
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent2(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnLoginButton() {
        click(By.xpath("//button[.='Login']"));
    }

    public void login() {
        clickOnLoginLinK();
        fillLoginRegistrationForm(new User().setEmail("student@gmail.com")
                .setPassword("Student1$"));
        clickOnLoginButton();
    }
}
