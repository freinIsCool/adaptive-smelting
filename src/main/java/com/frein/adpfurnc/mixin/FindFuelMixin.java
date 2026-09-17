package com.frein.adpfurnc.mixin;


import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.frein.adpfurnc.AdaptiveSmelting.LOGGER;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FindFuelMixin {
    @Inject(method = "burn", at = @At("HEAD"))
    private static void GetVar(
            RegistryAccess registryAccess,
            RecipeHolder<? extends AbstractCookingRecipe> recipeHolder,
            SingleRecipeInput input,
            NonNullList<ItemStack> items,
            int maxStackSize,
            CallbackInfoReturnable<Boolean> cir
    ) {
        ItemStack fuel = items.get(1);
        if (fuel.is(Items.COAL)) {
            LOGGER.info("this item was smelted with coal!");
        } else {
            LOGGER.info("this item was smelted with {}!", fuel);
        }
    }
}
