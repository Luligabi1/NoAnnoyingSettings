package me.luligabi.noannoyingsettings.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

@Environment(EnvType.CLIENT)
public class NoAnnoyingSettingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> {
            MinecraftClient.getInstance().options.setSoundVolume(SoundCategory.MUSIC, 0);
        });
    }
}
