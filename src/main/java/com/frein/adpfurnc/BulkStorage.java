package com.frein.adpfurnc;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import java.util.IdentityHashMap;
import java.util.Map;

public class BulkStorage {

    private static final Map<NonNullList<ItemStack>, Integer> BULK_AMOUNTS =
            new IdentityHashMap<>();

    public static void set(NonNullList<ItemStack> items, int amount) {
        BULK_AMOUNTS.put(items, amount);
    }

    public static int get(NonNullList<ItemStack> items) {
        return BULK_AMOUNTS.getOrDefault(items, 1);
    }
}