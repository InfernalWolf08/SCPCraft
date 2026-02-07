package com.github.InfernalWolf.entity.client;

import com.github.InfernalWolf.Scpcraft;
import com.github.InfernalWolf.entity.custom.SCP173Entity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SCP173Renderer extends MobEntityRenderer<SCP173Entity, SCP173Model<SCP173Entity>> {
    private static final Identifier TEXTURE = new Identifier(Scpcraft.MOD_ID, "textures/entity/scp173.png");

    public SCP173Renderer(EntityRendererFactory.Context context) {
        super(context, new SCP173Model<>(context.getPart(ModModelLayers.SCP173)), 0.6f);
    }

    @Override
    public Identifier getTexture(SCP173Entity entity) {
        return TEXTURE;
    }

    @Override
    public void render(SCP173Entity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {



        // Final Render
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
