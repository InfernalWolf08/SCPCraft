package com.github.InfernalWolf.entity.custom;

// Imports
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.function.Predicate;

// Class
public class SCP173Entity extends HostileEntity {
    // Fields
    public double baseSpeed;

    // Constructor
    public SCP173Entity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;
        baseSpeed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getBaseValue();
    }

    // Goals
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new AttackGoal(this));
        this.targetSelector.add(1, new WeepingAngelGoal(this, PlayerEntity.class, false));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    // Attributes
    public static DefaultAttributeContainer.Builder create173Atttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .8f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1000);
    }

    // Sounds
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    // Methods
    public boolean damage(DamageSource source, float amount)
    {
        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity player)
        {
            if (player.getMainHandStack().getItem() instanceof PickaxeItem)
            {
                return super.damage(source, amount);
            }
        }

        return false;
    }

    boolean isPlayerStaring(PlayerEntity player) {
        Vec3d vec3d = player.getRotationVec(1.0F).normalize();
        Vec3d vec3d2 = new Vec3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
        double d = vec3d2.length();
        vec3d2 = vec3d2.normalize();
        double e = vec3d.dotProduct(vec3d2);
        return e > (double)1.0F - 0.025 / d ? player.canSee(this) : false;
    }

    public boolean isEntityLookingAtMe(LivingEntity entity, double d, boolean bl, boolean visualShape, double... checkedYs) {
        Vec3d vec3d = entity.getRotationVec(1.0F).normalize();

        for(double e : checkedYs) {
            Vec3d vec3d2 = new Vec3d(this.getX() - entity.getX(), e - entity.getEyeY(), this.getZ() - entity.getZ());
            double f = vec3d2.length();
            vec3d2 = vec3d2.normalize();
            double g = vec3d.dotProduct(vec3d2);
            if (g > (double)1.0F - d / (bl ? f : (double)1.0F) && entity.canSee(this)){//, visualShape ? RaycastContext.ShapeType.VISUAL : RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, e)) {
                return true;
            }
        }

        return false;
    }

    // Custom Attributes
    static class WeepingAngelGoal extends ActiveTargetGoal<PlayerEntity> {
        // Fields
        private SCP173Entity mob;
        @Nullable
        private Class<PlayerEntity> targetClass;
        private PlayerEntity targetPlayer;
        private final TargetPredicate validTargetPredicate = TargetPredicate.createAttackable().ignoreVisibility();
        //private int debugInt=0;

        // Constructor
        public WeepingAngelGoal(SCP173Entity mob, @Nullable Class<PlayerEntity> targetClass, boolean checkVisibility) {
            super(mob, targetClass, checkVisibility);
            this.mob = mob;
            this.targetClass = targetClass;
        }

        // Methods
        public boolean canStart()
        {
            // Get target
            this.targetPlayer = this.mob.getWorld().getClosestPlayer(this.mob, Double.POSITIVE_INFINITY);

            if (targetPlayer!=null)
            {
                // Return if the player is looking at it
                boolean isBeingLookedAt = this.mob.isEntityLookingAtMe(targetPlayer, (double) 0.5F, false, true, new double[]{this.mob.getEyeY(), this.mob.getY() + (double) 0.5F * (double) this.mob.getScale(), (this.mob.getEyeY() + this.mob.getY()) / (double) 2.0F});
                //System.out.println(isBeingLookedAt + ": " + debugInt);
                //this.debugInt++;
                return isBeingLookedAt;
            } else {
                return false;
            }
        }

        public void start()
        {
            this.mob.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            //System.out.println("SCP173 is being stared at");
        }

        public void stop()
        {
            this.targetPlayer = null;
            this.mob.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(this.mob.baseSpeed);
            //System.out.println("SCP173 is no longer being stared at");
            super.stop();
        }

        @Override
        public boolean shouldContinue() {
            if (targetPlayer!=null)
            {
                boolean lookTest = this.mob.isEntityLookingAtMe(targetPlayer, (double)0.5F, false, true, new double[]{this.mob.getEyeY(), this.mob.getY() + (double)0.5F * (double)this.mob.getScale(), (this.mob.getEyeY() + this.mob.getY()) / (double)2.0F});
                //System.out.println(lookTest + ": " + debugInt);
                //debugInt++;
                return lookTest;
            } else {
                return this.validTargetPredicate.test(this.mob, this.targetEntity);
            }
        }
    }
}