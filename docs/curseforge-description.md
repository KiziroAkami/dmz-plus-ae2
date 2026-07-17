# DMZ Plus AE2

DMZ Plus AE2 is a compatibility bridge between [DragonMineZ](https://www.curseforge.com/minecraft/mc-mods/dragonminez) and [Applied Energistics 2](https://www.curseforge.com/minecraft/mc-mods/applied-energistics-2), from the [DMZ Plus](https://www.curseforge.com/minecraft/mc-mods/dragon-mine-z-plus) family.

Both mods are great neighbors — until an AE2 meteorite lands on Goku's House. This mod keeps the peace.

***

## Meteorite exclusion

DragonMineZ places its one-of-a-kind structures (Goku's House, the Cell Arena, Babidi's Ship, Kame House and friends) at planned sites in your world. AE2 meteorites generate independently and can crater straight through them — permanently, since these structures never spawn again.

*   **Protected landmarks** — meteorites are vetoed within a configurable radius (`compat.exclusionChunks`, default 4 chunks) of every DragonMineZ planned structure site.
*   **Nothing else changes** — meteorite frequency, loot, certus, and presses are untouched everywhere else; the veto only moves meteors politely out of the blast zone.
*   **Zero setup** — reads DragonMineZ's own structure plan at runtime. Works with any world seed and any DragonMineZ structure-distance configuration, including pre-generated worlds (as long as the mod is installed before chunks generate).

## Requirements

*   Minecraft 1.20.1 (Forge 47+)
*   DragonMineZ 2.1.1+
*   Applied Energistics 2 15.x

More DragonMineZ × AE2 integration is planned — suggestions welcome on the [issue tracker](https://github.com/KiziroAkami/dmz-plus-ae2/issues).

Open source under GPL-3.0-or-later: [github.com/KiziroAkami/dmz-plus-ae2](https://github.com/KiziroAkami/dmz-plus-ae2)
