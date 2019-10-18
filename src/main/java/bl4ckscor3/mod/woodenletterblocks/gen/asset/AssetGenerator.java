package bl4ckscor3.mod.woodenletterblocks.gen.asset;

import java.io.IOException;

public class AssetGenerator
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Starting to generate assets!");

			long then = System.currentTimeMillis();

			BlockModelGenerator.generate();
			System.out.println("Block model file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			BlockStateGenerator.generate();
			System.out.println("Block state file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			EnUsLangGenerator.generate();
			System.out.println("en_us language file gen took " + (System.currentTimeMillis() - then) + "ms");
			then = System.currentTimeMillis();

			ItemModelGenerator.generate();
			System.out.println("Item model file gen took " + (System.currentTimeMillis() - then) + "ms");
			System.out.println("Done!");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
