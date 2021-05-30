package space.sadnov.alenov.ecoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button btnmap, btnguide, btnpractice, btnsettings;
    RelativeLayout newroot; //записали родительский элемент


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //присвоили кнопку к кнопке на леяуте

        btnmap = findViewById(R.id.btnmap);
        btnguide = findViewById(R.id.btnguide);
        btnpractice = findViewById(R.id.btnpractice);
        btnsettings = findViewById(R.id.btnsettings);

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //переходим с первой на вторую активность
                Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });

    }
}