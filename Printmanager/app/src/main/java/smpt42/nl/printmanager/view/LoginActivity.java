package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import smpt42.nl.printmanager.R;

public class LoginActivity extends Activity {

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.requestFocus();

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

        loginBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                LogIn(hasFocus, username, password);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                //startActivity(intent);
            }
        });

        findViewById(R.id.activity_login_parent).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard(LoginActivity.this);
                return false;
            }
        });
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

    public void SetEditText(boolean hasFocus, String editTextName, EditText editText) {
        if (hasFocus) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            if (editText.getText().toString().equals(editTextName)) {
                editText.setText("");
                if (editTextName.equals("Password")) {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                editText.setTextColor(getResources().getColor(R.color.red));
            }
        } else {
            if (editText.getText().toString().equals("")) {
                editText.setText(editTextName);

                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                editText.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }

    public void LogIn(boolean hasFocus, EditText username, EditText password) {
        //deze code is als de database werkt
        /*if(hasFocus)
        {
            if(Database.getInstance().login(username.getText().toString(), password.getText().toString()))
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                builder.setMessage("Wrong username or password");
                builder.setTitle("Failed to login");

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }*/
        if (hasFocus) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(intent);
        }


    }
}
