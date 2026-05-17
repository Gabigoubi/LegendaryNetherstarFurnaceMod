package com.github.legendarynetherstarfurnace;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LegendaryNetherstarFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    // 8 slots: 0,1,2,3 = Entradas | 4,5,6,7 = Saídas correspondentes
    private final ItemStackHandler inventory = new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot < 4; // Funis só podem injetar nos 4 primeiros slots
        }
    };

    public LegendaryNetherstarFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.NETHERSTAR_FURNACE_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Cosmic Forge");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new LegendaryNetherstarFurnaceMenu(containerId, playerInventory, this);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LegendaryNetherstarFurnaceBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        boolean hasChanged = false;

        // Varre os 4 slots de entrada ao mesmo tempo
        for (int i = 0; i < 4; i++) {
            ItemStack input = blockEntity.inventory.getStackInSlot(i);
            if (input.isEmpty()) continue;

            SingleRecipeInput recipeInput = new SingleRecipeInput(input);
            Optional<RecipeHolder<SmeltingRecipe>> recipe = level.getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, recipeInput, level);

            if (recipe.isPresent()) {
                ItemStack recipeOutput = recipe.get().value().assemble(recipeInput, level.registryAccess());

                if (!recipeOutput.isEmpty()) {
                    // A MÁGICA ACONTECE AQUI: O output é sempre o Input atual + 4
                    int targetOutputSlot = i + 4;
                    ItemStack currentOutput = blockEntity.inventory.getStackInSlot(targetOutputSlot);

                    // Verifica se a saída tá vazia OU se é o mesmo item e ainda cabe no pack (limite de 64)
                    if (currentOutput.isEmpty() ||
                            (ItemStack.isSameItemSameComponents(currentOutput, recipeOutput) &&
                                    currentOutput.getCount() + recipeOutput.getCount() <= currentOutput.getMaxStackSize())) {

                        // Derrete 1 item da entrada
                        input.shrink(1);

                        // Joga o resultado direto na saída correspondente
                        if (currentOutput.isEmpty()) {
                            blockEntity.inventory.setStackInSlot(targetOutputSlot, recipeOutput.copy());
                        } else {
                            currentOutput.grow(recipeOutput.getCount());
                        }

                        hasChanged = true;
                    }
                }
            }
        }

        // Atualiza a máquina se algo foi derretido
        if (hasChanged) {
            blockEntity.setChanged();
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