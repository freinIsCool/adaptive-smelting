package com.frein.adpfurnc;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> BULK_1 =
                TagKey.create(Registries.ITEM, AdaptiveSmelting.id("bulk_1"));

        public static final TagKey<Item> BULK_2 =
                TagKey.create(Registries.ITEM, AdaptiveSmelting.id("bulk_2"));

        public static final TagKey<Item> BULK_3 =
                TagKey.create(Registries.ITEM, AdaptiveSmelting.id("bulk_3"));

        public static final TagKey<Item> BULK_4 =
                TagKey.create(Registries.ITEM, AdaptiveSmelting.id("bulk_4"));
    }
}