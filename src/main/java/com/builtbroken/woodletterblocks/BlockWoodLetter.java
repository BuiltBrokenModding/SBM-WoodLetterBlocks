package com.builtbroken.woodletterblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;

public class BlockWoodLetter extends BlockHorizontal
{
    public BlockWoodLetter()
    {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F));
    }

    @Override
    public IBlockState getStateForPlacement(BlockItemUseContext context)
    {
        return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(Builder<Block, IBlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }
}
