package com.frein.adpfurnc.mixin;

import com.frein.adpfurnc.BulkStorage;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class ControlSmeltingMixin {
    @Inject(
            method = "canBurn",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void modifyCanBurn(
            RegistryAccess registryAccess,
            @org.jspecify.annotations.Nullable RecipeHolder<? extends AbstractCookingRecipe> recipeHolder,
            SingleRecipeInput singleRecipeInput,
            NonNullList<ItemStack> nonNullList,
            int i,
            CallbackInfoReturnable<Boolean> cir
    ) {
        int smeltAmount = getSmeltAmount(nonNullList);
        ItemStack output = nonNullList.get(2);

        if (output.isEmpty()) {
            return;
        }

        if (output.getCount() + smeltAmount > output.getMaxStackSize()) {
            cir.setReturnValue(false);
        }
    }
    @ModifyArg(
            method = "burn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"
            ),
            index = 0
    )
    private static int deductCorrect(int i, @Local NonNullList<ItemStack> nonNullList) {
        return BulkStorage.get(nonNullList);
    }
    private static int getSmeltAmount(NonNullList<ItemStack> nonNullList) {
        return Math.min(
                BulkStorage.get(nonNullList),
                nonNullList.get(0).getCount()
        );
    }
}
