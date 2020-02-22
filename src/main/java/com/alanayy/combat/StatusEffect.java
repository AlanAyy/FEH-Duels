package com.alanayy.combat;

import com.alanayy.units.Unit;

public class StatusEffect {

    /*
     * ------------
     * |  LEGEND  |
     * ------------
     * U    =  Affects Unit           (unit)
     * E    =  Affects Enemy/Enemies  (unit.getEnemy())
     * P    =  By Percentage          (0.00 -> 1.00)
     * TNA  =  Through Next Action    (N/A)
     * EOT  =  Until End of Turn      (CombatState.END_OF_TURN)
     * 2S   =  Two Spaces             (unit.getTwoSpaceAllies())
     */

    /**
     * --------------------
     * |  Combat Effects  |
     * --------------------
     */

    public static void affectAtkSpd(Unit unit, int val) {
        unit.setTempAtk(unit.getTempAtk() + val);
        unit.setTempSpd(unit.getTempSpd() + val);
    }

    public static void affectDefRes(Unit unit, int val) {
        unit.setTempDef(unit.getTempDef() + val);
        unit.setTempRes(unit.getTempRes() + val);
    }

    public static void affectDefResP(Unit unit, double pct) {
        unit.setTempDef((int) Math.round(unit.getTempDef() * pct));
        unit.setTempRes((int) Math.round(unit.getTempRes() * pct));
    }

    public static void adaptiveDmgIfERangeIs2(Unit unit) {
        Unit enemy = unit.getTargetEnemy();
        if (unit.getTwoSpaceFoes().contains(enemy)
                && !unit.getAdjacentFoes().contains(enemy)) {
            unit.setAdaptiveDmg(true);
        }
    }

    /**
     * -----------------
     * |  TNA Effects  |
     * -----------------
     */

    public static void affectDefResTNA(Unit unit, int val) {
        // TODO TNA.
        unit.setTempDef(unit.getTempDef() + val);
        unit.setTempRes(unit.getTempRes() + val);
    }

    public static void chillAtkETNA(Unit unit, int val) {
        int counter = 0;
        int topAtk = 0;
        int topAtkUnit = 0;
        for (Unit enemy : unit.getEnemyTeam()) {
            if (enemy.getTempAtk() > topAtk) {
                topAtk = enemy.getTempAtk();
                topAtkUnit = counter;
            }
            counter++;
        }
        // Gotta go through it again to change the actual Unit's value,
        // instead of a stored value.
        for (Unit enemy : unit.getEnemyTeam()) {
            if (topAtkUnit == counter) {
                enemy.setTempAtk(enemy.getTempAtk() - val);
                break;
            }
        }
    }

    public static void chillResETNA(Unit unit, int val) {
        int counter = 0;
        int topRes = 0;
        int topResUnit = 0;
        for (Unit enemy : unit.getEnemyTeam()) {
            if (enemy.getTempRes() > topRes) {
                topRes = enemy.getTempRes();
                topResUnit = counter;
            }
            counter++;
        }
        // Gotta go through it again to change the actual Unit's value,
        // instead of a stored value.
        for (Unit enemy : unit.getEnemyTeam()) {
            if (topResUnit == counter) {
                enemy.setTempRes(enemy.getTempRes() - val);
                break;
            }
        }
    }

    public static void panicTNA(Unit unit) {
        int diff;

        diff = unit.getTempAtk() - unit.getAtk();
        if (diff > 0)
            unit.setTempAtk(unit.getTempAtk() - (diff * 2));

        diff = unit.getTempSpd() - unit.getSpd();
        if (diff > 0)
            unit.setTempSpd(unit.getSpd() - (diff * 2));

        diff = unit.getTempDef() - unit.getDef();
        if (diff > 0)
            unit.setTempDef(unit.getTempDef() - (diff * 2));

        diff = unit.getTempRes() - unit.getRes();
        if (diff > 0)
            unit.setTempRes(unit.getTempRes() - (diff * 2));
    }

    /**
     * -----------------------
     * |  Turn-long Effects  |
     * -----------------------
     */

    public static void affectAtkDefEOT(Unit unit, int val) {
        unit.setTempAtk(unit.getTempAtk() + val);
        unit.setTempDef(unit.getTempDef() + val);
    }

    public static void affectDefResEOT(Unit unit, int val) {
        unit.setTempDef(unit.getTempDef() + val);
        unit.setTempRes(unit.getTempRes() + val);
    }

    /**
     * -----------------------
     * |  Permanent Effects  |
     * -----------------------
     */

    public static void affectHp(Unit unit, int damage) {
        unit.setTempHp(unit.getTempHp() + damage);
        if (unit.getTempHp() <= 0) {
            unit.setTempHp(1);
        }
    }
}
