package com.github.InfernalWolf.effect;

import com.github.InfernalWolf.Scpcraft;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect SleepDeprived;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Scpcraft.MOD_ID, name), new SleepDeprivedEffect(StatusEffectCategory.HARMFUL, 0xc8dfe0));
    }

    public static void registerEffects() {
        SleepDeprived = registerStatusEffect("sleepdeprived");
    }
}
