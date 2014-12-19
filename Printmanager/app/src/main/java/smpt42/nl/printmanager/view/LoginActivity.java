package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.Database;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button   loginBtn = (Button)   findViewById(R.id.loginBtn);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                SetEditText(hasFocus, "Username", username);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                SetEditText(hasFocus, "Password", password);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Database.getInstance().login(username.getText().toString(), password.getText().toString()))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
                }
            }
        });
        // Create login button's setOnClickListener below.
        // The onClickListener will call Database.getInstance().login();
        // This method returns a boolean to check if it failed or succeeded.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetEditText(boolean hasFocus, String editTextName, EditText editText)
    {
        if (hasFocus) {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            if(editText.getText().toString().equals(editTextName))
            {
                editText.setText("");
                if(editTextName.equals("Password"))
                {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                editText.setTextColor(getResources().getColor(R.color.red));
            }
        } else {
            if(editText.getText().toString().equals(""))
            {
                editText.setText(editTextName);
                if(editTextName.equals("Password"))
                {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                editText.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }
}
