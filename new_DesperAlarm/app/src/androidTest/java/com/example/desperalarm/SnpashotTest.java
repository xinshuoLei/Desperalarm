package com.example.desperalarm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.ViewHelpers;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SnpashotTest {
    @Test
    public void doAboutScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.about, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doDesperateQuestionScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_question, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doRingScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_ring, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doCreateAlarmScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_createalarm, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doListAlarmScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_listalarms, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doStartScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_start, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doItemAlarmScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_alarm, null, false);
        ViewHelpers.setupView(view).setExactWidthDp(300).layout();
        Screenshot.snap(view).record();
    }
    @Test
    public void doRegularAlarmGuideScreenshot() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.regular_alarm_guide, null, false);
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

}
