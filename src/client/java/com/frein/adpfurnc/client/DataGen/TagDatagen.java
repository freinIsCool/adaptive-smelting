package com.frein.adpfurnc.client.DataGen;

import java.util.concurrent.CompletableFuture;

import com.frein.adpfurnc.AdaptiveSmelting;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class TagDatagen {
	private TagDatagen() {
	}

	public static class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
		public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		public void addTags(HolderLookup.Provider registries) {
			addBlockTag("bulk_1", BlockTags.LOGS_THAT_BURN, BlockTags.PLANKS);
			addBlockTag("bulk_3", Blocks.DRIED_KELP_BLOCK);
		}

		private void addBlockTag(String group, Block... blocks) {
			var tag = TagKey.create(Registries.BLOCK, AdaptiveSmelting.id(group));
			var builder = builder(tag);
			for (Block block : blocks) {
				builder.add(ResourceKey.create(Registries.BLOCK, BuiltInRegistries.BLOCK.getKey(block)));
			}
		}

		private void addBlockTag(String group, TagKey<Block>... tags) {
			var tag = TagKey.create(Registries.BLOCK, AdaptiveSmelting.id(group));
			var builder = builder(tag);
			for (TagKey<Block> value : tags) {
				builder.addOptionalTag(value);
			}
		}
	}

	public static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
		public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		public void addTags(HolderLookup.Provider registries) {
			addItemTag("bulk_1", ItemTags.LOGS_THAT_BURN, ItemTags.PLANKS);
			addItemTag("bulk_2", Items.COAL, Items.CHARCOAL);
			addItemTag("bulk_3", Items.DRIED_KELP_BLOCK, Items.KELP);
			addItemTag("bulk_4", Items.LAVA_BUCKET);
		}

		private void addItemTag(String group, Item... items) {
			var tag = TagKey.create(Registries.ITEM, AdaptiveSmelting.id(group));
			var builder = builder(tag);
			for (Item item : items) {
				builder.add(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
			}
		}

		private void addItemTag(String group, TagKey<Item>... tags) {
			var tag = TagKey.create(Registries.ITEM, AdaptiveSmelting.id(group));
			var builder = builder(tag);
			for (TagKey<Item> value : tags) {
				builder.addOptionalTag(value);
			}
		}
	}
}
