package team.lodestar.lodestone.setup.component;

import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import team.lodestar.lodestone.capability.LodestoneEntityDataCapability;

public class LodestoneEntityComponentRegistry implements EntityComponentInitializer {

    public static final ResourceLocation ENTITYDATA_LOCATION = new ResourceLocation("lodestone", "entity_data");
    public static final ComponentKey<LodestoneEntityDataCapability> ENTITYDATA_CAPABILITY = ComponentRegistryV3.INSTANCE.getOrCreate(ENTITYDATA_LOCATION, LodestoneEntityDataCapability.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Entity.class, ENTITYDATA_CAPABILITY, e -> new LodestoneEntityDataCapability());
    }
}
