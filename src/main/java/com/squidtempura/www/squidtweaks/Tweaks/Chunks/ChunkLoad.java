package com.squidtempura.www.squidtweaks.Tweaks.Chunks;

import com.squidtempura.www.squidtweaks.SquidTweaks;
import com.squidtempura.www.squidtweaks.config.Configs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoad implements net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents.Load {

    public static boolean ChunkDisabler;
    private static final List<ChunkLocationData> loc = new ArrayList<>();

    public static void init() {
        loc.clear();
        String[] data = Configs.Generic.DISABLE_CHUNK_RENDER_REGION.getStringValue().split("-");
        for (String datum : data) {
            String[] xz = datum.split(",");
            loc.add(new ChunkLocationData(Integer.parseInt(xz[0]), Integer.parseInt(xz[1])));
        }
    }

    @Override
    public void onChunkLoad(ClientWorld world, WorldChunk chunk) {
        if (ChunkDisabler) {
            ClientChunkManager chunkManager = world.getChunkManager();
            ChunkPos pos = chunk.getPos();
            int x = pos.x;
            int z = pos.z;
            for (ChunkLocationData data : loc) {
                if (data.getX() == x && data.getZ() == z) {
                    chunkManager.unload(x, z);
                    MinecraftClient.getInstance().player.sendMessage(Text.of("Chunk of x:" + x + " z: " + z + " is unloaded"));
                    SquidTweaks.LOGGER.info("Chunk of x:{} z: {} is unloaded", x, z);
                }
            }
        }
    }

    public static void toggle() {
        ChunkDisabler = !ChunkDisabler;
        MinecraftClient.getInstance().player.sendMessage(Text.of("§aToggled Chunk Disabler"));
    }
}
