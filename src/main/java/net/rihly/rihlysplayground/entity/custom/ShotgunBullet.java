package net.rihly.rihlysplayground.entity.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;


public class ShotgunBullet extends LargeFireball {
    private final double range;
    private final int explosionPower;

    public ShotgunBullet(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, int pExplosionPower, double range) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ, pExplosionPower);
        this.explosionPower = pExplosionPower;
        this.range = range;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    protected float getInertia() {
        return 1f;
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            this.markHurt();
            Entity entity = pSource.getEntity();
            if (entity != null) {
                if (!this.level().isClientSide)
                    this.setOwner(entity);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void tick(){
        super.tick();
        Entity owner = this.getOwner();
        if(owner != null) {
            Vec3 ownerPosition = owner.getPosition(1f);
            if(this.getPosition(1f).distanceTo(ownerPosition) > range) {
                this.discard();
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, false, Level.ExplosionInteraction.MOB);
            }
        }
    }
}
