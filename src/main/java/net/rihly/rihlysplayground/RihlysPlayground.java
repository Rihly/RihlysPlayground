package net.rihly.rihlysplayground;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rihly.rihlysplayground.block.ModBlocks;
import net.rihly.rihlysplayground.entity.custom.JonklerArrow;
import net.rihly.rihlysplayground.item.ModCreativeModeTabs;
import net.rihly.rihlysplayground.item.ModItems;
import net.rihly.rihlysplayground.item.custom.JonklerItem;
import net.rihly.rihlysplayground.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RihlysPlayground.MODID)
public class RihlysPlayground {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "rihlysplayground";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public RihlysPlayground() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModSounds.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        JonklerItem jonkler = (JonklerItem) ModItems.JONKLER.get();
        DispenseItemBehavior behavior = new DispenseItemBehavior() {
            @Override
            public @NotNull ItemStack dispense(BlockSource pSource, @NotNull ItemStack pStack) {
                Level pLevel = pSource.getLevel();
                Position position = DispenserBlock.getDispensePosition(pSource);
                Direction direction = pSource.getBlockState().getValue(DispenserBlock.FACING);
                for (int count = 0; count < 100; count++) {
                    JonklerArrow arrow = new JonklerArrow(pLevel);
                    arrow.setPos(position.x(), position.y(), position.z());
                    arrow.shoot(direction.getStepX(), ((float) direction.getStepY() + 0.1F), direction.getStepZ(), 8f, 20f);
                    pLevel.addFreshEntity(arrow);
                }
                pStack.shrink(1);
                return pStack;
            }
        };
        DispenserBlock.registerBehavior(jonkler, behavior);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
