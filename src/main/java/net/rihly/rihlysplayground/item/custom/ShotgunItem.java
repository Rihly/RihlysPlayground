package net.rihly.rihlysplayground.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rihly.rihlysplayground.entity.custom.ShotgunBullet;
import org.jetbrains.annotations.NotNull;

public class ShotgunItem extends Item{
    public ShotgunItem(Properties pProperties){
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        for (int count = 0; count < 20; count++) {
            ShotgunBullet bullet = new ShotgunBullet(pLevel, pPlayer, 0, 0, 0, 5, 60);
            bullet.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0, 5, 20);
            pLevel.addFreshEntity(bullet);
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}