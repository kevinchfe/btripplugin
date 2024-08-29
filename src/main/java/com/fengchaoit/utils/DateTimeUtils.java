package com.fengchaoit.utils;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具
 *
 * @author wanchuan
 * @version 0.0.1
 * @since 2020/1/28 17:28
 **/
public abstract class DateTimeUtils {
    /**
     * 毫秒数转换为Instant
     *
     * @param milliSeconds 毫秒数
     * @return instant
     */
    public static Instant milliSecondToInstant(long milliSeconds) {
        return Instant.ofEpochMilli(milliSeconds);
    }

    /**
     * 秒转换为instant
     *
     * @param seconds 相距秒数
     * @return instant
     */
    public static Instant secondToInstant(long seconds) {
        return Instant.ofEpochSecond(seconds);
    }

    /**
     * 时间转换为UTC时间
     *
     * @param dateTime 当前时区时间
     * @return UTC时间
     */
    public static LocalDateTime locDateToUTC(LocalDateTime dateTime) {
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime systemZonedDateTime = dateTime.atZone(systemDefaultZoneId);
        ZonedDateTime utcZonedDateTime = systemZonedDateTime.withZoneSameInstant(utcZoneId);
        return utcZonedDateTime.toLocalDateTime();
    }

    /**
     * UTC时间转换为当前时间
     *
     * @param utcDateTime utc日期时间
     * @return 当前时间
     */
    public static LocalDateTime UTCToLocDate(LocalDateTime utcDateTime) {
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(utcZoneId);
        ZonedDateTime systemZonedDateTime = utcZonedDateTime.withZoneSameInstant(systemDefaultZoneId);
        return systemZonedDateTime.toLocalDateTime();
    }

    /**
     * instant转换为LocalDate
     *
     * @param instant 向量参数
     * @return LocalDate
     */
    public static LocalDate instantToDate(Instant instant) {
        ZoneId zone = ZoneId.systemDefault();
        return LocalDate.ofInstant(instant, zone);
    }

    /**
     * instant转换为LocalDateTime
     *
     * @param instant 向量参数
     * @return LocalDateTime
     */
    public static LocalDateTime instantToDateTime(Instant instant) {
        ZoneId zone = ZoneId.systemDefault();
        return instantToDateTime(instant, zone);
    }

    /**
     * instant转换为LocalDateTime
     *
     * @param instant 向量参数
     * @param zone    时区
     * @return LocalDateTime
     */
    public static LocalDateTime instantToDateTime(Instant instant, ZoneId zone) {
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 日期转换为 Instant
     *
     * @param localDate 日期
     * @return Instant
     */
    public static Instant dateToInstant(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        return localDate.atStartOfDay().atZone(zone).toInstant();
    }

    /**
     * 日期时间转换为 Instant
     *
     * @param localDateTime 日期时间
     * @param zone          时区
     * @return Instant
     */
    public static Instant dateTimeToInstant(LocalDateTime localDateTime, ZoneId zone) {
        return localDateTime.atZone(zone).toInstant();
    }

    /**
     * 日期时间转换为 Instant
     *
     * @param localDateTime 日期时间
     * @return Instant
     */
    public static Instant dateTimeToInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        return dateTimeToInstant(localDateTime, zone);
    }

