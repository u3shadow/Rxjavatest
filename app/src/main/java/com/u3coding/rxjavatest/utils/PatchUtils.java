package com.u3coding.rxjavatest.utils;

/**
 * Created by ${U3} on 2017/3/17.
 */

public class PatchUtils {
    public static native int patch(String oldFilePatch, String newFilePath, String patchPath);
}
