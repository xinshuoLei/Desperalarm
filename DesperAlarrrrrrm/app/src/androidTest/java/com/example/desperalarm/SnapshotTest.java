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
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * snapshot tests
 */
@RunWith(AndroidJUnit4.class)
public class SnapshotTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.alarm_setter", appContext.getPackageName());
    }
    @Test
    public void doDesperateScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.desperalarm, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doRegularScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.regular_alarm, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doHelpScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.help, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doTimerScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.timer, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doMainScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
}
