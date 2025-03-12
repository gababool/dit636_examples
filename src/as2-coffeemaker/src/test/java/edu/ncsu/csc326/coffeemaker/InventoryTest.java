package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import static org.junit.jupiter.api.Assertions.*;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InventoryTest{
    private Inventory inventory;
    private Recipe recipe;

    @BeforeEach
    public void setUp() throws RecipeException {
        inventory = new Inventory();

        recipe = new Recipe();
        recipe.setName("Coffee");
        recipe.setAmtChocolate("3");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("3");
        recipe.setAmtSugar("3");
        recipe.setPrice("50");
    }

    @Test
    public void testSetChocolate_Normal(){
        inventory.setChocolate(5);
        assertEquals(inventory.getChocolate(), 5);
    }

    @Test
    public void testSetChocolate_NegativeAmount(){
        inventory.setChocolate(-5);
        assertEquals(inventory.getChocolate(), 15);
    }

    @Test
    public void testSetCoffee_Normal(){
        inventory.setCoffee(5);
        assertEquals(inventory.getCoffee(), 5);
    }

    @Test
    public void testSetCoffee_NegativeAmount(){
        inventory.setCoffee(-5);
        assertEquals(inventory.getCoffee(), 15);
    }

    @Test
    public void testSetMilk_Normal(){
        inventory.setMilk(5);
        assertEquals(inventory.getMilk(), 5);
    }

    @Test
    public void testSetMilk_NegativeAmount(){
        inventory.setMilk(-5);
        assertEquals(inventory.getMilk(), 15);
    }

    @Test
    public void testSetSugar_Normal(){
        inventory.setSugar(5);
        assertEquals(inventory.getSugar(), 5);
    }

    @Test
    public void testSetSugar_NegativeAmount(){
        inventory.setSugar(-5);
        assertEquals(inventory.getSugar(), 15);
    }

    @Test
    public void testAddChocolate_Normal() throws InventoryException {
        assertTrue(inventory.addChocolate("5"));
    }

    @Test
    public void testAddChocolate_NegativeAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addChocolate("-5");
                }
        );
    }
    @Test
    public void testAddChocolate_InvalidAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addChocolate("F");
                }
        );
    }

    @Test
    public void testAddCoffee_Normal() throws InventoryException {
        assertTrue(inventory.addCoffee("5"));
    }

    @Test
    public void testAddCoffee_NegativeAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addCoffee("-5");
                }
        );
    }
    @Test
    public void testAddCoffe_InvalidAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addCoffee("F");
                }
        );
    }


    @Test
    public void testAddMilk_Normal() throws InventoryException {
        assertTrue(inventory.addMilk("5"));
    }

    @Test
    public void testAddMilk_NegativeAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addMilk("-5");
                }
        );
    }
    @Test
    public void testAddMilk_InvalidAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addMilk("F");
                }
        );
    }

    @Test
    public void testAddSugar_Normal() throws InventoryException {
        assertTrue(inventory.addSugar("5"));
    }

    @Test
    public void testAddSugar_NegativeAmount()  {
        Throwable exception = assertThrows(
                InventoryException.class, () -> {
                    inventory.addSugar("-5");
                }
        );
    }
     @Test
      public void testAddSugar_InvalidAmount()  {
          Throwable exception = assertThrows(
                  InventoryException.class, () -> {
                      inventory.addSugar("F");
                  }
          );
      }


    @Test
    public void TestEnoughIngredients_Normal(){
        assertTrue(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testEnoughIngredients_ExactMatch() {
        inventory.setCoffee(3);
        inventory.setMilk(3);
        inventory.setSugar(3);
        inventory.setChocolate(3);
        assertTrue(inventory.enoughIngredients(recipe));
    }

    @Test
    public void TestEnoughIngredients_NotEnough(){
        inventory.setSugar(0);
        inventory.setCoffee(1);
        inventory.setMilk(0);
        inventory.setChocolate(1);
        assertFalse(inventory.enoughIngredients(recipe));
    }

    @Test
    public void testUseIngredients_Normal(){
        assertTrue(inventory.useIngredients(recipe));
        assertEquals(inventory.getCoffee(), 12);
        assertEquals(inventory.getChocolate(), 12);
        assertEquals(inventory.getMilk(), 12);
        assertEquals(inventory.getSugar(), 12);
    }

    @Test
    public void testUseIngredients_NotEnough(){
        inventory.setSugar(0);
        inventory.setCoffee(1);
        assertFalse(inventory.useIngredients(recipe));
    }

    
    

}