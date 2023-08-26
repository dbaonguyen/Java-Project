package Entities;

import java.util.ArrayList;

public class CanMoveToPort {
    private boolean canMove;
    private ArrayList<String> violatedRequirements;

    public CanMoveToPort(boolean canMove, ArrayList<String> violatedRequirements) {
        this.canMove = canMove;
        this.violatedRequirements = violatedRequirements;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public ArrayList<String> getViolatedRequirements() {
        return violatedRequirements;
    }

    public void setViolatedRequirements(ArrayList<String> violatedRequirements) {
        this.violatedRequirements = violatedRequirements;
    }

    @Override
    public String toString() {
        return "CanMoveToPort{" +
                "canMove=" + canMove +
                ", violatedRequirements=" + violatedRequirements +
                '}';
    }
}
