package model;

public class RoomType {
  private String code;
  private String description;

  public RoomType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }
}
