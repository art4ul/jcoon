package com.art4ul.jcoon.util;

import com.art4ul.jcoon.context.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Artsem Semianenka  http://art4ul.com on 10/30/14.
 */
public class HttpRequestUtil {

    private static final String HEADER_REGEX_PATTERN = "[\\s]*([^=\\s]+)[\\s]*=[\\s]*([^=;\\s]+)[\\s]*[\\;]*";

    public static void addHeaders(Context context, String[] stringArray) {
        Pattern pattern = Pattern.compile(HEADER_REGEX_PATTERN);
        for (String str : stringArray) {
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                if (matcher.groupCount() == 2) {
                    String headerName = matcher.group(1);
                    String headerValue = matcher.group(2);
                    context.getHttpHeaders().add(headerName, headerValue);
                }
            }
        }
    }
}
