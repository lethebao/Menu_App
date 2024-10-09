

package com.example.menu_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    // Item prices
    private final double PRICE_ITEM1 = 30.0;
    private final double PRICE_ITEM2 = 40.0;
    private final double PRICE_ITEM3 = 50.0;

    private int quantityItem1 = 0;
    private int quantityItem2 = 0;
    private int quantityItem3 = 0;

    private double subtotal = 0.0;
    private double shipping = 0.0;
    private double total = 0.0;

    private TextView tvSubtotal, tvSubtotalBelow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Initialize views
        tvSubtotal = findViewById(R.id.tv_subtotal);
        tvSubtotalBelow = findViewById(R.id.tv_subtotal_label); // Assuming same TextView for display

        // Item 1
        Button btnItem1Plus = findViewById(R.id.btn_item1_plus);
        Button btnItem1Minus = findViewById(R.id.btn_item1_minus);
        final TextView tvItem1Quantity = findViewById(R.id.tv_item1_quantity);

        // Item 2
        Button btnItem2Plus = findViewById(R.id.btn_item2_plus);
        Button btnItem2Minus = findViewById(R.id.btn_item2_minus);
        final TextView tvItem2Quantity = findViewById(R.id.tv_item2_quantity);

        // Item 3
        Button btnItem3Plus = findViewById(R.id.btn_item3_plus);
        Button btnItem3Minus = findViewById(R.id.btn_item3_minus);
        final TextView tvItem3Quantity = findViewById(R.id.tv_item3_quantity);

        // Place Order Button
        Button btnPlaceOrder = findViewById(R.id.btn_place_order);

        // Set onClick listeners
        btnItem1Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityItem1++;
                tvItem1Quantity.setText(String.valueOf(quantityItem1));
                updateSubtotal();
            }
        });

        btnItem1Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityItem1 > 0) {
                    quantityItem1--;
                    tvItem1Quantity.setText(String.valueOf(quantityItem1));
                    updateSubtotal();
                }
            }
        });

        btnItem2Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityItem2++;
                tvItem2Quantity.setText(String.valueOf(quantityItem2));
                updateSubtotal();
            }
        });

        btnItem2Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityItem2 > 0) {
                    quantityItem2--;
                    tvItem2Quantity.setText(String.valueOf(quantityItem2));
                    updateSubtotal();
                }
            }
        });

        btnItem3Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityItem3++;
                tvItem3Quantity.setText(String.valueOf(quantityItem3));
                updateSubtotal();
            }
        });

        btnItem3Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantityItem3 > 0) {
                    quantityItem3--;
                    tvItem3Quantity.setText(String.valueOf(quantityItem3));
                    updateSubtotal();
                }
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePlaceOrder();
            }
        });
    }

    private void updateSubtotal() {
        subtotal = (quantityItem1 * PRICE_ITEM1) + (quantityItem2 * PRICE_ITEM2) + (quantityItem3 * PRICE_ITEM3);
        tvSubtotal.setText(String.format("$%.2f", subtotal));

        // Update shipping based on subtotal
        if (subtotal > 100.0) {
            shipping = 0.0;
        } else {
            shipping = 20.0;
        }

        total = subtotal + shipping;

        // Update subtotal below with shipping and total
        String subtotalBelowText = "Subtotal: $" + String.format("%.2f", subtotal) +
                "\nShipping: $" + String.format("%.2f", shipping) +
                "\nTotal: $" + String.format("%.2f", total);
        tvSubtotalBelow.setText(subtotalBelowText);
    }

    private void handlePlaceOrder() {
        // Pass order details to PaymentActivity
        Intent intent = new Intent(MenuActivity.this, com.example.menu_app.PaymentActivity.class);
        intent.putExtra("quantityItem1", quantityItem1);
        intent.putExtra("quantityItem2", quantityItem2);
        intent.putExtra("quantityItem3", quantityItem3);
        intent.putExtra("subtotal", subtotal);
        intent.putExtra("shipping", shipping);
        intent.putExtra("total", total);
        startActivity(intent);
    }
}
