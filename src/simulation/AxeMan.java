package simulation;

import java.util.ArrayList;

public class AxeMan extends GameObject {

    public AxeMan(int x, int y, ID id) {
        super(x, y, id);
        super.setHp(300);
        super.setMaxHp(300);
        super.setBaseDmg(10);
        super.setAttack(60);
        super.setDefence(30);
    }

    public AxeMan() {
    }

    public ArrayList<GameObject> createAllyObjects(int startingX, int finalX, int startingY, int finalY) {
        int rowNumber = finalX - startingX + 1;
        int columnNumber = finalY - startingY + 1;
        int number = rowNumber * columnNumber;

        ArrayList<GameObject> warriors = new ArrayList<>(number);

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                warriors.add(new AxeMan(x, y, ID.Ally));
            }
        }
        return warriors;
    }

    public ArrayList<GameObject> createEnemyObjects(int startingX, int finalX, int startingY, int finalY) {
        int rowNumber = finalX - startingX + 1;
        int columnNumber = finalY - startingY + 1;
        int number = rowNumber * columnNumber;

        ArrayList<GameObject> warriors = new ArrayList<>(number);

        for (int x = startingX; x <= finalX; x++) {
            for (int y = startingY; y <= finalY; y++) {
                warriors.add(new AxeMan(x, y, ID.Enemy));
            }
        }
        return warriors;
    }
}
