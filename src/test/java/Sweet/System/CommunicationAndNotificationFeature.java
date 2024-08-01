package Sweet.System;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommunicationAndNotificationFeature {

    SweetSystem myApp;

    public CommunicationAndNotificationFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }
    @When("I send a message to user {string} with the content {string}")
    public void iSendAMessageToUserWithTheContent(String username, String message) {
        myApp.sendMessageToUser(message, username);
        assertTrue(myApp.isMessageSent());
    }

    @Then("the user {string} should receive the message with the content {string}")
    public void theUserShouldReceiveTheMessageWithTheContent(String username, String message) {
        // Fetch the actual user object from myApp's Users list
        User user = myApp.getUserByUsername(username);
        boolean messageFound = false;
        ArrayList<String> messages = user.getMessagesList();

//        System.out.println("Messages list size: " + messages.size()); // Debug statement

        for (String m : messages) {
            if (m.trim().equals(message.trim())) {
                messageFound = true;
                break;
            }
        }
        assertTrue("Message not found", messageFound);
    }



    @When("I send a message to supplier {string} with the content {string}")
    public void iSendAMessageToSupplierWithTheContent(String name, String message) {
        myApp.sendMessageToSupplier(message,name);
        assertTrue(myApp.isMessageSent());
    }

    @Then("the supplier {string} should receive the message with the content {string}")
    public void theSupplierShouldReceiveTheMessageWithTheContent(String name, String message) {
        RawSupplier r = myApp.getSupplierByUsername(name);
        boolean messageFound = false;
        ArrayList<String> messages = r.getMessagesList();

//        System.out.println("Messages list size: " + messages.size()); // Debug statement

        for(String m : messages) {
            if (m.equals(message)) {
                messageFound = true;
                break;
            }
        }
        assertTrue("Message not found",messageFound);
    }

    @Given("I have enabled email notifications")
    public void iHaveEnabledEmailNotifications() {
        myApp.enableEmailNotifications();
        assertTrue(myApp.isEmailNotificationsEnabled());
    }


    @When("a special request is made by user {string} to store owner {string}")
    public void aSpecialRequestIsMadeByUserToStoreOwner(String username, String ownername) {
        StoreOwner owner = myApp.getStoreOwnerByUsername(ownername);
        if (owner == null) {
            System.out.println("Owner is null");
        } else {
            System.out.println("Owner email: " + owner.getEmail());
        }
        User user = myApp.getUserByUsername(username);
        myApp.makeSpecialRequest(user, owner);
        assertTrue(myApp.isSpecialRequestMade());
    }



    @Then("I should receive an email notification with the content {string}")
    public void iShouldReceiveAnEmailNotificationWithTheContent(String expectedContent) {
        String actualContent = myApp.getLastEmailNotificationContent();
        assertNotNull("No email notification received", actualContent);
        assertEquals("Email content does not match", expectedContent, actualContent);
    }



}
