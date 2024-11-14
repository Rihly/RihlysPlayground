package net.rihly.rihlysplayground.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rihly.rihlysplayground.RihlysPlayground;
import net.rihly.rihlysplayground.item.custom.HomelanderItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RihlysPlayground.MODID);

    public static final RegistryObject<Item> JONKLER = ITEMS.register("jonkler",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BATEMAN = ITEMS.register("bateman",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HOMELANDER = ITEMS.register("homelander",
            () -> new HomelanderItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
