package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
  private String hotelId;
  private String roomType;
  private Date startDate;
  private Date endDate;

  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  public Booking(String hotelId, String roomType, String arrival, String departure) throws ParseException {
    this.hotelId = hotelId;
    this.roomType = roomType;
    this.startDate = dateFormat.parse(arrival);
    this.endDate = dateFormat.parse(departure);
  }

  public String getHotelId() {
    return hotelId;
  }

  public String getRoomType() {
    return roomType;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }
}
