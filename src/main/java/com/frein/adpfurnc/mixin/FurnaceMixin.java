package com.frein.adpfurnc.mixin;

import com.frein.adpfurnc.BulkStorage;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceMixin {
	@ModifyArg(
			method = "burn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/core/NonNullList;set(ILjava/lang/Object;)Ljava/lang/Object;"
			),
			index = 1
	)
	private static Object modifyOutput(
			Object stack,
			@Local NonNullList<ItemStack> nonNullList
	) {
		ItemStack result = (ItemStack) stack;
		result.grow(BulkStorage.get(nonNullList) - 1);

		return result;
	}

	@ModifyArg(
			method = "burn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;grow(I)V"
			),
			index = 0
	)
	private static int modifyExistingOutput(
			int amount,
			@Local NonNullList<ItemStack> nonNullList
	) {
		return BulkStorage.get(nonNullList);
	}
}