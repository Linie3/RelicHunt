package relichunt.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import relichunt.RelicHunt;
import relichunt.client.model.CrystalGuardianModel;
import relichunt.client.renderer.CrystalGuardianRenderer;
import relichunt.init.EntityInit;

@Mod.EventBusSubscriber(modid = RelicHunt.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.CRYSTAL_GUARDIAN.get(), CrystalGuardianRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CrystalGuardianModel.LAYER_LOCATION, CrystalGuardianModel::createBodyLayer);
    }
}
