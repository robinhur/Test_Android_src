package com.example.huza.test_160803_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView idVIEW;
    EditText nameEDIT;
    EditText quanEDIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idVIEW = (TextView) findViewById(R.id.TV_id);
        nameEDIT = (EditText) findViewById(R.id.editText_name);
        quanEDIT = (EditText) findViewById(R.id.editText_quantity);
    }

    public void add_click(View v) {
        DB_handler handler = new DB_handler(this, null, null, 0);

        String name = nameEDIT.getText().toString();
        int quantity = Integer.parseInt(quanEDIT.getText().toString());

        Product add_item = new Product(name, quantity);

        handler.addProduct(add_item);

        nameEDIT.setText("");
        quanEDIT.setText("");
    }
    public void find_click(View v) {
        DB_handler handler = new DB_handler(this, null, null, 0);

        Product product = handler.findProduct(nameEDIT.getText().toString());

        if (product != null) {
            idVIEW.setText(String.valueOf(product.getId()));
            quanEDIT.setText(String.valueOf(product.getQuantity()));
        } else {
            idVIEW.setText("No match found");
        }
    }
    public void delete_click(View v) {
        DB_handler handler = new DB_handler(this, null, null, 0);

        boolean result = handler.deleteProduct(nameEDIT.getText().toString());

        if (result) {
            idVIEW.setText("Item Deleted");
            nameEDIT.setText("");
            quanEDIT.setText("");
        } else {
            idVIEW.setText("No Match Found");
        }
    }
}
