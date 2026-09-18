package com.frein.adpfurnc;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class SmeltingUtils {

    public static int getSmeltAmount(NonNullList<ItemStack> items) {
        return Math.min(
                BulkStorage.get(items),
                items.get(0).getCount()
        );
    }
}