package bl4ckscor3.mod.woodenletterblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod(WoodenLetterBlocks.MODID)
@EventBusSubscriber(modid=WoodenLetterBlocks.MODID, bus=Bus.MOD)
public class WoodenLetterBlocks
{
	public static final String MODID = "woodenletterblocks";
	@ObjectHolder(MODID + ":lime_dark_oak_w")
	public static Block itemGroupIcon;
	public static final ItemGroup WOODEN_LETTER_BLOCKS_GROUP = new ItemGroup(MODID) {
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(itemGroupIcon);
		}
	};
	private static List<Item> itemBlocksToRegister = new ArrayList<>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Type.Color color : Type.Color.values())
		{
			for(Type.Wood wood : Type.Wood.values())
			{
				for(Type.Letter letter : Type.Letter.values())
				{
					//registry name is <color>_<wood>_<letter>
					Block block = new WoodenLetterBlock().setRegistryName(new ResourceLocation(MODID, color.name().toLowerCase() + "_" + wood.name().toLowerCase() + "_" + letter.name().toLowerCase()));

					itemBlocksToRegister.add(new BlockItem(block, new Item.Properties().group(WOODEN_LETTER_BLOCKS_GROUP)).setRegistryName(block.getRegistryName()));
					event.getRegistry().register(block);
				}
			}
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		itemBlocksToRegister.forEach(itemBlock -> event.getRegistry().register(itemBlock));
		itemBlocksToRegister = null;
	}
}
