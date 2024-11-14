package net.rihly.rihlysplayground.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rihly.rihlysplayground.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

public class HomelanderItem extends Item {
    public HomelanderItem(Properties pProperties){
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.playSound(ModSounds.YUMMERS.get());
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
