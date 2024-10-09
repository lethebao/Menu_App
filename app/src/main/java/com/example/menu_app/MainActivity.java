package com.example.menu_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnSignIn;

    // Replace with your actual mydtu email
    private final String MYDTU_EMAIL = "lethebao1@dtu.edu.vn";
    private final String PASSWORD = "abcabc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_sign_in), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleSignIn() {
        String emailInput = etEmail.getText().toString().trim();
        String passwordInput = etPassword.getText().toString();

        if (emailInput.equalsIgnoreCase(MYDTU_EMAIL) && passwordInput.equals(PASSWORD)) {
            // Credentials are correct, navigate to Layout 2 (MenuActivity)
            Intent intent = new Intent(MainActivity.this, com.example.menu_app.MenuActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the Sign In activity
        } else {
            // Credentials are incorrect, show a Toast or Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Authentication Failed")
                    .setMessage("Incorrect email or password. Please try again.")
                    .setPositiveButton("OK", null)
                    .show();

            // Alternatively, use Toast
            // Toast.makeText(MainActivity.this, "Incorrect email or password. Please try again.", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etEmail.setText("");
            etPassword.setText("");
        }
    }



}