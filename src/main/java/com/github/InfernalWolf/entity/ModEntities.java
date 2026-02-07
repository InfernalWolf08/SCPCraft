package com.github.InfernalWolf.entity;

import com.github.InfernalWolf.Scpcraft;
import com.github.InfernalWolf.entity.custom.SCP173Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SCP173Entity> SCP173 = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Scpcraft.MOD_ID, "scp173"),
            EntityType.Builder.create(SCP173Entity::new, SpawnGroup.MONSTER).dimensions(.5f, 1.7f).build());
}