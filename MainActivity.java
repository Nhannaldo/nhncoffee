package com.example.appandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements MovieItemClick {

    DrawerLayout drawerLayout;
    private List<Slide> lstList;
    private ViewPager sliderpager;
    Toolbar toolbar;
    NavigationView navigationView;
    private TabLayout indicator;
    private RecyclerView MovieRV,MovieAnime,MovieHanhDong,MovieTinhCam,MovieKinhDi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getinit();
        actionToolbar();
        actionMenuClick();
        ActionSlider();
        PhimMoiList();
        AnimeList();
        PhimHanhDong();
        PhimTinhcam();
        PhimKinhDi();
    }

    private void PhimKinhDi() {
        MovieAdapter moviekinhdiAdapter = new MovieAdapter(this,DataSource.getKinhDi(),this);
        MovieKinhDi.setAdapter(moviekinhdiAdapter);
        MovieKinhDi.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void PhimTinhcam() {
        MovieAdapter movietinhcamAdapter = new MovieAdapter(this,DataSource.getTinhCam(),this);
        MovieTinhCam.setAdapter(movietinhcamAdapter);
        MovieTinhCam.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void PhimHanhDong() {
        MovieAdapter moviehanhdongAdapter = new MovieAdapter(this,DataSource.getHanhDong(),this);
        MovieHanhDong.setAdapter(moviehanhdongAdapter);
        MovieHanhDong.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void actionMenuClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        Intent intent= new Intent(getApplication(),MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Favorite:
                        Toast.makeText(MainActivity.this, "Favorite", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.History:
                        Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Search:
                        Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                        Intent intent1= new Intent(getApplication(),ActivitySearch.class);
                        startActivity(intent1);
                        break;
                    case R.id.Profile:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        Intent intent2= new Intent(getApplication(),ProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.Signout:
                        Notify.exit(MainActivity.this);
                        break;
                }
                return true;
            }
        });
    }
    private void AnimeList() {
        MovieAdapter movieanimeAdapter = new MovieAdapter(this,DataSource.getAnimeTV(),this);
        MovieAnime.setAdapter(movieanimeAdapter);
        MovieAnime.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void PhimMoiList() {
        MovieAdapter movieAdapter = new MovieAdapter(this,DataSource.getPhimMoi(),this);
        MovieRV.setAdapter(movieAdapter);
        MovieRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        //MovieRV.setLayoutManager(new GridLayoutManager(getApplication(),2));
    }

    private void actionToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menuicon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void getinit(){
        toolbar=  findViewById(R.id.toobar);
        drawerLayout= findViewById(R.id.drawrlayout);
        navigationView=findViewById(R.id.navigationView);
        indicator=findViewById(R.id.indicator);
        sliderpager= findViewById(R.id.slider);
        MovieRV=findViewById(R.id.Rv_movie);
        MovieAnime=findViewById(R.id.Rv_movie_anime);
        MovieHanhDong=findViewById(R.id.Rv_movie_hanhdong);
        MovieTinhCam=findViewById(R.id.Rv_movie_tinhcam);
        MovieKinhDi=findViewById(R.id.Rv_movie_kinhdi);
        indicator.setupWithViewPager(sliderpager,true);
    }
    private void ActionSlider(){
        lstList= new ArrayList<>();
        lstList.add(new Slide(R.drawable.slide1));
        lstList.add(new Slide(R.drawable.slide2));
        lstList.add(new Slide(R.drawable.slide3));
        lstList.add(new Slide(R.drawable.slide4));
        lstList.add(new Slide(R.drawable.anime5));
        SlideAdapter adapter= new SlideAdapter(this,lstList);
        sliderpager.setAdapter(adapter);
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SliderTime(),3000,5000);
    }
//    @Override
//    public void onMovie(Movie movie, ImageView moveImageView) {
//        Intent intent= new Intent(this,MovieDetailActivity.class);
//        intent.putExtra("title",movie.getTitle());
//        intent.putExtra("imgURL",Integer.valueOf(movie.getImage()));
//        //ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,moveImageView,"sharedName");
//        startActivity(intent);
//    }

    @Override
    public void onItemClick(int position) {

    }

    class SliderTime extends TimerTask{
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderpager.getCurrentItem()<lstList.size()-1){
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else{
                        sliderpager.setCurrentItem(0);
                    }
                }
            });

        }
    }
}