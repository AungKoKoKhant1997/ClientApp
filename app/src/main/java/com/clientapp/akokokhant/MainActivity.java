package com.clientapp.akokokhant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.StrictMode;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TabLayout tabBar=findViewById(R.id.tabbar);
        StrictMode.ThreadPolicy mypolicy=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(mypolicy);
        final ViewPager pager=findViewById(R.id.viewpager);
        final ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        CollectionReference ref=db.collection("categories");
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot s:queryDocumentSnapshots)
                {
                    CategoryModel model=s.toObject(CategoryModel.class);
                    MovieFragment fragment=new MovieFragment();
                    fragment.name=model.categoryName;
                    adapter.addfragment(fragment,model.categoryName);
                }
                pager.setAdapter(adapter);
                tabBar.setupWithViewPager(pager);

            }
        });
    }
}
