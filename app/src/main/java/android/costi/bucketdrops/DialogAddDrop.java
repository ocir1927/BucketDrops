package android.costi.bucketdrops;

import android.app.DialogFragment;
import android.costi.bucketdrops.domain.Drop;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Costi on 13.02.2017.
 */

public class DialogAddDrop extends DialogFragment {
    Button btnAddADrop;
    EditText etDrop;
    ImageButton btnClose;
    DatePicker datePicker;

    public DialogAddDrop() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Realm.init(getContext());
        return inflater.inflate(R.layout.add_a_drop,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnClose= (ImageButton) view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        etDrop= (EditText) view.findViewById(R.id.et_add_drop);
        btnAddADrop= (Button) view.findViewById(R.id.btn_add_it);
        datePicker= (DatePicker) view.findViewById(R.id.date_picker);
        btnAddADrop.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("btn clicked");
                addDrop();
            }

        });
    }

    private void addDrop() {
        String what=etDrop.getText().toString();
        long now=System.currentTimeMillis();
        Drop drop=new Drop(what,now,0,false);

        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        Realm realm=Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(drop);
        realm.commitTransaction();

/*        RealmResults<Drop> rez= realm.where(Drop.class).findAll();
        for(Drop d : rez){
            System.out.println(d.toString());
        }*/

        realm.close();
    }
}
