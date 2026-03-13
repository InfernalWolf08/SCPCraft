package com.github.InfernalWolf.item;

import com.github.InfernalWolf.Scpcraft;
import com.github.InfernalWolf.block.ModBlocks;
import com.github.InfernalWolf.item.custom.SCP005Item;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SCP005 = registerItem("scp005", new SCP005Item(new Item.Settings().maxCount(1).maxDamage(10)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Scpcraft.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(SCP005);
            entries.add(ModBlocks.SCP009);
        });
    }
}
