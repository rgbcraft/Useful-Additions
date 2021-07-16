package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.GuiHandler.Guis;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.IRarityBlock;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import buildcraft.api.core.Position;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class BlockSmartSafe extends BlockMachineBase implements IRarityBlock {

    protected BlockSmartSafe(int id) {
        super(id, "smartSafe");

        this.setBlockUnbreakable();
        this.setResistance(6000000.0F);
        this.setTextureFile(Utils.getResource(ResourceType.MODEL, "ModelSmartSafe.png"));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ)) {
            TileEntity te = world.getBlockTileEntity(x, y, z);
            if (te != null && te instanceof TileSmartSafe) {
                if (((TileSmartSafe) te).isConfigured() && (Utils.isOperator(entityPlayer) || ((TileSmartSafe) te).isOwner(entityPlayer)) && entityPlayer.isSneaking())
                    Utils.openGui(entityPlayer, world, x, y, z, Guis.SmartSafeInventory);
                else
                    Utils.openGui(entityPlayer, world, x, y, z, Guis.SmartSafeLock);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving);
        ForgeDirection orientation = Utils.get2dOrientation(new Position(entityLiving.posX, entityLiving.posY, entityLiving.posZ), new Position(x, y, z));
        world.setBlockMetadataWithNotify(x, y, z, orientation.getOpposite().ordinal());
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        this.setBlockBounds(0.06F, 0F, 0.06F, 0.94F, 1F, 0.94F);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, start, end);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    @Override
    public int getRenderType() {
        return UsefulAdditions.proxy.getRenderId("smartSafe");
    }

    @Override
    public boolean canDragonDestroy(World world, int x, int y, int z) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.rare;
    }

    @Override
    public boolean canDismantle(EntityPlayer entityPlayer, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileSmartSafe) {
            TileSmartSafe tileSmartSafe = (TileSmartSafe) tileEntity;
            if (tileSmartSafe.isConfigured()) {
                if (tileSmartSafe.isOwner(entityPlayer) || Utils.isOperator(entityPlayer))
                    if (tileSmartSafe.isInventoryEmpty())
                        return true;
                    else
                        UsefulAdditions.proxy.sendMessageToPlayer(entityPlayer, LanguageManager.getTranslation("misc.smartSafe.cannotRemoveIfNotEmpty"));
            } else
                return true;
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileSmartSafe();
    }

}
