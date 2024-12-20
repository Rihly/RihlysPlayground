package net.rihly.rihlysplayground.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rihly.rihlysplayground.RihlysPlayground;
import net.rihly.rihlysplayground.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RihlysPlayground.MODID);

    public static final RegistryObject<Block> JONKLER_BLOCK = registerBlock("jonkler_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TNT).sound(SoundType.NETHERITE_BLOCK)
                    .strength(0.5f)));
    public static final RegistryObject<Block> BATEMAN_BLOCK = registerBlock("bateman_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TNT).sound(SoundType.AMETHYST)
                    .strength(0.5f)));
    public static final RegistryObject<Block> HOMELANDER_BLOCK = registerBlock("homelander_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TNT).sound(SoundType.ANVIL)
                    .strength(0.5f)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
