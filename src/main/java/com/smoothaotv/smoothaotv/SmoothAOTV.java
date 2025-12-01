package com.smoothaotv.smoothaotv;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "smoothaotv", name = "Smooth AOTV", version = "1.0", clientSideOnly = true)
public class SmoothAOTV {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SmoothAOTVHandler());
    }
}
