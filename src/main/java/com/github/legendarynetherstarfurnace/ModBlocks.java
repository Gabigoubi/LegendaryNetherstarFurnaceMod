package com.github.legendarynetherstarfurnace;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LegendaryNetherstarFurnaceMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, LegendaryNetherstarFurnaceMod.MODID);

    public static final DeferredBlock<Block> NETHER_STAR_BLOCK = registerBlock("nether_star_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(5.0F, 6.0F)));

    public static final DeferredBlock<Block> DENSE_NETHER_STAR_BLOCK = registerBlock("dense_nether_star_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(10.0F, 12.0F)));

    public static final DeferredBlock<Block> FLAWLESS_NETHER_STAR_BLOCK = registerBlock("flawless_nether_star_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(20.0F, 24.0F)));

    public static final DeferredBlock<Block> SINGULAR_NETHER_STAR_BLOCK = registerBlock("singular_nether_star_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(50.0F, 1200.0F)));

    // Fornalha alterada para usar a nova classe customizada que vamos criar logo abaixo
    public static final DeferredBlock<Block> NETHERSTAR_FURNACE = registerBlock("netherstar_furnace",
            () -> new LegendaryNetherstarFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(100.0F, 3600000.0F).requiresCorrectToolForDrops()));

    // Registro da Entidade de Bloco (Lógica)
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LegendaryNetherstarFurnaceBlockEntity>> NETHERSTAR_FURNACE_BE = 
            BLOCK_ENTITIES.register("netherstar_furnace_be",
            () -> BlockEntityType.Builder.of(LegendaryNetherstarFurnaceBlockEntity::new, NETHERSTAR_FURNACE.get()).build(null));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new net.minecraft.world.item.BlockItem(block.get(), new net.minecraft.world.item.Item.Properties()));
    }
}