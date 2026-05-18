# Netherstar Furnace

An extreme end-game automation mod for Minecraft NeoForge 1.21.1. It adds massive Nether Star compression tiers and a definitive processing furnace integrated with Mekanism and AdvancedAE.

---

## Technical Dependencies

* NeoForge 1.21.1
* Mekanism
* AdvancedAE
* Applied Energistics 2

---

## Crafting Recipes

### Tier 1: Blocks Compression
* 1x Nether Star Block = 9x Nether Stars
* 1x Dense Nether Star Block = 9x Nether Star Blocks
* 1x Flawless Nether Star Block = 9x Dense Nether Star Blocks
* 1x Singular Nether Star Block = 9x Flawless Nether Star Blocks

### Tier 2: Quantum Singularity Gear
Crafted in a 3x3 Grid:
* Center: 1x Singular Nether Star Block
* Top/Bottom/Left/Right: 4x AdvancedAE Singularities
* Corners: 4x Mekanism Antimatter Pellets

### Final Tier: Legendary Netherstar Furnace
Crafted in a 3x3 Grid:
* Top Row: 3x Quantum Singularity Gears
* Center: 1x Ultimate Smelting Factory (Mekanism)
* Center Left & Right: 2x Antimatter Pellets (Mekanism)
* Bottom Row: 3x Quantum Processors (AdvancedAE)

---

## Total Raw Cost

To craft one single furnace, you will need exactly:
* 19,683x Nether Stars
* 12x AdvancedAE Singularities
* 14x Mekanism Antimatter Pellets
* 16x Ultimate Control Circuits
* 3x Quantum Processors
* 1x Ultimate Smelting Factory

---

## Personal Development Checklist

### Phase 1: Infrastructure and Base Setup
- [ ] Setup standard Gradle directory tree structure (NeoForge 1.21.1 MDK)
- [ ] Configure mods.toml with ID, metadata, and required dependencies
- [ ] Create main class (NetherstarFurnaceMod.java) and initialize logger
- [ ] Configure .gitignore to exclude build artifacts and IDE cache files

### Phase 2: Block Registration (Tier 1)
- [ ] Create ModBlocks.java registry class
- [ ] Register Nether Star Block
- [ ] Register Dense Nether Star Block
- [ ] Register Flawless Nether Star Block
- [ ] Register Singular Nether Star Block
- [ ] Create ModCreativeTabs.java to group blocks under a dedicated creative tab

### Phase 3: Item Registration & Assets (Tier 2)
- [ ] Create ModItems.java registry class
- [ ] Register Quantum Singularity Gear item
- [ ] Create en_us.json localization file for all items and blocks
- [ ] Create blockstate and models/block JSON files for all compression tiers
- [ ] Create models/item JSON files for all block items and gears

### Phase 4: Machine Block and Logic (Final Tier)
- [ ] Register Legendary Netherstar Furnace block in ModBlocks.java
- [ ] Create NetherstarFurnaceBlockEntity.java to manage data, inventory, and energy capabilities
- [ ] Configure hardness, blast resistance, light emissions, and correct tool requirements
- [ ] Create texture and model JSON assets for the machine block

### Phase 5: Recipe Systems via Data Generation (Datapack)
- [ ] Create reciprocal compression and decompression JSON recipes for all 4 block tiers
- [ ] Create Quantum Singularity Gear shaped recipe linking Mekanism and AdvancedAE tags
- [ ] Create Legendary Netherstar Furnace shaped recipe with the final 20k star calculation cost