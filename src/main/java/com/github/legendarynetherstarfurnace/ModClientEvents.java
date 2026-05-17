package com.github.legendarynetherstarfurnace;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ModClientEvents {
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.NETHERSTAR_FURNACE_MENU.get(), LegendaryNetherstarFurnaceScreen::new);
    }
}