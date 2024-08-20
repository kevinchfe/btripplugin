package com.fengchaoit.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 * 时间格式工具
 *
 * @author wanchuan
 * @version 0.0.1
 * @since 2020/1/28 15:50
 **/
public abstract class DateTimeFormatter {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    /**
     * 获取日期格式
     *
     * @return 日期时间格式化
     */
    public static java.time.format.DateTimeFormatter dateFormatter() {
        return dateFormatter(DATE_PATTERN);
    }

    /**
     * 获取日期格式
     *
     * @param pattern 格式化格式
     * @return 日期时间格式化
     */
    public static java.time.format.DateTimeFormatter dateFormatter(String pattern) {
        return java.time.format.DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取时间格式
     *
     * @return 时间格式化
     */
    public static java.time.format.DateTimeFormatter timeFormatter() {
        return timeFormatter(TIME_PATTERN);
    }

    /**
     * 获取时间格式
     *
     * @param pattern 格式化格式
     * @return 时间格式化
     */
    public static java.time.format.DateTimeFormatter timeFormatter(String pattern) {
        return java.time.format.DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取日期时间格式
     *
     * @return 日期时间格式化
     */
    public static java.time.format.DateTimeFormatter dateTimeFormatter() {
        return dateTimeFormatter(DATE_TIME_PATTERN);
    }

    /**
     * 获取日期时间格式
     *
     * @param pattern 格式化格式
     * @return 日期时间格式化
     */
    public static java.time.format.DateTimeFormatter dateTimeFormatter(String pattern) {
        return dateTimeFormatter(pattern, Locale.getDefault());
    }

    /**
     * 获取日期时间格式
     *
     * @param pattern 格式化格式
     * @param locale  时区
     * @return 日期时间格式化
     */
    public static java.time.format.DateTimeFormatter dateTimeFormatter(String pattern, Locale locale) {
        return java.time.format.DateTimeFormatter.ofPattern(pattern, locale);
    }

    /**
     * 将日期转换成字符串
     *
     * @param localDate 日期
     * @param pattern   转换格式
     * @return 日期字符串格式
     */
    public static String dateToString(LocalDate localDate, String pattern) {
        return localDate.format(dateFormatter(pattern));
    }

    /**
     * 当前时间转换为字符串
     *
     * @param pattern 转换格式
     * @return 日期字符串格式
     */
    public static String dateToString(String pattern) {
        return dateToString(LocalDate.now(), pattern);
    }

    /**
     * 将日期转换为默认格式
     *
     * @param localDate 日期时间
     * @return 日期字符串格式
     */
    public static String dateToString(LocalDate localDate) {
        return dateToString(localDate, DATE_PATTERN);
    }

    /**
     * 当前日期转换为默认格式
     *
     * @return 日期字符串格式
     */
    public static String dateToString() {
        return dateToString(DATE_PATTERN);
    }

    /**
     * 将时间转换为字符串
     *
     * @param localTime 时间
     * @param pattern   时间字符格式化
     * @return 时间字符串格式
     */
    public static String timeToString(LocalTime localTime, String pattern) {
        return localTime.format(timeFormatter(pattern));
    }

    /**
     * 将时间转换为字符串
     *
     * @param localTime 时间
     * @return 时间字符串格式
     */
    public static String timeToString(LocalTime localTime) {
        return timeToString(localTime, TIME_PATTERN);
    }

    /**
     * 将当前时间转换为字符串
     *
     * @param pattern 时间格式化
     * @return 时间字符串格式
     */
    public static String timeToString(String pattern) {
        return timeToString(LocalTime.now(), pattern);
    }

    /**
     * 当前时间转换为字符串格式
     *
     * @return 时间
     */
    public static String timeToString() {
        return timeToString(TIME_PATTERN);
    }

    /**
     * 字符时间转换为字符串形式
     *
     * @param localDateTime 时间
     * @param pattern       格式化
     * @param locale        时区
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString(LocalDateTime localDateTime, String pattern, Locale locale) {
        return localDateTime.format(dateTimeFormatter(pattern, locale));
    }

    /**
     * 字符时间转换为字符串形式
     *
     * @param localDateTime 时间
     * @param pattern       格式化
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(dateTimeFormatter(pattern));
    }

    /**
     * 日期时间转换为字符串格式
     *
     * @param localDateTime 日期时间
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString(LocalDateTime localDateTime) {
        return dateTimeToString(localDateTime, DATE_TIME_PATTERN);
    }

    /**
     * 当前时间转换为字符串格式
     *
     * @param pattern 日期时间
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString(String pattern) {
        return dateTimeToString(LocalDateTime.now(), pattern);
    }

    /**
     * 当前时间转换为字符串格式
     *
     * @param pattern 日期时间
     * @param locale  时区
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString(String pattern, Locale locale) {
        return dateTimeToString(LocalDateTime.now(), pattern, locale);
    }

    /**
     * 当前时间转换为字符串格式
     *
     * @return 日期时间字符串格式
     */
    public static String dateTimeToString() {
        return dateTimeToString(DATE_TIME_PATTERN);
    }

    /**
     * 字符串转换为 LocalTime
     *
     * @param timeStr 时间字符串
     * @param pattern 时间字符串格式
     * @return LocalTime
     */
    public static LocalTime stringToTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, timeFormatter(pattern));
    }

    /**
     * 字符串转换为 LocalTime
     *
     * @param timeStr 时间字符串
     * @return LocalTime
     */
    public static LocalTime stringToTime(String timeStr) {
        return stringToTime(timeStr, TIME_PATTERN);
    }

    /**
     * 字符串转换为 LocalDate
     *
     * @param dateStr 日期字符串
     * @param pattern 日期字符串格式
     * @return LocalDate
     */
    public static LocalDate stringToDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, dateFormatter(pattern));
    }

    /**
     * 字符串转换为 LocalDate
     *
     * @param dateStr 日期字符串
     * @return LocalDate
     */
    public static LocalDate stringToDate(String dateStr) {
        return stringToDate(dateStr, DATE_PATTERN);
    }

    /**
     * 字符串转换为 LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern     转换为字符串格式
     * @return LocalDateTime
     */
    public static LocalDateTime stringToDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter(pattern));
    }

    /**
     * 字符串转换为 LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime
     */
    public static LocalDateTime stringToDateTime(String dateTimeStr) {
        return stringToDateTime(dateTimeStr, DATE_TIME_PATTERN);
    }


}
