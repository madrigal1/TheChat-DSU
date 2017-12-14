package chat.main.dsu_chat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
    }
}
