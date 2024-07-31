package com.example.mytodoapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://172.30.1.22:5001";
    private WebView webView;

    private ImageView imageView;

    private final String spValueText = "SharedPreference_Data";

    private String email_id;
    private String nick_name;
    private String user_id;

    private RecyclerView rcView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences(spValueText, Context.MODE_PRIVATE);

        // 로그인 상태 확인
        boolean isLogged = sp.getBoolean("isLogged", false);
        if (!isLogged) {
            // 로그인되어 있지 않으면 로그인 화면으로 이동
            launcher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            email_id = result.getData().getStringExtra("EMAIL_ID");
                            nick_name = result.getData().getStringExtra("NICK_NAME");
                            user_id = result.getData().getStringExtra("USER_ID");

                            TextView textView = findViewById(R.id.textViewAccount);
                            textView.setText(nick_name);

                            getTodos();
                        }
                    }
            );

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            launcher.launch(intent);
        }
    }

    private void getTodos()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TodoApi todoApi = retrofit.create(TodoApi.class);

        // RequestData requestData = new RequestData("test@gmail.com", "1234");
        Call<MyTodos> call = todoApi.getTodos(email_id, user_id);
        call.enqueue(new Callback<MyTodos>() {
            @Override
            public void onResponse(Call<MyTodos> call, Response<MyTodos> response) {
                if (response.isSuccessful()) {
                    MyTodos myTodos = response.body();
//                    Toast toast = Toast.makeText(getApplicationContext(), todoResponse.toString(), Toast.LENGTH_SHORT);
//                    toast.show();

                    initTodosForUser(myTodos);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<MyTodos> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void initTodosForUser(MyTodos myTodos) {
        rcView = findViewById(R.id.rcViewTodos);
        rcView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(layoutManager);

        adapter = new TodoAdaptor(myTodos);
        rcView.setAdapter(adapter);
    }

}