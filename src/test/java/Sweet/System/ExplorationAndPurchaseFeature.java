package Sweet.System;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExplorationAndPurchaseFeature {

    StoreOwner test;
    SweetSystem myApp;
    String expectedSearch="Chocolate Cake" + ": " + "a cake flavored with melted chocolate, cocoa powder" + "\n";
    String expectedOptions="Options: 1.Popular sweets 2.Cake \n";
    String expectedDietary="Chocolate Cake\n"+"Nutrient: Calories: 305 Fat: 22g Sugar: 74g Protein: 5g"+ "\n";
    String expectedListToPrint="Kunafa\n" +
            "Allergies: Butter\n" +
            "Nutrient: Calories: 3105 Fat: 221g Sugar: 741g Protein: 51g\n" +
            "Chocolate Cake\n" +
            "Allergies: dairy\n" +
            "Nutrient: Calories: 305 Fat: 22g Sugar: 74g Protein: 5g"+"\n";
    String expectedPaymentMessage="Chocolate Cake, Chocolate is very tasty!, 10.0\n" +
            "Change: 5.23\n";

    String expectedFoodAlergies="Kunafa: dough\n";
    String searchForDessert="Chocolate Cake";

    public ExplorationAndPurchaseFeature(SweetSystem myApp) {
        this.myApp = myApp;
        test=myApp.storeOwners.get(0);
    }

    @When("I want to explore dessert options")
    public void iWantToExploreDessertOptions() {

       assertEquals("the Recipes options went not as expected",expectedOptions,myApp.getOptionList());
    }

    @Then("I should be able to browse and search for dessert recipes")
    public void iShouldBeAbleToBrowseAndSearchForDessertRecipes() {

        assertEquals("the Recipes search went not as expected",expectedSearch,myApp.SearchingList(searchForDessert));
    }

    @Then("List of dessert recipes I'm looking for should appear.")
    public void listOfDessertRecipesIMLookingForShouldAppear() {
        myApp.printSearchingList(searchForDessert);
        assertEquals("the Recipes search went not as expected",expectedSearch,myApp.SearchingList(searchForDessert));

    }

    @When("I have specific dietary needs or food allergies")
    public void iHaveSpecificDietaryNeedsOrFoodAllergies() {
        assertEquals("the print of dietary needs and food allergies went not as expected",expectedListToPrint,myApp.PrintListOfDietaryNeedsAndFoodAlergies( ));

    }

    @Then("I should be able to filter recipes based on those requirements")
    public void iShouldBeAbleToFilterRecipesBasedOnThoseRequirements() {
        String searchForNutrient1="Sugar";
        String dietaryNeedSearchingFor="74g";
        String foodAlergiesToDeleteWhenFoundIt ="dairy";

        assertEquals("the dietary search went not as expected",expectedDietary,myApp.SearchingForNutrient( searchForNutrient1, dietaryNeedSearchingFor));
        assertEquals("the food allergies search went not as expected",expectedFoodAlergies,myApp.SearchingForFoodAlergies( foodAlergiesToDeleteWhenFoundIt));

    }

    @When("I want to buy dessert")
    public void iWantToBuyDessert() {
        assertTrue(test.printAllProducts());
    }

    @Then("I should be able to purchase dessert directly from store owners")
    public void iShouldBeAbleToPurchaseDessertDirectlyFromStoreOwners() {

        assertTrue(test.isProductAvailable("Chocolate Cake"));
        assertEquals("the payment process went not as expected",expectedPaymentMessage,test.completePayment(15.23,"Chocolate Cake"));

    }
}
