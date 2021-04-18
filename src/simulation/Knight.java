package simulation;

public class Knight extends GameObject {

    public Knight(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(300);
        super.setMaxHp(300);
        super.setBaseDmg(20);
        super.setAttack(20);
        super.setDefence(40);
    }
}
