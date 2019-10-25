package bl4ckscor3.mod.woodenletterblocks.gen.data;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockStateGenerator extends BlockStateProvider
{
	public BlockStateGenerator(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
	{
		super(gen, modid, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels()
	{
		Type.forEach((color, wood, letter) -> {
			String w = wood.name().toLowerCase();
			String l = letter.name().toLowerCase();
			String c = color.name().toLowerCase();

			horizontalBlock(ForgeRegistries.BLOCKS.getValue(Type.getRegistryNameForType(color, wood, letter)), state -> new UncheckedModelFile(new ResourceLocation(WoodenLetterBlocks.MODID, "block/" + c + "_" + w + "_" + l)), 0);
		});
	}
}