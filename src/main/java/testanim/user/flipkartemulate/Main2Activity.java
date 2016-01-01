package testanim.user.flipkartemulate;


import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;



import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;

import android.view.LayoutInflater;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.*;
import me.relex.circleindicator.CircleIndicator;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

RecyclerView  mRecyclerView;
    int numOfRows = 11;
    int numOfColumns = 9;
    final int maxMemorySize = (int) Runtime.getRuntime().maxMemory() / 1024;
    List<Flipkart> listOfItems;
    List<List<Flipkart>> listOfListOfItems;
    DisplayMetrics metrics = new DisplayMetrics();
    MyFlipkartAdapter flipkartadapter;
    private LinearLayoutManager mLayoutManager;
    private static LruCache<String, Bitmap> mMemoryCache;
    public static final String LOGTAG ="carousels";
    ViewPager pager;
   CircleIndicator indicator;

    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(1.3f);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mRecyclerView= (RecyclerView) findViewById(R.id.home_recyclerView);
        pager= (ViewPager) findViewById(R.id.viewpager_default);
        indicator= (CircleIndicator) findViewById(R.id.indicator);
        pager.setAdapter(new SlideAdapter(this));

indicator.setViewPager(pager);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));




        getSupportActionBar().setIcon(android.R.drawable.arrow_down_float);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

      // LinearLayoutManager llm = new LinearLayoutManager(this);

        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        /*
String images[]={" http://sherly.mobile9.com/download/thumb/446/120/colourfulf_emxqzqxw.jpg","http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-img1-averages.jpg",
        "http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-1080p-wallpaper-269x170.jpg","http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-background-wallpaper-free-269x170.jpg","http://wallpapersshd.com/wp-content/uploads/2014/09/love-colorful-hd-wallpaper-269x170.jpg",
        "http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-flower-hd-wallpaper-free-269x170.jpg","http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-flower-hd-images-269x170.jpg",
        "http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-flower-hd-images-269x170.jpg","http://wallpapersshd.com/wp-content/uploads/2014/09/colorful-cute-hd-wallpaper-269x170.jpg"};
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
*/
        Fragment demoFragment=Fragment.instantiate(this,DemoListFragment.class.getName());

    listOfListOfItems = new ArrayList<>();
for (int i=0;i<numOfRows;i++) {
  listOfItems = new ArrayList<>();
    for (int j = 0; j < numOfColumns; j++) {
        int drawableResourceId = this.getResources().getIdentifier("img"+String.valueOf(1+j + (10*i)), "drawable", this.getPackageName());
        Flipkart item = new Flipkart("img"+String.valueOf(j+1),String.valueOf(1+j + (10*i)),drawableResourceId);
        listOfItems.add(item);
    }
    listOfListOfItems.add(listOfItems);
}
        MainRecyclerAdapter mainRecyclerAdapter=new MainRecyclerAdapter(this,listOfListOfItems);
        /*
        MainRecyclerAdapter is the adapter  which is used for inflating individual rows and the individual rows
        itself contains another recyclerview whose layout configuration is horizontal
         */
        mRecyclerView.setAdapter(mainRecyclerAdapter);
     //   flipkartadapter=new MyFlipkartAdapter(this, flipkartitem);
      //  recyclerView.setAdapter(new MyFlipkartAdapter(this, generatePalettes()));
//mMemoryCache=new LruCache<>()

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                                              @Override
                                              public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                  super.onScrolled(recyclerView, dx, dy);
                                              }

                                              @Override
                                              public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                  super.onScrollStateChanged(recyclerView, newState);
                                                  switch(newState){

                                                      case RecyclerView.SCROLL_STATE_IDLE:

                                                          Log.i(LOGTAG,"X = " + (mRecyclerView.getX() + mRecyclerView.getWidth() )+ " and Y = " + (mRecyclerView.getY()+ mRecyclerView.getHeight()));
                                                          float targetBottomPosition1 = mRecyclerView.getY();
                                                          float targetBottomPosition2 = mRecyclerView.getY() + mRecyclerView.getHeight();

                                                          Log.i(LOGTAG,"targetBottomPosition1 = " + targetBottomPosition1);
                                                          Log.i(LOGTAG,"targetBottomPosition2 = " + targetBottomPosition2);

                                                          View v1 = mRecyclerView.findChildViewUnder(400, targetBottomPosition1);
                                                          View v2 = mRecyclerView.findChildViewUnder(400, targetBottomPosition2);

                                                          float y1 = targetBottomPosition1;
                                                          if(v1!=null){
                                                              y1 =v1.getY();
                                                          }

                                                          float y2 = targetBottomPosition2;
                                                          if(v2!=null){
                                                              y2 =v2.getY();
                                                          }

                                                          Log.i(LOGTAG,"y1 = " + y1);
                                                          Log.i(LOGTAG,"y2 = " + y2);

                                                          float dy1 = Math.abs(y1-mRecyclerView.getY() );
                                                          float dy2 = Math.abs(y2-(mRecyclerView.getY()+ mRecyclerView.getHeight()));

                                                          Log.i(LOGTAG,"dy1 = " + dy1);
                                                          Log.i(LOGTAG,"dy2 = " + dy2);

                                                          float visiblePortionOfItem1 = 0;
                                                          float visiblePortionOfItem2 = 0;

                                                          if(y1<0 && v1 != null){
                                                              visiblePortionOfItem1 = v1.getHeight() - dy1;
                                                          }

                                                          if(v2 != null){
                                                              visiblePortionOfItem2 = v2.getHeight() - dy2;
                                                          }


                                                          int position = 0;
                                                          if(visiblePortionOfItem1<=visiblePortionOfItem2){
                                                              position = mRecyclerView.getChildPosition(mRecyclerView.findChildViewUnder(0, targetBottomPosition1));
                                                          }else{

                                                              position = mRecyclerView.getChildPosition(mRecyclerView.findChildViewUnder(0, targetBottomPosition2));
                                                          }
                                                          mRecyclerView.scrollToPosition(position);


                                                          break;

                                                      case RecyclerView.SCROLL_STATE_DRAGGING:

                                                          break;

                                                      case RecyclerView.SCROLL_STATE_SETTLING:

                                                          break;

                                                  }
                                              }
                                          });

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public List<Flipkart> getListOfItems() {
        return listOfItems;
    }
    /*
    private ArrayList<Flipkart> generatePalettes() {
        ArrayList<Flipkart> palettes = new ArrayList<>();
        flipkartitem.add(new Flipkart("name1", " http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg","status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg","status1"));
        flipkartitem.add(new Flipkart("name1", " http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg","status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg","status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg","status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        flipkartitem.add(new Flipkart("name1", "  http://www.sellingtosmallbusinesses.com/wp-content/uploads/2009/02/small-business-averages.jpg", "status1"));
        return flipkartitem;
    }

*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        switch (id) {
            case R.id.nav_camara:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class DemoListFragment extends Fragment
    {



        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);

        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }


}
