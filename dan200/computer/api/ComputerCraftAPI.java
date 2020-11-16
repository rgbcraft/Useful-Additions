// 
// Decompiled by Procyon v0.5.36
// 

package dan200.computer.api;

import net.minecraft.creativetab.CreativeTabs;
import java.lang.reflect.Method;

public class ComputerCraftAPI
{
    private static boolean ccSearched;
    private static Class computerCraft;
    private static Method computerCraft_registerExternalPeripheral;
    private static Method computerCraft_getCreativeTab;
    
    public static CreativeTabs getCreativeTab() {
        findCC();
        if (ComputerCraftAPI.computerCraft_getCreativeTab != null) {
            try {
                return (CreativeTabs)ComputerCraftAPI.computerCraft_getCreativeTab.invoke(null, new Object[0]);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static void registerExternalPeripheral(final Class clazz, final IPeripheralHandler handler) {
        findCC();
        if (ComputerCraftAPI.computerCraft_registerExternalPeripheral != null) {
            try {
                ComputerCraftAPI.computerCraft_registerExternalPeripheral.invoke(null, clazz, handler);
            }
            catch (Exception ex) {}
        }
    }
    
    private static void findCC() {
        if (!ComputerCraftAPI.ccSearched) {
            try {
                ComputerCraftAPI.computerCraft = Class.forName("dan200.ComputerCraft");
                ComputerCraftAPI.computerCraft_getCreativeTab = findCCMethod("getCreativeTab", new Class[0]);
                ComputerCraftAPI.computerCraft_registerExternalPeripheral = findCCMethod("registerExternalPeripheral", new Class[] { Class.class, IPeripheralHandler.class });
            }
            catch (Exception e) {
                System.out.println("ComputerCraftAPI: ComputerCraft not found.");
            }
            finally {
                ComputerCraftAPI.ccSearched = true;
            }
        }
    }
    
    private static Method findCCMethod(final String name, final Class[] args) {
        try {
            return ComputerCraftAPI.computerCraft.getMethod(name, (Class[])args);
        }
        catch (NoSuchMethodException e) {
            System.out.println("ComputerCraftAPI: ComputerCraft method " + name + " not found.");
            return null;
        }
    }
    
    static {
        ComputerCraftAPI.ccSearched = false;
        ComputerCraftAPI.computerCraft = null;
        ComputerCraftAPI.computerCraft_registerExternalPeripheral = null;
        ComputerCraftAPI.computerCraft_getCreativeTab = null;
    }
}
