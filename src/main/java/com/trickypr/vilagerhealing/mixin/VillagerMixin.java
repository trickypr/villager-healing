package com.trickypr.vilagerhealing.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.InteractionObserver;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerDataContainer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin extends MerchantEntity
    implements InteractionObserver,
    VillagerDataContainer {

  public VillagerMixin(EntityType<? extends VillagerEntity> entityType, World world) {
    super(entityType, world);
  }

  @Inject(at = @At("TAIL"), method = "wakeUp")
  private void wakeUp(CallbackInfo info) {
    VillagerEntity villager = (VillagerEntity) (Object) this;
    VillagerAccessor accessor = (VillagerAccessor) villager;

    if (villager.isDead())
      return;
    if (accessor.getFoodLevel() + accessor.invokeGetAvailableFood() <= 4)
      return;
    if (accessor.getFoodLevel() < 4)
      accessor.invokeConsumeAvailableFood();

    villager.heal(1.0f);
    accessor.invokeDepleteFood(4);
  }
}
