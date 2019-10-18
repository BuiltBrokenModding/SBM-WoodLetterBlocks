package bl4ckscor3.mod.woodenletterblocks;

import org.apache.logging.log4j.util.TriConsumer;

import net.minecraft.util.ResourceLocation;

public class Type
{
	/**
	 * All colors a wooden letter block can have
	 */
	public static enum Color
	{
		WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, LIGHT_GRAY, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK;
	}

	/**
	 * All types of wood a wooden letter block can have
	 */
	public static enum Wood
	{
		OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK;
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
