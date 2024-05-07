package com.squidtempura.www.squidtweaks.config;

import com.squidtempura.www.squidtweaks.Tweaks.Chunks.ChunkLoad;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.minecraft.client.MinecraftClient;

public class ConfigCallbacks {
    public static void init() {
        HotkeyCallbacks hotkeyCallback = new HotkeyCallbacks();

        for (IConfigBase config : Configs.Generic.OPTIONS) {
            if(
                    config instanceof ConfigBooleanHotkeyed ||
                            config instanceof ConfigHotkey
            ) {
                ((IHotkey) config).getKeybind().setCallback(hotkeyCallback);
            }
        }
    }

    public static class HotkeyCallbacks implements IHotkeyCallback {

        @Override
        public boolean onKeyAction(KeyAction keyAction, IKeybind iKeybind) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null) {
                return false;
            } else if (iKeybind == Configs.Generic.OPEN_CONFIG_GUI_KEYBIND) {
                GuiBase.openGui(new ConfigGui());
                return true;
            } else if (iKeybind == Configs.Generic.DISABLE_CHUNK_RENDER_KEYBIND) {
                ChunkLoad.toggle();
            }

            return false;
        }
    }
}
