package space.sadnov.alenov.ecoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class PermissionActivity extends AppCompatActivity {

    private Button btnGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {   startActivity(new Intent(PermissionActivity.this, MapActivity.class));
            finish();
            return;
        }

        btnGrant = findViewById(R.id.btn_grant);

        btnGrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(PermissionActivity.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                startActivity(new Intent(PermissionActivity.this, MapActivity.class));
                                finish();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                if(permissionDeniedResponse.isPermanentlyDenied()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(PermissionActivity.this);
                                    builder.setTitle("Разрешение отказано")
                                            .setMessage("В разрешении на доступ к местоположению устройства постоянно отклоняется. Вам нужно перейти в настройки, чтобы разрешить разрешение.")
                                            .setNegativeButton("Отменить", null)
                                            .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent Intent = new Intent();
                                                    Intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    Intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                }
                                            })
                                            .show();

                                }   else {
                                    Toast.makeText(PermissionActivity.this, "Разрешение на пакет отказано", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        })
                        .check();


            }
        });


    }
}