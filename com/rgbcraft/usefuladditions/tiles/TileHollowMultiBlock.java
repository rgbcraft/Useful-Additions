package com.rgbcraft.usefuladditions.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;


public class TileHollowMultiBlock extends TileMultiBlock {

    public TileHollowMultiBlock() {}

    @Override
    public void doMultiBlockStuff() {
        // Sets diamond block 6 blocks above the master
        if (this.worldObj.isAirBlock(this.xCoord, this.yCoord + 6, this.zCoord))
            this.worldObj.setBlock(this.xCoord, this.yCoord + 6, this.zCoord, Block.blockDiamond.blockID);
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound tag) {}

    @Override
    public void masterReadFromNBT(NBTTagCompound tag) {}

    @Override
    public boolean checkMultiBlockForm() {
        int i = 0;
        // Scan a 3x3x3 area, starting with the bottom left corner
        for (int x = this.xCoord - 1; x < this.xCoord + 2; x++)
            for (int y = this.yCoord; y < this.yCoord + 3; y++)
                for (int z = this.zCoord - 1; z < this.zCoord + 2; z++) {
                    TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);
                    // Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a
                    // multiblock (if ours is already part of one)
                    if (tile != null && tile instanceof TileHollowMultiBlock)
                        if (this.isMaster()) {
                            if (((TileHollowMultiBlock) tile).hasMaster())
                                i++;
                        } else if (!((TileHollowMultiBlock) tile).hasMaster())
                            i++;
                }
        // check if there are 26 blocks present ((3*3*3) - 1) and check that center block is empty
        return i > 25 && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord);
    }

    @Override
    public void setupStructure() {
        for (int x = this.xCoord - 1; x < this.xCoord + 2; x++)
            for (int y = this.yCoord; y < this.yCoord + 3; y++)
                for (int z = this.zCoord - 1; z < this.zCoord + 2; z++) {
                    TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);
                    // Check if block is bottom center block
                    boolean master = x == this.xCoord && y == this.yCoord && z == this.zCoord;
                    if (tile != null && tile instanceof TileHollowMultiBlock) {
                        ((TileHollowMultiBlock) tile).setMasterCoords(this.xCoord, this.yCoord, this.zCoord);
                        ((TileHollowMultiBlock) tile).setHasMaster(true);
                        ((TileHollowMultiBlock) tile).setIsMaster(master);
                    }
                }
    }

    @Override
    public void resetStructure() {
        for (int x = this.xCoord - 1; x < this.xCoord + 2; x++)
            for (int y = this.yCoord; y < this.yCoord + 3; y++)
                for (int z = this.zCoord - 1; z < this.zCoord + 2; z++) {
                    TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);
                    if (tile != null && tile instanceof TileMultiBlock)
                        ((TileMultiBlock) tile).reset();
                }
    }

}
