package com.example.examplemod.entity.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.OreiaEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OreiaRenderer extends MobRenderer<OreiaEntity, OreiaModel<OreiaEntity>> {

    public OreiaRenderer(EntityRendererProvider.Context context) {
        super(context, new OreiaModel(context.bakeLayer(ModModelLayers.OREIA_LAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(OreiaEntity entity) {
        return new ResourceLocation(ExampleMod.MODID, "textures/entity/oreia.png");
    }

    @Override
    public void render(OreiaEntity p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_,
            MultiBufferSource p_115459_, int p_115460_) {
        if (p_115455_.isBaby()) {
            p_115458_.scale(0.5F, 0.5F, 0.5F);

        }
        super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
    }
}
