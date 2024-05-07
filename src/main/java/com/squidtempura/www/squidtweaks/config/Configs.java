package com.squidtempura.www.squidtweaks.config;


import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.squidtempura.www.squidtweaks.SquidTweaks;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigString;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;

public class Configs implements IConfigHandler {
    private static final String CONFIG_FILE_NAME  = SquidTweaks.MOD_ID + ".json";
    private static final int CONFIG_VERSION = 1;

    public static class Generic {

        public static final ConfigHotkey OPEN_CONFIG_GUI_KEYBIND = new ConfigHotkey("openConfigGUI", "TAB,C", "openConfigGUI");
        public static final ConfigHotkey DISABLE_CHUNK_RENDER_KEYBIND = new ConfigHotkey("disableChunkRender", "TAB,R", "disableChunkRender");
        public static final ConfigBoolean DEBUG_MODE = new ConfigBoolean("toggleDebugMode", false, "toggleDebugMode");
        public static final ConfigString DISABLE_CHUNK_RENDER_REGION = new ConfigString("disableChunkRenderRegion", "0,0-1,0", "disableChunkRenderRegion");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                OPEN_CONFIG_GUI_KEYBIND,
                DISABLE_CHUNK_RENDER_KEYBIND,
                DISABLE_CHUNK_RENDER_REGION,
                DEBUG_MODE
        );
    }

    public static void loadFromFile() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(root, "Generic", Configs.Generic.OPTIONS);
            }
        }
    }

    public static void saveToFile() {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();

            ConfigUtils.writeConfigBase(root, "Generic", Configs.Generic.OPTIONS);

            root.add("config_version", new JsonPrimitive(CONFIG_VERSION));

            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override
    public void load() {
        loadFromFile();
    }

    @Override
    public void save() {
        saveToFile();
    }
}
