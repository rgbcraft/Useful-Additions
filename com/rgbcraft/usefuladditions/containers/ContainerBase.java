package com.rgbcraft.usefuladditions.containers;

import com.rgbcraft.usefuladditions.tiles.TileBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class ContainerBase extends Container {
	
	private TileBase tileEntity;

	public ContainerBase(TileBase tileEntity) {
		this.tileEntity = tileEntity;
	}
	
	public TileBase getTileEntity() {
		return this.tileEntity;
	}
	
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < crafters.size(); i++) {
            this.tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
        }
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int key, int value) {
        this.tileEntity.getGUINetworkData(key, value);
    }

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return entityPlayer.getDistanceSq(this.tileEntity.xCoord + 0.5, this.tileEntity.yCoord + 0.5, this.tileEntity.zCoord + 0.5) <= 64;
	}
}
