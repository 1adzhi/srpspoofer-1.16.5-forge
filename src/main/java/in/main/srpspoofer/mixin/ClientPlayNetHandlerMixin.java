package in.main.srpspoofer.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.network.play.client.CResourcePackStatusPacket;
import net.minecraft.network.play.server.SSendResourcePackPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetHandler.class)
public class ClientPlayNetHandlerMixin {
    @Shadow private Minecraft minecraft;
    
    @Unique
    private static final Logger LOGGER = LogManager.getLogger("SRPSpoofer");

    @Inject(method = "handleResourcePack(Lnet/minecraft/network/play/server/SSendResourcePackPacket;)V", at = @At("HEAD"), cancellable = true)
    private void onResourcePack(SSendResourcePackPacket packet, CallbackInfo ci) {
        try {
            ci.cancel();
            ClientPlayNetHandler connection = (ClientPlayNetHandler) (Object) this;
            connection.send(new CResourcePackStatusPacket(CResourcePackStatusPacket.Action.ACCEPTED));
            connection.send(new CResourcePackStatusPacket(CResourcePackStatusPacket.Action.SUCCESSFULLY_LOADED));
        } catch (Exception e) {
            LOGGER.error("[SRPSpoofer] Error: {}", e.getMessage());
        }
    }
} 