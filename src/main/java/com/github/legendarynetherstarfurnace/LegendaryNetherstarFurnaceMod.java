package com.github.legendarynetherstarfurnace;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(LegendaryNetherstarFurnaceMod.MODID)
public class LegendaryNetherstarFurnaceMod {
    public static final String MODID = "legendarynetherstarfurnace";
    private static final Logger LOGGER = LogUtils.getLogger();

    public LegendaryNetherstarFurnaceMod(IEventBus modEventBus) {
        LOGGER.info("Initializing Legendary Netherstar Furnace Mod...");
        
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
}