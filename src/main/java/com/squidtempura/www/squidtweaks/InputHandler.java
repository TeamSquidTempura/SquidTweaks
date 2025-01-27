package com.squidtempura.www.squidtweaks;

import com.squidtempura.www.squidtweaks.config.Configs;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import fi.dy.masa.malilib.hotkeys.IKeyboardInputHandler;

import java.util.ArrayList;
import java.util.List;

public class InputHandler implements IKeybindProvider, IKeyboardInputHandler {
    private static final InputHandler INSTANCE = new InputHandler();

    private InputHandler() {
        super();
    }

    public static InputHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (IConfigBase config : Configs.Generic.OPTIONS) {
            if(
                    config instanceof ConfigBooleanHotkeyed ||
                            config instanceof ConfigHotkey
            ) {
                manager.addKeybindToMap(((IHotkey) config).getKeybind());
            }
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        List<IHotkey> hotkeys = new ArrayList<>();

        for (IConfigBase config : Configs.Generic.OPTIONS) {
            if(
                    config instanceof ConfigBooleanHotkeyed ||
                            config instanceof ConfigHotkey
            ) {
                hotkeys.add((IHotkey) config);
            }
        }
        manager.addHotkeysForCategory(SquidTweaks.MOD_ID, "SquidTweaks.hotkeys.category.hotkeys", hotkeys);
    }
}
