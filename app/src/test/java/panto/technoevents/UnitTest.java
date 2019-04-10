package panto.technoevents;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import panto.technoevents.network.DjRepository;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {


    @Test
    public void is_dj_repository_instance() {

        DjRepository expected = DjRepository.getInstance();

        Assert.assertEquals(expected, DjRepository.getInstance());
    }
}