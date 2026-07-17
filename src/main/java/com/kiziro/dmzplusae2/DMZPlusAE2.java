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
package com.kiziro.dmzplusae2;

import com.kiziro.dmzplusae2.config.DMZPlusAE2Config;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

/**
 * Compatibility bridge between DragonMineZ and Applied Energistics 2.
 * Feature 1: AE2 meteorites are vetoed near DragonMineZ's planned unique
 * structures (see {@code MeteoriteStructureMixin}) so craters can never
 * destroy one-of-a-kind landmarks.
 */
@Mod(DMZPlusAE2.MODID)
public class DMZPlusAE2 {

    public static final String MODID = "dmzplusae2";

    public DMZPlusAE2() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DMZPlusAE2Config.SPEC);
    }
}
