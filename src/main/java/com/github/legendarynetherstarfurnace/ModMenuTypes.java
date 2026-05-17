package com.github.legendarynetherstarfurnace;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, LegendaryNetherstarFurnaceMod.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<LegendaryNetherstarFurnaceMenu>> NETHERSTAR_FURNACE_MENU =
            MENUS.register("netherstar_furnace_menu", () -> IMenuTypeExtension.create((containerId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                net.minecraft.world.level.Level world = inv.player.getCommandSenderWorld();
                return new LegendaryNetherstarFurnaceMenu(containerId, inv, world.getBlockEntity(pos));
            }));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}