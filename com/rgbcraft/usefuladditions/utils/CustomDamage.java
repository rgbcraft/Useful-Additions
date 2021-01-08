package com.rgbcraft.usefuladditions.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class CustomDamage extends DamageSource {

	private String deathMessage;

	public CustomDamage(String deathMessage) {
		super("usefuladditionsDamage");
		
		this.deathMessage = deathMessage;
	}
	
	@Override
	public String getDeathMessage(EntityPlayer player) {
        return this.deathMessage;
    }

}
