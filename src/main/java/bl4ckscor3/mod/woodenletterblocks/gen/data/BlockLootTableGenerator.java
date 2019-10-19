package bl4ckscor3.mod.woodenletterblocks.gen.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bl4ckscor3.mod.woodenletterblocks.Type;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraftforge.registries.ForgeRegistries;

//derived from McJty's tutorial
public class BlockLootTableGenerator extends LootTableProvider
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	protected final Map<Block,LootTable.Builder> lootTables = new HashMap<>();
	private final DataGenerator generator;

	public BlockLootTableGenerator(DataGenerator dataGenerator)
	{
		super(dataGenerator);

		generator = dataGenerator;
	}

	@Override
	public void act(DirectoryCache cache)
	{
		Map<ResourceLocation,LootTable> tables = new HashMap<>();

		Type.forEach((color, wood, letter) -> {
			Block block = ForgeRegistries.BLOCKS.getValue(Type.getRegistryNameForType(color, wood, letter));

			lootTables.put(block, createStandardBlockLootTable(block));
		});

		lootTables.put(WoodenLetterBlocks.CREATOR_BLOCK, createStandardBlockLootTable(WoodenLetterBlocks.CREATOR_BLOCK));

		for(Map.Entry<Block,LootTable.Builder> entry : lootTables.entrySet())
		{
			tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}

		tables.forEach((key, lootTable) -> {
			try
			{
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), generator.getOutputFolder().resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	protected final LootTable.Builder createStandardBlockLootTable(Block block)
	{
		LootPool.Builder builder = LootPool.builder()
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}

	@Override
	public String getName()
	{
		return "Wooden Letter Blocks Loot Tables";
	}
}