    /**
     * 将datetime转换为date
     *
     * @param localDateTime datetime
     * @return date
     */
    public static Date dateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(dateTimeToInstant(localDateTime));
    }

    /**
     * 毫秒转换为 LocalDate
     *
     * @param milliSeconds 毫秒数
     * @return LocalDate
     */
    public static LocalDate milliSecondToDate(long milliSeconds) {
        Instant instant = milliSecondToInstant(milliSeconds);
        return instantToDate(instant);
    }

    /**
     * 毫秒转换为 LocalDateTime
     *
     * @param milliSeconds 毫秒数
     * @return LocalDateTime
     */
    public static LocalDateTime milliSecondToDateTime(long milliSeconds) {
        Instant instant = milliSecondToInstant(milliSeconds);
        return instantToDateTime(instant);
    }

    /**
     * 毫秒转换为 LocalDateTime
     *
     * @param milliSeconds 毫秒数
     * @param zoneId       时区ID
     * @return LocalDateTime
     */
    public static LocalDateTime milliSecondToDateTime(long milliSeconds, ZoneId zoneId) {
        Instant instant = milliSecondToInstant(milliSeconds);
        return instantToDateTime(instant, zoneId);
    }

    /**
     * 秒转换为LocalDateTime
     *
     * @param seconds 秒
     * @return LocalDateTime
     */
    public static LocalDateTime secondToDateTime(long seconds) {
        Instant instant = secondToInstant(seconds);
        return instantToDateTime(instant);
    }

    /**
     * 年开始时间
     *
     * @param localDateTime 时间参数
     * @return 时间
     */
    public static LocalDateTime yearBegin(LocalDateTime localDateTime) {
        LocalDateTime yearBeginTime = localDateTime.with(TemporalAdjusters.firstDayOfYear());
        return dayBegin(yearBeginTime);
    }

    /**
     * 今年开始时间
     *
     * @return 时间
     */
    public static LocalDateTime yearBegin() {
        return yearBegin(LocalDateTime.now());
    }

    /**
     * 月开始时间
     *
     * @param localDateTime 时间参数
     * @return 时间
     */
    public static LocalDateTime monthBegin(LocalDateTime localDateTime) {
        LocalDateTime monthBeginTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        return dayBegin(monthBeginTime);
    }

    /**
     * 获取月最后结果时间
     *
     * @param localDateTime 时间参数
     * @return 时间
     */
    public static LocalDateTime monthEnd(LocalDateTime localDateTime) {
        LocalDateTime monthBeginTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return dayEnd(monthBeginTime);
    }

    /**
     * 获取日期开始时间
     *
     * @param localDateTime 日期时间
     * @return 0时日期时间
     */
    public static LocalDateTime dayBegin(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay();
    }

    /**
     * 获取今天的日期开始时间
     *
     * @return 今天0时日期时间
     */
    public static LocalDateTime dayBegin() {
        return dayBegin(LocalDateTime.now());
    }

    /**
     * 获取日期的结束时间
     *
     * @param localDateTime 日期时间
     * @return 传入时间的23时59分59秒
     */
    public static LocalDateTime dayEnd(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(LocalTime.MAX);
    }

    /**
     * 获取日期的结束时间
     *
     * @return 当前日期的23时59分59秒
     */
    public static LocalDateTime dayEnd() {
        return dayEnd(LocalDateTime.now());
    }

    /**
     * 日期时间转换为 秒
     *
     * @param localDateTime 日期时间
     * @return 秒
     */
    public static long dateTimeToSecond(LocalDateTime localDateTime) {
        Instant instant = dateTimeToInstant(localDateTime);
        return instant.getEpochSecond();
    }

    /**
     * 日期转换为 毫秒
     *
     * @param localDate 日期
     * @return 毫秒
     */
    public static long dateToMilliSecond(LocalDate localDate) {
        Instant instant = dateToInstant(localDate);
        return instant.toEpochMilli();
    }

    /**
     * 日期时间转换为 毫秒
     *
     * @param localDateTime 日期时间
     * @return 毫秒
     */
    public static long dateTimeToMilliSecond(LocalDateTime localDateTime) {
        Instant instant = dateTimeToInstant(localDateTime);
        long epochMilli = instant.toEpochMilli();
        return instant.toEpochMilli();
    }

    /**
     * 日期时间转换为 毫秒
     *
     * @param localDateTime 日期时间
     * @param zone          时区
     * @return 毫秒
     */
    public static Long dateTimeToMilliSecond(LocalDateTime localDateTime, ZoneId zone) {
        Instant instant = dateTimeToInstant(localDateTime, zone);
        return instant.toEpochMilli();
    }

    /**
     * 获取当前时间的秒数
     *
     * @return 秒
     */
    public static long dateTimeToSecond() {
        return dateTimeToSecond(LocalDateTime.now());
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return 毫秒
     */
    public static long dateTimeToMilliSecond() {
        return dateTimeToMilliSecond(LocalDateTime.now());
    }

    /**
     * 日期类型转换为java8 LocalDateTime
     *
     * @param date 日期类型
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    /**
     * 日期转换为java8 LocalDateTime
     *
     * @param date 日期类型
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return dateToLocalDateTime(date, ZoneId.systemDefault());
    }

}
