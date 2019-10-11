package com.oldbie.apflux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.oldbie.apflux.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById( R.id.ivBackground );
        collapsingToolbarLayout=findViewById( R.id.collapsingLayout );
        tabLayout=findViewById( R.id.tabLayout );
        viewPager=(ViewPager) findViewById( R.id.viewPaper );

        adapter = new FragmentAdapter( getSupportFragmentManager(),this );
        tabLayout.setupWithViewPager( viewPager );
        viewPager.setAdapter( adapter );

        collapsingToolbarLayout.setTitle( adapter.getPageTitle( 0 ) );


        AppBarLayout appbar = findViewById(R.id.appbarMain);
        appbar.setElevation( 0 );
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        collapsingToolbarLayout.setTitle( adapter.getPageTitle( position ) );
                        imageView.setImageResource( R.drawable.bg_gradient_1 );
                        break;
                    case 1:
                        collapsingToolbarLayout.setTitle( adapter.getPageTitle( position ) );
                        imageView.setImageResource( R.drawable.bg_gradient_2 );
                        break;
                    case 2:
                        collapsingToolbarLayout.setTitle( adapter.getPageTitle( position ) );
                        imageView.setImageResource( R.drawable.bg_gradient_3);
//                        tabLayout.getTabAt( position ).setIcon( R.drawable.socials_color );

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
    }
}
