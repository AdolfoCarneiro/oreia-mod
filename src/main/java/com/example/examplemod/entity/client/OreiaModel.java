package com.example.examplemod.entity.client;

import com.example.examplemod.entity.animations.ModAnimationDefinitions;
import com.example.examplemod.entity.custom.OreiaEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class OreiaModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart oreia;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart body;
	private final ModelPart head;

	public OreiaModel(ModelPart root) {
		this.oreia = root.getChild("oreia");
		this.leftLeg = this.oreia.getChild("leftLeg");
		this.rightLeg = this.oreia.getChild("rightLeg");
		this.leftArm = this.oreia.getChild("leftArm");
		this.rightArm = this.oreia.getChild("rightArm");
		this.body = this.oreia.getChild("body");
		this.head = this.oreia.getChild("head");
	}

	public static LayerDefinition createbodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition oreia = partdefinition.addOrReplaceChild("oreia", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftLeg = oreia.addOrReplaceChild("leftLeg",
				CubeListBuilder.create().texOffs(16, 48)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(1.9F, -12.0F, 0.0F, -0.1745F, 0.0F, -0.0349F));

		PartDefinition rightLeg = oreia.addOrReplaceChild("rightLeg",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(-1.9F, -12.0F, 0.0F, 0.192F, 0.0F, 0.0349F));

		PartDefinition leftArm = oreia.addOrReplaceChild("leftArm",
				CubeListBuilder.create().texOffs(32, 48)
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(5.0F, -22.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

		PartDefinition rightArm = oreia.addOrReplaceChild("rightArm",
				CubeListBuilder.create().texOffs(40, 16)
						.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(-5.0F, -22.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition body = oreia.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(16, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head = oreia.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw,
			float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netheadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.IDLE,limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((OreiaEntity) entity).idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float headYaw, float headPitch, float ageInTicks) {

		headYaw = Mth.clamp(headYaw, -30.0f, 30.0f);
		headPitch = Mth.clamp(headPitch, -25, 45);

		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = headYaw * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		oreia.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return oreia;
	}
}