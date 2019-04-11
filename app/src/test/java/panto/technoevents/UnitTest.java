package panto.technoevents;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import panto.technoevents.network.DjRepository;

import static org.junit.Assert.*;

public class UnitTest {


    @Test
    public void is_dj_repository_instance() {

        DjRepository expected = DjRepository.getInstance();

        Assert.assertEquals(expected, DjRepository.getInstance());

    }
}