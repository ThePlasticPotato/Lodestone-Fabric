package team.lodestar.lodestone.systems.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import team.lodestar.lodestone.systems.datagen.itemsmith.*;
import team.lodestar.lodestone.systems.datagen.statesmith.BlockStateSmith;
import team.lodestar.lodestone.systems.datagen.statesmith.ModularBlockStateSmith;

public class BlockStateSmithTypes {

    /**
     * A modular state smith, requires two separate functions, one that generates the model, and another that creates the blockstate.
     */
    public static ModularBlockStateSmith<Block> CUSTOM_MODEL = new ModularBlockStateSmith<>(Block.class, (block, provider, stateFunction, modelFileSupplier) -> {
        stateFunction.act(block, modelFileSupplier.generateModel(block));
    });

    /**
     * Generates a cube model and a blockstate to match.
     */
    public static BlockStateSmith<Block> FULL_BLOCK = new BlockStateSmith<>(Block.class, (block, provider) -> {
        provider.simpleBlock(block);
    });

    /**
     * Generates a cross model, used by flowers and grass, and a blockstate to match.
     */
    public static BlockStateSmith<Block> CROSS_MODEL_BLOCK = new BlockStateSmith<>(Block.class, ItemModelSmithTypes.CROSS_MODEL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        provider.simpleBlock(block, provider.models().cross(name, provider.getBlockTexture(name)));
    });

    /**
     * Generates leaves. Not much else to say here.
     */
    public static BlockStateSmith<Block> LEAVES_BLOCK = new BlockStateSmith<>(Block.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile leaves = provider.models().withExistingParent(name, new ResourceLocation("block/leaves")).texture("all", provider.getBlockTexture(name));
        provider.simpleBlock(block, leaves);
    });

    /**
     * Generates a log block model and state, or any pillar-like block really.
     */
    public static BlockStateSmith<RotatedPillarBlock> LOG_BLOCK = new BlockStateSmith<>(RotatedPillarBlock.class, (block, provider) -> {
        provider.logBlock(block);
    });

    /**
     * Generates an axis oriented block model and state.
     */
    public static BlockStateSmith<RotatedPillarBlock> AXIS_BLOCK = new BlockStateSmith<>(RotatedPillarBlock.class, (block, provider) -> {
        provider.axisBlock(block);
    });

    /**
     * Generates a wood block model and state, one of those 6-sided log blocks mainly used for building.
     */
    public static BlockStateSmith<RotatedPillarBlock> WOOD_BLOCK = new BlockStateSmith<>(RotatedPillarBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_wood", "") + "_log";
        ResourceLocation logTexture = provider.getBlockTexture(textureName);
        provider.axisBlock(block, logTexture, logTexture);
    });

    /**
     * Generates a directional block model and state.
     */
    public static BlockStateSmith<DirectionalBlock> DIRECTIONAL_BLOCK = new BlockStateSmith<>(DirectionalBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation textureName = provider.getBlockTexture(name);
        BlockModelBuilder directionalModel = provider.models().cubeColumnHorizontal(name, textureName, provider.extend(textureName, "_top"));
        provider.directionalBlock(block, directionalModel);
    });

    /**
     * Generates a horizontal block model and state.
     */
    public static BlockStateSmith<HorizontalDirectionalBlock> HORIZONTAL_BLOCK = new BlockStateSmith<>(HorizontalDirectionalBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        ResourceLocation textureName = provider.getBlockTexture(name);
        BlockModelBuilder horizontalModel = provider.models().cubeAll(name, textureName);
        provider.horizontalBlock(block, horizontalModel);
    });

    /**
     * Generates stairs!
     */
    public static BlockStateSmith<StairBlock> STAIRS_BLOCK = new BlockStateSmith<>(StairBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_stairs", "");
        provider.stairsBlock(block, provider.getBlockTexture(textureName));
    });

    /**
     * Slabs!
     */
    public static BlockStateSmith<SlabBlock> SLAB_BLOCK = new BlockStateSmith<>(SlabBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_slab", "");
        provider.slabBlock(block, provider.getBlockTexture(textureName), provider.getBlockTexture(textureName));
    });

    /**
     * Generates a wall block model and state.
     */
    public static BlockStateSmith<WallBlock> WALL_BLOCK = new BlockStateSmith<>(WallBlock.class, ItemModelSmithTypes.WALL_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_wall", "");
        provider.wallBlock(block, provider.getBlockTexture(textureName));
    });

    /**
     * Generates a fence block model and state.
     */
    public static BlockStateSmith<FenceBlock> FENCE_BLOCK = new BlockStateSmith<>(FenceBlock.class, ItemModelSmithTypes.FENCE_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_fence", "");
        provider.fenceBlock(block, provider.getBlockTexture(textureName));
    });

    /**
     * Generates a fence gate block model and state.
     */
    public static BlockStateSmith<FenceGateBlock> FENCE_GATE_BLOCK = new BlockStateSmith<>(FenceGateBlock.class, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("_fence_gate", "");
        provider.fenceGateBlock(block, provider.getBlockTexture(textureName));
    });

    /**
     * DOOR.
     */
    public static BlockStateSmith<DoorBlock> DOOR_BLOCK = new BlockStateSmith<>(DoorBlock.class, ItemModelSmithTypes.GENERATED_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        provider.doorBlock(block, provider.getBlockTexture(name + "_bottom"), provider.getBlockTexture(name + "_top"));
    });

    /**
     * Generates a trapdoor block model and state.
     */
    public static BlockStateSmith<TrapDoorBlock> TRAPDOOR_BLOCK = new BlockStateSmith<>(TrapDoorBlock.class, ItemModelSmithTypes.TRAPDOOR_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        provider.trapdoorBlock(block, provider.getBlockTexture(name), true);
    });

    /**
     * Generates a standing torch block model and state. Wall torch not included.
     */
    public static BlockStateSmith<TorchBlock> TORCH_BLOCK = new BlockStateSmith<>(TorchBlock.class, ItemModelSmithTypes.BLOCK_TEXTURE_ITEM, (block, provider) -> {
        String name = provider.getBlockName(block);
        ModelFile torchModel = provider.models().torch(provider.getBlockName(block), provider.getBlockTexture(name));
        provider.getVariantBuilder(block).forAllStates(s -> ConfiguredModel.builder().modelFile(torchModel).build());
    });

    /**
     * Generates a wall torch block model and state. Standing torch not included.
     */
    public static BlockStateSmith<WallTorchBlock> WALL_TORCH_BLOCK = new BlockStateSmith<>(WallTorchBlock.class, ItemModelSmithTypes.NO_MODEL, (block, provider) -> {
        String name = provider.getBlockName(block);
        String textureName = name.replace("wall_", "");
        ModelFile torchModel = provider.models().torchWall(provider.getBlockName(block), provider.getBlockTexture(textureName));
        provider.horizontalBlock(block, torchModel, 90);
    });

}