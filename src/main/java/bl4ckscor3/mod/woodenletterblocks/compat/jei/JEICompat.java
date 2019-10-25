package bl4ckscor3.mod.woodenletterblocks.compat.jei;

import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import bl4ckscor3.mod.woodenletterblocks.client.CreatorScreen;
import bl4ckscor3.mod.woodenletterblocks.recipe.CreatorRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEICompat implements IModPlugin
{
	public static final ResourceLocation CREATOR_RECIPES = new ResourceLocation(WoodenLetterBlocks.MODID, "creator");

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration)
	{
		registration.addRecipeCategories(new CreatorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		registration.addRecipeCatalyst(new ItemStack(WoodenLetterBlocks.CREATOR_BLOCK), CREATOR_RECIPES);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		registration.addRecipes(CreatorRecipe.RECIPES.values(), CREATOR_RECIPES);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		registration.addRecipeClickArea(CreatorScreen.class, 18, 43, 26, 16, CREATOR_RECIPES);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return new ResourceLocation(WoodenLetterBlocks.MODID, WoodenLetterBlocks.MODID);
	}
}
