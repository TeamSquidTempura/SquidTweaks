package com.squidtempura.www.squidtweaks.Tweaks.Debug;

import com.squidtempura.www.squidtweaks.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Test {

    private static boolean mode = Configs.Generic.DEBUG_MODE.getBooleanValue();

    public static void toggle() {
        mode = !mode;
        MinecraftClient.getInstance().player.sendMessage(Text.of("§l§bDebug mode enabled!"));
    }


}
