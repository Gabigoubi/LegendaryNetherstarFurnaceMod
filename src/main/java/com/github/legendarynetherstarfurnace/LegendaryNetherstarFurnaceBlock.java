package com.github.legendarynetherstarfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.Nullable;

public class LegendaryNetherstarFurnaceBlock extends BaseEntityBlock {
    public static final MapCodec<LegendaryNetherstarFurnaceBlock> CODEC = simpleCodec(LegendaryNetherstarFurnaceBlock::new);

    public LegendaryNetherstarFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // Método obrigatório no 1.21.1 para clique direito
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof LegendaryNetherstarFurnaceBlockEntity furnaceBE) {
                player.openMenu(furnaceBE, pos); // Abre a Interface
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    // cospe os itens pra fora se explodir ou quebrar a máquina
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof LegendaryNetherstarFurnaceBlockEntity furnaceBE) {
                for (int i = 0; i < furnaceBE.getInventory().getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), furnaceBE.getInventory().getStackInSlot(i));
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LegendaryNetherstarFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ModBlocks.NETHERSTAR_FURNACE_BE.get(), LegendaryNetherstarFurnaceBlockEntity::tick);
    }
}