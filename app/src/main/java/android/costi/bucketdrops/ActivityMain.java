package android.costi.bucketdrops;

import android.app.DialogFragment;
import android.costi.bucketdrops.Adapter.AdapterDrops;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ActivityMain extends AppCompatActivity {

    Toolbar mainToolbar;
    Button addButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainToolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        initBackgroundImage();
        addButton= (Button) findViewById(R.id.btn_add_drop);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ActivityMain.this, "Buttonul a fost apasat", Toast.LENGTH_SHORT).show();
                showDialogAdd();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.rv_drop);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new AdapterDrops(this));


    }

    private void showDialogAdd() {
        DialogFragment dialogAddFragment=new DialogAddDrop();
        dialogAddFragment.show(getFragmentManager(),"Add");
    }

    private void initBackgroundImage() {
        ImageView background=(ImageView) findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.background)
                .centerCrop()
                .into(background);
    }
}
