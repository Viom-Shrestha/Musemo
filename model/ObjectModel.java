package com.musemo.model;

/**
 *
 * @author Viom Shrestha
 */
public class ObjectModel {
    private int ObjectID;
    private String  ObjectName;
    private String  ObjectType;
    private String  Creator;
    private String  Status;
    private String  Origin;
    private String  Condition;
    private short  Floor;
    private short  RoomNo;

    public ObjectModel() {
    }

    public ObjectModel(int ObjectID, String ObjectName, String ObjectType, String Creator, String Status, String Origin, String Condition, short Floor, short RoomNo) {
        this.ObjectID = ObjectID;
        this.ObjectName = ObjectName;
        this.ObjectType = ObjectType;
        this.Creator = Creator;
        this.Status = Status;
        this.Origin = Origin;
        this.Condition = Condition;
        this.Floor = Floor;
        this.RoomNo = RoomNo;
    }

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int ObjectID) {
        this.ObjectID = ObjectID;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String ObjectName) {
        this.ObjectName = ObjectName;
    }

    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String ObjectType) {
        this.ObjectType = ObjectType;
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
