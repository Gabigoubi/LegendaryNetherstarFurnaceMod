package com.github.legendarynetherstarfurnace;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class LegendaryNetherstarFurnaceMenu extends AbstractContainerMenu {
    private final LegendaryNetherstarFurnaceBlockEntity blockEntity;

    public LegendaryNetherstarFurnaceMenu(int containerId, Inventory playerInventory, BlockEntity entity) {
        super(ModMenuTypes.NETHERSTAR_FURNACE_MENU.get(), containerId);
        this.blockEntity = (LegendaryNetherstarFurnaceBlockEntity) entity;
        ItemStackHandler inventory = this.blockEntity.getInventory();

        // =====================================================================
        // 4 SLOTS DE ENTRADA (INPUTS) - LINHA DE CIMA
        // =====================================================================
        for (int i = 0; i < 4; i++) {
            // MUDE O 64 (X - Lados) E O 24 (Y - Cima/Baixo)
            addSlot(new SlotItemHandler(inventory, i, 56 + (i * 37), 18));
        }

        // =====================================================================
        // 4 SLOTS DE SAÍDA (OUTPUTS) - LINHA DE BAIXO
        // =====================================================================
        for (int i = 0; i < 4; i++) {
            // MUDE O 64 (X - Lados) E O 69 (Y - Cima/Baixo)
            addSlot(new SlotItemHandler(inventory, i + 4, 57 + (i * 36), 54) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return false;
                }
            });
        }

        // =====================================================================
        // INVENTÁRIO DO JOGADOR (As 3 linhas do baú cinza)
        // O seu parecia estar perfeito no X=83 e Y=89, mas se precisar, mude aqui:
        // =====================================================================
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                // MUDE O 83 (X - Lados) E O 89 (Y - Cima/Baixo)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 39 + j * 18, 86 + i * 18));
            }
        }

        // =====================================================================
        // HOTBAR DO JOGADOR (A linha única de slots lá embaixo)
        // O X costuma ser igual ao de cima.
        // =====================================================================
        for (int i = 0; i < 9; ++i) {
            // MUDE O 83 (X - Lados) E O 147 (Y - Cima/Baixo)
            this.addSlot(new Slot(playerInventory, i, 39 + i * 18, 145));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < 8) {
                if (!this.moveItemStackTo(itemstack1, 8, 44, true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return !this.blockEntity.isRemoved() && player.distanceToSqr(this.blockEntity.getBlockPos().getX() + 0.5D, this.blockEntity.getBlockPos().getY() + 0.5D, this.blockEntity.getBlockPos().getZ() + 0.5D) <= 64.0D;
    }
}