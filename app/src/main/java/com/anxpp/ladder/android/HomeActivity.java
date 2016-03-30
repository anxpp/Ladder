package com.anxpp.ladder.android;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.anxpp.ladder.R;
import com.anxpp.ladder.android.view.tabcarousel.CarouselContainer;
import com.anxpp.ladder.android.view.tabcarousel.CarouselPagerAdapter;
import com.anxpp.ladder.android.view.tabcarousel.demo.ColorFragment;
import com.anxpp.ladder.android.view.tabcarousel.demo.DummyListFragment;
import com.anxpp.ladder.android.view.tabcarousel.demo.PagerAdapter;
import com.anxpp.ladder.android.view.tabcarousel.demo.TestFragment;

public class HomeActivity extends AppCompatActivity{

    private static final int FIRST_TAB = CarouselContainer.TAB_INDEX_FIRST;
    private static final int SECOND_TAB = CarouselContainer.TAB_INDEX_SECOND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //界面一体化 android4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏半透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_home);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {
                } else if (id == R.id.nav_slideshow) {
                } else if (id == R.id.nav_manage) {
                } else if (id == R.id.nav_share) {
                } else if (id == R.id.nav_send) {
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.openDrawer(GravityCompat.START);
//                startActivity(new Intent(HomeActivity.this,MainActivity.class));
            }
        });
        initContent();
    }

    private void initContent() {
        //获取资源
        final Resources res = getResources();
        //初始化头部
        final CarouselContainer carousel = (CarouselContainer) findViewById(R.id.carousel_header);
        // Indicates that the carousel should only show a fraction of the
        // secondary tab
        carousel.setUsesDualTabs(true);
        //添加文字说明
        carousel.setLabel(FIRST_TAB, "Android");
        carousel.setLabel(SECOND_TAB, "More");
        //添加背景图
        carousel.setImageDrawable(FIRST_TAB, res.getDrawable(R.mipmap.android));
        carousel.setImageDrawable(SECOND_TAB, res.getDrawable(R.mipmap.more));
        // The Bundle for the color fragment
        final Bundle blue = new Bundle();
        blue.putInt("color", Color.parseColor("#ff33b5e5"));
        // Initialize the pager adatper
        final PagerAdapter pagerAdapter = new PagerAdapter(this);
        pagerAdapter.add(DummyListFragment.class, new Bundle());
        pagerAdapter.add(TestFragment.class, blue);
        // Initialize the pager
        final ViewPager carouselPager = (ViewPager) findViewById(R.id.carousel_pager);
        // This is used to communicate between the pager and header
        carouselPager.setOnPageChangeListener(new CarouselPagerAdapter(carouselPager, carousel));
        carouselPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
