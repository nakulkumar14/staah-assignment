package parser;

import model.Hotel;
import model.Room;
import model.RoomType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelDataParser {
  public List<Hotel> parseHotelData(InputStream inputStream) {
    // Convert InputStream to String
    Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
    String jsonContent = scanner.useDelimiter("\\A").next();
    scanner.close();

    JSONArray hotelsArray = new JSONArray(new JSONTokener(jsonContent));
    List<Hotel> hotels = new ArrayList<>();

    for (int i = 0; i < hotelsArray.length(); i++) {
      JSONObject hotelJson = hotelsArray.getJSONObject(i);
      Hotel hotel = new Hotel(hotelJson.getString("id"), hotelJson.getString("name"));

      // Parse rooms and room types
      parseRoomsAndTypes(hotelJson, hotel);

      hotels.add(hotel);
    }

    return hotels;
  }

  private void parseRoomsAndTypes(JSONObject hotelJson, Hotel hotel) {
    // Parse rooms
    JSONArray roomsJson = hotelJson.getJSONArray("rooms");
    for (int j = 0; j < roomsJson.length(); j++) {
      JSONObject roomJson = roomsJson.getJSONObject(j);
      String roomType = roomJson.getString("roomType");
      hotel.addRoom(new Room(roomJson.getString("roomId"), roomType));
    }

    // Parse room types
    JSONArray roomTypesJson = hotelJson.getJSONArray("roomTypes");
    for (int j = 0; j < roomTypesJson.length(); j++) {
      JSONObject roomTypeJson = roomTypesJson.getJSONObject(j);
      String code = roomTypeJson.getString("code");
      hotel.addRoomType(new RoomType(code, roomTypeJson.getString("description")));
    }
  }
}
