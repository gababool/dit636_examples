package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import static org.junit.jupiter.api.Assertions.*;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {

    private CoffeeMaker coffeeMaker;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;


    @BeforeEach
    public void setUp() throws Exception {

        // Create a new coffee maker for each test
        coffeeMaker = new CoffeeMaker();

        // Add recipes that can be used to test the methods of the coffee maker
        // (Copied from "ExampleTest")

        //Set up for r1
        r1 = new Recipe();
        r1.setName("Coffee");
        r1.setAmtChocolate("0");
        r1.setAmtCoffee("3");
        r1.setAmtMilk("1");
        r1.setAmtSugar("1");
        r1.setPrice("50");

        //Set up for r2
        r2 = new Recipe();
        r2.setName("Mocha");
        r2.setAmtChocolate("20");
        r2.setAmtCoffee("3");
        r2.setAmtMilk("1");
        r2.setAmtSugar("1");
        r2.setPrice("75");

        //Set up for r3
        r3 = new Recipe();
        r3.setName("Latte");
        r3.setAmtChocolate("0");
        r3.setAmtCoffee("3");
        r3.setAmtMilk("3");
        r3.setAmtSugar("1");
        r3.setPrice("100");

        //Set up for r4
        r4 = new Recipe();
        r4.setName("Hot Chocolate");
        r4.setAmtChocolate("4");
        r4.setAmtCoffee("0");
        r4.setAmtMilk("1");
        r4.setAmtSugar("1");
        r4.setPrice("65");

        //Set up for r5
        r5 = new Recipe();
        r4.setName("Cortado");
        r4.setAmtChocolate("0");
        r4.setAmtCoffee("4");
        r4.setAmtMilk("2");
        r4.setAmtSugar("0");
        r4.setPrice("110");
    }

    // Adding a valid recipe that does not already exist when there is space available should work
    @Test
    public void testAddRecipe_Normal(){
        assertTrue(coffeeMaker.addRecipe(r1));
    }

    // Adding the same recipe twice should return false, because the duplicate should not be added
    @Test
    public void testAddRecipe_Duplicate(){
        coffeeMaker.addRecipe(r1);
        assertFalse(coffeeMaker.addRecipe(r1));
    }

    // Adding a fifth recipe should return false, because there is only room for four recipes at a time
    @Test
    public void testAddRecipe_ExceedsLimit(){
        coffeeMaker.addRecipe(r1);
        coffeeMaker.addRecipe(r2);
        coffeeMaker.addRecipe(r3);
        coffeeMaker.addRecipe(r4);
        assertFalse(coffeeMaker.addRecipe(r5));
    }

    // Deleting a recipe that exists in the coffee maker should work, i.e. return the name of the recipe
    @Test
    public void testDeleteRecipe_Normal(){
        coffeeMaker.addRecipe(r1);
        assertEquals(coffeeMaker.deleteRecipe(0), "Coffee");
    }

    // Deleting a recipe that does not exist should return null
    @Test
    public void testDeleteRecipe_RecipeDoesNotExist(){
        assertNull(coffeeMaker.deleteRecipe(0));
    }


    // Trying to delete a recipe out of bounds should return null, but produces an OutOfBounds-exception
    @Test
    public void testDeleteRecipe_OutOfBounds(){
        assertNull(coffeeMaker.deleteRecipe(4));
    }


    // Replacing r1 with r2 as an edit should work, since both recipes are valid and r1 exists
    @Test
    public void testEditRecipe_Normal(){
        coffeeMaker.addRecipe(r1);
        assertEquals(coffeeMaker.editRecipe(0, r2), "Coffee");
    }

    // Editing a recipe that does not exist should return null
    @Test
    public void testEditRecipe_RecipeDoesNotExist(){
        assertNull(coffeeMaker.editRecipe(0, r1));
    }

    // Editing a recipe that is out of bounds should return null, but produces and exception
    @Test
    public void testEditRecipe_OutOfBounds(){
        assertNull(coffeeMaker.editRecipe(4, r1));
    }

    // Adding inventory with valid input should return true, but it does not
    @Test
    public void testAddInventory_Normal() throws InventoryException {
        assertTrue(coffeeMaker.addInventory("1", "1", "1", "1"));
    }

    // Adding inventory with invalid input like negative numbers or non-number strings should throw exception
    @Test
    public void testAddInventory_InvalidInput(){
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    coffeeMaker.addInventory("3", "-1", "hello", "3");
                }
        );
    }

    // Checking inventory should return valid results. 15 of everything is default.
    @Test
    public void testCheckInventory_Normal() throws InventoryException {

        StringBuffer buf = new StringBuffer();
        buf.append("Coffee: ");
        buf.append("16"); // Add one to coffee amount to see if expected result is correct
        buf.append("\n");
        buf.append("Milk: ");
        buf.append("15");
        buf.append("\n");
        buf.append("Sugar: ");
        buf.append("15");
        buf.append("\n");
        buf.append("Chocolate: ");
        buf.append("15");
        buf.append("\n");
        String expected = buf.toString();

        coffeeMaker.addInventory("1", "0", "0", "0");
        assertEquals(coffeeMaker.checkInventory(), expected);
    }

    // Making coffee shall return correct change
    @Test
    public void testMakeCoffee_Normal() throws Exception {
        coffeeMaker.addRecipe(r1);
        assertEquals(coffeeMaker.makeCoffee(0, 60), 10);
    }


    // Making coffee with a recipe that does not exist shall return all money
    @Test
    public void testMakeCoffee_RecipeDoesNotExist() throws Exception {
        assertEquals(coffeeMaker.makeCoffee(0, 60), 60);
    }

    // Making coffee and paying too little, shall return all money
    @Test
    public void testMakeCoffee_NotEnoughMoney() throws Exception {
        coffeeMaker.addRecipe(r1);
        assertEquals(coffeeMaker.makeCoffee(0, 40), 40);
    }

    // Making coffee with negative amount of money should ideally throw exception
    @Test
    public void testMakeCoffee_InvalidInput() throws Exception {
        coffeeMaker.addRecipe(r1);
        Throwable exception = assertThrows(
                Exception.class, () -> {
                    coffeeMaker.makeCoffee(0, -40);
                }
        );
    }

    // The recipe array should be identical to what is expected, having added four recipes
    @Test
    public void testGetRecipes_RecipesExist(){
        coffeeMaker.addRecipe(r1);
        coffeeMaker.addRecipe(r2);
        coffeeMaker.addRecipe(r3);
        coffeeMaker.addRecipe(r4);
        Recipe[] expected = {r1, r2, r3, r4};
        assertArrayEquals(coffeeMaker.getRecipes(), expected);
    }

    // Adding no recipes should return an empty array
    @Test
    public void testGetRecipes_NoRecipes(){
        Recipe[] expected = new Recipe[4]; // Empty array
        assertArrayEquals(coffeeMaker.getRecipes(), expected);
    }













}