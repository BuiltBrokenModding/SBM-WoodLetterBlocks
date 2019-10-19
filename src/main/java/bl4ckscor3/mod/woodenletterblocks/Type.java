package bl4ckscor3.mod.woodenletterblocks;

import org.apache.logging.log4j.util.TriConsumer;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;

public class Type
{
	/**
	 * All colors a wooden letter block can have
	 */
	public static enum Color
	{
		WHITE(Blocks.WHITE_WOOL),
		ORANGE(Blocks.ORANGE_WOOL),
		MAGENTA(Blocks.MAGENTA_WOOL),
		LIGHT_BLUE(Blocks.LIGHT_BLUE_WOOL),
		YELLOW(Blocks.YELLOW_WOOL),
		LIME(Blocks.LIME_WOOL),
		PINK(Blocks.PINK_WOOL),
		GRAY(Blocks.GRAY_WOOL),
		LIGHT_GRAY(Blocks.LIGHT_GRAY_WOOL),
		CYAN(Blocks.CYAN_WOOL),
		PURPLE(Blocks.PURPLE_WOOL),
		BLUE(Blocks.BLUE_WOOL),
		BROWN(Blocks.BROWN_WOOL),
		GREEN(Blocks.GREEN_WOOL),
		RED(Blocks.RED_WOOL),
		BLACK(Blocks.BLACK_WOOL);

		public final Block block;

		Color(Block block)
		{
			this.block = block;
		}
	}

	/**
	 * All types of wood a wooden letter block can have
	 */
	public static enum Wood
	{
		OAK(Blocks.OAK_PLANKS),
		SPRUCE(Blocks.SPRUCE_PLANKS),
		BIRCH(Blocks.BIRCH_PLANKS),
		JUNGLE(Blocks.JUNGLE_PLANKS),
		ACACIA(Blocks.ACACIA_PLANKS),
		DARK_OAK(Blocks.DARK_OAK_PLANKS);

		public final Block block;

		Wood(Block block)
		{
			this.block = block;
		}
	}

	/**
	 * All letters a wooden letter block can have
	 */
	public static enum Letter
	{
		A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
	}

	/**
	 * Accepts the given tri consumer for each wooden letter block type
	 */
	public static void forEach(TriConsumer<Color,Wood,Letter> triConsumer)
	{
		for(Color color : Color.values())
		{
			for(Wood wood : Wood.values())
			{
				for(Letter letter : Letter.values())
				{
					triConsumer.accept(color, wood, letter);
				}
			}
		}
	}

	public static ResourceLocation getRegistryNameForType(Color color, Wood wood, Letter letter)
	{
		return new ResourceLocation(WoodenLetterBlocks.MODID, color.name().toLowerCase() + "_" + wood.name().toLowerCase() + "_" + letter.name().toLowerCase());
	}
}
