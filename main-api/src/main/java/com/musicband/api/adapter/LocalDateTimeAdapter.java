package com.musicband.api.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    // Формат: 2025-10-17T12:06:18Z (без миллисекунд и наносекунд)
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public LocalDateTime unmarshal(String v) {
        if (v == null) {
            return null;
        }
        return LocalDateTime.parse(v.replace("Z", ""), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String marshal(LocalDateTime v) {
        if (v == null) {
            return null;
        }
        LocalDateTime truncated = v.withNano(0);
        return truncated.atZone(ZoneOffset.UTC).format(FORMATTER);
    }
}