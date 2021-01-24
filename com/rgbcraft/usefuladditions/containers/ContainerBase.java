package com.rgbcraft.usefuladditions.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;


public class ContainerBase extends Container {

    public TileEntity tileEntity;

    public ContainerBase(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return entityPlayer.getDistanceSq(this.tileEntity.xCoord + 0.5, this.tileEntity.yCoord + 0.5, this.tileEntity.zCoord + 0.5) <= 64;
    }

}
