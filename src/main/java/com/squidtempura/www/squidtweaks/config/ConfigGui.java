package com.squidtempura.www.squidtweaks.config;

import com.squidtempura.www.squidtweaks.SquidTweaks;
import fi.dy.masa.malilib.gui.GuiConfigsBase;

import java.util.List;


public class ConfigGui extends GuiConfigsBase {

    public ConfigGui() {
        super(10, 50, SquidTweaks.MOD_ID, null, "SquidTweaks");
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        return List.of();
    }
}
