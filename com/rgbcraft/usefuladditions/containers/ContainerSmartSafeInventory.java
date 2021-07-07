package com.rgbcraft.usefuladditions.containers;

import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;


public class ContainerSmartSafeInventory extends ContainerBase {

    private TileSmartSafe tileSmartSafe;

    public ContainerSmartSafeInventory(InventoryPlayer playerInventory, TileSmartSafe tileSmartSafe) {
        super(tileSmartSafe);

        this.tileSmartSafe = tileSmartSafe;
        this.tileSmartSafe.openChest();

        int numRows = this.tileSmartSafe.getSizeInventory() / 9;
        int var3 = (numRows - 4) * 18;
        for (int x = 0; x < numRows; ++x)
            for (int y = 0; y < 9; ++y)
                this.addSlotToContainer(new Slot(tileSmartSafe, y + x * 9, 8 + y * 18, 18 + x * 18));

        for (int x = 0; x < 3; ++x)
            for (int y = 0; y < 9; ++y)
                this.addSlotToContainer(new Slot(playerInventory, y + x * 9 + 9, 8 + y * 18, 104 + x * 18 + var3));

        for (int x = 0; x < 9; ++x)
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 162 + var3));
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileSmartSafe.isUseableByPlayer(entityPlayer);
    }

    @Override
    public void onCraftGuiClosed(final EntityPlayer entityplayer) {
        this.tileSmartSafe.closeChest();
    }

}
