package info.meetyou.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem itemEvents;
    private ResideMenuItem itemPlaces;
    private ResideMenuItem itemBlogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        changeFragment(new BlogsFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);

        // create menu items;
        itemEvents = new ResideMenuItem(this, R.drawable.icon_calendar, "События");
        itemPlaces = new ResideMenuItem(this, R.drawable.icon_home,  "Места");
        itemBlogs = new ResideMenuItem(this, R.drawable.icon_profile, "Блоги");

        itemEvents.setOnClickListener(this);
        itemPlaces.setOnClickListener(this);
        itemBlogs.setOnClickListener(this);

        resideMenu.addMenuItem(itemEvents, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPlaces, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemBlogs, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.onInterceptTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemEvents){
            changeFragment(new EventsFragment());
        }else if (view == itemPlaces){
            changeFragment(new PlacesFragment());
        }else if (view == itemBlogs){
            changeFragment(new BlogsFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            //Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
