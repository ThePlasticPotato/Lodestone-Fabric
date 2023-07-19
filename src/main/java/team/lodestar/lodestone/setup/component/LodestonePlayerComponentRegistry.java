package team.lodestar.lodestone.setup.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.capability.LodestonePlayerDataCapability;

public class LodestonePlayerComponentRegistry implements EntityComponentInitializer {

    public static final ResourceLocation PLAYERDATA_LOCATION = new ResourceLocation("lodestone", "player_data");
    public static final ComponentKey<LodestonePlayerDataCapability> PLAYERDATA_CAPABILITY = ComponentRegistryV3.INSTANCE.getOrCreate(PLAYERDATA_LOCATION, LodestonePlayerDataCapability.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYERDATA_CAPABILITY, e -> new LodestonePlayerDataCapability());
    }
}
