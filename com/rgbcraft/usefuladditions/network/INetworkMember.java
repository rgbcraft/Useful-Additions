package com.rgbcraft.usefuladditions.network;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.player.EntityPlayer;

public interface INetworkMember {
	
	public void onClientPacketReceived(int packetId, ByteArrayDataInput data, EntityPlayer entityPlayer);
	
	public void onServerPacketReceived(int packetId, ByteArrayDataInput data, EntityPlayer entityPlayer);
	
}
