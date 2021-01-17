package com.rgbcraft.usefuladditions.containers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ICrafting;

public class ContainerFluidCounter extends ContainerBase {

	private TileFluidCounter tileFluidCounter;
	private int lastCounter = 0;
	private String lastLiquid = "None";

	public ContainerFluidCounter(TileFluidCounter tileFluidCounter) {
		super(tileFluidCounter);
		
		this.tileFluidCounter = tileFluidCounter;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
    	
        ByteArrayDataOutput data = NetworkHandler.createBasePacket(20, this.tileFluidCounter.xCoord, this.tileFluidCounter.yCoord, this.tileFluidCounter.zCoord);
    	data.writeInt(this.tileFluidCounter.getAmount());
    	data.writeUTF(this.tileFluidCounter.getLiquidName());
    	NetworkHandler.sendDataPacketToClient(data, crafter);
    }
    
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        int counter = this.tileFluidCounter.getAmount();
        String liquid = this.tileFluidCounter.getLiquidName();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting crafter = (ICrafting) this.crafters.get(i);
            if (this.lastCounter != counter) {
            	ByteArrayDataOutput data = NetworkHandler.createBasePacket(20, this.tileFluidCounter.xCoord, this.tileFluidCounter.yCoord, this.tileFluidCounter.zCoord);
            	data.writeInt(this.tileFluidCounter.getAmount());
            	NetworkHandler.sendDataPacketToClient(data, crafter);
            }
            
            if (!this.lastLiquid.equals(liquid)) {
            	ByteArrayDataOutput data = NetworkHandler.createBasePacket(21, this.tileFluidCounter.xCoord, this.tileFluidCounter.yCoord, this.tileFluidCounter.zCoord);
            	data.writeUTF(this.tileFluidCounter.getLiquidName());
            	NetworkHandler.sendDataPacketToClient(data, crafter);
            }
        }

        this.lastCounter = counter;
        this.lastLiquid = liquid;
    }
}
