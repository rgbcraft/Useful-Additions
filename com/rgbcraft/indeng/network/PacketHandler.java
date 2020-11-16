package com.rgbcraft.indeng.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
        EntityPlayer entityPlayer = (EntityPlayer) player;
        byte packetId = reader.readByte();

//        switch (packetId) {
//            case 1:
//                byte buttonId = reader.readByte();
//                Container container = entityPlayer.openContainer;
//                if (container != null && container instanceof ContainerSmartSafe) {
//                    TileSmartSafe tileSmartSafe = ((ContainerSmartSafe) container).getTileSmartSafe();
//                    tileSmartSafe.onButtonClick(buttonId);
//                }
//                
//                break;
//        }
    }

//     public static void sendButtonPacket(byte id) {
//         ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//         DataOutputStream dataStream = new DataOutputStream(byteStream);
//        
//         try {
//             dataStream.writeByte((byte) 1);
//             dataStream.writeByte(id);
//            
//             PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("IndustrialEngineering", byteStream.toByteArray()));
//         } catch (IOException exception) {
//             System.err.append("Failed to send button click packet.");
//         }
//     }

}
