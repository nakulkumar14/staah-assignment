package parser;

import model.Booking;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingDataParser {
  public List<Booking> parseBookingData(InputStream inputStream) throws ParseException {
    // Convert InputStream to String
    Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
    String jsonContent = scanner.useDelimiter("\\A").next();
    scanner.close();

    JSONArray bookingsArray = new JSONArray(jsonContent);
    List<Booking> bookings = new ArrayList<>();

    for (int i = 0; i < bookingsArray.length(); i++) {
      JSONObject bookingJson = bookingsArray.getJSONObject(i);
      String hotelId = bookingJson.getString("hotelId");
      String roomType = bookingJson.getString("roomType");
      String arrival = bookingJson.getString("arrival");
      String departure = bookingJson.getString("departure");

      Booking booking = new Booking(hotelId, roomType, arrival, departure);
      bookings.add(booking);
    }

    return bookings;
  }
}
