package fr.ulille.iut.chatisfait.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PizzaDto extends PizzaShortDto {
	private final static Logger logger = LoggerFactory.getLogger(PizzaDto.class);
	private List<IngredientDto> ingredients;

	public List<IngredientDto> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
	}
}