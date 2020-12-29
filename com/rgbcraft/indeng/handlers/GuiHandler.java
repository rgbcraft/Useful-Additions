package com.rgbcraft.indeng.handlers;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.containers.ContainerSmartSafeInventory;
import com.rgbcraft.indeng.containers.ContainerSmartSafeLock;
import com.rgbcraft.indeng.guis.GuiSmartSafeInventory;
import com.rgbcraft.indeng.guis.GuiSmartSafeLock;
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
                     return new ContainerSmartSafeLock(player.inventory, ((TileSmartSafe) te));
                }
                break;
            case 1:
                if (te != null && te instanceof TileSmartSafe) {
                     return new ContainerSmartSafeInventory(player.inventory, ((TileSmartSafe) te));
                }
                break;
        }
        return null;
    }   

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        System.out.println(te.toString());
        System.out.println(te.xCoord);
        System.out.println(te.yCoord);
        System.out.println(te.zCoord);
        switch (id) {
            case 0:
                if (te != null && te instanceof TileSmartSafe) {
                     return new GuiSmartSafeLock(player.inventory, ((TileSmartSafe) te), player);
                }
                break;
            case 1:
                if (te != null && te instanceof TileSmartSafe) {
                     return new GuiSmartSafeInventory(player.inventory, ((TileSmartSafe) te));
                }
                break;
        }
        return null;
    }
    
}
