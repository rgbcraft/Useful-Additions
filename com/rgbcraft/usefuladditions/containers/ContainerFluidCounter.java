package com.rgbcraft.usefuladditions.containers;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;

import net.minecraft.inventory.ICrafting;


public class ContainerFluidCounter extends ContainerBase {

    private TileFluidCounter tileFluidCounter;
    private String lastLiquid = "None";
    private int lastCounter = 0;

    public ContainerFluidCounter(TileFluidCounter tileFluidCounter) {
        super(tileFluidCounter);

        this.tileFluidCounter = tileFluidCounter;
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);

        ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendAll", this.tileFluidCounter);
        data.writeInt(this.tileFluidCounter.getAmount());
        data.writeUTF(this.tileFluidCounter.getLiquidName());
        NetworkHandler.sendDataPacketToClient(data, crafter);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        int counter = this.tileFluidCounter.getAmount();
        String liquid = this.tileFluidCounter.getLiquidName();

        for (Object element : this.crafters) {
            ICrafting crafter = (ICrafting) element;
            if (this.lastCounter != counter) {
                ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendAmount", this.tileFluidCounter);
                data.writeInt(this.tileFluidCounter.getAmount());
                NetworkHandler.sendDataPacketToClient(data, crafter);
            }

            if (!this.lastLiquid.equals(liquid)) {
                ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendLiquidName", this.tileFluidCounter);
                data.writeUTF(this.tileFluidCounter.getLiquidName());
                NetworkHandler.sendDataPacketToClient(data, crafter);
            }
        }

        this.lastCounter = counter;
        this.lastLiquid = liquid;
    }

}
