package org.ait.phonebook;

import org.ait.phonebook.models.Contact;
import org.ait.phonebook.models.User;
import org.ait.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        // precondition: if user should be logged out
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().login();
        // click on Add link - [href='/add'] - css
        app.getContact().clickOnAddLink();
    }

    @Test
    public void addContactPositiveTest() {
        // enter all input fields in a contact form - input:nth-child(1) - css
        app.getContact().fillContactForm(new Contact().setName("Karl")
                .setSurname("Adam")
                .setPhone("1234567890")
                .setEmail("karladam@gmail.com")
                .setAddress("Koblenz")
                .setDesc("goalkeeper"));
        // click on save button - .add_form__2rsm2 button - css
        app.getContact().clickOnSaveButton();
        // assert by text contact is added
        Assert.assertTrue(app.getContact().isContactAdded("Karl"));
    }

    @AfterMethod
    public void postCondition() {
        app.getContact().removeContact();
    }


    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProvider(String name, String surname, String phone,
                                                       String email, String address, String description) {

        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDesc(description));

        app.getContact().clickOnSaveButton();

        Assert.assertTrue(app.getContact().isContactAdded(name));
    }

    @Test(dataProvider = "newContactWithCSVFile", dataProviderClass = DataProviders.class)
    public void addContactPositiveTestFromDataProviderWithCSV(Contact contact) {

        app.getContact().fillContactForm(contact);
        app.getContact().pause(1000);
        app.getContact().clickOnSaveButton();

        Assert.assertEquals(Integer.toString(app.getContact().sizeOfContacts()), "1");
    }

}
