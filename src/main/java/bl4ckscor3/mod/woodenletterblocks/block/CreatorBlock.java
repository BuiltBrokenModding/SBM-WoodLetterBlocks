package bl4ckscor3.mod.woodenletterblocks.block;

import bl4ckscor3.mod.woodenletterblocks.container.CreatorContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CreatorBlock extends HorizontalBlock implements INamedContainerProvider
{
	public CreatorBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if(!world.isRemote)
			NetworkHooks.openGui((ServerPlayerEntity)player, this);

		return true;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity player)
	{
		return new CreatorContainer(windowId, playerInv);
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TranslationTextComponent(getTranslationKey());
	}
}
