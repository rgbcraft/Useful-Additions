package com.rgbcraft.usefuladditions.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgbcraft.usefuladditions.api.IDebuggable;
import com.rgbcraft.usefuladditions.utils.LanguageManager;

import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.IGridInterface;
import appeng.api.me.util.IMEInventoryHandler;
import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IPeripheral;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;


public class TileMEBridge extends TileInventory implements IGridMachine, IPeripheral, IDebuggable {

    private IGridInterface grid = null;
    private boolean isPowered = false;
    private int gridIndex = 0;
    private float powerDrain = 0.1f;
    private boolean isLoaded = false;

    private List<ItemStack> itemsQueue = new ArrayList<ItemStack>();

    public TileMEBridge() {
        super("container.meBridge", 9);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        this.processItemsQueue();

        if (!this.isLoaded) {
            this.isLoaded = true;

            if (this instanceof IGridTileEntity)
                MinecraftForge.EVENT_BUS.post(new GridTileLoadEvent(this.worldObj, this.getLocation()));
        }
    }

    public void processItemsQueue() {
        for (int i = 0; i < this.itemsQueue.size(); i++) {
            ItemStack itemStack = this.itemsQueue.get(i);
            if (this.canAddStack(itemStack)) {
                ItemStack leftItems = this.addToInventory(itemStack);
                this.itemsQueue.remove(i);

                if (leftItems != null)
                    this.itemsQueue.add(leftItems);
            }
        }
    }

    public void unloadTile() {
        if (this.isLoaded) {
            this.isLoaded = false;

            if (this instanceof IGridTileEntity)
                MinecraftForge.EVENT_BUS.post(new GridTileUnloadEvent(this.worldObj, this.getLocation()));
        }
    }

