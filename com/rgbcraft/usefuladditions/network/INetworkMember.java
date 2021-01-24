package com.rgbcraft.usefuladditions.network;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.player.EntityPlayer;


public interface INetworkMember {

    void onClientPacketReceived(int packetId, ByteArrayDataInput data, EntityPlayer entityPlayer);

    void onServerPacketReceived(int packetId, ByteArrayDataInput data, EntityPlayer entityPlayer);

}
