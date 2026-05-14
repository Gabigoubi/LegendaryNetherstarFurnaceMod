package com.github.legendarynetherstarfurnace;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = LegendaryNetherstarFurnaceMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlocks.NETHERSTAR_FURNACE_BE.get(),
                (blockEntity, context) -> blockEntity.getInventory()
        );
    }
}