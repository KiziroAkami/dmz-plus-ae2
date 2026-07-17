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
package com.kiziro.dmzplusae2.compat;

import com.dragonminez.server.world.structure.placement.StructureSpawnPlanner;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Bridges structure generation (which only sees a {@link ChunkGenerator}) to
 * DragonMineZ's per-level structure plan. The owning {@link ServerLevel} is found
 * by generator identity; DMZ publishes the overworld plan synchronously at world
 * load, before any chunk generates, so it is available during play and pre-gen.
 */
public final class DmzPlanLookup {

    private DmzPlanLookup() {}

    /** Planned DMZ unique-structure sites as {@code [chunkX, chunkZ]}; empty when unknown. */
    public static List<int[]> plannedSiteChunks(ChunkGenerator generator) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) {
            return List.of();
        }
        for (ServerLevel level : server.getAllLevels()) {
            if (level.getChunkSource().getGenerator() == generator) {
                Map<Integer, ChunkPos> positions = StructureSpawnPlanner.publishedPositions(level);
                if (positions.isEmpty()) {
                    return List.of();
                }
                List<int[]> sites = new ArrayList<>(positions.size());
                for (ChunkPos pos : positions.values()) {
                    sites.add(new int[]{pos.x, pos.z});
                }
                return sites;
            }
        }
        return List.of();
    }
}
