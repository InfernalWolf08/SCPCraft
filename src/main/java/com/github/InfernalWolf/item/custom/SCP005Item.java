package com.github.InfernalWolf.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SCP005Item extends Item {
    public SCP005Item(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        if (clickedBlock instanceof DoorBlock doorBlock && !world.isClient())
        {
            boolean isOpen = (boolean) state.get(DoorBlock.OPEN);
            doorBlock.setOpen(context.getPlayer(), world, state, pos, !isOpen);
            //world.playSound(context.getPlayer(), pos, !isOpen ? doorBlock.getBlockSetType().doorOpen() : doorBlock.getBlockSetType().doorClose(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}