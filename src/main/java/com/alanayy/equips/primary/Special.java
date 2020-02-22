package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import static com.alanayy.combat.StatusEffect.affectDefResP;

public class Special {

    public Special(SpecialName specialName, Unit unit, Combat combat) {
        Unit enemy = unit.getTargetEnemy();

        switch (specialName) {
            case AETHER:
                // CD = 5.
                unit.setSpecCharge(5);
                // Resolve combat as if foe suffered Def/Res-50%.
                affectDefResP(enemy, 0.5);
                // Unit recovers HP = half damage dealt.
                // TODO above :v
                break;
            case DRACONIC_AURA:
                // CD = 3.
                unit.setSpecCharge(3);
                // Boosts Atk by 30%.
                unit.setTempAtk((int) Math.round(unit.getTempAtk() * 1.3));
                break;
            case MOONBOW:
                // CD = 2.
                unit.setSpecCharge(2);
                // Resolve combat as if foe suffered Def/Res-30%.
                affectDefResP(enemy, 0.3);
                break;
            case BONFIRE:
                // CD = 3.
                unit.setSpecCharge(3);
                // Boosts damage dealt by 50% of unit's Def.
                unit.setTempAtk(unit.getTempAtk()
                        + (int) Math.round(unit.getTempDef() * 0.5));
                break;
            case ICEBERG:
                // CD = 3.
                unit.setSpecCharge(3);
                // Boosts damage dealt by 50% of unit's Res.
                unit.setTempAtk(unit.getTempAtk()
                        + (int) Math.round(unit.getTempRes() * 0.5));
                break;
            case GLIMMER:
                // CD = 2.
                unit.setSpecCharge(2);
                // Boosts damage dealt by 50%.
                unit.setTempAtk((int) (Math.round(unit.getTempAtk()) * 1.5));
                break;
        }

        unit.setTempSpecCharge(unit.getSpecCharge());
    }

    public enum SpecialName {
        AETHER, DRACONIC_AURA,
        MOONBOW, BONFIRE, ICEBERG, GLIMMER
    }
}
