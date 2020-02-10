package grondag.hamsandwich.mixin;

import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.world.World;

import grondag.hamsandwich.HamSandwich;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
	@Redirect(method = "reload", at = @At(value = "NEW", target = "net/minecraft/client/render/chunk/ChunkBuilder"))
	private ChunkBuilder swapExecutor(World world, WorldRenderer worldRenderer, Executor executor, boolean flag, BlockBufferBuilderStorage blockBufferBuilderStorage) {
		return new ChunkBuilder(world, worldRenderer, HamSandwich.EXECUTOR, flag, blockBufferBuilderStorage);
	}
}
