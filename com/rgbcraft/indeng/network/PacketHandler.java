package com.rgbcraft.indeng.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.indeng.containers.ContainerSmartSafeLock;
import com.rgbcraft.indeng.tiles.TileSmartSafe;

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
        byte packetId = reader.readByte();

        switch (packetId) {
            case 1:
                byte buttonId = reader.readByte();
                Container container = entityPlayer.openContainer;
                if (container != null && container instanceof ContainerSmartSafeLock) {
                    ((ContainerSmartSafeLock) container).getTileSmartSafe().onButtonClick(buttonId, entityPlayer);
                }
                
                break;
        }
    }

    public static void sendButtonPacket(byte id) {
    	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);
        
        try {
            dataStream.writeByte((byte) 1);
            dataStream.writeByte(id);
            
            PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("indeng", byteStream.toByteArray()));
        } catch (IOException exception) {
            System.err.append("Failed to send button click packet.");
        }
    }

}
