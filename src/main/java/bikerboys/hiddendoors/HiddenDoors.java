package bikerboys.hiddendoors;

import bikerboys.hiddendoors.block.ModBlocks;
import bikerboys.hiddendoors.gui.ConverterScreenHandler;
import bikerboys.hiddendoors.gui.screen.ConverterScreen;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HiddenDoors implements ModInitializer {
	public static final String MOD_ID = "hidden-doors";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	//public static ScreenHandlerType<ConverterScreenHandler> CONVERTER_SCREEN_HANDLER;

	public static final ScreenHandlerType<ConverterScreenHandler> CONVERTER_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of("hiddendoors", "converter_block"), new ScreenHandlerType<>(ConverterScreenHandler::new, FeatureSet.empty()));

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		LOGGER.info("Hello Fabric world!");

	}
}