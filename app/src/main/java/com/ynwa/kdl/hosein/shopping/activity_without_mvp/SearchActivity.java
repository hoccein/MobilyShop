package com.ynwa.kdl.hosein.shopping.activity_without_mvp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;
import com.ynwa.kdl.hosein.shopping.realm_db.PhoneDAO;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.iv_back_search_activity)
    ImageView ivBack;
    @BindView(R.id.et_input_search_activity)
    EditText etInput;
    @BindView(R.id.iv_clear_search_activity)
    ImageView ivClear;

    @BindView(R.id.tv_result)
    TextView tvResult;

    PhoneDAO phoneDAO = new PhoneDAO();
    List<Phone> phones;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);




        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (timer != null)
                    timer.cancel();
            }

            @Override
            public void afterTextChanged(Editable s) {

                final String str = String.valueOf(s);

                //Log.i("textchange_test", "afterTextChanged: "+ s);
                if (s.length() == 0)
                    goneClearItemOfToolbar();
                else {
                    visibleClearItemOfToolbar();
                   /* timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                        }
                    }, 500);*/

                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           phones = phoneDAO.findPhonesByName( String.valueOf(str) );
                           Log.i("textchange_test", "size: "+ phones.size());

                           tvResult.setText("");
                           for (Phone p : phones){
                               tvResult.append(p.getId()+ ") "+ p.getName()+ " "+ p.getBrand());
                               tvResult.append("\n\n");
                           }
                       }
                   }, 500);

                }
            }
        });

    }

    @OnClick(R.id.iv_clear_search_activity)
    public void clear(){
        etInput.setText(null);
    }

    @OnClick(R.id.iv_back_search_activity)
    public void back(){
        finish();
    }

    public void goneClearItemOfToolbar(){
        ivClear.setVisibility(View.GONE);
        tvResult.setVisibility(View.GONE);
    }

    public void visibleClearItemOfToolbar(){
        ivClear.setVisibility(View.VISIBLE);
        tvResult.setVisibility(View.VISIBLE);
    }
}
