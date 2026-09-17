package com.frein.adpfurnc.client;

import com.frein.adpfurnc.client.DataGen.TagDatagen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AdaptiveSmeltingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(TagDatagen.BlockTagProvider::new);
		pack.addProvider(TagDatagen.ItemTagProvider::new);
	}
}
