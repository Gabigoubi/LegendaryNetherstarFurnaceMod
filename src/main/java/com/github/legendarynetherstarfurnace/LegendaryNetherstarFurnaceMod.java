package com.github.legendarynetherstarfurnace;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(LegendaryNetherstarFurnaceMod.MODID)
public class LegendaryNetherstarFurnaceMod {
    public static final String MODID = "legendarynetherstarfurnace";
    private static final Logger LOGGER = LogUtils.getLogger();

    public LegendaryNetherstarFurnaceMod(IEventBus modEventBus) {
        LOGGER.info("Initializing Legendary Netherstar Furnace Mod...");

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        // CORREÇÃO: Mudado para addListener normal, que o NeoForge aceita rindo
        if (net.neoforged.fml.loading.FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(ModClientEvents::registerScreens);
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.QUANTUM_SINGULARITY_GEAR.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.NETHER_STAR_BLOCK.get());
            event.accept(ModBlocks.DENSE_NETHER_STAR_BLOCK.get());
            event.accept(ModBlocks.FLAWLESS_NETHER_STAR_BLOCK.get());
            event.accept(ModBlocks.SINGULAR_NETHER_STAR_BLOCK.get());
            event.accept(ModBlocks.NETHERSTAR_FURNACE.get());
        }
    }
}