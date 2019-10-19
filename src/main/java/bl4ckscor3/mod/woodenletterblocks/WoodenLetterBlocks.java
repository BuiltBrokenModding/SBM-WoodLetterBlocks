package bl4ckscor3.mod.woodenletterblocks;

import java.util.ArrayList;
import java.util.List;

import bl4ckscor3.mod.woodenletterblocks.block.CreatorBlock;
import bl4ckscor3.mod.woodenletterblocks.block.WoodenLetterBlock;
import bl4ckscor3.mod.woodenletterblocks.container.CreatorContainer;
import bl4ckscor3.mod.woodenletterblocks.packet.SendButtonClick;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ObjectHolder;

@Mod(WoodenLetterBlocks.MODID)
@EventBusSubscriber(modid=WoodenLetterBlocks.MODID, bus=Bus.MOD)
public class WoodenLetterBlocks
{
	public static final String MODID = "woodenletterblocks";
	@ObjectHolder(MODID + ":lime_dark_oak_w")
	public static final Block ITEM_GROUP_ICON = null;
	@ObjectHolder(MODID + ":creator")
	public static final Block CREATOR_BLOCK = null;
	@ObjectHolder(MODID + ":creator")
	public static final ContainerType<CreatorContainer> CREATOR_CONTAINER = null;
	public static final ItemGroup WOODEN_LETTER_BLOCKS_GROUP = new ItemGroup(MODID) {
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ITEM_GROUP_ICON);
		}
	};
	private static List<Item> itemBlocksToRegister = new ArrayList<>();
	public static final String PROTOCOL_VERSION = "1.0";
	public static SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{
		channel.registerMessage(0, SendButtonClick.class, SendButtonClick::encode, SendButtonClick::decode, SendButtonClick::onMessage);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new CreatorBlock(Block.Properties.from(Blocks.FURNACE)).setRegistryName(new ResourceLocation(MODID, "creator")));

		Type.forEach((color, wood, letter) -> {
			//registry name is <color>_<wood>_<letter>
			Block block = new WoodenLetterBlock().setRegistryName(Type.getRegistryNameForType(color, wood, letter));

			itemBlocksToRegister.add(new BlockItem(block, new Item.Properties().group(WOODEN_LETTER_BLOCKS_GROUP)).setRegistryName(block.getRegistryName()));
			event.getRegistry().register(block);
		});
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new BlockItem(CREATOR_BLOCK, new Item.Properties().group(WOODEN_LETTER_BLOCKS_GROUP)).setRegistryName(CREATOR_BLOCK.getRegistryName()));
		itemBlocksToRegister.forEach(itemBlock -> event.getRegistry().register(itemBlock));
		itemBlocksToRegister = null;
	}

	@SubscribeEvent
	public static void onRegisterContainerTypes(RegistryEvent.Register<ContainerType<?>> event)
	{
		event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> new CreatorContainer(windowId, inv)).setRegistryName(new ResourceLocation(MODID, "creator")));
	}
}
