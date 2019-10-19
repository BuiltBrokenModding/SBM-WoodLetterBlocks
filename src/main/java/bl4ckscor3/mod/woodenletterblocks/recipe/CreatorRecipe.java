package bl4ckscor3.mod.woodenletterblocks.recipe;

import java.util.HashMap;
import java.util.Map;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.Type.Letter;
import bl4ckscor3.mod.woodenletterblocks.client.LetterButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class CreatorRecipe
{
	public static final Map<ResourceLocation,CreatorRecipe> RECIPES = new HashMap<>();

	static {
		Type.forEach((color, wood, letter) -> {
			ResourceLocation rl = Type.getRegistryNameForType(color, wood, letter);

			if(ForgeRegistries.BLOCKS.containsKey(rl))
				register(new CreatorRecipe(wood.block, color.block, letter, ForgeRegistries.BLOCKS.getValue(rl)).setRegistryName(rl));
		});
	}

	private final LetterButton[] letterButtons = new LetterButton[Letter.values().length];
	private ResourceLocation registryName;
	private Item planks, wool;
	private Letter letter;
	private ItemStack output;

	public CreatorRecipe(IItemProvider planks, IItemProvider wool, Letter letter, IItemProvider output)
	{
		this(planks, wool, letter, new ItemStack(output));
	}

	public CreatorRecipe(IItemProvider planks, IItemProvider wool, Letter letter, ItemStack output)
	{
		this.planks = planks.asItem();
		this.wool = wool.asItem();
		this.letter = letter;
		this.output = output;

		final int size = 15;
		final int xStart = 68;
		int x = xStart;
		int y = 1;

		for(int i = 0; i < Letter.values().length; i++)
		{
			if(i % 7 == 0 && i != 0)
			{
				x = xStart;
				y += size;
			}

			letterButtons[i] = new LetterButton(x, y, size, size, b -> {}, Letter.values()[i]);

			if(i == letter.ordinal())
			{
				letterButtons[0].active = true;
				letterButtons[i].active = false;
			}

			x += size;
		}
	}


	public CreatorRecipe setRegistryName(ResourceLocation registryName)
	{
		this.registryName = registryName;
		return this;
	}

	public LetterButton[] getLetterButtons()
	{
		return letterButtons;
	}

	public ResourceLocation getRegistryName()
	{
		return registryName;
	}

	public Item getPlanks()
	{
		return planks;
	}

	public Item getWool()
	{
		return wool;
	}

	public Letter getLetter()
	{
		return letter;
	}

	public ItemStack getOutput()
	{
		return output;
	}

	public static CreatorRecipe getMatchingRecipe(Item planks, Item wool, Letter letter)
	{
		for(CreatorRecipe recipe : RECIPES.values())
		{
			if(recipe.getPlanks() == planks && recipe.getWool() == wool && letter == recipe.letter)
				return recipe;
		}

		return null;
	}

	public static void register(CreatorRecipe recipe)
	{
		if(recipe.registryName == null)
			throw new IllegalStateException("Creator recipe cannot have null registry name!");
		else if(recipe.registryName.getNamespace().equals("minecraft"))
			throw new IllegalStateException("Creator recipe cannot be registered under the \"minecraft\" namespace!");
		else if(RECIPES.containsKey(recipe.registryName))
			throw new IllegalStateException(String.format("Creator recipe with registry name %s already exists!", recipe.registryName.toString()));
		else if(recipe.planks == null)
			throw new IllegalStateException("Creator recipe's planks input cannot be null!");
		else if(recipe.wool == null)
			throw new IllegalStateException("Creator recipe's wool input cannot be null!");
		else if(recipe.letter == null)
			throw new IllegalStateException("Creator recipe's letter cannot be null!");
		else if(recipe.output == null || recipe.output.isEmpty())
			throw new IllegalStateException("Creator recipe's output cannot be null or empty!");

		RECIPES.put(recipe.getRegistryName(), recipe);
	}
}
