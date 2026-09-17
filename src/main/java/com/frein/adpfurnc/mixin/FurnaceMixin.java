package com.frein.adpfurnc.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.frein.adpfurnc.ModTags;

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
		ItemStack fuel = nonNullList.get(1);

		result.grow(getBulkAmount(fuel) - 1);

		return result;
	}
	private static int getBulkAmount(ItemStack fuel) {
		if (fuel.is(ModTags.Items.BULK_1)) {
			return 1;
		}
		if (fuel.is(ModTags.Items.BULK_2)) {
			return 2;
		}
		if (fuel.is(ModTags.Items.BULK_3)) {
			return 3;
		}
		if (fuel.is(ModTags.Items.BULK_4)) {
			return 4;
		}


		return 1;
	}
}
