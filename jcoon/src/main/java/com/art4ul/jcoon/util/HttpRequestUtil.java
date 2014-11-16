/*
 * Copyright (C) 2014 Artsem Semianenka (http://art4ul.com/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.art4ul.jcoon.util;

import com.art4ul.jcoon.context.Context;
import com.art4ul.jcoon.exception.ContextException;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestUtil {

    private static final String HEADER_REGEX_PATTERN = "[\\s]*([^=\\s]+)[\\s]*=[\\s]*([^=;\\s]+)[\\s]*[;]*";

    public static void addHeaders(Context context, String[] stringArray) {
        if (context == null) {
            throw new ContextException("Context is null");
        }
        if (stringArray != null) {
            Pattern pattern = Pattern.compile(HEADER_REGEX_PATTERN);
            for (String str : stringArray) {
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    if (matcher.groupCount() == 2) {
                        String headerName = matcher.group(1);
                        String headerValue = matcher.group(2);
                        context.getHttpHeaders().add(headerName.trim(), headerValue.trim());
                    }
                }
            }
        }
    }

    public static List<MediaType> getAcceptedTypes(String[] types) {
        List<MediaType> result = new ArrayList<MediaType>();
        if (types == null) {
            return result;
        }
        for (String type : types) {
            result.add(new MediaType(type));
        }
        return result;
    }
}
