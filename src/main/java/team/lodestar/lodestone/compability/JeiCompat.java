package team.lodestar.lodestone.compability;

import mezz.jei.api.runtime.IRecipesGui;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.fml.ModList;

public class JeiCompat {
    public static boolean LOADED;

    public static void init() {
        LOADED = FabricLoaderImpl.INSTANCE.isModLoaded("jei");
    }

}