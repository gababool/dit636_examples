package edu.ncsu.csc326.coffeemaker;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeBookTest {
    private RecipeBook recipeBook;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5;

    @BeforeEach
    void setup() {
        recipeBook = new RecipeBook();
        recipe1 = new Recipe();
        recipe1.setName("Cappuccino");
        recipe2 = new Recipe();
        recipe2.setName("Espresso");
        recipe3 = new Recipe();
        recipe3.setName("Chocolate");
        recipe4 = new Recipe();
        recipe4.setName("OrangeJuice");
        recipe5 = new Recipe();
        recipe5.setName("OrangeJuice");
    }

    @Test
    void addRecipe_Success() {
        assertTrue(recipeBook.addRecipe(recipe1));
    }

    @Test
    void testAddRecipe_Duplicate() {
        recipeBook.addRecipe(recipe1);
        assertFalse(recipeBook.addRecipe(recipe1));
    }

    @Test
    void testAddRecipe_OutOfBounds() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
        recipeBook.addRecipe(recipe4);
        assertFalse(recipeBook.addRecipe(recipe5)); //max recepies 4.

    }

    @Test
    void testDeleteRecipe_Success() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Cappuccino", recipeBook.deleteRecipe(0));
    }


    @Test
    void testDeleteRecipe_InvalidIndex() {
        assertNull(recipeBook.deleteRecipe(10));
    }
    @Test
    void testDeleteRecipe_NegativeIndex() {
        assertNull(recipeBook.deleteRecipe(-1));
    }

    @Test
    void testEditRecipe_Success() {
        recipeBook.addRecipe(recipe1);
        assertEquals("Cappuccino", recipeBook.editRecipe(0, recipe2));
    }

    @Test
    void testEditRecipe_InvalidIndex() {
        assertNull(recipeBook.editRecipe(10, recipe2));
    }
    @Test
    void testEditRecipe_NegativeIndex() {
        assertNull(recipeBook.editRecipe(-1, recipe2));
    }

}

