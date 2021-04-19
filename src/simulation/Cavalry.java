package simulation;

import java.util.ArrayList;

public class Cavalry extends GameObject {

    public Cavalry(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(100);
        super.setMaxHp(100);
    }

    @Override
    public void attack(ArrayList<GameObject> warriors, GameObject attackedObject) {

        int attackValue = 0;

        if (attackedObject instanceof Knight) attackValue = 90;
        if (attackedObject instanceof AxeMan) attackValue = 50;
        if (attackedObject instanceof Archer) attackValue = 150;
        if (attackedObject instanceof Cavalry) attackValue = 40;

        if (getStatus() == Status.Charged) {
            attackValue *= 2;
        }

        attackedObject.setHp(attackedObject.getHp() - attackValue);

    }
}
