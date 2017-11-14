package com.example.morozione.testreminder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.morozione.testreminder.adapter.TabAdapter;
import com.example.morozione.testreminder.dialog.AddingDialogFragment;
import com.example.morozione.testreminder.fragment.SplashFragment;

public class MainActivity extends AppCompatActivity implements AddingDialogFragment.AddingTaskListener {
    private FragmentManager fragmentManager;
    private PreferenceHelper preferenceHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.i_m_dont_show_splash);
        menuItem.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IN_VISIBLE));
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.getInstance().init(this);
        preferenceHelper = PreferenceHelper.getInstance();

        fragmentManager = getSupportFragmentManager();

        runSplash();
        setUI();
    }

    private void runSplash() {
        if (preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IN_VISIBLE)) {
            SplashFragment splashFragment = new SplashFragment();
            fragmentManager
                    .beginTransaction().addToBackStack(null)
                    .replace(R.id.container, splashFragment)
                    .commit();
        }
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.current_task));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.done_task));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TabAdapter tabAdapter = new TabAdapter(fragmentManager, 2);

        viewPager.setAdapter(tabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddingDialogFragment dialogFragment = new AddingDialogFragment();
                dialogFragment.show(fragmentManager, "Dialog added task");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.i_m_dont_show_splash) {
            item.setChecked(!item.isChecked());
            preferenceHelper.putBoolean(PreferenceHelper.SPLASH_IN_VISIBLE, item.isChecked());
        }
        return true;
    }

    @Override
    public void onTaskAdded() {
        Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskAddingCancel() {
        Toast.makeText(this, "Task no added", Toast.LENGTH_SHORT).show();
    }
}
