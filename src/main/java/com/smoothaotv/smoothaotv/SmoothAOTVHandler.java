package com.smoothaotv.smoothaotv;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

public class SmoothAOTVHandler {

    private Vec3 lastPos = null;
    private Vec3 targetPos = null;
    private long animStart = 0L;
    private long animDuration = 150L; // milliseconds

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        Vec3 current = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);

        if (lastPos == null) {
            lastPos = current;
            return;
        }

        double distSq = current.squareDistanceTo(lastPos);
        if (distSq > 9.0D) { // teleport detected if moved more than 3 blocks
            targetPos = current;
            animStart = System.currentTimeMillis();
        }

        lastPos = current;
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.START || targetPos == null) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;

        long now = System.currentTimeMillis();
        float t = (now - animStart) / (float) animDuration;

        if (t >= 1.0F) {
            targetPos = null;
            return;
        }

        t = Math.min(1.0F, Math.max(0.0F, t));

        double x = lerp(lastPos.xCoord, targetPos.xCoord, t);
        double y = lerp(lastPos.yCoord, targetPos.yCoord, t);
        double z = lerp(lastPos.zCoord, targetPos.zCoord, t);

        double dx = x - mc.thePlayer.posX;
        double dy = y - mc.thePlayer.posY;
        double dz = z - mc.thePlayer.posZ;

        // Removed private 'orientCamera' call to fix compilation error

        GL11.glTranslated(dx, dy, dz);
    }

    private double lerp(double a, double b, float t) {
        return a + (b - a) * t;
    }
}
