package com.github.InfernalWolf.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SCP009Block extends Block {
    public SCP009Block(Settings settings) { super(settings); }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        convertNearbyBlocks(worldIn, pos);
    }

    // Spread the ice
    private void convertNearbyBlocks(World world, BlockPos pos)
    {
        for(Direction direction : Direction.values()) {
            Block blockCheck = world.getBlockState(pos.offset(direction)).getBlock();
            boolean canReplace = blockCheck.equals(Blocks.WATER) || blockCheck.equals(Blocks.ICE) || blockCheck.equals(Blocks.PACKED_ICE) || blockCheck.equals(Blocks.BLUE_ICE) || blockCheck.equals(Blocks.SNOW) || blockCheck.equals(Blocks.SNOW_BLOCK) || blockCheck.equals(Blocks.POWDER_SNOW);
            System.out.println("Block name: " + blockCheck.getName());
            System.out.println("Replaceable: " + canReplace);
            if (canReplace) {
                world.setBlockState(pos.offset(direction), this.asBlock().getDefaultState());
            }
        }
    }
}