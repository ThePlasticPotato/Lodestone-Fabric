package com.sammy.ortus.events;

import com.sammy.ortus.handlers.RenderHandler;
import com.sammy.ortus.handlers.ScreenParticleHandler;
import com.sammy.ortus.setup.OrtusParticles;
import com.sammy.ortus.setup.OrtusScreenParticles;
import com.sammy.ortus.systems.block.OrtusBlockProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetupEvents {

    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
        OrtusParticles.registerParticleFactory(event);
        OrtusScreenParticles.registerParticleFactory(event);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderHandler.onClientSetup(event);
        ScreenParticleHandler.registerParticleEmitters(event);
        OrtusBlockProperties.setRenderLayers(event);
    }
}