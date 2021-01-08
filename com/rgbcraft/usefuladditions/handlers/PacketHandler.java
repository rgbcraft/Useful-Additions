package com.rgbcraft.usefuladditions.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.containers.ContainerBase;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
        EntityPlayer entityPlayer = (EntityPlayer) player;
        Container container = entityPlayer.openContainer;
        byte packetId = reader.readByte();

        switch (packetId) {
            case 1:
                byte id = reader.readByte();
                String value = reader.readUTF();

                if (container != null && container instanceof ContainerBase) {
                    ((ContainerBase) container).getTileEntity().onDataReceived(id, value, entityPlayer);
                }
                
                break;
            case 2:
                int buttonId = reader.readByte();
                
                if (container != null && container instanceof ContainerBase) {
                    ((ContainerBase) container).getTileEntity().onButtonClick(buttonId);
                }
                
                break;
        }
    }

    public static void sendButtonPacket(byte id) {
    	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);
        
        try {
            dataStream.writeByte((byte) 2);
            dataStream.writeByte(id);
            
            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("usefuladditions", byteStream.toByteArray()));
        } catch (IOException exception) {
            System.err.append("Failed to send button click packet.");
        }
    }
    
	public static void sendSafeData(int id, String value) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte((byte) 1);
			dataStream.writeByte(id);
			dataStream.writeUTF(value);
			
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("usefuladditions", byteStream.toByteArray()));
		} catch (IOException exception) {
			System.err.append("Failed to send safe data packet.");
		}
	}

}
