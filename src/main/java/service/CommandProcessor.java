package service;

import service.AvailabilityService;
import service.SearchService;
import model.Hotel;
import model.Booking;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CommandProcessor {
  private AvailabilityService availabilityService;
  private SearchService searchService;
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  public CommandProcessor(AvailabilityService availabilityService, SearchService searchService) {
    this.availabilityService = availabilityService;
    this.searchService = searchService;
  }

  public void processCommand(String command, List<Hotel> hotels, List<Booking> bookings) throws ParseException {
    if (command.startsWith("Availability")) {
      String[] parts = command.substring("Availability".length()).replaceAll("[()]", "").split(",");
      String hotelId = parts[0].trim();
      String dateRange = parts[1].trim();
      String roomType = parts[2].trim();

      Hotel hotel = getHotelById(hotelId, hotels);
      if (dateRange.contains("-")) {
        String[] dates = dateRange.split("-");
        Date startDate = dateFormat.parse(dates[0].trim());
        Date endDate = dateFormat.parse(dates[1].trim());
        int availableRooms = availabilityService.getRoomAvailability(hotel, startDate, endDate, roomType, bookings);
        System.out.println("Available rooms: " + availableRooms);
      } else {
        Date date = dateFormat.parse(dateRange);
        int availableRooms = availabilityService.getRoomAvailability(hotel, date, roomType, bookings);
        System.out.println("Available rooms: " + availableRooms);
      }
    } else if (command.startsWith("Search")) {
      String[] parts = command.substring("Search".length()).replaceAll("[()]", "").split(",");
      String hotelId = parts[0].trim();
      int daysAhead = Integer.parseInt(parts[1].trim());
      String roomType = parts[2].trim();

      Hotel hotel = getHotelById(hotelId, hotels);
      List<String> availableDates = searchService.searchAvailability(hotel, daysAhead, roomType, bookings);
      if (availableDates.isEmpty()) {
        System.out.println();
      } else {
        System.out.println(String.join(", ", availableDates));
      }
    }
  }

  private Hotel getHotelById(String hotelId, List<Hotel> hotels) {
    for (Hotel hotel : hotels) {
      if (hotel.getId().equals(hotelId)) {
        return hotel;
      }
    }
    return null;
  }
}
