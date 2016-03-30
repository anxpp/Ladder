package com.anxpp.ladder.android.view.tabcarousel.demo;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.anxpp.ladder.R;
import com.anxpp.ladder.android.view.tabcarousel.CarouselContainer;
import com.anxpp.ladder.android.view.tabcarousel.CarouselPagerAdapter;


/**
 * @author Andrew Neal (andrewdneal@gmail.com)
 */
public class MainActivity extends FragmentActivity {

    /**
     * First tab index
     */
    private static final int FIRST_TAB = CarouselContainer.TAB_INDEX_FIRST;

    /**
     * Second tab index
     */
    private static final int SECOND_TAB = CarouselContainer.TAB_INDEX_SECOND;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.carousel_container);

        // Resources
        final Resources res = getResources();

        // Initialize the header
        final CarouselContainer carousel = (CarouselContainer) findViewById(R.id.carousel_header);
        // Indicates that the carousel should only show a fraction of the
        // secondary tab
        carousel.setUsesDualTabs(true);
        // Add some text to the labels
        carousel.setLabel(FIRST_TAB, "Lost in Translation");
        carousel.setLabel(SECOND_TAB, "The Prestige");
        // Add some images to the tabs
        carousel.setImageDrawable(FIRST_TAB, res.getDrawable(R.mipmap.android));
        carousel.setImageDrawable(SECOND_TAB, res.getDrawable(R.mipmap.more));

        // The Bundle for the color fragment
        final Bundle blue = new Bundle();
        blue.putInt("color", Color.parseColor("#ff33b5e5"));

        // Initialize the pager adatper
        final PagerAdapter pagerAdapter = new PagerAdapter(this);
        pagerAdapter.add(DummyListFragment.class, new Bundle());
        pagerAdapter.add(ColorFragment.class, blue);

        // Initialize the pager
        final ViewPager carouselPager = (ViewPager) findViewById(R.id.carousel_pager);
        // This is used to communicate between the pager and header
        carouselPager.setOnPageChangeListener(new CarouselPagerAdapter(carouselPager, carousel));
        carouselPager.setAdapter(pagerAdapter);
    }

}
