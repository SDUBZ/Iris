package net.coderbot.iris.compat.sodium.mixin.shader_overrides;

import me.jellysquid.mods.sodium.client.gl.buffer.GlMutableBuffer;
import me.jellysquid.mods.sodium.client.gl.shader.GlProgram;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderMatrices;
import me.jellysquid.mods.sodium.client.render.chunk.RegionChunkRenderer;
import me.jellysquid.mods.sodium.client.render.chunk.shader.ChunkShaderInterface;
import net.coderbot.iris.compat.sodium.impl.shader_overrides.ShaderChunkRendererExt;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RegionChunkRenderer.class)
public abstract class MixinRegionChunkRenderer implements ShaderChunkRendererExt {
	@Redirect(method = "render", remap = false,
			at = @At(value = "INVOKE",
					target = "me/jellysquid/mods/sodium/client/gl/shader/GlProgram.getInterface ()Ljava/lang/Object;"))
	private Object iris$getInterface(GlProgram<?> program) {
		if (program == null) {
			// Iris sentinel null
			return iris$getOverride().getInterface();
		} else {
			return program.getInterface();
		}
	}

}
