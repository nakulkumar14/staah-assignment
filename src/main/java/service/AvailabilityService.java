package service;

import model.Hotel;
import model.Booking;
import model.Room;

import java.util.List;
import java.util.Date;

public class AvailabilityService {

  /**
   * Checks room availability for a given hotel, room type, and a date range.
   * @param hotel The hotel to check availability for.
   * @param startDate The start date of the range.
   * @param endDate The end date of the range.
   * @param roomType The type of room (e.g., "SGL", "DBL").
   * @param bookings List of bookings for the hotel.
   * @return The number of available rooms.
   */
  public int getRoomAvailability(Hotel hotel, Date startDate, Date endDate, String roomType, List<Booking> bookings) {
    List<Room> rooms = hotel.getRooms(roomType);
    int availableCount = rooms.size();

    // Subtract rooms booked within the date range
    for (Booking booking : bookings) {
      if (booking.getRoomType().equals(roomType) && isOverlapping(booking, startDate, endDate)) {
        availableCount--;
      }
    }

    return availableCount;
  }

  /**
   * Checks room availability for a given hotel, room type, and a specific date.
   * @param hotel The hotel to check availability for.
   * @param date The specific date to check availability.
   * @param roomType The type of room (e.g., "SGL", "DBL").
   * @param bookings List of bookings for the hotel.
   * @return The number of available rooms.
   */
  public int getRoomAvailability(Hotel hotel, Date date, String roomType, List<Booking> bookings) {
    // In this case, the start and end date are the same (single date check)
    return getRoomAvailability(hotel, date, date, roomType, bookings);
  }

  /**
   * Checks if a booking overlaps with a given date range.
   * @param booking The booking to check.
   * @param startDate The start date of the range.
   * @param endDate The end date of the range.
   * @return True if the booking overlaps with the date range.
   */
  private boolean isOverlapping(Booking booking, Date startDate, Date endDate) {
    Date bookingStart = booking.getStartDate();
    Date bookingEnd = booking.getEndDate();
    return (startDate.before(bookingEnd) && endDate.after(bookingStart));
  }
}
