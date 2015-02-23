package com.jack.dicewars.dice_wars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Displays a simple tutorial on how to play DiceWars to the user.
 */
public class HowToPlayActivity extends FragmentActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private static final int NUM_PAGES = 2;
    private final int[] pages = new int[NUM_PAGES];

    private static final String HOW_TO_PLAY_FILENAME = "how_to_play_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize pages in order
        for (int n = 0; n < NUM_PAGES; n++) {
            final String drawableResources = "drawable";
            pages[n] = getResources().getIdentifier(HOW_TO_PLAY_FILENAME + (n + 1), drawableResources,
                    getPackageName());
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

    /**
     * Facilitates loading how to play images in a slider.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        /**
         * @param fm link between current fragment (the current image) and the activity
         *
         */
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Make page fragment
            SliderPagerFragment pageFragment = new SliderPagerFragment();
            //Create fragment's bundle and store corresponding page Id, then set the bundle
            Bundle pageInfo = new Bundle();
            pageInfo.putInt("pageId", pages[position]);
            pageFragment.setArguments(pageInfo);
            return pageFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
