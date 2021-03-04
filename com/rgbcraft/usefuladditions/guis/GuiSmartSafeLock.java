package com.rgbcraft.usefuladditions.guis;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.containers.ContainerBase;
import com.rgbcraft.usefuladditions.guis.components.GuiTooltip;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;


@SideOnly(Side.CLIENT)
public class GuiSmartSafeLock extends GuiContainer {

    private TileSmartSafe tileSmartSafe;
    private String originalPin = "";
    private GuiTextField pinInput;
    private EntityPlayer player;
    private GuiTooltip tooltip;
    private boolean pinWrong = false;

    private int main_width;
    private int center;
    private int left;
    private int top;

    private Map<Integer, GuiButton> buttons = new HashMap<>();

    public GuiSmartSafeLock(InventoryPlayer inventory, TileSmartSafe tileSmartSafe, EntityPlayer player) {
        super(new ContainerBase(tileSmartSafe));

        this.tooltip = new GuiTooltip();

        this.player = player;
        this.tileSmartSafe = tileSmartSafe;

        this.xSize = 129;
        this.ySize = 172;

        int BORDER = 4;

        this.main_width = this.xSize - BORDER * 2;
        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
    }

    private void sendString(String packetName, String value) {
        ByteArrayDataOutput data = NetworkHandler.createBasePacket(packetName, this.tileSmartSafe);
        data.writeUTF(value);
        NetworkHandler.sendDataPacketToServer(data);
    }

    private void openGui() {
        ByteArrayDataOutput data = NetworkHandler.createBasePacket("openSafe", this.tileSmartSafe);
        NetworkHandler.sendDataPacketToServer(data);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);

        Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getTexture(Utils.getResource(ResourceType.GUI, "GuiSmartSafeLock.png")));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        if (this.tileSmartSafe.isConfigured())
            Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.smartSafe.lock.set"), this.center, this.top + 5, 0x404040);
        else
            Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.smartSafe.lock.notSet"), this.center, this.top + 5, 0x404040);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.pinInput = new GuiTextField(this.fontRenderer, this.guiLeft + 28, this.guiTop + 25, 72, 20);
        this.pinInput.setMaxStringLength(4);

        this.controlList.clear();

        int buttonID = 0;
        for (int y = 0; y < 4; y++)
            for (int x = 0; x < 3; x++) {
                GuiButton button;
                switch (buttonID) {
                    case 9:
                        button = new GuiButton(buttonID, this.guiLeft + 30 + 24 * x, this.guiTop + 50 + 24 * y + 4, 20, 20, "\2474\u2718");
                        break;
                    case 10:
                        button = new GuiButton(buttonID, this.guiLeft + 30 + 24 * x, this.guiTop + 50 + 24 * y + 4, 20, 20, "0");
                        break;
                    case 11:
                        button = new GuiButton(buttonID, this.guiLeft + 30 + 24 * x, this.guiTop + 50 + 24 * y + 4, 20, 20, "\2472\u2714");
                        break;
                    default:
                        button = new GuiButton(buttonID, this.guiLeft + 30 + 24 * x, this.guiTop + 50 + 24 * y + 4, 20, 20, String.valueOf(buttonID + 1));
                        break;
                }

                this.buttons.put(buttonID, button);
                this.controlList.add(button);
                buttonID++;
            }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        this.pinInput.drawTextBox();

        if (this.isPointInRegion(28, 25, 72, 20, mouseX, mouseY)) {
            this.tooltip.lines.clear();
            this.tooltip.lines.add(LanguageManager.getTranslation("container.smartSafe.lock.toolTip"));
            this.tooltip.draw(mouseX, mouseY);
        }
    }

    @Override
    protected void keyTyped(char character, int keyId) {
        super.keyTyped(character, keyId);

        if (keyId >= 2 && keyId <= 11 || keyId >= 71 && keyId <= 73 || keyId >= 75 && keyId <= 77 || keyId >= 79 && keyId <= 82 || keyId == 28 || keyId == 14) {
            int id = 0;
            switch (keyId) {
                case 2:
                case 79:
                    id = 0;
                    break;
                case 3:
                case 80:
                    id = 1;
                    break;
                case 4:
                case 81:
                    id = 2;
                    break;
                case 5:
                case 75:
                    id = 3;
                    break;
                case 6:
                case 76:
                    id = 4;
                    break;
                case 7:
                case 77:
                    id = 5;
                    break;
                case 8:
                case 71:
                    id = 6;
                    break;
                case 9:
                case 72:
                    id = 7;
                    break;
                case 10:
                case 73:
                    id = 8;
                    break;
                case 11:
                case 82:
                    id = 10;
                    break;
                case 28:
                    id = 11;
                    break;
                case 14:
                    id = 9;
                    break;
            }

            this.actionPerformed(this.buttons.get(id));
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        if (GuiScreen.isShiftKeyDown())
            this.pinInput.setText(this.originalPin);
        else if (this.originalPin.length() > 0)
            this.pinInput.setText(new String(new char[this.originalPin.length()]).replace("\0", "*"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 9) {
            if (this.originalPin.length() > 0)
                if (this.pinWrong && this.tileSmartSafe.isConfigured()) {
                    this.pinWrong = false;
                    this.pinInput.setTextColor(14737632);

                    this.originalPin = "";
                } else
                    this.originalPin = this.originalPin.substring(0, this.originalPin.length() - 1);
        } else if (button.id == 11) {
            if (GuiScreen.isCtrlKeyDown() && this.tileSmartSafe.isConfigured() && !this.originalPin.equals(this.tileSmartSafe.getPin())) {
                if (!(this.pinInput.getText().length() < 4)) {
                    if (this.tileSmartSafe.isOwner(this.player) || Utils.isOperator(this.player)) {
                        this.sendString("sendPin", this.originalPin);

                        UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.pinUpdated"));

                        this.pinWrong = false;
                        this.pinInput.setTextColor(0x32FC00);

                        this.openGui();
                    } else
                        UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.cannotUpdatePin"));
                } else {
                    this.pinWrong = true;
                    this.pinInput.setTextColor(0xFC1D00);
                }
                return;
            }

            if (this.tileSmartSafe.getPin().equals(this.originalPin) && this.originalPin.length() > 0) {
                this.pinWrong = false;
                this.pinInput.setTextColor(0x32FC00);

                this.openGui();
            } else if (this.originalPin.length() > 0) {
                if (this.tileSmartSafe.isConfigured() || this.originalPin.length() < 4)
                    this.pinWrong = true;
                else {
                    this.sendString("sendOwner", this.player.username);

                    UsefulAdditions.proxy.sendMessageToPlayer(this.player, LanguageManager.getTranslation("misc.smartSafe.lock.pinCreated"));

                    this.sendString("sendPin", this.originalPin);
                    this.openGui();
                }

                if (this.pinWrong)
                    this.pinInput.setTextColor(0xFC1D00);
            }
        } else if (this.pinWrong) {
            this.pinWrong = false;
            this.pinInput.setTextColor(14737632);

            this.originalPin = button.displayString;
        } else if (this.originalPin.length() < 4)
            this.originalPin = this.originalPin + button.displayString;
        this.pinInput.setText(this.originalPin);
    }

}
