package net.rihly.rihlysplayground.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class JonklerArrow extends Arrow {
    private int life = 0;
    public JonklerArrow(Level pLevel) {
        super(EntityType.ARROW, pLevel);
    }

    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 15) {
            this.discard();
        }
    }
}
