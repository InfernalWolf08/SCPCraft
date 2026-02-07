package com.github.InfernalWolf.client;

import com.github.InfernalWolf.entity.ModEntities;
import com.github.InfernalWolf.entity.client.ModModelLayers;
import com.github.InfernalWolf.entity.client.SCP173Model;
import com.github.InfernalWolf.entity.client.SCP173Renderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ScpcraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Entity Initalization
            // SCP-173
            EntityRendererRegistry.register(ModEntities.SCP173, SCP173Renderer::new);
            EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SCP173, SCP173Model::getTexturedModelData);
    }
}