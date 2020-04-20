package net.larntech.retrofit_post;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TextInputEditText username,fname,lname,email;
    Button reg_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        reg_user = findViewById(R.id.btn_register);


        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(createRequest());
            }
        });
    }


    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username.getText().toString());
        userRequest.setEmail(email.getText().toString());
        userRequest.setLast_name(lname.getText().toString());
        userRequest.setFirst_name(fname.getText().toString());

        return userRequest;
    }



    public void saveUser(UserRequest userRequest){

        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Saved successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Request failed",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Request failed "+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

}
