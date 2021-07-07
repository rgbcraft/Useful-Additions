package com.rgbcraft.usefuladditions.tiles;

import java.util.Map;

import com.google.common.io.ByteArrayDataInput;
import com.rgbcraft.usefuladditions.network.INetworkMember;
import com.rgbcraft.usefuladditions.utils.ICardInfoProvider;
import com.rgbcraft.usefuladditions.utils.IRoteableTile;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;

import buildcraft.api.core.Position;
import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IPeripheral;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;


public class TileFluidCounter extends TileEntity implements ITankContainer, IPeripheral, IRoteableTile, INetworkMember, ICardInfoProvider {

    private LiquidTank tank;
    private int amount = 0;
    private boolean filling = false;
    private boolean redstoneLocked = false;
    private boolean computerLocked = false;
    private String liquidName = null;

    public TileFluidCounter() {
        this.tank = new LiquidTank(1000);
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            this.redstoneLocked = Utils.isRedstonePowered(this.worldObj, this.xCoord, this.yCoord, this.zCoord);

            if (this.tank.getLiquid() != null)
                this.liquidName = this.tank.getLiquid().asItemStack().getDisplayName();
            else
                this.liquidName = null;

            if (this.tank.getLiquid() != null) {
                Position position = new Position(this.xCoord, this.yCoord, this.zCoord, this.getSide().getOpposite());
                position.moveForwards(1);
                this.amount += Utils.outputLiquidOnSide(this.tank, this.worldObj, position);
            }
        }
    }

    public int getAmount() {
        return this.amount;
    }

    public String getLiquidName() {
        if (this.liquidName != null)
            return this.liquidName;
        else
            return LanguageManager.getTranslation("container.fluidCounter.liquid.none");
    }

    private ForgeDirection getSide() {
        switch (Utils.unmergeBits((byte) this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord))[1]) {
            case 0:
                return ForgeDirection.DOWN;
            case 1:
                return ForgeDirection.UP;
            case 2:
                return ForgeDirection.NORTH;
            case 3:
                return ForgeDirection.SOUTH;
            case 4:
                return ForgeDirection.WEST;
            case 5:
                return ForgeDirection.EAST;
            default:
                return ForgeDirection.UNKNOWN;
        }
    }

    @Override
    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
        if (this.getSide() == from)
            return this.fill(this.amount, resource, doFill);
        return 0;
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        if (!this.redstoneLocked && !this.computerLocked) {
            if ((this.tank.getLiquid() == null && resource.amount <= this.tank.getCapacity()) || (this.tank.getLiquid().amount + resource.amount <= this.tank.getCapacity())) {
                this.tank.fill(resource, doFill);
                this.filling = true;
                return resource.amount;
            }
        }
        this.filling = false;
        return 0;
    }

    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (this.getSide().getOpposite() == from)
            if (this.tank.getLiquid() != null)
                if (this.filling)
                    return this.drain(0, maxDrain - 1, doDrain);
                else
                    return this.drain(0, maxDrain, doDrain);
        return null;
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        if (tankIndex == 0)
            return this.tank.drain(maxDrain, doDrain);
        return null;
    }

    @Override
    public ILiquidTank[] getTanks(ForgeDirection direction) {
        if (this.getSide() == direction.getOpposite() || this.getSide() == direction)
            return new ILiquidTank[] {this.tank};
        return new ILiquidTank[0];
    }

    @Override
    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
        if (this.getSide() == direction.getOpposite() || this.getSide() == direction)
            return this.tank;
        return null;
    }

    @Override
    public String getType() {
        return "fluidCounter";
    }

    @Override
    public String[] getMethodNames() {
        return new String[] {"getAmount", "getLiquid", "reset", "isLocked"};
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, int method, Object[] arguments) throws Exception {
        switch (method) {
            case 0:
                return new Object[] {this.amount};
            case 1:
                return new Object[] {this.liquidName};
            case 2:
                this.amount = 0;
                return new Object[] {true};
            case 3:
                if (arguments.length > 0)
                    if (arguments[0] instanceof Boolean) {
                        this.computerLocked = (Boolean) arguments[0];
                        return new Object[] {this.computerLocked};
                    } else
                        throw new IllegalArgumentException("The argument must be a boolean.");
                else
                    return new Object[] {this.redstoneLocked || this.computerLocked};
        }
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
    public String getMachineName() {
        return LanguageManager.getTranslation("tile.fluidCounter.name");
    }

    @Override
    public Map<String, String> getRows(Map<String, String> rows) {
        rows.put(LanguageManager.getTranslation("misc.fluidCounter.sensor.amount"), String.valueOf(Utils.formatNumber(this.getAmount())) + " mB");
        rows.put(LanguageManager.getTranslation("misc.fluidCounter.sensor.liquid"), this.getLiquidName());
        return rows;
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("Amount", this.amount);

        if (this.tank.getLiquid() != null) {
            NBTTagCompound tag = new NBTTagCompound();
            this.tank.getLiquid().writeToNBT(tag);
            compound.setCompoundTag("Tank", tag);
        }
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.amount = compound.getInteger("Amount");
        this.tank.setLiquid(LiquidStack.loadLiquidStackFromNBT(compound.getCompoundTag("Tank")));
    }

    @Override
    public int getRotation(World world, int x, int y, int z, EntityPlayer entityPlayer, int side) {
        return Utils.mergeBits((byte) 1, (byte) Utils.get3dOrientation(new Position(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ), new Position(x, y, z)).getOpposite().ordinal());
    }

    @Override
    public void onClientPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer) {
        if (packetName.equals("tile.fluidCounter.sendAll")) {
            this.amount = data.readInt();
            this.liquidName = data.readUTF();
        } else if (packetName.equals("tile.fluidCounter.sendLiquidName"))
            this.liquidName = data.readUTF();
        else if (packetName.equals("tile.fluidCounter.sendAmount"))
            this.amount = data.readInt();
    }

    @Override
    public void onServerPacketReceived(String packetName, ByteArrayDataInput data, EntityPlayer entityPlayer) {
        if (packetName.equals("tile.fluidCounter.resetCounter")) {
            this.liquidName = null;
            this.amount = 0;
        }
    }

}
