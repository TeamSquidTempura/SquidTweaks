package com.squidtempura.www.squidtweaks.Tweaks.Chunks;

public class ChunkLocationData {
    private int x;
    private int z;

    public ChunkLocationData(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
