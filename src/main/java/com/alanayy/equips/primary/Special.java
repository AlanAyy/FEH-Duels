package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class Special {

    public Special(SpecialName specialName, Unit unit, Combat combat) {
        switch (specialName) {
            // TODO Make specials.
        }
    }

    public enum SpecialName {
        AETHER, DRACONIC_AURA,
        MOONBOW, BONFIRE, ICEBERG, GLIMMER
    }
}
