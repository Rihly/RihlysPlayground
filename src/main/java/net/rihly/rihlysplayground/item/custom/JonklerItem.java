package net.rihly.rihlysplayground.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class JonklerItem extends Item{
    public JonklerItem(Properties pProperties){
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        for(int count = 0; count < 100; count++) {
            Arrow arrow = new Arrow(EntityType.ARROW, pLevel);
            arrow.setPos(pPlayer.getX(), pPlayer.getY() + 1.5f, pPlayer.getZ());
            arrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 8f, 5f);
            arrow.setBaseDamage(10);
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            pLevel.addFreshEntity(arrow);
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
