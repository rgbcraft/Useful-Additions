package com.rgbcraft.usefuladditions.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public interface IDebuggable {

    public Map<String, Boolean> getRequirements(EntityPlayer player, HashMap<String, Boolean> requirements);

    public Map<String, String> getAdditionalAdvancedInfos(EntityPlayer player, HashMap<String, String> additionalInfos);

}
