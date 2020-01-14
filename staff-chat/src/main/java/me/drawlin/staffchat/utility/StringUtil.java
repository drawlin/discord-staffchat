package me.drawlin.staffchat.utility;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class StringUtil {

    public static String buildString(String[] args, int start) {
        return String.join(" ", Arrays.copyOfRange(args, start, args.length));
    }

}
