package team.lodestar.lodestone.compability;

import net.fabricmc.loader.impl.FabricLoaderImpl;

public class CuriosCompat {
    public static boolean LOADED;

    public static void init() {
        LOADED = FabricLoaderImpl.INSTANCE.isModLoaded("curios");
    }
}