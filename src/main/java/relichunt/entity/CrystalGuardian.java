package relichunt.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import relichunt.RelicHunt;
import relichunt.init.EntityInit;
import relichunt.init.ItemInit;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CrystalGuardian extends PathfinderMob implements NeutralMob {
    private Item searchedItem = Items.DIAMOND;

    private Block[] angerBlocks = {Blocks.AMETHYST_BLOCK, Blocks.BUDDING_AMETHYST, Blocks.AMETHYST_CLUSTER, Blocks.SMALL_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.LARGE_AMETHYST_BUD};

    @Nullable
    private Player angerBlockBrokenBy = null;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Bee.class, EntityDataSerializers.INT);
    private UUID persistentAngerTarget;
    private Player lastInteractionPlayer = null;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private final SimpleContainer inventory = new SimpleContainer(1);
    public CrystalGuardian(EntityType<? extends CrystalGuardian> type, Level level) {
        super(type, level);
    }

    public CrystalGuardian(Level level, double x, double y, double z) {
        this(EntityInit.CRYSTAL_GUARDIAN.get(), level);
        setPos(x, y, z);
    }
    public CrystalGuardian(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Pig.createAttributes().add(Attributes.ATTACK_DAMAGE, 6.0f).add(Attributes.MAX_HEALTH, 40f);
    }

    public void setAngerBlockBrokenBy(Player newAngerBlockBrokenBy) {angerBlockBrokenBy = newAngerBlockBrokenBy;}

    public boolean IsAngerBlockBroken() {return getAngerBlockBrokenBy() != null;}

    public Player getAngerBlockBrokenBy() {return angerBlockBrokenBy;}

    public class EvaluateItemGoal extends LookAtPlayerGoal {

        private boolean onlyHorizontal;

        public EvaluateItemGoal(Mob p_25520_, Class<? extends LivingEntity> p_25521_, float p_25522_) {
            super(p_25520_, p_25521_, p_25522_, 1);
        }

        @Override
        public boolean canUse() {
            //System.out.println("Checking if can use!");
            if (!getInventory().isEmpty()) {
                //System.out.println("Checking if can use with Interaction!");
                return super.canUse();
            }
            //System.out.println("Cant use!");
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (!getInventory().isEmpty()) {
                //System.out.println("Checking if can continue to use with Interaction!");
                return super.canContinueToUse();
            }
            //System.out.println("Cant continue to use!");
            return false;
        }

        @Override
        public void stop() {
            //System.out.println("Stopped!");
            if (getInventory().getItem(0).getItem() == getSearchedItem()) {
                throwTowardsLastInteractionPlayer(ItemInit.ESSENCE_OF_SIGHT.get().getDefaultInstance());
                getInventory().clearContent();
            } else {
                throwOutInventory();
            }
            this.lookAt = null;
        }
    }

    public class AmethystDestroyedTargetGoal extends TargetGoal{
        public AmethystDestroyedTargetGoal(Mob p_26140_, boolean p_26141_) {
            super(p_26140_, p_26141_);
        }

        public AmethystDestroyedTargetGoal(Mob p_26143_, boolean p_26144_, boolean p_26145_) {
            super(p_26143_, p_26144_, p_26145_);
        }

        @Override
        public boolean canUse() {
            if (IsAngerBlockBroken()) {
                return true;
            }
            return false;
        }

        @Override
        public void start() {
            setTarget(getAngerBlockBrokenBy());
            this.targetMob = getAngerBlockBrokenBy();
            setAngerBlockBrokenBy(null);
            super.start();
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, (new CrystalGuardian.AmethystDestroyedTargetGoal(this, true, false)));
        this.goalSelector.addGoal(2, new CrystalGuardian.EvaluateItemGoal(this, Player.class, 500.0f));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8f));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public Item getSearchedItem() {return this.searchedItem;}

    public void setSearchedItem(Item newSearchedItem) {this.searchedItem = newSearchedItem;}

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int p_27795_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_27795_);
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_27791_) {
        this.persistentAngerTarget = p_27791_;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setLastInteractionPlayer(Player player) {
        lastInteractionPlayer = player;
    }

    public Player getLastInteractionPlayer() {
        return lastInteractionPlayer;
    }

    public boolean hasLastInteractionPlayer() {
        return !(lastInteractionPlayer == null);
    }

    public void clearLastInteractionPlayer() {
        lastInteractionPlayer = null;
    }

    @Override
    public void updatePersistentAnger(ServerLevel p_21667_, boolean p_21668_) {
        NeutralMob.super.updatePersistentAnger(p_21667_, p_21668_);
    }
    public static boolean canSpawn(EntityType<CrystalGuardian> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return Mob.checkMobSpawnRules(entityType, level, spawnType, position, random) && level.getBrightness(LightLayer.SKY, position) < 8 && level.getBlockState(position.below()).getBlock().equals(Blocks.AMETHYST_BLOCK);
    }

    @Override
    public void load(CompoundTag p_20259_) {
        System.out.println("Registered!");
        super.load(p_20259_);
        RelicHunt.registerEntityToForgeBus(this);
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        System.out.println(event.getPlayer());
        System.out.println(Player.class);
        if (event.getPlayer().getClass() == ServerPlayer.class) {
            if (Arrays.asList(this.angerBlocks).contains(level().getBlockState(event.getPos()).getBlock())) {
                setAngerBlockBrokenBy(event.getPlayer());
            }
        }
    }

    public InteractionResult mobInteract(Player interactionPlayer, InteractionHand interactionHand) {
        ItemStack itemstackPlayerInteractionHand = interactionPlayer.getItemInHand(interactionHand);
        System.out.println(this.getTarget());
        if (!itemstackPlayerInteractionHand.isEmpty() && this.getInventory().isEmpty() && this.getTarget() == null) {
            this.setInventoryItem(itemstackPlayerInteractionHand.copyWithCount(1));
            interactionPlayer.getItemInHand(interactionHand).shrink(1);
            setLastInteractionPlayer(interactionPlayer);
            return InteractionResult.SUCCESS;
        }
        if (this.level().isClientSide) {
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(interactionPlayer, interactionHand);
    }
    //For dropping a useless item
    private void throwOutInventory() {
        if (!getInventory().isEmpty() && !this.level().isClientSide) {
            throwTowardsLastInteractionPlayer(getInventory().getItem(0));
            this.getInventory().clearContent();
        }
    }
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public void setInventoryItem(ItemStack itemstack) {
        if (this.getInventory().isEmpty()) {
            this.getInventory().addItem(itemstack);
        }
    }

    public void throwTowardsLastInteractionPlayer(ItemStack itemStack) {
        ItemEntity itementity;
        if (hasLastInteractionPlayer()) {
            Vec3 thisToPlayerDirection = this.getPosition(0).vectorTo(getLastInteractionPlayer().getPosition(0)).normalize();
            itementity = new ItemEntity(this.level(), this.getX() + thisToPlayerDirection.x, this.getY() + 1.0D, this.getZ() + thisToPlayerDirection.z, itemStack);
        } else {
            itementity = new ItemEntity(this.level(), this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, itemStack);
        }
        itementity.setPickUpDelay(20);
        itementity.setThrower(this.getUUID());
        this.level().addFreshEntity(itementity);
    }

    @Override
    public void dropEquipment() {
        ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), getInventory().getItem(0));
        this.level().addFreshEntity(itementity);
        getInventory().clearContent();
    }
}
