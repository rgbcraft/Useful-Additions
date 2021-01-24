package com.rgbcraft.usefuladditions.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;


public abstract class TileMultiBlock extends TileEntity {

    private boolean hasMaster, isMaster;
    private int masterX, masterY, masterZ;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote)
            if (this.hasMaster()) {
                if (this.isMaster() && this.checkMultiBlockForm())
                    this.doMultiBlockStuff();
            } else // Constantly check if structure is formed until it is.
            if (this.checkMultiBlockForm())
                this.setupStructure();
    }

    /**
     * Stuff the multiblock will do when formed
     */
    public abstract void doMultiBlockStuff();

    /**
     * Check that structure is properly formed
     */
    public abstract boolean checkMultiBlockForm();

    /**
     * Setup all the blocks in the structure
     */
    public abstract void setupStructure();

    /**
     * Reset method to be run when the master is gone or tells them to
     */
    public void reset() {
        this.masterX = 0;
        this.masterY = 0;
        this.masterZ = 0;
        this.hasMaster = false;
        this.isMaster = false;
    }

    /**
     * Check that the master exists
     */
    public boolean checkForMaster() {
        TileEntity tile = this.worldObj.getBlockTileEntity(this.masterX, this.masterY, this.masterZ);
        return tile != null && tile instanceof TileMultiBlock;
    }

    /**
     * Reset all the parts of the structure
     */
    public abstract void resetStructure();

    public abstract void masterWriteToNBT(NBTTagCompound tag);

    public abstract void masterReadFromNBT(NBTTagCompound tag);

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("masterX", this.masterX);
        data.setInteger("masterY", this.masterY);
        data.setInteger("masterZ", this.masterZ);
        data.setBoolean("hasMaster", this.hasMaster);
        data.setBoolean("isMaster", this.isMaster);
        if (this.hasMaster() && this.isMaster())
            this.masterWriteToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.masterX = data.getInteger("masterX");
        this.masterY = data.getInteger("masterY");
        this.masterZ = data.getInteger("masterZ");
        this.hasMaster = data.getBoolean("hasMaster");
        this.isMaster = data.getBoolean("isMaster");
        if (this.hasMaster() && this.isMaster())
            this.masterReadFromNBT(data);
    }

    public boolean hasMaster() {
        return this.hasMaster;
    }

    public boolean isMaster() {
        return this.isMaster;
    }

    public int getMasterX() {
        return this.masterX;
    }

    public int getMasterY() {
        return this.masterY;
    }

    public int getMasterZ() {
        return this.masterZ;
    }

    public void setHasMaster(boolean bool) {
        this.hasMaster = bool;
    }

    public void setIsMaster(boolean bool) {
        this.isMaster = bool;
    }

    public void setMasterCoords(int x, int y, int z) {
        this.masterX = x;
        this.masterY = y;
        this.masterZ = z;
    }

}
