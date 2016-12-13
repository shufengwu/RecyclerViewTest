package com.sfw.recyclerviewtest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm = null;
    FragmentTransaction translation = null;
    LinearLayoutFragment llf = null;
    GridLayoutFragment glf = null;
    StaggeredGridLayoutFragment sglf = null;

    int fragment_number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();
        translation = fm.beginTransaction();
        llf = new LinearLayoutFragment();
        translation.replace(R.id.container, llf);
        translation.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "LinearLayoutManager");
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "GridLayoutManager");
        menu.add(Menu.NONE,Menu.FIRST+3,3,"StaggeredGridLayoutManager");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fm = getFragmentManager();
        translation = fm.beginTransaction();
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                //if (translation.isEmpty()) {
                    llf = new LinearLayoutFragment();
                    translation.replace(R.id.container, llf);
                fragment_number = 1;
                //}

                break;
            case Menu.FIRST + 2:
                //if (translation.isEmpty()) {
                    glf = new GridLayoutFragment();
                    translation.replace(R.id.container, glf);
                fragment_number = 2;
                //}
                break;
            case Menu.FIRST+3:
                sglf = new StaggeredGridLayoutFragment();
                translation.replace(R.id.container,sglf);
                fragment_number = 3;
                break;
            case R.id.menu_add:
                if(fragment_number == 1){
                    llf.addData(5);
                }else if(fragment_number == 2){
                    glf.addData(5);
                }else if(fragment_number == 3){

                }
                break;
            case R.id.menu_delete:
                if(fragment_number == 1){
                    llf.removeData(5);
                }else if(fragment_number == 2){
                    glf.removeData(5);
                }else if(fragment_number == 3){

                }
                break;
        }
        translation.commit();
        return false;
    }
}
