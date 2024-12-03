package net.rihly.rihlysplayground.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rihly.rihlysplayground.RihlysPlayground;
import net.rihly.rihlysplayground.entity.custom.ShotgunBullet;

@Mod.EventBusSubscriber(modid = RihlysPlayground.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void Detonate(ExplosionEvent event) {
        Entity entity = event.getExplosion().getDirectSourceEntity();
        if (entity instanceof ShotgunBullet) {
            LivingEntity shooter = ((ShotgunBullet) entity).getShooter();
            shooter.setInvulnerable(true);
        }
    }

    @SubscribeEvent
    public static void EntityLeaveLevelEvent(EntityLeaveLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ShotgunBullet) {
            LivingEntity shooter = ((ShotgunBullet) entity).getShooter();
            shooter.setInvulnerable(false);
        }
    }
}


