
import model.Booking;
import model.Hotel;
import parser.BookingDataParser;
import parser.HotelDataParser;
import service.AvailabilityService;
import service.CommandProcessor;
import service.SearchService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HotelManager {
  private static Map<String, Hotel> hotels = new HashMap<>();
  private static Map<String, List<Booking>> bookings = new HashMap<>();

  public static void main(String[] args) throws Exception {
    if (args.length != 4) {
      System.out.println("Usage: myapp --hotels <hotels.json> --bookings <bookings.json>");
      return;
    }

    // Read the hotel data and booking data from JSON files
    List<Hotel> hotelList = new HotelDataParser().parseHotelData(HotelManager.class.getClassLoader().getResourceAsStream("hotels.json"));
    hotels = hotelList.stream().collect(Collectors.toMap(Hotel::getId, Function.identity()));
    List<Booking> bookingList = new BookingDataParser().parseBookingData(HotelManager.class.getClassLoader().getResourceAsStream("bookings.json"));
    for (Booking booking : bookingList) {
      if (!bookings.containsKey(booking.getHotelId())) {
        bookings.put(booking.getHotelId(), new ArrayList<>());
      }
      bookings.get(booking.getHotelId()).add(booking);
    }

    AvailabilityService availabilityService = new AvailabilityService();
    SearchService searchService = new SearchService(availabilityService);
    CommandProcessor commandProcessor = new CommandProcessor(availabilityService, searchService);

    // Read user input and process commands
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        break;
      }
      commandProcessor.processCommand(input, hotelList, bookingList);
    }
    scanner.close();
  }
}
