package model;

import java.util.*;

public class Hotel {
  private String id;
  private String name;
  private List<Room> rooms = new ArrayList<>();
  private Map<String, RoomType> roomTypes = new HashMap<>();

  public Hotel(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void addRoom(Room room) {
    rooms.add(room);
  }

  public List<Room> getRooms(String roomType) {
    List<Room> roomList = new ArrayList<>();
    for (Room room : rooms) {
      if (room.getRoomType().equals(roomType)) {
        roomList.add(room);
      }
    }
    return roomList;
  }

  public void addRoomType(RoomType roomType) {
    roomTypes.put(roomType.getCode(), roomType);
  }
}
