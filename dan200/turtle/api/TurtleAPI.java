// 
// Decompiled by Procyon v0.5.36
// 

package dan200.turtle.api;

import java.lang.reflect.Method;

public class TurtleAPI
{
    private static boolean ccTurtleSearched;
    private static Class ccTurtle;
    private static Method ccTurtle_registerTurtleUpgrade;
    
    public static void registerUpgrade(final ITurtleUpgrade upgrade) {
        if (upgrade != null) {
            findCCTurtle();
            if (TurtleAPI.ccTurtle_registerTurtleUpgrade != null) {
                try {
                    TurtleAPI.ccTurtle_registerTurtleUpgrade.invoke(null, upgrade);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private static void findCCTurtle() {
        if (!TurtleAPI.ccTurtleSearched) {
            try {
                TurtleAPI.ccTurtle = Class.forName("dan200.CCTurtle");
                TurtleAPI.ccTurtle_registerTurtleUpgrade = findCCTurtleMethod("registerTurtleUpgrade", new Class[] { ITurtleUpgrade.class });
            }
            catch (ClassNotFoundException e) {
                System.out.println("ComputerCraftAPI: CCTurtle not found.");
            }
            finally {
                TurtleAPI.ccTurtleSearched = true;
            }
        }
    }
    
    private static Method findCCTurtleMethod(final String name, final Class[] args) {
        try {
            return TurtleAPI.ccTurtle.getMethod(name, (Class[])args);
        }
        catch (NoSuchMethodException e) {
            System.out.println("ComputerCraftAPI: CCTurtle method " + name + " not found.");
            return null;
        }
    }
    
    static {
        TurtleAPI.ccTurtleSearched = false;
        TurtleAPI.ccTurtle = null;
        TurtleAPI.ccTurtle_registerTurtleUpgrade = null;
    }
}
