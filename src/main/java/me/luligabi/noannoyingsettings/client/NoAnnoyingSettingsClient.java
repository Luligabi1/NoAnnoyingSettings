package me.luligabi.noannoyingsettings.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.tutorial.TutorialStep;
import net.minecraft.sound.SoundCategory;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class NoAnnoyingSettingsClient implements ClientModInitializer {

    SimpleConfig config = SimpleConfig.of("noannoyingsettings").provider(this::provider).request();

    private String provider(String filename) {
        return "# No Annoying Settings Configuration File\n\n" +

                "# Keep in mind that the 'disable' and 'set' configurations will only prevent/allow the mod to change the settings every start. If the config is already set, it will remain the same.\n\n" +

                "disableAutoJump=true\n\n" +

                "disableMusic=true\n\n" +

                "setGuiScale=true\n" +
                "guiScaleValue=3\n\n" +
                "showSubtitles=false";
    }
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> {
            if(config.getOrDefault("disableAutoJump", true)) {
                MinecraftClient.getInstance().options.autoJump = false;
            }
            if(config.getOrDefault("disableMusic", true)) {
                MinecraftClient.getInstance().options.setSoundVolume(SoundCategory.MUSIC, 0);
            }
            if(config.getOrDefault("setGuiScale", true)) {
                MinecraftClient.getInstance().options.guiScale = config.getOrDefault("guiScaleValue", 3);
            }
            if(config.getOrDefault("showSubtitles", false)) {
                MinecraftClient.getInstance().options.showSubtitles = true;
            }
            MinecraftClient.getInstance().options.tutorialStep = TutorialStep.NONE;
        });
    }
}