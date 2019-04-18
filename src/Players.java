public class Players {

    private String playersName;
    private int x_Coordinate;
    private int y_Coordinate;
    private char obj;
    private Boolean playerWin = false;
    private int count = 0;

    public Players() {

    }

    public String getPlayersName() {
        return playersName;
    }

    public void setPlayersName(String playersName) {
        this.playersName = playersName;
    }

    public Boolean getPlayerWin() {
        return playerWin;
    }

    public void setPlayerWin(Boolean playerwin) {
        this.playerWin = playerwin;
    }

    public int getX() {
        return x_Coordinate;
    }

    public void setX(int x_Coordinate) {
        this.x_Coordinate = x_Coordinate;
    }

    public int getY() {
        return y_Coordinate;
    }

    public void setY(int y_Coordinate) {
        this.y_Coordinate = y_Coordinate;
    }

    public char getObj() {
        return obj;
    }

    public void setObjPlayer(char obj) {
        this.obj = obj;
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
