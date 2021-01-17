package com.rgbcraft.usefuladditions.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.UsefulAdditions;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class NetworkHandler implements IPacketHandler {
	
	public static final String NETWORK_CHANNEL = "usefuladditions";

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        UsefulAdditions.proxy.onPacketReceived(manager, packet, player);
    }
    
	public static void sendDataPacketToServer(ByteArrayDataOutput data) {
		try {
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(NETWORK_CHANNEL, data.toByteArray()));
		} catch (Exception exception) {
			UsefulAdditions.log.warning("Failed to send a packet to the server!");
		}
	}
	
	public static void sendDataPacketToClient(ByteArrayDataOutput data, ICrafting crafter) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = NETWORK_CHANNEL;
		packet.isChunkDataPacket = false;
		packet.data = data.toByteArray();
		packet.length = packet.data.length;
		
		try {
			((EntityPlayerMP) crafter).playerNetServerHandler.sendPacketToPlayer(packet);
		} catch (Exception exception) {
			UsefulAdditions.log.warning("Failed to send a packet to the client!");
		}
	}
	
	public static ByteArrayDataOutput createBasePacket(int packetId, int x, int y, int z) {
    	ByteArrayDataOutput data = ByteStreams.newDataOutput();
    	data.writeShort(packetId);
    	data.writeInt(x);
    	data.writeInt(y);
    	data.writeInt(z);
    	return data;
	}

}
