package team.lodestar.lodestone.systems.postprocess;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles world-space post-processing.
 * Based on vanilla {@link net.minecraft.client.renderer.PostChain} system, but allows the shader to access the world depth buffer.
 */
public class PostProcessHandler implements WorldRenderEvents.End {
    private static final List<PostProcessor> instances = new ArrayList<>();

    private static boolean didCopyDepth = false;

    /**
     * Add an {@link PostProcessor} for it to be handled automatically.
     * IMPORTANT: processors has to be added in the right order!!!
     * There's no way of getting an instance, so you need to keep the instance yourself.
     */
    public static void addInstance(PostProcessor instance) {
        instances.add(instance);
    }

    public static void copyDepthBuffer() {
        if (didCopyDepth) return;
        instances.forEach(PostProcessor::copyDepthBuffer);
        didCopyDepth = true;
    }

    public static void resize(int width, int height) {
        instances.forEach(i -> i.resize(width, height));
    }

    @Override
    public void onEnd(WorldRenderContext context) {
        copyDepthBuffer(); // copy the depth buffer if the mixin didn't trigger

        PostProcessor.viewModelStack = context.matrixStack();
        instances.forEach(PostProcessor::applyPostProcess);

        didCopyDepth = false; // reset for next frame
    }
}
