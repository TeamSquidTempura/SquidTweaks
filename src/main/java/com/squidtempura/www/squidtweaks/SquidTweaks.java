package com.squidtempura.www.squidtweaks;

import com.squidtempura.www.squidtweaks.Tweaks.Chunks.ChunkLoad;
import com.squidtempura.www.squidtweaks.config.Configs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fi.dy.masa.malilib.event.InitializationHandler;

public class SquidTweaks implements ModInitializer {

    public static FabricLoader loader;
    public static final Logger LOGGER = LoggerFactory.getLogger("SquidTweaks");
    public static final String MOD_ID = "SquidTweaks";
    @Override
    public void onInitialize() {
        loader = FabricLoader.getInstance();
        ClientChunkEvents.CHUNK_LOAD.register(new ChunkLoad());
        InitializationHandler.getInstance().registerInitializationHandler(new com.squidtempura.www.squidtweaks.InitializationHandler());
        LOGGER.info("config: {}", Configs.Generic.OPEN_CONFIG_GUI_KEYBIND.getStringValue());
        LOGGER.info("disabler: {}", Configs.Generic.DISABLE_CHUNK_RENDER_KEYBIND.getStringValue());
    }
}