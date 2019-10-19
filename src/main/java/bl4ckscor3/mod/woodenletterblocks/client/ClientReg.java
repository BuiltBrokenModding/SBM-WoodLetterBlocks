package bl4ckscor3.mod.woodenletterblocks.client;

import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid=WoodenLetterBlocks.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientReg
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		ScreenManager.registerFactory(WoodenLetterBlocks.CREATOR_CONTAINER, CreatorScreen::new);
	}
}
