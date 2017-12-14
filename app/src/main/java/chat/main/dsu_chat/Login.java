package chat.main.dsu_chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by infan_000 on 12/14/2017.
 */


public class Login extends Activity {
    private EditText username;
    private EditText password;
    private Button login;
    private DatabaseReference mref;


    @Override
    public void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.simple_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        mref = FirebaseDatabase.getInstance().getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(username.getText().toString())){
                            mref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot){
                                    Users user = dataSnapshot.getValue(Users.class);
                                    if(password.getText().toString() == user.password){
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        intent.putExtra("user", username.getText().toString());
                                    }else{
                                        Toast.makeText(Login.this, "Invalid Login!", Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(Login.this, "Database Error", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
