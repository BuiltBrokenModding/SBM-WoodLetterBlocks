package bl4ckscor3.mod.woodenletterblocks.compat.jei;

import java.util.Arrays;

import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import bl4ckscor3.mod.woodenletterblocks.client.CreatorScreen;
import bl4ckscor3.mod.woodenletterblocks.client.LetterButton;
import bl4ckscor3.mod.woodenletterblocks.recipe.CreatorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class CreatorRecipeCategory implements IRecipeCategory<CreatorRecipe>
{
	private final IDrawable background;
	private final IDrawable icon;

	public CreatorRecipeCategory(IGuiHelper helper)
	{
		background = helper.createDrawable(CreatorScreen.TEXTURE, 11, 19, 174, 62);
		icon = helper.createDrawableIngredient(new ItemStack(WoodenLetterBlocks.CREATOR_BLOCK));
	}

	@Override
	public ResourceLocation getUid()
	{
		return JEICompat.CREATOR_RECIPES;
	}

	@Override
	public Class<? extends CreatorRecipe> getRecipeClass()
	{
		return CreatorRecipe.class;
	}

	@Override
	public String getTitle()
	{
		return I18n.format(WoodenLetterBlocks.CREATOR_BLOCK.getTranslationKey());
	}

	@Override
	public IDrawable getBackground()
	{
		return background;
	}

	@Override
	public IDrawable getIcon()
	{
		return icon;
	}

	@Override
	public void draw(CreatorRecipe recipe, double mouseX, double mouseY)
	{
		for(LetterButton button : recipe.getLetterButtons())
		{
			button.renderButton((int)mouseX, (int)mouseY, 0.0F);
		}
	}

	@Override
	public void setIngredients(CreatorRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(Arrays.asList(Ingredient.fromItems(recipe.getPlanks()), Ingredient.fromItems(recipe.getWool())));
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, CreatorRecipe recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 6);
		guiItemStacks.init(1, true, 0, 40);
		guiItemStacks.init(2, false, 37, 23);
		guiItemStacks.set(ingredients);
	}
}
