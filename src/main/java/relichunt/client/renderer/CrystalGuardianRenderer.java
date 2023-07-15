package relichunt.client.renderer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import relichunt.RelicHunt;
import relichunt.client.model.CrystalGuardianModel;
import relichunt.entity.CrystalGuardian;

public class CrystalGuardianRenderer extends MobRenderer<CrystalGuardian, CrystalGuardianModel<CrystalGuardian>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(RelicHunt.MODID, "textures/entity/crystal_guardian.png");
    public CrystalGuardianRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new CrystalGuardianModel<>(ctx.bakeLayer(CrystalGuardianModel.LAYER_LOCATION)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(CrystalGuardian entity) {
        return TEXTURE;
    }
}
