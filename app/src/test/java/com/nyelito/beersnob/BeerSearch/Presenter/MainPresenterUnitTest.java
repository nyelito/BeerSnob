package com.nyelito.beersnob.BeerSearch.Presenter;

import com.nyelito.beersnob.MockCreator;
import com.nyelito.beersnob.BeerSearch.Model.Beer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainPresenterUnitTest {

    private MainPresenter mainPresenter;

    @Before
    public void setUp(){
        mainPresenter = new MainPresenter();
    }

    @Test
    public void filterVerifiedBeersTest() throws Exception {

        MockCreator mockCreator = new MockCreator();
        List<Beer> beerList = new ArrayList<>();

        Beer beerOne = new Beer();
        beerOne.setStatus("verified");
        Beer beerTwo = new Beer();
        beerTwo.setStatus("unverified");

        beerList.add(beerOne);
        beerList.add(beerTwo);

        List<Beer> verifiedBeers = mainPresenter.filterVerifiedBeers(beerList);

        assertEquals(1, verifiedBeers.size());
        assertTrue("verified".equalsIgnoreCase(verifiedBeers.get(0).getStatus()));


    }
}