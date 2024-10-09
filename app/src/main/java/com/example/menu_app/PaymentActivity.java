

package com.example.menu_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    private RadioGroup rgPaymentMethods;
    private RadioButton rbSelected;
    private Button btnConfirmPayment;

    // Variables to receive order details
    private int quantityItem1, quantityItem2, quantityItem3;
    private double subtotal, shipping, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Initialize views
        rgPaymentMethods = findViewById(R.id.rg_payment_methods);
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment);

        // Receive data from MenuActivity
        if (getIntent() != null) {
            quantityItem1 = getIntent().getIntExtra("quantityItem1", 0);
            quantityItem2 = getIntent().getIntExtra("quantityItem2", 0);
            quantityItem3 = getIntent().getIntExtra("quantityItem3", 0);
            subtotal = getIntent().getDoubleExtra("subtotal", 0.0);
            shipping = getIntent().getDoubleExtra("shipping", 0.0);
            total = getIntent().getDoubleExtra("total", 0.0);
        }

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleConfirmPayment();
            }
        });
    }

    private void handleConfirmPayment() {
        int selectedId = rgPaymentMethods.getCheckedRadioButtonId();

        if (selectedId == -1) {
            // No payment method selected
            Toast.makeText(PaymentActivity.this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
            return;
        }

        rbSelected = findViewById(selectedId);
        String paymentMethod = rbSelected.getText().toString();

        // Create a summary of the order
        String orderSummary = "Order Details:\n" +
                "Item 1 Quantity: " + quantityItem1 + "\n" +
                "Item 2 Quantity: " + quantityItem2 + "\n" +
                "Item 3 Quantity: " + quantityItem3 + "\n" +
                "Subtotal: $" + String.format("%.2f", subtotal) + "\n" +
                "Shipping: $" + String.format("%.2f", shipping) + "\n" +
                "Total: $" + String.format("%.2f", total) + "\n\n" +
                "Payment Method: " + paymentMethod;

        // Display the summary in a Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        builder.setTitle("Confirm Order")
                .setMessage(orderSummary)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        // Optionally, you can reset the app or navigate to a confirmation screen
                        Toast.makeText(PaymentActivity.this, "Order Placed Successfully!", Toast.LENGTH_LONG).show();
                        finishAffinity(); // Close all activities
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
