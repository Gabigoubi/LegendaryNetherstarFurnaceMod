package com.github.legendarynetherstarfurnace;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LegendaryNetherstarFurnaceMod.MODID);

    public static final DeferredItem<Item> QUANTUM_SINGULARITY_GEAR = ITEMS.register("quantum_singularity_gear",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC))); // Epic gives a distinct look, but translation handles the custom orange color
}