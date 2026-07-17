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
package com.kiziro.dmzplusae2.config;

import net.minecraftforge.common.ForgeConfigSpec;

/** Global per-instance config — lives at config/dmzplusae2-common.toml. */
public final class DMZPlusAE2Config {

    private DMZPlusAE2Config() {}

    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue EXCLUSION_CHUNKS;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();
        b.push("compat");
        EXCLUSION_CHUNKS = b
                .comment("Veto AE2 meteorite generation within this many chunks (Chebyshev / square "
                        + "radius) of any DragonMineZ planned unique structure site, so craters can "
                        + "never destroy them. Applies while chunks generate — existing chunks are "
                        + "unaffected. 0 = disabled.")
                .defineInRange("exclusionChunks", 4, 0, 32);
        b.pop();

        SPEC = b.build();
    }

    /** Chebyshev chunk radius around DMZ sites where meteorites are vetoed (0 = off). */
    public static int exclusionChunks() {
        return EXCLUSION_CHUNKS.get();
    }
}
