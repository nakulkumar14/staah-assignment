package service;

import model.Hotel;
import model.Booking;
import model.Room;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchService {
  private AvailabilityService availabilityService;

  public SearchService(AvailabilityService availabilityService) {
    this.availabilityService = availabilityService;
  }

  public List<String> searchAvailability(Hotel hotel, int daysAhead, String roomType, List<Booking> bookings) {
    List<String> availableDates = new ArrayList<>();
    Date currentDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    calendar.add(Calendar.DAY_OF_YEAR, daysAhead);

    List<Room> rooms = hotel.getRooms(roomType);
    int availableCount = rooms.size();
    Date startDate = calendar.getTime();

    for (int i = 0; i < daysAhead; i++) {
      Date endDate = calendar.getTime();
      int availableRooms = availabilityService.getRoomAvailability(hotel, startDate, endDate, roomType, bookings);
      if (availableRooms > 0) {
        availableDates.add("(" + new SimpleDateFormat("yyyyMMdd").format(startDate) + "-" + new SimpleDateFormat("yyyyMMdd").format(endDate) + ", " + availableRooms + ")");
      }
      calendar.add(Calendar.DATE, 1);
    }
    return availableDates;
  }
}
