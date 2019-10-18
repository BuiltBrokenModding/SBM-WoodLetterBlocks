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
	}
}
