package com.serverseri.core.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtility {

  public static final String UTC = "UTC";

  public static LocalDateTime anyToUtc(LocalDateTime dateTime, ZoneId sourceZoneId) {
    return changeZone(dateTime, sourceZoneId,ZoneId.of(UTC));
  }

  public static LocalDateTime utcToAny(LocalDateTime dateTimeInUtc, ZoneId destinationZoneId) {
    return changeZone(dateTimeInUtc,ZoneId.of(UTC),destinationZoneId);
  }

  public static Timestamp localDateTimeToUtcSqlTimeStamp(LocalDateTime dateTime, ZoneId sourceZoneId) {
    return Timestamp.valueOf(changeZone(dateTime,sourceZoneId,ZoneId.of(UTC)));
  }

  public static LocalDateTime SqlTimeStampUTCToLocalDateTime(Timestamp timeStamp, ZoneId destinationZoneId) {
    return timeStamp.toLocalDateTime().atZone(destinationZoneId).toLocalDateTime();
  }

  public static LocalDateTime changeZone(LocalDateTime dateTime, ZoneId sourceZoneId, ZoneId destinationZoneId){
    ZonedDateTime sourceZonedTime = dateTime.atZone(sourceZoneId);
    ZonedDateTime destinationzonedTime = sourceZonedTime.withZoneSameInstant(destinationZoneId);
    return destinationzonedTime.toLocalDateTime();
  }

  public static LocalDateTime nowOf(ZoneId zoneId) {
    return LocalDateTime.now(zoneId);
  }
}
