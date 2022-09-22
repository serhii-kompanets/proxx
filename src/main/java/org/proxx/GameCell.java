package org.proxx;

public class GameCell {
    private boolean hasHole;
    private boolean isOpened;
    private int neighborHoleTotal;

    public GameCell() {
    }

    public GameCell(boolean hasHole, boolean isOpened, int neighborHoleTotal) {
        this.hasHole = hasHole;
        this.isOpened = isOpened;
        this.neighborHoleTotal = neighborHoleTotal;
    }

    public boolean hasHole() {
        return hasHole;
    }

    public void setHole(boolean hasHole) {
        this.hasHole = hasHole;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public int getNeighborHoleTotal() {
        return neighborHoleTotal;
    }

    public void setNeighborHoleTotal(int neighborHoleTotal) {
        this.neighborHoleTotal = neighborHoleTotal;
    }

    @Override
    public String toString() {
        return "GameCell{" +
                "hasMine=" + hasHole +
                ", isOpen=" + isOpened +
                ", neighborMineTotal=" + neighborHoleTotal +
                '}';
    }
}
