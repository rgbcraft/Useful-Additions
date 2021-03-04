package com.rgbcraft.usefuladditions.proxies;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.network.INetworkMember;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;


public class CommonProxy {

    public void onPacketReceived(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

            String packetName = data.readUTF();
            int x = data.readInt();
            int y = data.readInt();
            int z = data.readInt();

            TileEntity tileEntity = entityPlayerMP.worldObj.getBlockTileEntity(x, y, z);
            if (tileEntity != null && tileEntity instanceof INetworkMember)
                ((INetworkMember) tileEntity).onServerPacketReceived(packetName, data, (EntityPlayer) player);
            else
                UsefulAdditions.log.warning(String.format("Received a server packet ID for a non network member! (Packet ID: %d)", packetName));
        }
    }

    public void sendMessageToPlayer(EntityPlayer entityPlayer, String message) {
        if (entityPlayer instanceof EntityPlayerMP)
            ((EntityPlayerMP) entityPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(message));
        else
            entityPlayer.addChatMessage(message);
    }

    public void preloadTextures() {}

    public void applyLiquidFX() {}

    public void initSounds() {}

    public void initRenderers() {}

    public int getRenderId(String name) {
        return 0;
    }

}
