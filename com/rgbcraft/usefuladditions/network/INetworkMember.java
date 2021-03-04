package com.rgbcraft.usefuladditions.network;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.player.EntityPlayer;


public interface INetworkMember {

    void onClientPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer);

    void onServerPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer);

}
