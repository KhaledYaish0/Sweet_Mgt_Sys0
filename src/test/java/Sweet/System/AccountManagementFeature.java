package Sweet.System;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class AccountManagementFeature {
    SweetSystem myApp;
    String accountDetails;
    boolean emailCheck;
    boolean passwordCheck;
    String newDetails;
    boolean bnameCheck;
    boolean addressCheck;
    public AccountManagementFeature(SweetSystem myApp) {
        this.myApp = myApp;
    }
    @When("I request to view my account details")
    public void iRequestToViewMyAccountDetails() {
        for (StoreOwner s : myApp.getStoreOwners()) {
            accountDetails = s.viewAccountDetails();
        }
    }

    @Then("I should see my account details including username, email, and business information")
    public void iShouldSeeMyAccountDetailsIncludingUsernameEmailAndBusinessInformation() {
            System.out.println(accountDetails);
    }

    @When("I update my email to {string} and password to {string}")
    public void iUpdateMyEmailToAndPasswordTo(String newEmail, String newPassword) {
        for (StoreOwner st : myApp.getStoreOwners()) {
            emailCheck = st.updateEmail(newEmail);
            passwordCheck = st.updatePassword(newPassword);
        }
    }

    @Then("my account details should be updated with the new email and password")
    public void myAccountDetailsShouldBeUpdatedWithTheNewEmailAndPassword() {
        for (StoreOwner st : myApp.getStoreOwners()) {
            assertTrue(emailCheck && passwordCheck);
            newDetails = st.viewAccountDetails();
            System.out.println(newDetails);
        }
    }

    @Then("a confirmation message should appear")
    public void aConfirmationMessageShouldAppear() {
        myApp.setMessage("Account details has been updated successfully!");
        assertTrue(myApp.getMessage().equals("Account details has been updated successfully!"));
    }


    @When("I update my business name to {string} and address to {string}")
    public void iUpdateMyBusinessNameToAndAddressTo(String newBusinessName, String newAddress) {
        for (StoreOwner st : myApp.getStoreOwners()) {
            bnameCheck = st.updateBusinessName(newBusinessName);
            addressCheck = st.updateAddress(newAddress);
        }
    }

    @Then("my business information should be updated")
    public void myBusinessInformationShouldBeUpdated() {
        assertTrue(bnameCheck && addressCheck);
    }


    @When("I attempt to update my email to an invalid format {string}")
    public void iAttemptToUpdateMyEmailToAnInvalidFormat(String wrongEmail) {
        if (myApp.isEmailValid(wrongEmail))
        {
            myApp.setMessage("valid email format");
        }
        else {
            myApp.setMessage("Invalid email format");
        }
    }

    @Then("I should receive an error message indicating that the email format is invalid")
    public void iShouldReceiveAnErrorMessageIndicatingThatTheEmailFormatIsInvalid() {
        System.out.println(myApp.getMessage());
    }

    @When("I change my password to {string}")
    public void iChangeMyPasswordTo(String newPassword) {
        for (StoreOwner st : myApp.getStoreOwners()) {
            passwordCheck = st.updatePassword(newPassword);
        }
    }

    @Then("my password should be updated successfully")
    public void myPasswordShouldBeUpdatedSuccessfully() {
        assertTrue(passwordCheck);
    }



}
