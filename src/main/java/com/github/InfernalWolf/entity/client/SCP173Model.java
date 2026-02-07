// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.github.InfernalWolf.entity.client;

import com.github.InfernalWolf.entity.custom.SCP173Entity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class SCP173Model <T extends SCP173Entity> extends SinglePartEntityModel<T> {
	private final ModelPart bb_main;

	public SCP173Model(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(29, 22).cuboid(2.0F, -8.0F, 0.0F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(29, 22).cuboid(-4.0F, -8.0F, 0.0F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 18).cuboid(-4.0F, -20.0F, 0.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.5F, -29.5F, -1.5F, 7.0F, 10.0F, 7.0F, new Dilation(-0.5F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData ArmL_r1 = bb_main.addChild("ArmL_r1", ModelPartBuilder.create().uv(29, 5).cuboid(4.0F, -4.0F, 16.0F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(29, 5).cuboid(-6.0F, -4.0F, 16.0F, 2.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 41, 34);
	}

	@Override
	public void setAngles(SCP173Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart getPart() {
		return bb_main;
	}
}