package com.loveis.demo.module.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeAgoUtil {
	 public static String getTimeAgo(String logTimeString) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime logTime = LocalDateTime.parse(logTimeString, formatter);
	            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

	            Duration duration = Duration.between(logTime, now);
	            long seconds = duration.getSeconds();

	            if (seconds < 60) {
	                return "방금 전 접속";
	            } else if (seconds < 60 * 60) {
	                return (seconds / 60) + "분 전 접속";
	            } else if (seconds < 60 * 60 * 24) {
	                return (seconds / 3600) + "시간 전 접속";
	            } else if (seconds < 60L * 60 * 24 * 30) {
	                return (seconds / (60 * 60 * 24)) + "일 전 접속";
	            } else if (seconds < 60L * 60 * 24 * 365) {
	                return (seconds / (60 * 60 * 24 * 30)) + "달 전 접속";
	            } else {
	                return (seconds / (60 * 60 * 24 * 365)) + "년 전 접속";
	            }
	        } catch (Exception e) {
	            return "";
	        }
	    }
}
