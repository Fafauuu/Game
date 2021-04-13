package simulation;

import javax.swing.*;
import java.util.ArrayList;

public class Action {

    ArrayList<ArrayList<GameObject>> board;

    public ArrayList<ArrayList<GameObject>> getBoard() {
        return board;
    }

    public void setList(ArrayList<ArrayList<GameObject>> board) {
        this.board = board;
    }

    public void printField() {
        System.out.println(board.size());
    }


    public void placeWarriors(ArrayList<GameObject> list) {

        int xToPlace = -1;
        int yToPlace = -1;

        for (int i = 0; i < list.size(); i++) {

            xToPlace = list.get(i).getX();
            yToPlace = list.get(i).getY();

            if (board.get(xToPlace).get(yToPlace).getId() == ID.Ground) {
                board.get(list.get(i).getX()).set(list.get(i).getY(), list.get(i));
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

        for (int i = 0; i < warriors.size(); i++) {
            if (warriors.get(i).getId() == ID.Ally) allyPresence = true;
            if (warriors.get(i).getId() == ID.Enemy) enemyPresence = true;
        }
        if (!allyPresence || !enemyPresence) {
            endSimulation = true;
        }

        return endSimulation;
    }


    public void removeDeadWarriors(ArrayList<GameObject> warriors) {
        for (int i = 0; i < warriors.size(); i++) {
            if (warriors.get(i).getHp() <= 0) {
                int x = warriors.get(i).getX();
                int y = warriors.get(i).getY();
                board.get(x).set(y, new GameObject(ID.Ground, Status.Ground));
                warriors.remove(i);
            }
        }
    }

    public void move(GameObject warrior, String destination) {

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

        if (targetX >= 0 && targetX < board.size() && targetY >= 0 && targetY < board.size()) {


            if (board.get(targetX).get(targetY).getId() == ID.Ground) {
                board.get(targetX).set(targetY, warrior);
                board.get(warrior.getX()).set(warrior.getY(), new GameObject(ID.Ground, Status.Ground));
//                warrior.setPreviousX(warrior.getX());
//                warrior.setPreviousY(warrior.getY());
                warrior.setX(targetX);
                warrior.setY(targetY);

                if (destination.equals("up")) warrior.setStatus(Status.MovedUp);
                if (destination.equals("down")) warrior.setStatus(Status.MovedDown);
                if (destination.equals("left")) warrior.setStatus(Status.MovedLeft);
                if (destination.equals("right")) warrior.setStatus(Status.MovedRight);
            } else {
//                System.out.println("Can't stack objects");
                return;
            }
        } else {
            System.out.println("Object moved out of board");
            warrior.setStatus(Status.NotMoved);
        }
    }

    public void attack(GameObject attacker, GameObject attackedWarrior) {

        int attackValue = 0;

        if(attacker.getAttack() >= attackedWarrior.getDefence()) {
            attackValue = attacker.getBaseDmg() +  2 * (attacker.getAttack() - attackedWarrior.getDefence());
        }
        else attackValue = attacker.getBaseDmg() + attackedWarrior.getDefence() - attacker.getAttack();

        System.out.println("AttackerX: " + attacker.getX() + " AttackerY: " + attacker.getY() + " DefenderX: " + attackedWarrior.getX() + " DefenderY: " + attackedWarrior.getY());


        attackedWarrior.setHp(attackedWarrior.getHp() - attackValue);
    }

//    private void moveIfPossible(ArrayList<GameObject> warriors, int a, boolean allyToMove, int x, int y, int targetX, int targetY) {
//        if ((warriors.get(a).getId() == ID.Ally && allyToMove) || warriors.get(a).getId() == ID.Enemy && !allyToMove) {
//            if (Math.abs(targetX - x) >= Math.abs(targetY - y)) {
//                if (targetX - x > 0) move(warriors.get(a), "down");
//                if (targetX - x < 0) move(warriors.get(a), "up");
//            } else {
//                if (targetY - y > 0) move(warriors.get(a), "right");
//                if (targetY - y < 0) move(warriors.get(a), "left");
//            }
//        }
//    }

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


    private void attackIfPossible(ArrayList<GameObject> warriors, int turn, int attackerListPosition, int attackerX, int attackerY, int targetX, int targetY) {

        boolean allyToMove;
        if (turn % 2 == 0) allyToMove = true;
        else allyToMove = false;

//        GameObject attacker = null;

        for (int i = 0; i < warriors.size(); i++) {
            if ((warriors.get(i).getId() == ID.Ally && !allyToMove) || (warriors.get(i).getId() == ID.Enemy && allyToMove)) {
                if (warriors.get(i).getX() == targetX && (warriors.get(i).getY() == targetY)) {
//                    for (int j = 0; j < warriors.size(); j++) {
//                        if (warriors.get(j).getX() == attackerX && (warriors.get(j).getY() == attackerY)) ;
//                        attacker = warriors.get(j);
//                    }
                    attack(warriors.get(attackerListPosition), warriors.get(i));
                    warriors.get(attackerListPosition).setStatus(Status.Attacked);
                }
            }
        }
    }

    public void scanForEnemy(ArrayList<GameObject> warriors, int turn) {

        for (int t = 0; t < board.size(); t++) {
            for (int attackerListPosition = 0; attackerListPosition < warriors.size(); attackerListPosition++) {
                if (t == 0 || (warriors.get(attackerListPosition).getStatus() == Status.NotMoved)) {

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


                    // Close range attack
                    if (minDistance == 1) {
                        attackIfPossible(warriors, turn, attackerListPosition, x, y, targetX, targetY);
                    }

                    // Moving towards closest an enemy and charge attack
                    if (minDistance > 1 && minDistance <= 2 && warriors.get(attackerListPosition).getStatus() != Status.Attacked) {

//                        System.out.println("Before moving: " + warriors.get(attackerListPosition).getId() + " " + warriors.get(attackerListPosition).getStatus() + " x: " + warriors.get(attackerListPosition).getX() + " y: " + warriors.get(attackerListPosition).getY() + " targetX: " + targetX + " targetY: " + targetY);

                        moveIfPossible(warriors, attackerListPosition, allyToMove, x, y, targetX, targetY);

//                        System.out.println("After moving: " + warriors.get(attackerListPosition).getId() + " " + warriors.get(attackerListPosition).getStatus() + " x: " + warriors.get(attackerListPosition).getX() + " y: " + warriors.get(attackerListPosition).getY() + " targetX: " + targetX + " targetY: " + targetY);

                        Status status = warriors.get(attackerListPosition).getStatus();


                        if ((status == Status.MovedUp && targetX - warriors.get(attackerListPosition).getX() == -1) || (status == Status.MovedDown && targetX - warriors.get(attackerListPosition).getX() == 1)
                                || (status == Status.MovedLeft && targetY - warriors.get(attackerListPosition).getY() == -1) || (status == Status.MovedRight && targetY - warriors.get(attackerListPosition).getY() == 1))
                            attackIfPossible(warriors, turn, attackerListPosition, x, y, targetX, targetY);

                    }

                    // Moving towards closest an enemy
                    if (warriors.get(attackerListPosition).getStatus() == Status.NotMoved) {
                        moveIfPossible(warriors, attackerListPosition, allyToMove, x, y, targetX, targetY);
                    }
                    removeDeadWarriors(warriors);
                }
            }
        }
    }
}
