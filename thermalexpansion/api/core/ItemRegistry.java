// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

import java.util.TreeMap;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import java.util.Map;

public final class ItemRegistry
{
    private static final Map registry;
    
    public static ItemStack getItem(final String name, final int qty) {
        ItemStack result = (ItemStack) ItemRegistry.registry.get(name);
        if (result != null) {
            result = result.copy();
            result.stackSize = qty;
        }
        return result;
    }
    
    public static void registerItem(final String name, final ItemStack item) {
        ItemRegistry.registry.put(name, item);
    }
    
    static {
        registry = new TreeMap();
    }
}
