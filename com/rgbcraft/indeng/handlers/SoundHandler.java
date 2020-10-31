package com.rgbcraft.indeng.handlers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.utils.Sounds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

@SideOnly(Side.CLIENT)
public class SoundHandler {

    public SoundHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @ForgeSubscribe
    public void onSoundsLoad(SoundLoadEvent event) {
        for (Sounds sound : Sounds.values()) {
            addSound(event, sound);
        }
    }

    private void addSound(SoundLoadEvent event, Sounds sound) {
        event.manager.soundPoolSounds.addSound("indeng/" + sound.getName() + ".ogg", IndustrialEngineering.class.getResource("/com/rgbcraft/indeng/assets/sounds/" + sound.getName() + ".ogg"));
    }

}
