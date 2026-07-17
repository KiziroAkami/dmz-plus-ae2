/*
 * DMZ Plus AE2 — a DragonMineZ x Applied Energistics 2 compatibility mod.
 * Copyright (C) 2026 Kiziro Akami
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.kiziro.dmzplusae2.mixin;

import appeng.worldgen.meteorite.MeteoriteStructure;
import com.kiziro.dmzplusae2.compat.DmzPlanLookup;
import com.kiziro.dmzplusae2.compat.MeteorExclusion;
import com.kiziro.dmzplusae2.config.DMZPlusAE2Config;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Vetoes AE2 meteorite generation near DragonMineZ's planned unique structures,
 * so a crater can never carve through a one-of-a-kind landmark.
 *
 * <p>{@code findGenerationPoint} overrides vanilla {@link Structure}, so it is
 * SRG-named in the shipped AE2 jar and this mixin uses the normal remap/refmap
 * path (unlike DMZ-targeted mixins in dmz-plus, which need {@code remap = false}).
 * Cancelling with {@code Optional.empty()} is exactly how the structure itself
 * reports "no valid spot here" — safe at any generation stage, including pre-gen.
 */
@Mixin(MeteoriteStructure.class)
public abstract class MeteoriteStructureMixin {

    @Unique
    private static final Logger dmzplusae2$LOGGER = LogUtils.getLogger();

    @Inject(method = "findGenerationPoint", at = @At("HEAD"), cancellable = true)
    private void dmzplusae2$vetoNearDmzStructures(Structure.GenerationContext context,
            CallbackInfoReturnable<Optional<Structure.GenerationStub>> cir) {
        int radius = DMZPlusAE2Config.exclusionChunks();
        if (radius <= 0) {
            return;
        }
        ChunkPos chunk = context.chunkPos();
        if (MeteorExclusion.vetoed(chunk.x, chunk.z,
                DmzPlanLookup.plannedSiteChunks(context.chunkGenerator()), radius)) {
            dmzplusae2$LOGGER.debug("[dmzplusae2] vetoed meteorite at chunk {},{} "
                    + "(within {} chunks of a DMZ structure site)", chunk.x, chunk.z, radius);
            cir.setReturnValue(Optional.empty());
        }
    }
}
