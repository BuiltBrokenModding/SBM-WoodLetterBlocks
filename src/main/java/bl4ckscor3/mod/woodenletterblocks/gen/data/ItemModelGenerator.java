package bl4ckscor3.mod.woodenletterblocks.gen.data;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class ItemModelGenerator extends ItemModelProvider
{
	public ItemModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
	{
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		Type.forEach((color, wood, letter) -> {
			String name = color.name().toLowerCase() + "_" + wood.name().toLowerCase() + "_" + letter.name().toLowerCase();

			getBuilder(WoodenLetterBlocks.MODID + ":item/" + name).parent(new UncheckedModelFile(new ResourceLocation(WoodenLetterBlocks.MODID, "block/" + name)));
		});
	}

	@Override
	public String getName()
	{
		return "Wooden Letter Blocks Item Models";
	}
}