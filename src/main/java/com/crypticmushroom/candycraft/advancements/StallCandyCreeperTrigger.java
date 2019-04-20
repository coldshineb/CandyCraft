package com.crypticmushroom.candycraft.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class StallCandyCreeperTrigger implements ICriterionTrigger<StallCandyCreeperTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("stop_candy_creeper");
    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<StallCandyCreeperTrigger.Instance> listener) {
        StallCandyCreeperTrigger.Listeners listeners = this.listeners.computeIfAbsent(playerAdvancementsIn, Listeners::new);
        listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<StallCandyCreeperTrigger.Instance> listener) {
        StallCandyCreeperTrigger.Listeners tameCandyWolfTrigger = this.listeners.get(playerAdvancementsIn);

        if (tameCandyWolfTrigger != null) {
            tameCandyWolfTrigger.remove(listener);

            if (tameCandyWolfTrigger.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }

    @Override
    public StallCandyCreeperTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new StallCandyCreeperTrigger.Instance();
    }

    public void trigger(EntityPlayerMP player) {
        StallCandyCreeperTrigger.Listeners listeners = this.listeners.get(player.getAdvancements());
        if (listeners != null) {
            listeners.trigger();
        }
    }

    public static class Instance extends AbstractCriterionInstance {

        public Instance() {
            super(StallCandyCreeperTrigger.ID);
        }
    }

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(Listener<StallCandyCreeperTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(Listener<StallCandyCreeperTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger() {
            for (Listener<StallCandyCreeperTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
                listener.grantCriterion(this.playerAdvancements);
            }
        }
    }
}
