package simulation;

public class AxeMan extends GameObject {

    public AxeMan(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(300);
        super.setMaxHp(300);
        super.setBaseDmg(10);
        super.setAttack(60);
        super.setDefence(30);
    }
}
