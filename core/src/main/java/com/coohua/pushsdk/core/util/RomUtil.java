package com.coohua.pushsdk.core.util;

import java.io.IOException;


/**
 * @author Leo Wang
 * 2017/8/14
 * desc: 获取rom 信息
 */

public class RomUtil {
    private static ClientTarget mTarget = null;
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";

    private static final String KEY_FLYME_ICON = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_PUBLISHED = "ro.flyme.published";
    private static final String KEY_FLYME_FLYME = "ro.meizu.setupwizard.flyme";


    /**
     * 华为rom
     */
    private static boolean isEMUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.containsKey(KEY_EMUI_VERSION_CODE);
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 小米rom
     */

    private static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            /*String rom = "" + prop.getProperty(KEY_MIUI_VERSION_CODE, null) + prop.getProperty(KEY_MIUI_VERSION_NAME, null)+prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null);
            Log.d("Android_Rom", rom);*/
            return prop.containsKey(KEY_MIUI_VERSION_CODE)
                    || prop.containsKey(KEY_MIUI_VERSION_NAME)
                    || prop.containsKey(KEY_MIUI_REAL_BLUR)
                    || prop.containsKey(KEY_MIUI_HANDY_MODE_SF);
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 魅族rom
     */
    private static boolean isFlyme() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.containsKey(KEY_FLYME_ICON)
                    || prop.containsKey(KEY_FLYME_PUBLISHED)
                    || prop.containsKey(KEY_FLYME_FLYME);
        } catch (final IOException e) {
            return false;
        }
    }


    public static ClientTarget rom() {
        if (mTarget != null)
            return mTarget;

        if (isEMUI()) {
            mTarget = ClientTarget.EMUI;
            return mTarget;
        }
        if (isMIUI()) {
            mTarget = ClientTarget.MIUI;
            return mTarget;
        }
        if (isFlyme()) {
            mTarget = ClientTarget.FLYME;
            return mTarget;
        }

        mTarget = ClientTarget.BAI_DU;
        return mTarget;
    }

}
