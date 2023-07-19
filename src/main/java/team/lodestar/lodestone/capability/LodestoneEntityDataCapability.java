package team.lodestar.lodestone.capability;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import org.jetbrains.annotations.Nullable;
import team.lodestar.lodestone.LodestoneLib;
import team.lodestar.lodestone.helpers.NBTHelper;
import team.lodestar.lodestone.network.capability.SyncLodestoneEntityCapabilityPacket;
import team.lodestar.lodestone.setup.LodestonePacketRegistry;
import team.lodestar.lodestone.setup.component.LodestoneEntityComponentRegistry;
import team.lodestar.lodestone.systems.capability.LodestoneCapability;
import team.lodestar.lodestone.systems.capability.LodestoneCapabilityProvider;
import team.lodestar.lodestone.systems.fireeffect.FireEffectInstance;
import team.lodestar.lodestone.handlers.FireEffectHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class LodestoneEntityDataCapability implements ComponentV3, AutoSyncedComponent {


    @Nullable
    public static LodestoneEntityDataCapability get(Entity entity) {
        return entity.getComponent(LodestoneEntityComponentRegistry.ENTITY_DATA_CAPABILITY);
    }


    public static LodestoneEntityDataCapability CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    public FireEffectInstance fireEffectInstance;

    public LodestoneEntityDataCapability() {

    }

//    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
//        event.register(LodestoneEntityDataCapability.class);
//    }
//
//    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
//        final LodestoneEntityDataCapability capability = new LodestoneEntityDataCapability();
//        event.addCapability(LodestoneLib.lodestonePath("entity_data"), new LodestoneCapabilityProvider<>(LodestoneEntityDataCapability.CAPABILITY, () -> capability));
//    }
//
//    public static void syncEntityCapability(PlayerEvent.StartTracking event) {
//        if (event.getEntity().level instanceof ServerLevel) {
//            LodestoneEntityDataCapability.syncTracking(event.getEntityLiving());
//        }
//    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        FireEffectHandler.deserializeNBT(this, tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        FireEffectHandler.serializeNBT(this, tag);
    }
}