package bl4ckscor3.mod.woodenletterblocks.gen.data;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class BlockModelGenerator extends BlockModelProvider
{
	public BlockModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
	{
		super(generator, modid, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		Type.forEach((color, wood, letter) -> {
			String w = wood.name().toLowerCase();
			String l = letter.name().toLowerCase();
			String c = color.name().toLowerCase();

			getBuilder(WoodenLetterBlocks.MODID + ":block/" + c + "_" + w + "_"  + l)
			.parent(new UncheckedModelFile(new ResourceLocation(WoodenLetterBlocks.MODID, "block/base/" + l)))
			.texture("0", new ResourceLocation("block/" + w + "_planks"))
			.texture("1", new ResourceLocation("block/" + c + "_wool"))
			.texture("particle", new ResourceLocation("block/" + w + "_planks"));
		});
	}

	@Override
	public String getName()
	{
		return "Wooden Letter Blocks Block Models";
	}
}