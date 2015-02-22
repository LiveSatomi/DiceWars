package com.jack.dicewars.dice_wars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class HowToPlayActivity extends FragmentActivity {

    private ViewPager pager;

    private PagerAdapter pagerAdapter;

    private static final int NUM_PAGES = 2;

    private final int[] PAGES = new int[NUM_PAGES];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize pages in order
        for(int n = 0; n < NUM_PAGES; n++) {
            PAGES[n] = getResources().getIdentifier("how_to_play_page" + (n + 1), "drawable", getPackageName());
            Log.i("active", "talk " + ((Integer) PAGES[n]).toString());
        }

        // Load the pager containing layout
        setContentView(R.layout.a_how_to_play);

        // find the pager in the view
        pager = (ViewPager) findViewById(R.id.pager);

        // Creates an adapter (sliding transition handler) *But, what does this really do?*
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        // Sets the adapter to the pager
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            // only go back to the last page in the pager
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Make page fragment
            SliderPagerFragment pageFragment = new SliderPagerFragment();
            Log.i("active", Integer.toString(position));
            //Create fragment's bundle and store corresponding page Id, then set the bundle
            Bundle pageInfo = new Bundle();
            pageInfo.putInt("pageId", PAGES[position]);
            pageFragment.setArguments(pageInfo);
            return pageFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }



}
