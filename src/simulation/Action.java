package simulation;

import java.util.ArrayList;

public class Action {

    private ArrayList<ArrayList<GameObject>> field = new ArrayList<>(0);
    ;

    public void setEmptyField(int size) {

        for (int i = 0; i < size; i++) {
            field.add(new ArrayList<>(size));
        }

        GameObject ground = new GameObject(ID.Ground);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field.get(i).add(j, ground);
            }
        }
    }

    public ArrayList<ArrayList<GameObject>> getField() {
        return field;
    }

    public ArrayList<GameObject> createObjects(ID side, String type, int startingX, int finalX, int startingY, int finalY) {

        ArrayList<GameObject> warriors = new ArrayList<>(0);

        if (type.equals("knight") || type.equals("axeman") || type.equals("archer") || type.equals("cavalry")) {

            if (startingX > finalX) {
                int buff;
                buff = finalX;
                finalX = startingX;
                startingX = buff;
            }

            if (startingY > finalY) {
                int buff;
                buff = finalY;
                finalY = startingY;
                startingY = buff;
            }

            for (int x = startingX; x <= finalX; x++) {
                for (int y = startingY; y <= finalY; y++) {
                    if (type.equals("knight")) warriors.add(new Knight(x, y, side));
                    if (type.equals("axeman")) warriors.add(new AxeMan(x, y, side));
                    if (type.equals("archer")) warriors.add(new Archer(x, y, side));
                    if (type.equals("cavalry")) warriors.add(new Cavalry(x, y, side));
                }
            }
        } else {
            System.out.println("wrong unit type");
            System.exit(0);
        }
        return warriors;
    }

    public void placeWarriors(ArrayList<GameObject> list) {

        int xToPlace;
        int yToPlace;

        for (GameObject object : list) {

            xToPlace = object.getX();
            yToPlace = object.getY();

            if (field.get(xToPlace).get(yToPlace).getId() == ID.Ground) {
                field.get(xToPlace).set(yToPlace, object);
            } else {
                System.out.println("Can't place stacked units!!!");
                System.exit(0);
            }
        }
    }


    public boolean endSimulation(ArrayList<GameObject> warriors) {

        boolean allyPresence = false;
        boolean enemyPresence = false;
        boolean endSimulation = false;

        for (GameObject warrior : warriors) {
            if (warrior.getId() == ID.Ally) allyPresence = true;
            if (warrior.getId() == ID.Enemy) enemyPresence = true;
        }
        if (!allyPresence || !enemyPresence) {
            endSimulation = true;
        }

        return endSimulation;
    }


    private void removeDeadWarriors(ArrayList<GameObject> warriors) {
        for (int i = 0; i < warriors.size(); i++) {
            if (warriors.get(i).getHp() <= 0) {
                int x = warriors.get(i).getX();
                int y = warriors.get(i).getY();
                field.get(x).set(y, new GameObject(ID.Ground));
                warriors.remove(i);
            }
        }
    }

    private void move(GameObject warrior, String destination) {

        if (warrior.getId() == ID.Ground) {
            System.out.println("Can't move ground");
            return;
        }

        int xModifier = 0;
        int yModifier = 0;

        if (destination.equals("up")) xModifier = -1;
        if (destination.equals("down")) xModifier = 1;
        if (destination.equals("left")) yModifier = -1;
        if (destination.equals("right")) yModifier = 1;

        int targetX = warrior.getX() + xModifier;
        int targetY = warrior.getY() + yModifier;

        if (targetX >= 0 && targetX < field.size() && targetY >= 0 && targetY < field.size()) {

            if (field.get(targetX).get(targetY).getId() == ID.Ground) {
                field.get(targetX).set(targetY, warrior);
                field.get(warrior.getX()).set(warrior.getY(), new GameObject(ID.Ground));
                warrior.setX(targetX);
                warrior.setY(targetY);

                if (destination.equals("up")) warrior.setStatus(Status.MovedUp);
                if (destination.equals("down")) warrior.setStatus(Status.MovedDown);
                if (destination.equals("left")) warrior.setStatus(Status.MovedLeft);
                if (destination.equals("right")) warrior.setStatus(Status.MovedRight);
            }

        } else {
            System.out.println("Object moved out of board");
        }
    }

    private void moveIfPossible(ArrayList<GameObject> warriors, int a, boolean allyToMove, int x, int y, int targetX, int targetY) {
        if ((warriors.get(a).getId() == ID.Ally && allyToMove) || warriors.get(a).getId() == ID.Enemy && !allyToMove) {

            String firstDirection = "no first direction";
            String secondDirection = "no second direction";

            if (Math.abs(targetX - x) > Math.abs(targetY - y)) {
                if (targetX - x > 0) firstDirection = "down";
                if (targetX - x < 0) firstDirection = "up";
                move(warriors.get(a), firstDirection);
            }
            if (Math.abs(targetX - x) < Math.abs(targetY - y)) {
                if (targetY - y > 0) firstDirection = "right";
                if (targetY - y < 0) firstDirection = "left";
                move(warriors.get(a), firstDirection);
            }
            if (Math.abs(targetX - x) == Math.abs(targetY - y)) {
                if (targetX - x > 0) firstDirection = "down";
                if (targetX - x < 0) firstDirection = "up";
                if (targetY - y > 0) secondDirection = "right";
                if (targetY - y < 0) secondDirection = "left";

                move(warriors.get(a), firstDirection);
                if (warriors.get(a).getStatus() == Status.NotMoved) move(warriors.get(a), secondDirection);
            }

        }
    }


    private void attackIfPossible(ArrayList<GameObject> warriors, boolean allyToMove, int attackerListPosition, int targetX, int targetY) {

        for (GameObject warrior : warriors) {
            if ((warrior.getId() == ID.Ally && !allyToMove) || (warrior.getId() == ID.Enemy && allyToMove)) {
                if (warrior.getX() == targetX && (warrior.getY() == targetY)) {
                    warriors.get(attackerListPosition).attack(warriors, warrior);
                    warriors.get(attackerListPosition).setStatus(Status.Attacked);
                }
            }
        }
    }

    public void scanForEnemy(ArrayList<GameObject> warriors, int turn) {

        for (int t = 0; t < field.size(); t++) {
            for (int attackerListPosition = 0; attackerListPosition < warriors.size(); attackerListPosition++) {
                if (t == 0 || (warriors.get(attackerListPosition).getStatus() == Status.NotMoved)) {

                    removeDeadWarriors(warriors);

                    boolean allyToMove;
                    if (turn % 2 == 0) allyToMove = true;
                    else allyToMove = false;

                    double minDistance = 0;
                    double distance;
                    int x = warriors.get(attackerListPosition).getX();
                    int y = warriors.get(attackerListPosition).getY();
                    int targetX = x;
                    int targetY = y;
                    ID id = warriors.get(attackerListPosition).getId();
                    ID opponentId = ID.Ally;
                    if (id == ID.Ally) opponentId = ID.Enemy;
                    warriors.get(attackerListPosition).setStatus(Status.NotMoved);


                    // Scan for the closest enemy
                    for (int i = 0; i < warriors.size(); i++) {
                        if (i != attackerListPosition) {
                            if (warriors.get(i).getId() == opponentId) {
                                if (minDistance == 0) {
                                    minDistance = Math.sqrt((warriors.get(i).getX() - x) * (warriors.get(i).getX() - x)
                                            + (warriors.get(i).getY() - y) * (warriors.get(i).getY() - y));
                                    targetX = warriors.get(i).getX();
                                    targetY = warriors.get(i).getY();
                                }
                                if (minDistance != 0) {
                                    distance = Math.sqrt((warriors.get(i).getX() - x) * (warriors.get(i).getX() - x)
                                            + (warriors.get(i).getY() - y) * (warriors.get(i).getY() - y));
                                    if (distance < minDistance) {
                                        minDistance = distance;
                                        targetX = warriors.get(i).getX();
                                        targetY = warriors.get(i).getY();
                                    }
                                }
                            }
                        }
                    }

                    if (warriors.get(attackerListPosition) instanceof Archer) {
                        if (minDistance == 1) {
                            ((Archer) warriors.get(attackerListPosition)).setMelee(false);
                        } else ((Archer) warriors.get(attackerListPosition)).setMelee(true);
                    }

                    int range = warriors.get(attackerListPosition).getRange();

                    // Close range attack
                    if (minDistance <= range) {
                        attackIfPossible(warriors, allyToMove, attackerListPosition, targetX, targetY);
                    }

                    // Move towards closest enemy and charge attack
                    if (minDistance > range && minDistance <= range + 1 && warriors.get(attackerListPosition).getStatus() != Status.Attacked) {
                        moveIfPossible(warriors, attackerListPosition, allyToMove, x, y, targetX, targetY);

                        Status status = warriors.get(attackerListPosition).getStatus();

                        if ((status == Status.MovedUp && targetX - warriors.get(attackerListPosition).getX() == -1) || (status == Status.MovedDown && targetX - warriors.get(attackerListPosition).getX() == 1)
                                || (status == Status.MovedLeft && targetY - warriors.get(attackerListPosition).getY() == -1) || (status == Status.MovedRight && targetY - warriors.get(attackerListPosition).getY() == 1)) {
                            warriors.get(attackerListPosition).setStatus(Status.Charged);
                            attackIfPossible(warriors, allyToMove, attackerListPosition, targetX, targetY);
                        }
                    }

                    // Move towards closest enemy
                    if (warriors.get(attackerListPosition).getStatus() == Status.NotMoved) {
                        moveIfPossible(warriors, attackerListPosition, allyToMove, x, y, targetX, targetY);
                    }
                    removeDeadWarriors(warriors);
                }
            }
        }
    }
}
