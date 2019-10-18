package bl4ckscor3.mod.woodenletterblocks.gen.asset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;

public class EnUsLangGenerator
{
	public static void generate() throws IOException
	{
		List<String> content = new ArrayList<>();

		content.add("{");

		Type.forEach((color, wood, letter) -> {
			String w = wood.name().toLowerCase();
			String l = letter.name().toLowerCase();
			String c = color.name().toLowerCase();
			String name = c + "_" + w + "_" + l;
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

			content.add("	\"block." + WoodenLetterBlocks.MODID + "." + name + "\": \"" + cConcat + " " + wConcat + " " + capitalizeFirstLetter(l) + "\",");
		});

		content.set(content.size() - 1, content.get(content.size() - 1).replace("\",", "\""));
		content.add("}");
		FileUtils.writeLines(new File(System.getProperty("user.home") + "/Downloads/generated/assets/" + WoodenLetterBlocks.MODID + "/lang/en_us.json"), content);
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