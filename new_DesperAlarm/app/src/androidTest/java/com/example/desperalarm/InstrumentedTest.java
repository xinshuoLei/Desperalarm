package com.example.desperalarm;

import android.app.Application;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.data.AlarmRepository;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    /**
     * alarm database/repository used for testing
     */
    Application app = (Application) InstrumentationRegistry.getInstrumentation().
            getTargetContext().getApplicationContext();
    AlarmRepository repo = new AlarmRepository(app);
    /**
     * 2 ids used
     */

    @Test
    /**
     * check a regular alarm is successfully inserted into the database
     */
    public void checkRegularAlarmInsertion() {
        // insert a regular alarm
        int id = 12345;
        Alarm regular = new Alarm(id, 1, 1, "testRegular", 1, false,
                false, false, false, false, false, false,
                false, false, false);
        repo.insert(regular);
        // check values are correct
        assertNotNull(repo.getById(id));
        assertEquals("testRegular", repo.getById(id).getTitle());
        assertEquals(false, repo.getById(id).isDesperate());
        // clean up
        AlarmRepository.deleteById(id);
    }

    @Test
    /**
     * check a desperalarm is successfully inserted into the database
     */
    public void checkDesperalarmInsertion() {
        // insert a desper alarm
        int id = 12345678;
        Alarm desper = new Alarm(id, 1, 1, "testDesper", 1, false,
                false, false, false, false, false, false,
                false, false, true);
        repo.insert(desper);
        // check values are correct
        assertNotNull(repo.getById(id));
        assertEquals("testDesper", repo.getById(id).getTitle());
        assertEquals(true, repo.getById(id).isDesperate());
        // clean up
        AlarmRepository.deleteById(id);
    }

    @Test
    /**
     * check an alarm is successfully deleted from the database
     */
    public void checkalarmDeletion() {
        // insert a desper alarm
        int id = 123123;
        Alarm regular = new Alarm(id, 1, 1, "testDelete", 1, false,
                false, false, false, false, false, false,
                false, false, true);
        repo.insert(regular);
        // check insertion is successful
        assertNotNull(repo.getById(id));
        // delete the alarm
        AlarmRepository.deleteById(id);
        // check deletion is successful
        assertNull(repo.getById(id));
    }
}
