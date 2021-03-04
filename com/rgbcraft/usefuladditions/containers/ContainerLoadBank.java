package com.rgbcraft.usefuladditions.containers;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileLoadBank;

import net.minecraft.inventory.ICrafting;


public class ContainerLoadBank extends ContainerBase {

    private TileLoadBank tileLoadBank;
    private int lastMaxEnergy = -1;

    public ContainerLoadBank(TileLoadBank tileLoadBank) {
        super(tileLoadBank);

        this.tileLoadBank = tileLoadBank;
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);

        ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendMaxEnergy", this.tileLoadBank);
        data.writeInt(this.tileLoadBank.maxEnergy);
        NetworkHandler.sendDataPacketToClient(data, crafter);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        int maxEnergy = this.tileLoadBank.maxEnergy;

        for (Object element : this.crafters) {
            ICrafting crafter = (ICrafting) element;
            if (this.lastMaxEnergy == -1 || this.lastMaxEnergy != maxEnergy) {
                ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendMaxEnergy", this.tileLoadBank);
                data.writeInt(this.tileLoadBank.maxEnergy);
                NetworkHandler.sendDataPacketToClient(data, crafter);
            }
        }
        this.lastMaxEnergy = maxEnergy;
    }

}
