package com.frein.adpfurnc.mixin;


import com.frein.adpfurnc.AdaptiveFurnace;
import com.frein.adpfurnc.BulkStorage;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public class PersistOnRejoin implements AdaptiveFurnace {

    @Unique
    private int adaptiveSmelting$bulkAmount = 1;

    @Override
    public int adaptiveSmelting$getBulkAmount() {
        return adaptiveSmelting$bulkAmount;
    }

    @Override
    public void adaptiveSmelting$setBulkAmount(int amount) {
        adaptiveSmelting$bulkAmount = amount;
    }

    @Inject(
            method = "saveAdditional",
            at = @At("TAIL")
    )
    private void saveFuel(ValueOutput valueOutput, CallbackInfo ci) {
        valueOutput.putInt(
                "AdaptiveSmeltingBulk",
                adaptiveSmelting$bulkAmount
        );
    }

    @Inject(
            method = "loadAdditional",
            at = @At("TAIL")
    )
    private void loadFuel(ValueInput valueInput, CallbackInfo ci) {
        adaptiveSmelting$bulkAmount =
                valueInput.getIntOr("AdaptiveSmeltingBulk", 1);

        BulkStorage.set(
                ((RecordFuelAccessor)(Object)this).adaptiveSmelting$getItems(),
                adaptiveSmelting$bulkAmount
        );
    }
}