package com.musemo.model;

/**
 *
 * @author Viom Shrestha
 */
public class ArtifactModel {
    private int ArtifactID;
    private String  ArtifactName;
    private String  ArtifactType;
    private String  Creator;
    private String  Status;
    private String  Origin;
    private String  Condition;
    private short  Floor;
    private short  RoomNo;

    public ArtifactModel() {
    }

    public ArtifactModel(int ArtifactID, String ArtifactName, String ArtifactType, String Creator, String Status, String Origin, String Condition, short Floor, short RoomNo) {
        this.ArtifactID = ArtifactID;
        this.ArtifactName = ArtifactName;
        this.ArtifactType = ArtifactType;
        this.Creator = Creator;
        this.Status = Status;
        this.Origin = Origin;
        this.Condition = Condition;
        this.Floor = Floor;
        this.RoomNo = RoomNo;
    }

    public int getArtifactID() {
        return ArtifactID;
    }

    public void setArtifactID(int ArtifactID) {
        this.ArtifactID = ArtifactID;
    }

    public String getArtifactName() {
        return ArtifactName;
    }

    public void setArtifactName(String ArtifactName) {
        this.ArtifactName = ArtifactName;
    }

    public String getArtifactType() {
        return ArtifactType;
    }

    public void setArtifactType(String ArtifactType) {
        this.ArtifactType = ArtifactType;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public short getFloor() {
        return Floor;
    }

    public void setFloor(short Floor) {
        this.Floor = Floor;
    }

    public short getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(short RoomNo) {
        this.RoomNo = RoomNo;
    }
}
