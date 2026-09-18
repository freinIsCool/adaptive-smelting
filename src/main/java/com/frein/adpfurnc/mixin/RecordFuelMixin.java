package com.frein.adpfurnc.mixin;


import com.frein.adpfurnc.AdaptiveFurnace;
import com.frein.adpfurnc.BulkStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.frein.adpfurnc.AdaptiveSmelting.LOGGER;
import static com.frein.adpfurnc.getBulk.getBulkAmount;


@Mixin(AbstractFurnaceBlockEntity.class)
public class RecordFuelMixin {

    @Inject(
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;",
                    shift = At.Shift.AFTER
            )
    )
    private static void recordFuel(
            ServerLevel serverLevel,
            BlockPos blockPos,
            BlockState blockState,
            AbstractFurnaceBlockEntity furnace,
            CallbackInfo ci
    ) {
        ItemStack fuel = furnace.getItem(1);
        int bulkAmount = getBulkAmount(fuel);

        NonNullList<ItemStack> items =
                ((RecordFuelAccessor) (Object) furnace).adaptiveSmelting$getItems();

        BulkStorage.set(items, bulkAmount);
    }
}

@Mixin(AbstractFurnaceBlockEntity.class)
interface RecordFuelAccessor {

    @Accessor("items")
    NonNullList<ItemStack> adaptiveSmelting$getItems();
}