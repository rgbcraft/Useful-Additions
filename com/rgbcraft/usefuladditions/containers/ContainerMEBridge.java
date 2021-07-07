package com.rgbcraft.usefuladditions.containers;

import com.rgbcraft.usefuladditions.tiles.TileMEBridge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;


public class ContainerMEBridge extends ContainerBase {

    private TileMEBridge tileMEBridge;

    public ContainerMEBridge(InventoryPlayer inventoryPlayer, TileMEBridge tileMEBridge) {
        super(tileMEBridge);

        this.tileMEBridge = tileMEBridge;

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                this.addSlotToContainer(new Slot(tileMEBridge, y + x * 3, 62 + y * 18, 17 + x * 18));

        this.bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileMEBridge.isUseableByPlayer(entityPlayer);
    }

}
