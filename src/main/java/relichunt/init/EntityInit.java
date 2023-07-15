package relichunt.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import relichunt.RelicHunt;
import relichunt.entity.CrystalGuardian;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RelicHunt.MODID);

    public static final RegistryObject<EntityType<CrystalGuardian>> CRYSTAL_GUARDIAN = ENTITIES.register("crystal_guardian",
            () -> EntityType.Builder.<CrystalGuardian>of(CrystalGuardian::new, MobCategory.MONSTER)
                    .fireImmune()
                    .sized(0.8f, 0.8f)
                    .build(new ResourceLocation(RelicHunt.MODID, "crystal_guardian").toString())
    );
}
