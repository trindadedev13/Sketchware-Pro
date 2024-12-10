package pro.sketchware.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.Constructor;

public class DynamicViewCreator {

    private static final String TAG_INCLUDE = "include";
    private static final String TAG_BLINK = "blink";
    private static final String[] sClassPrefixList = {
        "android.widget.", "android.view.", "android.webkit.", "android.app."
    };

    public static View create(@NonNull String name, @NonNull Context ctx) {
        View v = null;

        if (isFullPackage(name)) {
            v = createViewByFullName(name, ctx);
        } else {
            v = createSpecialView(name, ctx);
            if (v == null) {
                for (String prefix : sClassPrefixList) {
                    v = createViewByFullName(prefix + name, ctx);
                    if (v != null) return v;
                }
            }
        }

        if (v == null) return createDefaultView(ctx, name);
        return v;
    }

    public static View create(@NonNull String name, @NonNull Context ctx, int defStyle) {
        View v = null;

        if (isFullPackage(name)) {
            v = createViewByFullName(name, ctx, defStyle);
        } else {
            v = createSpecialView(name, ctx);
            if (v == null) {
                for (String prefix : sClassPrefixList) {
                    v = createViewByFullName(prefix + name, ctx, defStyle);
                    if (v != null) return v;
                }
            }
        }

        if (v == null) return createDefaultView(ctx, name);
        return v;
    }

    private static View createDefaultView(@NonNull Context ctx, String text) {
        View defaultView = new View(ctx);
        defaultView.setContentDescription("Default view for: " + text);
        return defaultView;
    }

    private static View createViewByFullName(@NonNull String name, @NonNull Context ctx) {
        try {
            Class<?> clazz = Class.forName(name);
            Constructor<?> constructor = clazz.getDeclaredConstructor(Context.class);
            constructor.setAccessible(true);
            return (View) constructor.newInstance(ctx);
        } catch (Exception e) {
        }
        return null;
    }

    private static View createViewByFullName(@NonNull String name, @NonNull Context ctx, int defStyle) {
        try {
            Class<?> clazz = Class.forName(name);
            Constructor<?> constructor = clazz.getDeclaredConstructor(Context.class, AttributeSet.class, int.class);
            constructor.setAccessible(true);
            return (View) constructor.newInstance(ctx, null, defStyle);
        } catch (Exception e) {
        }
        return null;
    }

    private static View createSpecialView(@NonNull String tag, @NonNull Context ctx) {
        if (TAG_INCLUDE.equals(tag)) {
            return createDefaultView(ctx, tag);
        } else if (TAG_BLINK.equals(tag)) {
            View blinkView = new View(ctx);
            blinkView.setContentDescription("Blink layout placeholder");
            return blinkView;
        }
        return null;
    }

    public static boolean isFullPackage(@NonNull String s) {
        return s.contains(".");
    }

    public static String getNameFromTag(@NonNull String s) {
        try {
            if (isFullPackage(s)) return s.substring(s.lastIndexOf(".") + 1);
        } catch (Exception e) {
        }
        return s;
    }
}
