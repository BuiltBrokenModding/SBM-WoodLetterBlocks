package bl4ckscor3.mod.woodenletterblocks.gen.data;

import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid=WoodenLetterBlocks.MODID, bus=Bus.MOD)
public class DataGenRegistrar
{
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event)
	{
		event.getGenerator().addProvider(new BlockLootTableGenerator(event.getGenerator()));
		event.getGenerator().addProvider(new BlockModelGenerator(event.getGenerator(), WoodenLetterBlocks.MODID, event.getExistingFileHelper()));
		event.getGenerator().addProvider(new BlockStateGenerator(event.getGenerator(), WoodenLetterBlocks.MODID, event.getExistingFileHelper()));
		event.getGenerator().addProvider(new EnUsLangGenerator(event.getGenerator(), WoodenLetterBlocks.MODID, "en_us"));
		event.getGenerator().addProvider(new ItemModelGenerator(event.getGenerator(), WoodenLetterBlocks.MODID, event.getExistingFileHelper()));
	}
}
