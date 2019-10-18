package bl4ckscor3.mod.woodenletterblocks.filegen;

import java.io.IOException;

public class AssetGenerator
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Starting to generate assets!");

			long then = System.currentTimeMillis();

			BlockModelFileGen.generate();
			System.out.println("Block model file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			BlockStateFileGen.generate();
			System.out.println("Block state file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			EnUsLangFileGen.generate();
			System.out.println("en_us language file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			ItemModelFileGen.generate();
			System.out.println("Item model file gen took " + (System.currentTimeMillis() - then) + "ms");
			System.out.println("Done!");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
