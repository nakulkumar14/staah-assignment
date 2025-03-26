package model;

public class Room {
  private String roomId;
  private String roomType;

  public Room(String roomId, String roomType) {
    this.roomId = roomId;
    this.roomType = roomType;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomType() {
    return roomType;
  }
}
