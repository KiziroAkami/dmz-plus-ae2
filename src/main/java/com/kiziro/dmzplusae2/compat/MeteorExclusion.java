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

/**
 * Decides whether a meteorite may generate in a chunk, given DragonMineZ's
 * planned unique-structure sites. Pure and Minecraft-free so it is unit-testable.
 */
public final class MeteorExclusion {

    private MeteorExclusion() {}

    /**
     * @param chunkX       candidate meteorite chunk X
     * @param chunkZ       candidate meteorite chunk Z
     * @param siteChunks   planned DMZ structure sites as {@code [chunkX, chunkZ]} pairs
     * @param radiusChunks Chebyshev (square) exclusion radius; {@code <= 0} disables the veto
     * @return true when the meteorite must NOT generate here
     */
    public static boolean vetoed(int chunkX, int chunkZ, Iterable<int[]> siteChunks, int radiusChunks) {
        if (radiusChunks <= 0) {
            return false;
        }
        for (int[] site : siteChunks) {
            int dist = Math.max(Math.abs(chunkX - site[0]), Math.abs(chunkZ - site[1]));
            if (dist <= radiusChunks) {
                return true;
            }
        }
        return false;
    }
}
