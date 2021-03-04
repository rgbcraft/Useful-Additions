package com.rgbcraft.usefuladditions.tiles;

import com.google.common.io.ByteArrayDataInput;
import com.rgbcraft.usefuladditions.network.INetworkMember;
import com.rgbcraft.usefuladditions.utils.Utils;

import ic2.api.Direction;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;


public class TileLoadBank extends TileEntity implements IEnergySink, INetworkMember {

    private boolean isAdded = false;
    public int maxEnergy = 0;
    public int energy = 0;

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            if (!this.isAdded) {
                MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
                this.isAdded = true;
            }

            if (this.energy >= this.maxEnergy)
                this.energy -= this.maxEnergy;

            byte[] data = Utils.unmergeBits((short) this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
            if (this.maxEnergy > 0)
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, Utils.mergeBits((byte) 1, data[1]));
            else
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, Utils.mergeBits((byte) 0, data[1]));

            if (this.maxEnergy >= 32768 && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
                this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord + 1, this.zCoord, Block.fire.blockID);
        }
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction) {
        return true;
    }

    @Override
    public boolean isAddedToEnergyNet() {
        return this.isAdded;
    }

    @Override
    public int demandsEnergy() {
        return this.maxEnergy - this.energy;
    }

    @Override
    public int injectEnergy(Direction directionFrom, int amount) {
        this.energy += amount;
        int re = 0;
        if (this.energy > this.maxEnergy) {
            re = this.energy - this.maxEnergy;
            this.energy = this.maxEnergy;
        }
        return re;
    }

    @Override
    public int getMaxSafeInput() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onClientPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer) {
        if (packetName.equals("tile.loadBank.sendMaxEnergy"))
            this.maxEnergy = data.readInt();
    }

    @Override
    public void onServerPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer) {
        if (packetName.equals("tile.loadBank.sendAmount"))
            this.maxEnergy = data.readInt();
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("MaxEnergy", this.maxEnergy);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.maxEnergy = compound.getInteger("MaxEnergy");
    }

}
