package net.rihly.rihlysplayground.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rihly.rihlysplayground.RihlysPlayground;
import net.rihly.rihlysplayground.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RihlysPlayground.MODID);

    public static final RegistryObject<CreativeModeTab> RIHLYS_TAB = CREATIVE_MODE_TABS.register("rihlys_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.JONKLER.get()))
                    .title(Component.translatable("creativetab.rihlys_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.JONKLER.get());
                        pOutput.accept(ModItems.BATEMAN.get());
                        pOutput.accept(ModBlocks.JONKLER_BLOCK.get());
                        pOutput.accept(ModBlocks.BATEMAN_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
