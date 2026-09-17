package com.frein.adpfurnc;

import net.minecraft.world.item.ItemStack;

public class getBulk {

    public static int getBulkAmount(ItemStack fuel) {
        if (fuel.is(ModTags.Items.BULK_4)) {
            return 4;
        }
        if (fuel.is(ModTags.Items.BULK_3)) {
            return 3;
        }
        if (fuel.is(ModTags.Items.BULK_2)) {
            return 2;
        }
        return 1;
    }
}