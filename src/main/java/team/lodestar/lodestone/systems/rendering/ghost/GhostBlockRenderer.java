package team.lodestar.lodestone.systems.rendering.ghost;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.fabricators_of_create.porting_lib.model.IModelData;
import team.lodestar.lodestone.handlers.PlacementAssistantHandler;
import team.lodestar.lodestone.handlers.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public abstract class GhostBlockRenderer {

    public static final GhostBlockRenderer STANDARD = new DefaultGhostBlockRenderer();
    public static final GhostBlockRenderer TRANSPARENT = new TransparentGhostBlockRenderer();

    public static GhostBlockRenderer standard() {
        return STANDARD;
    }

    public static GhostBlockRenderer transparent() {
        return TRANSPARENT;
    }

    public abstract void render(PoseStack ps, GhostBlockOptions params);

    private static class DefaultGhostBlockRenderer extends GhostBlockRenderer {
        @Override
        public void render(PoseStack ps, GhostBlockOptions options) {
            ps.pushPose();
            BlockRenderDispatcher dispatch = Minecraft.getInstance().getBlockRenderer();
            BakedModel bakedModel = dispatch.getBlockModel(options.blockState);
            RenderType renderType = ItemBlockRenderTypes.getRenderType(options.blockState, false);
            VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(renderType);
            BlockPos pos = options.blockPos;

            ps.translate(pos.getX(), pos.getY(), pos.getZ());
            dispatch.getModelRenderer().renderModel(ps.last(), consumer, options.blockState, bakedModel, 1.0F, 1.0F, 1.0F, LightTexture.FULL_BRIGHT,
                    OverlayTexture.NO_OVERLAY);
            ps.popPose();
        }
    }

    private static class TransparentGhostBlockRenderer extends GhostBlockRenderer {
        @Override
        public void render(PoseStack ps, GhostBlockOptions options) {
            ps.pushPose();
            Minecraft minecraft = Minecraft.getInstance();
            BlockRenderDispatcher dispatch = minecraft.getBlockRenderer();
            BakedModel bakedModel = dispatch.getBlockModel(options.blockState);
            RenderType renderType = RenderType.translucent();
            VertexConsumer consumer = RenderHandler.DELAYED_RENDER.getBuffer(renderType);
            BlockPos pos = options.blockPos;

            ps.translate(pos.getX(), pos.getY(), pos.getZ());

            ps.translate(0.5D, 0.5D, 0.5D);
            ps.scale(0.85F, 0.85F, 0.85F);
            ps.translate(-0.5D, -0.5D, -0.5D);

            float alpha = options.alphaSupplier.get() * 0.75F * PlacementAssistantHandler.getCurrentAlpha();
            renderModel(ps.last(), consumer, options.blockState, bakedModel, 1.0F, 1.0F, 1.0F, alpha, LevelRenderer.getLightColor(minecraft.level, pos),
                    OverlayTexture.NO_OVERLAY, VirtualEmptyModelData.INSTANCE);

            ps.popPose();
        }

        public void renderModel(PoseStack.Pose pose, VertexConsumer consumer, BlockState state, BakedModel model, float red, float green, float blue, float alpha, int packedLight, int packedOverlay,
                                IModelData extraData) {
            Random random = new Random();

            for (Direction direction : Direction.values()) {
                random.setSeed(42L);
                renderQuadList(pose, consumer, red, green, blue, alpha, model.getQuads(state, direction, random), packedLight, packedOverlay);
            }

            random.setSeed(42L);
            renderQuadList(pose, consumer, red, green, blue, alpha, model.getQuads(state, null, random), packedLight, packedOverlay);
        }

        private static void renderQuadList(PoseStack.Pose pose, VertexConsumer consumer, float red, float green, float blue, float alpha, List<BakedQuad> quads, int packedLight, int packedOverlay) {
            for (BakedQuad quad : quads) {
                float r, g, b;
                if (quad.isTinted()) {
                    r = Mth.clamp(red, 0.0F, 1.0F);
                    g = Mth.clamp(green, 0.0F, 1.0F);
                    b = Mth.clamp(blue, 0.0F, 1.0F);
                } else {
                    r = 1.0F;
                    g = 1.0F;
                    b = 1.0F;
                }
                consumer.putBulkData(pose, quad, r, g, b, packedLight, packedOverlay);
            }
        }
    }
}