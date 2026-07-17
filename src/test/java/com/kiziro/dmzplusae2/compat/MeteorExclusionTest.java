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

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MeteorExclusionTest {

    private static final List<int[]> SITE_AT_ORIGIN = List.of(new int[]{0, 0});

    @Test
    void chunkInsideRadiusIsVetoed() {
        assertTrue(MeteorExclusion.vetoed(4, 0, SITE_AT_ORIGIN, 4));
    }

    @Test
    void diagonalCornerCountsAsInside() {
        // Chebyshev distance, not Euclidean: (4,4) is exactly 4 chunks away.
        assertTrue(MeteorExclusion.vetoed(4, 4, SITE_AT_ORIGIN, 4));
    }

    @Test
    void chunkJustOutsideRadiusIsAllowed() {
        assertFalse(MeteorExclusion.vetoed(5, 0, SITE_AT_ORIGIN, 4));
    }

    @Test
    void zeroRadiusDisablesTheVeto() {
        // Even the site's own chunk is allowed when the radius is 0 (feature off).
        assertFalse(MeteorExclusion.vetoed(0, 0, SITE_AT_ORIGIN, 0));
    }

    @Test
    void negativeRadiusDisablesTheVeto() {
        assertFalse(MeteorExclusion.vetoed(0, 0, SITE_AT_ORIGIN, -1));
    }

    @Test
    void noSitesMeansNoVeto() {
        assertFalse(MeteorExclusion.vetoed(0, 0, List.of(), 32));
    }

    @Test
    void anyOfSeveralSitesCanVeto() {
        List<int[]> sites = List.of(new int[]{100, 100}, new int[]{-200, 50});
        assertTrue(MeteorExclusion.vetoed(-198, 47, sites, 4));
        assertFalse(MeteorExclusion.vetoed(0, 0, sites, 4));
    }
}
