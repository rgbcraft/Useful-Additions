package com.rgbcraft.usefuladditions.guis;

import org.lwjgl.opengl.GL11;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.containers.ContainerBase;
import com.rgbcraft.usefuladditions.guis.components.GuiButtonCustom;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileLoadBank;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;


@SideOnly(Side.CLIENT)
public class GuiLoadBank extends GuiContainer {

    private int main_width;
    private int left;
    private int top;
    private int center;
    private GuiTextField loadAmountInput;
    private int amount = 0;
    private TileLoadBank tileLoadBank;
    private GuiButton add;
    private GuiButton remove;

    public GuiLoadBank(InventoryPlayer inventory, TileLoadBank tileLoadBank) {
        super(new ContainerBase(tileLoadBank));

        this.tileLoadBank = tileLoadBank;

        this.xSize = 176;
        this.ySize = 80;

        int BORDER = 4;

        this.main_width = this.xSize - BORDER * 2;
        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
    }

    private void sendAmount() {
        ByteArrayDataOutput data = NetworkHandler.createBasePacket("sendAmount", this.tileLoadBank);
        data.writeInt(this.amount);
        NetworkHandler.sendDataPacketToServer(data);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);

        Utils.bindTexture(this.mc, Utils.getResource(ResourceType.GUI, "GuiLoadBank.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.loadBank"), this.center, this.top + 2, 0x404040);
        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.loadBank.loadAmount"), this.center, this.top + 27, 0x404040);

        this.loadAmountInput.drawTextBox();
    }

    @Override
    public void initGui() {
        super.initGui();

        this.amount = this.tileLoadBank.maxEnergy;

        this.loadAmountInput = new GuiTextField(this.fontRenderer, 61, 46, 55, 12);

        this.loadAmountInput.setMaxStringLength(5);
        this.loadAmountInput.setEnableBackgroundDrawing(false);
        this.loadAmountInput.setCanLoseFocus(true);
        this.loadAmountInput.setFocused(false);

        this.controlList.clear();
        this.controlList.add(this.remove = new GuiButtonCustom(0, this.guiLeft + 40, this.guiTop + 43, 190, 0, 14, 14, Utils.getResource(ResourceType.GUI, "GuiLoadBank.png")));
        this.controlList.add(this.add = new GuiButtonCustom(1, this.guiLeft + 121, this.guiTop + 43, 176, 0, 14, 14, Utils.getResource(ResourceType.GUI, "GuiLoadBank.png")));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (this.amount <= 0) {
            this.amount = 0;
            this.remove.enabled = false;
        } else
            this.remove.enabled = true;

        if (this.amount >= 32768) {
            this.amount = 32768;
            this.add.enabled = false;
        } else
            this.add.enabled = true;

        this.loadAmountInput.setText(Integer.toString(this.amount));
    }

    @Override
    protected void keyTyped(char character, int keyId) {
        super.keyTyped(character, keyId);
        if ((character == '\b' || Character.isDigit(character)) && this.loadAmountInput.textboxKeyTyped(character, keyId))
            try {
                if (this.loadAmountInput.getText().equals("")) {
                    this.amount = 0;
                    this.loadAmountInput.setText("");
                } else
                    this.amount = Integer.valueOf(this.loadAmountInput.getText());
                this.sendAmount();
            } catch (Exception ex) {
            }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.loadAmountInput.mouseClicked(par1 - super.guiLeft, par2 - super.guiTop, par3);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                if (GuiScreen.isShiftKeyDown())
                    this.amount -= 16;
                else if (GuiScreen.isCtrlKeyDown())
                    this.amount -= 1;
                else
                    this.amount -= 128;
                this.sendAmount();
                break;
            case 1:
                if (GuiScreen.isShiftKeyDown())
                    this.amount += 16;
                else if (GuiScreen.isCtrlKeyDown())
                    this.amount += 1;
                else
                    this.amount += 128;
                this.sendAmount();
                break;
        }
    }

}
