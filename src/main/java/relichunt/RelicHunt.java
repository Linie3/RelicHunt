package relichunt;

import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import relichunt.entity.CrystalGuardian;
import relichunt.init.BlockInit;
import relichunt.init.EntityInit;
import relichunt.init.ItemInit;

@Mod(RelicHunt.MODID)
public class RelicHunt {
    public static final String MODID = "relichunt";

    public RelicHunt() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //MinecraftForge.EVENT_BUS.register(CrystalGuardian.class);

        EntityInit.ENTITIES.register(bus);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
    }

    public static void registerEntityToForgeBus(PathfinderMob mob) {
        MinecraftForge.EVENT_BUS.register(mob);
    }
}
