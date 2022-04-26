package su.ANV.models;

public class Step {
    private Long playGroundId;
    private Long playGroundKey;
    private Long playerId;
    private Long playerKey;
    private int cell;

    public Step() {
    }

    public Step(Long playGroundId, Long playGroundKey, Long playerId, Long playerKey, int cell) {
        this.playGroundId = playGroundId;
        this.playGroundKey = playGroundKey;
        this.playerId = playerId;
        this.playerKey = playerKey;
        this.cell = cell;
    }

    public Long getPlayGroundId() {
        return playGroundId;
    }

    public void setPlayGroundId(Long playGroundId) {
        this.playGroundId = playGroundId;
    }

    public Long getPlayGroundKey() {
        return playGroundKey;
    }

    public void setPlayGroundKey(Long playGroundKey) {
        this.playGroundKey = playGroundKey;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(Long playerKey) {
        this.playerKey = playerKey;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Step{" +
                "playGroundId=" + playGroundId +
                ", playGroundKey=" + playGroundKey +
                ", playerId=" + playerId +
                ", playerKey=" + playerKey +
                ", cell=" + cell +
                '}';
    }
}
