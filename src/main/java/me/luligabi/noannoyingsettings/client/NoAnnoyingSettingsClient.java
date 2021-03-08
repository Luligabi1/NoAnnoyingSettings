package me.luligabi.noannoyingsettings.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

@Environment(EnvType.CLIENT)
public class NoAnnoyingSettingsClient implements ClientModInitializer {

    SimpleConfig config = SimpleConfig.of("noannoyingsettings").provider(this::provider).request();

    private String provider(String filename) {
        return "# No Annoying Settings Configuration File\n\n" +

                "# Keep in mind this will only prevent the mod of changing the settings every start. If the config is already set, it will remain the same.\n\n" +

                "disableAutoJump=true\n" +
                "disableMusic=true";
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
        });
    }
}