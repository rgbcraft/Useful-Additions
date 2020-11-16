package com.rgbcraft.indeng.handlers;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.containers.ContainerSmartSafe;
import com.rgbcraft.indeng.guis.GuiSmartSafe;
import com.rgbcraft.indeng.tiles.TileSmartSafe;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    
    public GuiHandler() {
        NetworkRegistry.instance().registerGuiHandler(IndustrialEngineering.instance, this);
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        switch (id) {
            case 0:     
                if (te != null && te instanceof TileSmartSafe) {
                     return new ContainerSmartSafe(player.inventory, (TileSmartSafe) te);
                }
                break;
        }
        return null;
    }   

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        switch (id) {
            case 0:
                if (te != null && te instanceof TileSmartSafe) {
                     return new GuiSmartSafe(player.inventory, (TileSmartSafe) te, player);
                }
                break;
        }
        return null;
    }
    
}
