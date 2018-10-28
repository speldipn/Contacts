package com.example.jyo05.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int REQ_PERM = 1;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPerm();
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerView);

        Loader loader = new Loader();
        List<Contact> list = loader.getData(this);
        CustomAdapter customAdapter = new CustomAdapter(list);
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void checkPerm() {
        String perms[] = {Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS};

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(perms[0]) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(perms[1]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(perms, REQ_PERM);
            }
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_PERM) {
            for(int i = 0; i < grantResults.length; ++i) {
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한이 없으면 앱을 실핼할 수 없습니다", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            init();
        }
    }
}
