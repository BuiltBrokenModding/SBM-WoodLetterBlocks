package bl4ckscor3.mod.woodenletterblocks.gen.data;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class EnUsLangGenerator extends LanguageProvider
{
	public EnUsLangGenerator(DataGenerator gen, String modid, String locale)
	{
		super(gen, modid, locale);
	}

	@Override
	protected void addTranslations()
	{
		add("itemGroup.woodenletterblocks", "Wooden Letter Blocks");
		add(WoodenLetterBlocks.CREATOR_BLOCK, "Wooden Letter Block Creator");
		Type.forEach((color, wood, letter) -> {
			String w = wood.name().toLowerCase();
			String l = letter.name().toLowerCase();
			String c = color.name().toLowerCase();
			String[] wSplit = w.split("_");
			String[] cSplit = c.split("_");

			for(int i = 0; i < wSplit.length; i++)
			{
				wSplit[i] = capitalizeFirstLetter(wSplit[i]);
			}

			for(int i = 0; i < cSplit.length; i++)
			{
				cSplit[i] = capitalizeFirstLetter(cSplit[i]);
			}

			String wConcat = "";
			String cConcat = "";

			for(String s : wSplit)
			{
				wConcat += s + " ";
			}

			for(String s : cSplit)
			{
				cConcat += s + " ";
			}

			wConcat = wConcat.trim();
			cConcat = cConcat.trim();

			addBlock(() -> ForgeRegistries.BLOCKS.getValue(Type.getRegistryNameForType(color, wood, letter)), cConcat + " " + wConcat + " " + capitalizeFirstLetter(l));
		});
	}

	/**
	 * Capitalizes the first letter of a non-empty string if possible
	 * @param string The string to capitalize the first letter of
	 * @return The string with its first letter capitalized
	 */
	private static String capitalizeFirstLetter(String string)
	{
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
}