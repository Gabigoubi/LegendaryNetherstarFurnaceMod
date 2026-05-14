package com.github.legendarynetherstarfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.Optional;

public class LegendaryNetherstarFurnaceBlockEntity extends BlockEntity {
    
    private final ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public LegendaryNetherstarFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.NETHERSTAR_FURNACE_BE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LegendaryNetherstarFurnaceBlockEntity blockEntity) {
        ItemStack input = blockEntity.inventory.getStackInSlot(0);
        
        if (input.isEmpty()) {
            return;
        }

        SingleRecipeInput recipeInput = new SingleRecipeInput(input);
        Optional<RecipeHolder<SmeltingRecipe>> recipe = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, recipeInput, level);

        if (recipe.isPresent()) {
            ItemStack recipeOutput = recipe.get().value().assemble(recipeInput, level.registryAccess());
            
            if (!recipeOutput.isEmpty() && blockEntity.canInsertOutput(recipeOutput)) {
                // Processamento instantâneo: reduz 1 da entrada e adiciona o resultado na saída
                input.shrink(1);
                blockEntity.insertOutput(recipeOutput);
                blockEntity.setChanged();
            }
        }
    }

    private boolean canInsertOutput(ItemStack output) {
        ItemStack currentOutput = this.inventory.getStackInSlot(1);
        if (currentOutput.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(currentOutput, output)) return false;
        return (currentOutput.getCount() + output.getCount()) <= currentOutput.getMaxStackSize();
    }

    private void insertOutput(ItemStack output) {
        ItemStack currentOutput = this.inventory.getStackInSlot(1);
        if (currentOutput.isEmpty()) {
            this.inventory.setStackInSlot(1, output.copy());
        } else {
            currentOutput.grow(output.getCount());
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", this.inventory.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory")) {
            this.inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        }
    }

 
    public ItemStackHandler getInventory() {
        return this.inventory;
    }
}