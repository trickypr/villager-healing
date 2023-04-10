package com.trickypr.vilagerhealing.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.passive.VillagerEntity;

@Mixin(VillagerEntity.class)
public interface VillagerAccessor {
  @Accessor
  int getFoodLevel();

  @Invoker("getAvailableFood")
  int invokeGetAvailableFood();

  @Invoker("consumeAvailableFood")
  void invokeConsumeAvailableFood();

  @Invoker("depleteFood")
  void invokeDepleteFood(int amount);
}
