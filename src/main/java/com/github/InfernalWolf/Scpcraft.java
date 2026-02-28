package com.github.InfernalWolf;

import com.github.InfernalWolf.effect.ModEffects;
import com.github.InfernalWolf.entity.ModEntities;
import com.github.InfernalWolf.entity.custom.SCP173Entity;
import com.github.InfernalWolf.item.ModItems;
import com.github.InfernalWolf.world.gen.ModEntitySpawns;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class Scpcraft implements ModInitializer {
    // Variables
    public static final String MOD_ID = "scpcraft";

    @Override
    public void onInitialize() {
        // Entity Attributes
        FabricDefaultAttributeRegistry.register(ModEntities.SCP173, SCP173Entity.create173Atttributes());

        // Entity Spawns
        ModEntitySpawns.addSpawns();

        // Items
        ModItems.registerModItems();

        // Status Effects
        ModEffects.registerEffects();

        // Custom Sound
        Registry.register(Registries.SOUND_EVENT, new Identifier(MOD_ID, "scp173die"),
                SoundEvent.of(new Identifier(MOD_ID, "scp173die")));
    }
}