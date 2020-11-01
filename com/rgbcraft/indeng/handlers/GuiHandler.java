package com.rgbcraft.indeng.handlers;

import com.rgbcraft.indeng.IndustrialEngineering;

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
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        switch (ID) {
            case 0:     
//                if (te != null && te instanceof TileEntityMachine) {
//                    // return new ContainerMachine(player.inventory, (TileEntityMachine) te);
//                }
                
                break;
        }
        return null;
    }   

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        switch (ID) {
            case 0:
//                if (te != null && te instanceof TileEntityMachine) {
//                    // return new GuiMachine(player.inventory, (TileEntityMachine) te);
//                }
                
                break;
        }
        return null;
    }
    
}
