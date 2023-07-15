package relichunt.events;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.UnmodifiableView;
import relichunt.RelicHunt;
import relichunt.entity.CrystalGuardian;
import relichunt.init.EntityInit;

@Mod.EventBusSubscriber(modid = RelicHunt.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.CRYSTAL_GUARDIAN.get(), CrystalGuardian.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(
                EntityInit.CRYSTAL_GUARDIAN.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE_WG,
                CrystalGuardian::canSpawn,
                SpawnPlacementRegisterEvent.Operation.OR);
    }
}