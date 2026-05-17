package com.github.legendarynetherstarfurnace;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModCreativeTabs {
    // Cria o registro da aba
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "legendarynetherstarfurnace");

    // Configura a aba
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LEGENDARY_TAB = CREATIVE_MODE_TABS.register("legendary_tab", () -> CreativeModeTab.builder()
            // Título que vai aparecer ao passar o mouse
            .title(Component.translatable("creativetab.legendarynetherstarfurnace.legendary_tab"))

            // Define o ícone da aba como a sua Quantum Gear
            .icon(() -> new ItemStack(ModItems.QUANTUM_SINGULARITY_GEAR.get()))

            // Adiciona todos os seus blocos e itens na aba
            .displayItems((parameters, output) -> {
                // A Máquina
                output.accept(ModBlocks.NETHERSTAR_FURNACE.get()); // Se o bloco se chamar NETHERSTAR_FURNACE, troque aqui

                // Os Itens
                output.accept(ModItems.QUANTUM_SINGULARITY_GEAR.get());

                // Os Blocos de Compressão
                output.accept(ModBlocks.NETHER_STAR_BLOCK.get());
                output.accept(ModBlocks.DENSE_NETHER_STAR_BLOCK.get());
                output.accept(ModBlocks.FLAWLESS_NETHER_STAR_BLOCK.get());
                output.accept(ModBlocks.SINGULAR_NETHER_STAR_BLOCK.get());
            })
            .build());
}