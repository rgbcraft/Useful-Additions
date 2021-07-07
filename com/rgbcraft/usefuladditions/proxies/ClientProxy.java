package com.rgbcraft.usefuladditions.proxies;

import java.util.HashMap;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.SoundHandler;
import com.rgbcraft.usefuladditions.liquids.Liquids;
import com.rgbcraft.usefuladditions.network.INetworkMember;
import com.rgbcraft.usefuladditions.renderers.RenderSmartSafe;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.Player;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;


public class ClientProxy extends CommonProxy {

    private static HashMap<String, Integer> renderIds = new HashMap<String, Integer>();

    @Override
    public void initSounds() {
        new SoundHandler();
    }

    @Override
    public void preloadTextures() {
        MinecraftForgeClient.preloadTexture(Utils.getResource(ResourceType.TEXTURE, "blocks.png"));
        MinecraftForgeClient.preloadTexture(Utils.getResource(ResourceType.TEXTURE, "items.png"));
        MinecraftForgeClient.preloadTexture(Utils.getResource(ResourceType.TEXTURE, "liquids.png"));
        MinecraftForgeClient.preloadTexture(Utils.getResource(ResourceType.TEXTURE, "liquidsfx.png"));

        MinecraftForgeClient.preloadTexture(Utils.getResource(ResourceType.MODEL, "ModelSmartSafe.png"));
    }

    @Override
    public void applyLiquidFX() {
        // Salt Water
        Liquids.applyLiquidFx(50, 125, 255, 70, 185, 252, 0);

        // Town Gas
        // Liquids.applyLiquidFx(5, 165, 252, 5, 226, 252, 1);
        //
        // // GPL
        // Liquids.applyLiquidFx(5, 165, 252, 5, 227, 252, 2);
        //
        // // CO
        // Liquids.applyLiquidFx(5, 165, 252, 5, 229, 252, 3);
        //
        // // Gasoline
        // Liquids.applyLiquidFx(5, 165, 252, 5, 222, 252, 4);
        //
        // // Kerosene
        // Liquids.applyLiquidFx(5, 165, 252, 5, 221, 252, 5);
        //
        // // HFO
        // Liquids.applyLiquidFx(5, 165, 252, 5, 213, 252, 6);
        //
        // // Bunker C
        // Liquids.applyLiquidFx(5, 165, 252, 5, 253, 252, 7);
        //
        // // Heated BunkerC
        // Liquids.applyLiquidFx(5, 165, 252, 5, 223, 252, 8);
        //
        // // Crude residue
        // Liquids.applyLiquidFx(5, 165, 252, 5, 123, 252, 9);
        //
        // // Asphalt
        // Liquids.applyLiquidFx(5, 165, 252, 5, 223, 252, 10);
        //
        // // Paraffin
        // Liquids.applyLiquidFx(5, 165, 252, 5, 213, 252, 11);
        //
        // // Lubricant
        // Liquids.applyLiquidFx(5, 165, 252, 5, 125, 252, 12);
    }

    @Override
    public void onPacketReceived(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        super.onPacketReceived(manager, packet, player);

        if (!(player instanceof EntityPlayerMP)) {
            World world = FMLClientHandler.instance().getClient().theWorld;
            ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

            String packetName = data.readUTF();
            int x = data.readInt();
            int y = data.readInt();
            int z = data.readInt();

            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity != null && tileEntity instanceof INetworkMember)
                ((INetworkMember) tileEntity).onClientPacketReceived(packetName, data, (EntityPlayer) player);
            else
                UsefulAdditions.log.warning(String.format("Received a client packet for a non network member! (Packet Name: %s)", packetName));
        }
    }

    private void registerRenderer(String name, Class tileEntity, TileEntitySpecialRenderer renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, renderer);
        if (renderer instanceof ISimpleBlockRenderingHandler) {
            ISimpleBlockRenderingHandler handler = (ISimpleBlockRenderingHandler) renderer;
            renderIds.put(name, handler.getRenderId());
            RenderingRegistry.registerBlockHandler(handler);
        }
    }

    @Override
    public void initRenderers() {
        this.registerRenderer("smartSafe", TileSmartSafe.class, new RenderSmartSafe());
    }

    @Override
    public int getRenderId(String name) {
        return renderIds.get(name);
    }

}
