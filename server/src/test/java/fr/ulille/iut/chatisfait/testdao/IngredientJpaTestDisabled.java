package fr.ulille.iut.chatisfait.testdao;

import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/*
 * Summary for everey test to make sure they are independent and that they may be run simultaneously
 *
 *   Initial Data (populate.sql)
 *
 *   Ingredients
 *
 *   id  nom             modified by
 *   1   tomate
 *   2   lardons
 *   3   fromage
 *   4   oeuf
 *   5   jambon
 *   6   merguez
 *   7   champignons
 *   8   ananas
 *
 *   Pizzas
 *
 *   id  nom         base    prix_petite prix_grande ingredients         modified by         action
 *   1   oranaise    tomate  5.0         8.0         { 1 }
 *   2   margarita   tomate  4           7.5         { 1, 3 }
 *   3   carbonara   creme   5.5         9           { 2, 3 }
 *   4   4 saisons   tomate  10.0        15.0        { }
 *   4   hawaii      creme   11.0        11.5        { 5, 8 }
 */

public class IngredientJpaTestDisabled {

	@Test
	public void testGetAllIngredients() {
		DataAccess dataAccess = DataAccess.begin();
		List<IngredientEntity> ingredients;
		dataAccess.closeConnection(false);
	}

	@Test
	public void testGetIngredientById_Found() {
		DataAccess dataAccess = DataAccess.begin();
		IngredientEntity ingredient;

		dataAccess.closeConnection(false);
	}

    @Test
	public void testGetIngredientById_NotFound() {
		DataAccess dataAccess = DataAccess.begin();
		IngredientEntity ingredient;
		dataAccess.closeConnection(false);
	}

    @Test
	public void testGetIngredientByName_Found() {
		DataAccess dataAccess = DataAccess.begin();
		IngredientEntity ingredient;
		dataAccess.closeConnection(false);
	}

    @Test
	public void testGetIngredientByName_NotFound() {
		DataAccess dataAccess = DataAccess.begin();
		IngredientEntity ingredient;
		dataAccess.closeConnection(false);
	}

    @Test
    public void testAddIngredientsOk() throws DatabaseConstraintException {
		DataAccess dataAccess = DataAccess.begin();
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setNom("artichaut");



		dataAccess.closeConnection(false);
    }

    @Test(expected = DatabaseConstraintException.class)
    public void testAddIngredientsExists() throws DatabaseConstraintException {
		final String NEW_NAME = "tomate";
		DataAccess dataAccess = DataAccess.begin();
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setNom(NEW_NAME);
	    try {
			fail();
        } finally {
			dataAccess.closeConnection(false);
        }
    }

	@Test
	public void testUpdateIngredientOk() throws DatabaseConstraintException {
		final String NEW_NAME = "anchois";
		DataAccess dataAccess = DataAccess.begin();

		try {

		} finally {
			dataAccess.closeConnection(false);
		}
	}

	@Test(expected= DatabaseConstraintException.class)
	public void testUpdateIngredientNull() throws DatabaseConstraintException {

		}
	}

