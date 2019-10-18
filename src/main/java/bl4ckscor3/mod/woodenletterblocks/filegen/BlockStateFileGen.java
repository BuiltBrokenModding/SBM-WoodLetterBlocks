package bl4ckscor3.mod.woodenletterblocks.filegen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;

public class BlockStateFileGen
{
	public static void generate() throws IOException
	{
		for(Type.Color color : Type.Color.values())
		{
			for(Type.Wood wood : Type.Wood.values())
			{
				for(Type.Letter letter : Type.Letter.values())
				{
					String w = wood.name().toLowerCase();
					String l = letter.name().toLowerCase();
					String c = color.name().toLowerCase();
					String name = c + "_" + w + "_" + l;
					List<String> content = new ArrayList<>();

					content.add("{");
					content.add("	\"variants\": {");
					content.add("        \"facing=south\": { \"model\": \"" + WoodenLetterBlocks.MODID + ":block/" + c + "_" + w + "_" + l + "\"},");
					content.add("        \"facing=west\": { \"model\": \"" + WoodenLetterBlocks.MODID + ":block/" + c + "_" + w + "_" + l + "\", \"y\": 90 },");
					content.add("        \"facing=north\": { \"model\": \"" + WoodenLetterBlocks.MODID + ":block/" + c + "_" + w + "_" + l + "\", \"y\": 180 },");
					content.add("        \"facing=east\": { \"model\": \"" + WoodenLetterBlocks.MODID + ":block/" + c + "_" + w + "_" + l + "\", \"y\": 270 }");
					content.add("	}");
					content.add("}");
					FileUtils.writeLines(new File(System.getProperty("user.home") + "/Downloads/generated/assets/" + WoodenLetterBlocks.MODID + "/blockstates/" + name + ".json"), content);
				}
			}
		}
	}
}