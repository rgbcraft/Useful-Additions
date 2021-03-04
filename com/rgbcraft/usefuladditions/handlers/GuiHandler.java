package com.rgbcraft.usefuladditions.handlers;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.containers.ContainerBase;
import com.rgbcraft.usefuladditions.containers.ContainerFluidCounter;
import com.rgbcraft.usefuladditions.containers.ContainerLoadBank;
import com.rgbcraft.usefuladditions.containers.ContainerOsmosisGenerator;
import com.rgbcraft.usefuladditions.containers.ContainerSmartSafeInventory;
import com.rgbcraft.usefuladditions.guis.GuiFluidCounter;
import com.rgbcraft.usefuladditions.guis.GuiLoadBank;
import com.rgbcraft.usefuladditions.guis.GuiOsmosisGenerator;
import com.rgbcraft.usefuladditions.guis.GuiSmartSafeInventory;
import com.rgbcraft.usefuladditions.guis.GuiSmartSafeLock;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;
import com.rgbcraft.usefuladditions.tiles.TileLoadBank;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class GuiHandler implements IGuiHandler {

    public GuiHandler() {
        NetworkRegistry.instance().registerGuiHandler(UsefulAdditions.instance, this);
    }

    @Override
    public Container getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        switch (id) {
            case 0:
                if (te != null && te instanceof TileSmartSafe)
                    return new ContainerBase(te);
            case 1:
                if (te != null && te instanceof TileSmartSafe)
                    return new ContainerSmartSafeInventory(player.inventory, (TileSmartSafe) te);
            case 2:
                if (te != null && te instanceof TileOsmosisGenerator)
                    return new ContainerOsmosisGenerator(player.inventory, (TileOsmosisGenerator) te);
            case 3:
                if (te != null && te instanceof TileFluidCounter)
                    return new ContainerFluidCounter((TileFluidCounter) te);
            case 4:
                if (te != null && te instanceof TileLoadBank)
                    return new ContainerLoadBank((TileLoadBank) te);
            default:
                return new ContainerBase(te);
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null)
            switch (id) {
                case 0:
                    if (te instanceof TileSmartSafe)
                        return new GuiSmartSafeLock(player.inventory, (TileSmartSafe) te, player);
                case 1:
                    if (te instanceof TileSmartSafe)
                        return new GuiSmartSafeInventory(player.inventory, (TileSmartSafe) te);
                case 2:
                    if (te instanceof TileOsmosisGenerator)
                        return new GuiOsmosisGenerator(player.inventory, (TileOsmosisGenerator) te);
                case 3:
                    if (te != null && te instanceof TileFluidCounter)
                        return new GuiFluidCounter(player.inventory, (TileFluidCounter) te);
                case 4:
                    if (te != null && te instanceof TileLoadBank)
                        return new GuiLoadBank(player.inventory, (TileLoadBank) te);
            }
        return null;
    }

}
