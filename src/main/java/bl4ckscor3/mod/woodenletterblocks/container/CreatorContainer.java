package bl4ckscor3.mod.woodenletterblocks.container;

import bl4ckscor3.mod.woodenletterblocks.Type.Letter;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import bl4ckscor3.mod.woodenletterblocks.recipe.CreatorRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;

public class CreatorContainer extends Container
{
	private static final int PLANKS = 0, WOOL = 1, OUTPUT = 2;
	private IInventory inv = new Inventory(3);
	public Letter letter = null;

	public CreatorContainer(int windowId, PlayerInventory playerInv)
	{
		super(WoodenLetterBlocks.CREATOR_CONTAINER, windowId);

		int xOffset = 10;
		int yOffset = 16;

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18 + xOffset, 84 + i * 18 + yOffset));
			}
		}

		for(int i = 0; i < 9; i++)
		{
			addSlot(new Slot(playerInv, i, 8 + i * 18 + xOffset, 142 + yOffset));
		}

		addSlot(new Slot(inv, PLANKS, 12, 26) {
			@Override
			public void onSlotChanged()
			{
				super.onSlotChanged();

				updateOutput(inv);
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}

			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem().isIn(ItemTags.PLANKS);
			}
		});
		addSlot(new Slot(inv, WOOL, 12, 60) {
			@Override
			public void onSlotChanged()
			{
				super.onSlotChanged();

				updateOutput(inv);
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}

			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return stack.getItem().isIn(ItemTags.WOOL);
			}
		});
		addSlot(new Slot(inv, OUTPUT, 49, 43) {
			@Override
			public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack)
			{
				inv.clear();
				return super.onTake(thePlayer, stack);
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}

			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
		});
	}

	public void updateOutput(IInventory inventory)
	{
		ItemStack planks = inventory.getStackInSlot(PLANKS);
		ItemStack wool = inventory.getStackInSlot(WOOL);

		if(planks.isEmpty() || wool.isEmpty())
			inventory.setInventorySlotContents(OUTPUT, ItemStack.EMPTY);
		else if(!planks.isEmpty() && !wool.isEmpty() && letter != null)
		{
			CreatorRecipe recipe = CreatorRecipe.getMatchingRecipe(planks.getItem(), wool.getItem(), letter);

			if(recipe != null)
				inventory.setInventorySlotContents(OUTPUT, recipe.getOutput());
		}
	}

	@Override
	public void onContainerClosed(PlayerEntity player)
	{
		super.onContainerClosed(player);

		inv.setInventorySlotContents(OUTPUT, ItemStack.EMPTY);
		clearContainer(player, player.world, inv);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canInteractWith(PlayerEntity player)
	{
		return true;
	}

	public IInventory getInv()
	{
		return inv;
	}
}
