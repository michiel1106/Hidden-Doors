package bikerboys.hiddendoors;

import bikerboys.hiddendoors.gui.screen.ConverterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class HiddenDoorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(HiddenDoors.CONVERTER_SCREEN_HANDLER, ConverterScreen::new);
    }
}