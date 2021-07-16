package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.handlers.GuiHandler.Guis;
import com.rgbcraft.usefuladditions.tiles.TileMEBridge;
import com.rgbcraft.usefuladditions.utils.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockMEBridge extends BlockMachineBase {

    protected BlockMEBridge(int id) {
        super(id, "meBridge");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ))
            Utils.openGui(entityPlayer, world, x, y, z, Guis.MEBridge);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileMEBridge) {
            TileMEBridge meBridge = (TileMEBridge) te;
            meBridge.unloadTile();
        }

        super.breakBlock(world, x, y, z, id, metadata);
    }

    @Override
    public int getBlockTextureFromSide(int side) {
        return 34;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileMEBridge();
    }

}