    @Override
    public WorldCoord getLocation() {
        return new WorldCoord(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void setPowerStatus(boolean powerStatus) {
        this.isPowered = powerStatus;
    }

    @Override
    public boolean isPowered() {
        return this.isPowered;
    }

    @Override
    public IGridInterface getGrid() {
        return this.grid instanceof IGridInterface ? this.grid.isValid() ? this.grid : null : null;
    }

    @Override
    public void setGrid(IGridInterface gridInterface) {
        this.grid = gridInterface;
        if (gridInterface != null)
            this.gridIndex = gridInterface.getGridIndex();
        else
            this.gridIndex = 0;

        if (gridInterface != null)
            gridInterface.requestUpdate(this);

        super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, super.worldObj.getBlockId(super.xCoord, super.yCoord, super.zCoord));
    }

    @Override
    public float getPowerDrainPerTick() {
        return this.powerDrain;
    }

    @Override
    public String getType() {
        return "meBridge";
    }

    @Override
    public String[] getMethodNames() {
        return new String[] {"listAll", "listStored", "listCraftable", "get", "craft"};
    }

    public ItemStack parseBlockArgument(String idAndMetadata, double quantity) throws Exception {
        String[] blockData = idAndMetadata.split(":");
        ItemStack itemStack = new ItemStack(Integer.parseInt(blockData[0]), new Double(quantity).intValue(), Integer.parseInt(blockData[1]));
        return itemStack;
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, int method, Object[] arguments) throws Exception {
        if (this.grid != null) {
            Map<Integer, Map<String, String>> serializedItems;
            int index;
            switch (method) {
                case 0:
                    List<ItemStack> allItems = this.grid.getFullCellArray().getAvailableItems(new ArrayList<ItemStack>());
                    serializedItems = new HashMap<Integer, Map<String, String>>();
                    index = 0;
                    for (ItemStack item : allItems) {
                        Map<String, String> itemData = new HashMap<String, String>();
                        itemData.put("displayName", item.getDisplayName());
                        itemData.put("amount", Integer.toString(item.stackSize));
                        itemData.put("id", Integer.toString(item.itemID));
                        itemData.put("metadata", Integer.toString(item.getItemDamage()));

                        serializedItems.put(index, itemData);
                        index++;
                    }
                    return new Object[] {serializedItems};
                case 1:
                    List<ItemStack> availableItems = this.grid.getCellArray().getAvailableItems(new ArrayList<ItemStack>());
                    serializedItems = new HashMap<Integer, Map<String, String>>();
                    index = 0;
                    for (ItemStack item : availableItems) {
                        Map<String, String> itemData = new HashMap<String, String>();
                        itemData.put("displayName", item.getDisplayName());
                        itemData.put("amount", Integer.toString(item.stackSize));
                        itemData.put("id", Integer.toString(item.itemID));
                        itemData.put("metadata", Integer.toString(item.getItemDamage()));

                        serializedItems.put(index, itemData);
                        index++;
                    }
                    return new Object[] {serializedItems};
                case 2:
                    List<ItemStack> craftableItems = this.grid.getCraftableArray().getAvailableItems(new ArrayList<ItemStack>());
                    serializedItems = new HashMap<Integer, Map<String, String>>();
                    index = 0;
                    for (ItemStack item : craftableItems) {
                        Map<String, String> itemData = new HashMap<String, String>();
                        itemData.put("displayName", item.getDisplayName());
                        itemData.put("id", Integer.toString(item.itemID));
                        itemData.put("metadata", Integer.toString(item.getItemDamage()));

                        serializedItems.put(index, itemData);
                        index++;
                    }
                    return new Object[] {serializedItems};
                case 3:
                    ItemStack toExtract = this.parseBlockArgument((String) arguments[0], (double) arguments[1]);
                    IMEInventoryHandler storedItems = this.grid.getCellArray();

                    if (arguments.length > 2)
                        if (!((boolean) arguments[2]) && storedItems.countOfItemType(toExtract) < toExtract.stackSize)
                            throw new Exception("Not enough items to complete the request.");

                    ItemStack extractedItem;
                    int totalExtracted = 0;
                    if (toExtract.stackSize > toExtract.getMaxStackSize()) {
                        for (int i = 1; i <= toExtract.stackSize / toExtract.getMaxStackSize(); i++) {
                            ItemStack fakeStack = toExtract.copy();
                            fakeStack.stackSize = toExtract.getMaxStackSize();

                            if (fakeStack != null) {
                                ItemStack fakeExtracted = storedItems.extractItems(fakeStack);
                                totalExtracted += fakeExtracted.stackSize;
                                this.itemsQueue.add(fakeExtracted);
                            }
                        }

                        ItemStack fakeStack = toExtract.copy();
                        fakeStack.stackSize = toExtract.stackSize % toExtract.getMaxStackSize();

                        if (fakeStack != null) {
                            ItemStack fakeExtracted = storedItems.extractItems(fakeStack);
                            totalExtracted += fakeExtracted.stackSize;
                            this.itemsQueue.add(fakeExtracted);
                        }

                        extractedItem = toExtract;
                    } else {
                        extractedItem = storedItems.extractItems(toExtract);
                        if (extractedItem != null) {
                            this.itemsQueue.add(extractedItem);
                            totalExtracted += extractedItem.stackSize;
                        }
                    }
                    
                    if (extractedItem == null)
                        throw new Exception("Cannot request that item.");
                    
                    return new Object[] {totalExtracted};
                case 4:
                    this.grid.craftingRequest(this.parseBlockArgument((String) arguments[0], (double) arguments[1]), true);
                    return new Object[] {};
            }
        } else
            throw new Exception("Bridge not connected.");

        return null;
    }

    @Override
    public boolean canAttachToSide(int side) {
        return true;
    }

    @Override
    public void attach(IComputerAccess computer) {}

    @Override
    public void detach(IComputerAccess computer) {}

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (this.itemsQueue.size() > 0) {
            NBTTagList queueItems = new NBTTagList();

            for (ItemStack itemStack : this.itemsQueue)
                if (itemStack != null) {
                    NBTTagCompound item = new NBTTagCompound();
                    itemStack.writeToNBT(item);
                    queueItems.appendTag(item);
                }

            compound.setTag("ItemsQueue", queueItems);
        }
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList queueItems = compound.getTagList("ItemsQueue");
        if (queueItems != null)
            for (int i = 0; i < queueItems.tagCount(); i++) {
                NBTTagCompound item = (NBTTagCompound) queueItems.tagAt(i);
                this.itemsQueue.add(ItemStack.loadItemStackFromNBT(item));
            }
    }

    @Override
    public Map<String, Boolean> getRequirements(EntityPlayer player, HashMap<String, Boolean> requirements) {
        return null;
    }

    @Override
    public Map<String, String> getAdditionalAdvancedInfos(EntityPlayer player, HashMap<String, String> additionalInfos) {
        int queueLength = 0;
        for (ItemStack is : this.itemsQueue)
            queueLength += is.stackSize;

        additionalInfos.put(LanguageManager.getTranslation("misc.meBridge.debug.additionalInfo.queueLength"), String.valueOf(queueLength));
        return additionalInfos;
    }

}
