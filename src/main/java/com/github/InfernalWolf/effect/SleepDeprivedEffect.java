package com.github.InfernalWolf.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3d;

public class SleepDeprivedEffect extends StatusEffect {
    protected SleepDeprivedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.getWorld().isClient()) {
            Vec3d velocity = pLivingEntity.getVelocity();
            Vec3d newVelocity = new Vec3d(
                    velocity.getX()*0.15,
                    velocity.getY()*0.15,
                    velocity.getZ()*0.15);
            pLivingEntity.setVelocity(newVelocity);
            //pLivingEntity.setVelocity(new Vec3d(0, 0, 0));
            System.out.println(pLivingEntity.getClass());
        }

        return super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}