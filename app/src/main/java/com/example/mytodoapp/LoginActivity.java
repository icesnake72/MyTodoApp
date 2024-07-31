package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private final String BASE_URL = "http://172.30.1.22:5001";
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                TodoApi todoApi = retrofit.create(TodoApi.class);

                // RequestData requestData = new RequestData("test@gmail.com", "1234");
                Call<TodoResponse> call = todoApi.tryLogin("test@gmail.com", "1234");

                call.enqueue(new Callback<TodoResponse>() {
                    @Override
                    public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                        if (response.isSuccessful()) {
                            TodoResponse todoResponse = response.body();
//                    Toast toast = Toast.makeText(getApplicationContext(), todoResponse.toString(), Toast.LENGTH_SHORT);
//                    toast.show();
                            String email_id = todoResponse.getEmail_id();
                            String nick_name = todoResponse.getNick_name();
                            String user_id = todoResponse.getId();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("EMAIL_ID", email_id);
                            resultIntent.putExtra("NICK_NAME", nick_name);
                            resultIntent.putExtra("USER_ID", user_id);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TodoResponse> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}