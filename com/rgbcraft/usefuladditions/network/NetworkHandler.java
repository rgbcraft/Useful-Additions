package com.rgbcraft.usefuladditions.network;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.UsefulAdditions;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;


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

    public static ByteArrayDataOutput createBasePacket(String packetName, int x, int y, int z) {
        ByteArrayDataOutput data = ByteStreams.newDataOutput();
        data.writeUTF(packetName);
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        return data;
    }

    public static ByteArrayDataOutput createBasePacket(String packetName, TileEntity tileEntity) {
        ByteArrayDataOutput data = ByteStreams.newDataOutput();
        int x = tileEntity.xCoord;
        int y = tileEntity.yCoord;
        int z = tileEntity.zCoord;

        data.writeUTF(new ItemStack(tileEntity.worldObj.getBlockId(x, y, z), 1, tileEntity.worldObj.getBlockMetadata(x, y, z)).getItemName() + "." + packetName);
        data.writeInt(tileEntity.xCoord);
        data.writeInt(tileEntity.yCoord);
        data.writeInt(tileEntity.zCoord);
        return data;
    }

}
