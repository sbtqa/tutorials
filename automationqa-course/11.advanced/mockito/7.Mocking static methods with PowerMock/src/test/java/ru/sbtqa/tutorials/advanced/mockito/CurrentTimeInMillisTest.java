package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * С помощью PowerMock теперь можно мокать даже системные классы. Раньше этого было не сделать.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(System.class)
public class CurrentTimeInMillisTest {
    @Before
    public void setUp() {
        mockStatic(System.class);
    }

    @Test
    public void testCurrentTimeInMillis() {
        when(currentTimeMillis()).thenReturn(100L);

        long value = currentTimeMillis();

        assertEquals(100L, value);
    }
}
