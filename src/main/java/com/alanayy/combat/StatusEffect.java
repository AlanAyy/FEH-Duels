package com.alanayy.combat;

import com.alanayy.units.Unit;

public class StatusEffect {

    /*
     * ------------
     * |  LEGEND  |
     * ------------
     * T  = Target
     * 2S = Two Spaces
     */

    /**
     * --------------------
     * |  Combat Effects  |
     * --------------------
     */

    public static void affectDefRes(Unit unit, int def, int res) {
        unit.setTempDef(unit.getTempDef() + def);
        unit.setTempRes(unit.getTempRes() + res);
    }

    /**
     * -----------------
     * |  TNA Effects  |  (TNA = Through Next Action)
     * -----------------
     */

    public static void panicT2S(Unit unit) {

    }

    /**
     * -------------------
     * |  Other Effects  |
     * -------------------
     */

    public static void dealDamageT2S() {

    }
}
