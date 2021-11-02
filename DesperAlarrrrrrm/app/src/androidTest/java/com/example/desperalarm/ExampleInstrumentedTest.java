package com.example.desperalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.ViewHelpers;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.alarm_setter", appContext.getPackageName());
    }
    @Test
    public void doScreenshot() {
        /*
         * Create and set up your view some how. This might be inflating,
         * or creating from a view class. You might want to set properties
         * on the view.
         */
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null, false);

        /*
         * Measure and layout the view. In this example we give an exact
         * width but all the height to be WRAP_CONTENT.
         */
        ViewHelpers.setupView(view)
                .setExactWidthDp(300)
                .layout();

        /*
         * Take the actual screenshot. At the end of this call the screenshot
         * is stored on the device, and the gradle plugin takes care of
         * pulling it and displaying it to you in nice ways.
         */
        Screenshot.snap(view)
                .record();
    }
}