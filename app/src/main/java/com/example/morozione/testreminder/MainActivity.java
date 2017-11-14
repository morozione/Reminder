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
import com.example.morozione.testreminder.fragment.CurrentTaskFragment;
import com.example.morozione.testreminder.fragment.DoneTaskFragment;
import com.example.morozione.testreminder.fragment.SplashFragment;
import com.example.morozione.testreminder.model.ModelTask;

public class MainActivity extends AppCompatActivity implements AddingDialogFragment.AddingTaskListener {
    public static FragmentManager fragmentManager;
    private PreferenceHelper preferenceHelper;
    private TabAdapter tabAdapter;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;

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
        tabAdapter = new TabAdapter(fragmentManager, 2);

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

        currentTaskFragment = (CurrentTaskFragment) tabAdapter.getItem(TabAdapter.CURRENT_TASK_FRAGMENT);
        doneTaskFragment = (DoneTaskFragment) tabAdapter.getItem(TabAdapter.DONE_TASK_FRAGMENT);

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
    public void onTaskAdded(ModelTask newTask) {
        currentTaskFragment.addTask(newTask);
    }

    @Override
    public void onTaskAddingCancel() {
        Toast.makeText(this, "Task no added", Toast.LENGTH_SHORT).show();
    }
}
