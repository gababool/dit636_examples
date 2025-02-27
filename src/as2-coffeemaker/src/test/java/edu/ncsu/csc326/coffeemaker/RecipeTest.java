package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {
    private Recipe recipe;

    @BeforeEach // Create a new recipe for each test
    void setUp() {
        recipe = new Recipe();
    }

    @Test // Test for setting a valid chocolate amount
    void setAmtChocolate_Valid() throws RecipeException {
        recipe.setAmtChocolate("10");
        assertEquals(10, recipe.getAmtChocolate());
    }

    @Test // Test setting invalid (negative) chocolate amount, should throw exception
    void setAmtChocolate_InvalidNegative() {
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("-5"));
    }

    @Test // Test setting amount of chocolates to a string
    void setAmtChocolate_InvalidString() {
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("chocolate"));
    }

    @Test // Test setting valid coffee amount
    void setAmtCoffee_Valid() throws RecipeException {
        recipe.setAmtCoffee("5");
        assertEquals(5, recipe.getAmtCoffee());
    }

    @Test
    void setAmtCoffee_InvalidNegative() { // Test setting negative value as coffee amount
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-5"));
    }

    @Test
    void setAmtCoffee_InvalidString() {
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("coffee"));
    }

    @Test
    void setAmtMilk_Valid() throws RecipeException {
        recipe.setAmtMilk("2");
        assertEquals(2, recipe.getAmtMilk());
    }

    @Test
    void testSetAmtMilk_InvalidNegative() {
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("-1"));
    }

    @Test
    void testSetAmtMilk_InvalidString() {
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("milk"));
    }

    @Test
    void testSetAmtSugar_Valid() throws RecipeException {
        recipe.setAmtSugar("7");
        assertEquals(7, recipe.getAmtSugar());
    }

    @Test
    void testSetAmtSugar_InvalidNegative() {
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("-2"));
    }

    @Test
    void testSetAmtSugar_InvalidString() {
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("sugar"));
    }

    @Test
    void testSetPrice_Valid() throws RecipeException {
        recipe.setPrice("100");
        assertEquals(100, recipe.getPrice());
    }

    @Test
    void testSetPrice_InvalidNegative() {
        assertThrows(RecipeException.class, () -> recipe.setPrice("-5"));
    }

    @Test
    void testSetPrice_InvalidString() {
        assertThrows(RecipeException.class, () -> recipe.setPrice("Free"));
    }

    @Test
    void testSetName_Valid() {
        recipe.setName("Espresso");
        assertEquals("Espresso", recipe.getName());
    }

    @Test
    void testSetName_Null() {
        recipe.setName(null);
        assertNotNull(recipe.getName()); // Should not be null
    }

    @Test
    void testEquals_SameRecipe() {
        Recipe recipe2 = new Recipe();
        recipe.setName("Coffee");
        recipe2.setName("Coffee");
        assertTrue(recipe.equals(recipe2));
    }

    @Test
    void testEquals_DifferentRecipe() {
        Recipe recipe2 = new Recipe();
        recipe.setName("Coffee");
        recipe2.setName("NotCoffee");
        assertFalse(recipe.equals(recipe2));
    }

    @Test // Test that a recipe isnt equal to null
    void testEquals_NullObject() {
        assertFalse(recipe.equals(null));
    }

    @Test // Test that a recipe isnt equal to a diff obj type
    void testEquals_DifferentObjectType() {
        assertFalse(recipe.equals("StringCoffee")); // Comparing with a string instead of a Recipe
    }
}