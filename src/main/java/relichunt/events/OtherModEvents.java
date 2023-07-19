package relichunt.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import relichunt.RelicHunt;
import relichunt.init.ItemInit;

import java.util.List;

@Mod.EventBusSubscriber(modid = RelicHunt.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OtherModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            int maxUses = 1;
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.CHAINMAIL_BOOTS, 1),
                    new ItemStack(ItemInit.ESSENCE_OF_TOUGHNESS.get()),
                    maxUses,
                    6,
                    0.06f
            ));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.CHAINMAIL_HELMET, 1),
                    new ItemStack(ItemInit.ESSENCE_OF_TOUGHNESS.get()),
                    maxUses,
                    6,
                    0.06f
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.CHAINMAIL_LEGGINGS, 1),
                    new ItemStack(ItemInit.ESSENCE_OF_TOUGHNESS.get()),
                    maxUses,
                    6,
                    0.06f
            ));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1),
                    new ItemStack(ItemInit.ESSENCE_OF_TOUGHNESS.get()),
                    maxUses,
                    6,
                    0.06f
            ));
        }

        if (event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.GOLDEN_APPLE, 2),
                    new ItemStack(ItemInit.ESSENCE_OF_VITALITY.get()),
                    2,
                    6,
                    0.06f
            ));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                    new ItemStack(ItemInit.ESSENCE_OF_VITALITY.get(), 4),
                    1,
                    20,
                    0.06f
            ));
        }
    }
}
