package units.bricks;


import units.bonuses.Bonus;

public interface BonusHolder {

    Bonus getBonus();

    void addBonus(String BonusType);
}
