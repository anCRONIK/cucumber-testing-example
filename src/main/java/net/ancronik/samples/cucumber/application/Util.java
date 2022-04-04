package net.ancronik.samples.cucumber.application;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

/**
 * General utility class.
 *
 * @author Nikola Presecki
 */
public abstract class Util {

    /**
     * Formatter which should be used for all timestamp in applications.
     */
    public static final DateTimeFormatter APP_DATE_TIME_FORMATTER = ISO_LOCAL_DATE_TIME.withZone(ZoneId.of("UTC"));

}
