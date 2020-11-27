package com.rgbcraft.indeng.utils;

import net.minecraft.client.Minecraft;

public enum Sounds {

	CodeBreaker("codebreaker"),
	SmartSafeOpen("safe_open"),
	SmartSafeClose("safe_close");
	
    private String name;

    Sounds(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void play(double x, double y, double z, float volume, float pitch) {
        Minecraft.getMinecraft().sndManager.playSound("indeng." + name, (float) x, (float) y, (float) z, volume, pitch);
    }

}
