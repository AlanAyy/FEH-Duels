package com.alanayy.units;

import com.alanayy.equips.primary.Weapon;
import com.alanayy.equips.secondary.passives.PassiveA;
import com.alanayy.equips.secondary.passives.PassiveB;
import com.alanayy.equips.secondary.passives.PassiveC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Unit {

    private int id;
    private String name, color, race, type;
    private boolean isAttacking, isAttacked, canAttackTwice, canAdaptiveDmg,
            canCloseCounter, canFarCounter, canFollowUp, hasCounterPriority;
    private int lvl, hp, atk, spd, def, res, movement;
    private int tempHp, tempAtk, tempSpd, tempDef, tempRes, tempMovement;
    private Weapon weapon;
    private ArrayList<Object> passiveList;
    private int specCharge, tempSpecCharge;
    private ArrayList<Unit> adjacentAllies, twoSpaceAllies, adjacentFoes, twoSpaceFoes,
            cardinalAllies, cardinalFoes;
    private Unit ally1, ally2, ally3, enemy;

    public Unit(String name, String color, String race, String type, int lvl, int hp,
                int atk, int spd, int def, int res) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.color = color;
        this.race = race;
        this.type = type;
        this.lvl = lvl;
        this.hp = hp;
        this.atk = atk;
        this.spd = spd;
        this.def = def;
        this.res = res;
    }

    public Unit(String name, String color, String race, String type, String lvl, String hp,
                String atk, String spd, String def, String res) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
        this.color = color;
        this.race = race;
        this.type = type;
        this.lvl = Integer.parseInt(lvl);
        this.hp = Integer.parseInt(hp);
        this.atk = Integer.parseInt(atk);
        this.spd = Integer.parseInt(spd);
        this.def = Integer.parseInt(def);
        this.res = Integer.parseInt(res);
    }

    /**
     * ---------------
     * |  Unit Info  |
     * ---------------
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public String getColor() {
        return color;
    }

    public String getRace() {
        return race;
    }

    public String getType() {
        return type;
    }


    /**
     * ---------------------
     * |  Permanent Stats  |
     * ---------------------
     */

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    /**
     * ---------------------
     * |  Temporary Stats  |
     * ---------------------
     */

    public int getTempHp() {
        return tempHp;
    }

    public void setTempHp(int tempHp) {
        this.tempHp = tempHp;
    }

    public int getTempAtk() {
        return tempAtk;
    }

    public void setTempAtk(int tempAtk) {
        this.tempAtk = tempAtk;
    }

    public int getTempSpd() {
        return tempSpd;
    }

    public void setTempSpd(int tempSpd) {
        this.tempSpd = tempSpd;
    }

    public int getTempDef() {
        return tempDef;
    }

    public void setTempDef(int tempDef) {
        this.tempDef = tempDef;
    }

    public int getTempRes() {
        return tempRes;
    }

    public void setTempRes(int tempRes) {
        this.tempRes = tempRes;
    }

    public int getTempMovement() {
        return tempMovement;
    }

    public void setTempMovement(int tempMovement) {
        this.tempMovement = tempMovement;
    }

    /**
     * ------------
     * |  Skills  |
     * ------------
     */

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Object> getPassives() {
        return passiveList;
    }

    public void setPassives(PassiveA.PassiveListA a, PassiveB.PassiveListB b, PassiveC.PassiveListC c) {
        passiveList = new ArrayList<>(Arrays.asList(a, b, c));
    }

    public int getSpecCharge() {
        return specCharge;
    }

    public void setSpecCharge(int specCharge) {
        this.specCharge = specCharge;
    }

    public int getTempSpecCharge() {
        return tempSpecCharge;
    }

    public void setTempSpecCharge(int tempSpecCharge) {
        this.tempSpecCharge = tempSpecCharge;
    }

    /**
     * -------------------
     * |  Combat Values  |
     * -------------------
     */

    public boolean isAttacking() {
        return isAttacking;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public boolean canAttackTwice() {
        return canAttackTwice;
    }

    public void setAttackTwice(boolean canAttackTwice) {
        this.canAttackTwice = canAttackTwice;
    }

    public boolean canAdaptiveDmg() {
        return canAdaptiveDmg;
    }

    public void setAdaptiveDmg(boolean canAdaptiveDmg) {
        this.canAdaptiveDmg = canAdaptiveDmg;
    }

    public boolean canCloseCounter() {
        return canCloseCounter;
    }

    public void setCloseCounter(boolean canCloseCounter) {
        this.canCloseCounter = canCloseCounter;
    }

    public boolean canFarCounter() {
        return canFarCounter;
    }

    public void setFarCounter(boolean canFarCounter) {
        this.canFarCounter = canFarCounter;
    }

    public boolean canFollowUp() {
        return canFollowUp;
    }

    public void setFollowUp(boolean canFollowUp) {
        this.canFollowUp = canFollowUp;
    }

    public boolean hasCounterPriority() {
        return hasCounterPriority;
    }

    public void setCounterPriority(boolean hasCounterPriority) {
        this.hasCounterPriority = hasCounterPriority;
    }

    /**
     * -----------------------
     * |  Other Unit Values  |
     * -----------------------
     */

    public List<Unit> getTeammates() {
        return Arrays.asList(ally1, ally2, ally3);
    }

    public void setTeammates(Unit ally1, Unit ally2, Unit ally3) {
        this.ally1 = ally1;
        this.ally2 = ally2;
        this.ally3 = ally3;
    }

    public Unit getEnemy() {
        return enemy;
    }

    public void setEnemy(Unit enemy) {
        this.enemy = enemy;
    }

    public ArrayList<Unit> getAdjacentAllies() {
        return adjacentAllies;
    }

    public void setAdjacentAllies(Unit... units) {
        adjacentAllies = (ArrayList<Unit>) Arrays.asList(units);
    }

    public ArrayList<Unit> getTwoSpaceAllies() {
        return twoSpaceAllies;
    }

    public void setTwoSpaceAllies(Unit... units) {
        twoSpaceAllies = (ArrayList<Unit>) Arrays.asList(units);
    }

    public ArrayList<Unit> getAdjacentFoes() {
        return adjacentFoes;
    }

    public void setAdjacentFoes(Unit... units) {
        adjacentFoes = (ArrayList<Unit>) Arrays.asList(units);
    }

    public ArrayList<Unit> getTwoSpaceFoes() {
        return twoSpaceFoes;
    }

    public void setTwoSpaceFoes(Unit... units) {
        twoSpaceFoes = (ArrayList<Unit>) Arrays.asList(units);
    }

    public ArrayList<Unit> getCardinalAllies() {
        return cardinalAllies;
    }

    public void setCardinalAllies(Unit... units) {
        cardinalAllies = (ArrayList<Unit>) Arrays.asList(units);
    }

    public ArrayList<Unit> getCardinalFoes() {
        return cardinalFoes;
    }

    public void setCardinalFoes(Unit... units) {
        cardinalFoes = (ArrayList<Unit>) Arrays.asList(units);
    }
}