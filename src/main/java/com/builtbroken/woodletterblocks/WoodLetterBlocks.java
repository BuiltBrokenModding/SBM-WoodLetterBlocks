package com.builtbroken.woodletterblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod(WoodLetterBlocks.MODID)
@EventBusSubscriber(modid=WoodLetterBlocks.MODID, bus=Bus.MOD)
public class WoodLetterBlocks
{
	public static final String MODID = "woodletterblocks";
	@ObjectHolder("woodletterblocks:lime_dark_oak_w")
	public static Block itemGroupIcon;
	public static final ItemGroup WOOD_LETTER_BLOCKS_GROUP = new ItemGroup(MODID) {
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(itemGroupIcon);
		}
	};
	private static final List<Item> ITEM_BLOCKS_TO_REGISTER = new ArrayList<>();

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
					Block block = new BlockWoodLetter().setRegistryName(new ResourceLocation(MODID, color.name().toLowerCase() + "_" + wood.name().toLowerCase() + "_" + letter.name().toLowerCase()));

					ITEM_BLOCKS_TO_REGISTER.add(new ItemBlock(block, new Item.Properties().group(WOOD_LETTER_BLOCKS_GROUP)).setRegistryName(block.getRegistryName()));
					event.getRegistry().register(block);
				}
			}
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		ITEM_BLOCKS_TO_REGISTER.stream().forEach(entry -> event.getRegistry().register(entry));
		ITEM_BLOCKS_TO_REGISTER.clear();
	}
}
