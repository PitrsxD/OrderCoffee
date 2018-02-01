package com.svobodapeter.ordecoffee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int quantityOfCoffees = 2;
    int priceOfOneCoffee = 0;
    String messageCream;
    String messageChocolate;
    int priceOfCoffees = 0;
    int priceOfTopping = 0;
    String typeOfCoffee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner dropdownCoffee = findViewById(R.id.sortOfCoffeeSpinner);
        ArrayAdapter<CharSequence> adapterCoffee = ArrayAdapter.createFromResource(this,
                R.array.sortOfCoffee, android.R.layout.simple_spinner_item);
        adapterCoffee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownCoffee.setAdapter(adapterCoffee);
        startSpinner();

        Spinner dropdownTopping = findViewById(R.id.topping1);
        ArrayAdapter<CharSequence> adapterTopping = ArrayAdapter.createFromResource(this,
                R.array.topping1, android.R.layout.simple_spinner_item);
        adapterTopping.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownTopping.setAdapter(adapterTopping);
        startSpinner2();

        Spinner dropdownTopping2 = findViewById(R.id.topping2);
        ArrayAdapter<CharSequence> adapterTopping2 = ArrayAdapter.createFromResource(this,
                R.array.topping2, android.R.layout.simple_spinner_item);
        adapterTopping2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownTopping2.setAdapter(adapterTopping2);
        startSpinner3();

    }



    /**
     * This is changing number of coffees according to the button "+" or "-"
     **/
    public void increment(View view) {
        quantityOfCoffees = quantityOfCoffees + 1;
        if (quantityOfCoffees >= 100) {
            quantityOfCoffees = 100;
            Toast.makeText(this, getString(R.string.toast_more_100), Toast.LENGTH_SHORT).show();

        }
        displayQuantity(quantityOfCoffees);
    }

    public void decrement(View view) {
        quantityOfCoffees = quantityOfCoffees - 1;
        if (quantityOfCoffees < 2) {
            quantityOfCoffees = 1;

        }
        if (quantityOfCoffees == 1) {
            quantityOfCoffees = 1;
            Toast.makeText(this, getString(R.string.tost_less_1), Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantityOfCoffees);
    }

    /**
     * This method is called when the order button is clicked.
     * @priceOfCoffees is calling calculation
     */

    public void submitOrder(View view) {
        EditText nameOfcustomer = findViewById(R.id.customer_name);
        String customerName = nameOfcustomer.getText().toString();
        startSpinner();
        startSpinner2();
        startSpinner3();
        int priceOfCoffees = getPrice(quantityOfCoffees, priceOfOneCoffee, priceOfTopping);
        createMessageOrderSummary(customerName, quantityOfCoffees, priceOfCoffees, messageCream, messageChocolate, typeOfCoffee);
        createMessageOrder();
    }

    /**
     * Calculation of coffees from order
     */
    private int getPrice(int quantityOfCoffees, int priceOfOneCoffee, int priceOfTopping) {
        return priceOfCoffees + (quantityOfCoffees * priceOfTopping) + (quantityOfCoffees * priceOfOneCoffee);
    }

    /**Text of Summary**/
    public void createMessageOrder() {
        String createMessageOrder = getString(R.string.name_order) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.type_of_coffe_question) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.whipped_cream_question) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.chocolate_question) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.quantity_order) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.total_order) + "\n";
        createMessageOrder = createMessageOrder + getString(R.string.thanks);
        displayMessage(createMessageOrder);
    }

    /**Confirming text of order with name, quantity and total price for coffees.**/
    public void createMessageOrderSummary(String customerName, int quantityOfCoffees, int priceOfCoffees, String messageCream, String messageChocolate, String typeOfCoffee) {
        String createMessageOrderSummary = customerName + "\n";
        createMessageOrderSummary = createMessageOrderSummary + typeOfCoffee +"\n";
        createMessageOrderSummary = createMessageOrderSummary + messageCream +"\n";
        createMessageOrderSummary = createMessageOrderSummary + messageChocolate + "\n";
        createMessageOrderSummary = createMessageOrderSummary + quantityOfCoffees + "\n";
        createMessageOrderSummary = createMessageOrderSummary + priceOfCoffees + " " + getString(R.string.currency) + "\n";
        displayMessageSummary(createMessageOrderSummary);
        switchButtons();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantityOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantityOfCoffees);
        quantityTextView.setText("" + quantityOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void displayMessageSummary(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view_total);
        orderSummaryTextView.setText(message);
    }

    //Switch Button ORDER and NEW ORDER
    public void switchButtons (){
        Button submitOrder = findViewById(R.id.button);
        submitOrder.setVisibility(View.GONE);
        Button new_Order = findViewById(R.id.newOrder);
        new_Order.setVisibility(View.VISIBLE);
        ImageView underSubmitOrder = findViewById(R.id.img_under_button);
        underSubmitOrder.setVisibility(View.GONE);
        ImageView underNewOrder = findViewById(R.id.img_under_new_order);
        underNewOrder.setVisibility(View.VISIBLE);
    }
    //Aplly NEW ORDER
    public void new_Order (View v){
        priceOfOneCoffee = 40;
        priceOfCoffees = 0;
        priceOfTopping = 0;
        EditText customerName = (EditText) findViewById(R.id.customer_name);
        customerName.setText("");
        TextView createMessageOrder = (TextView) findViewById(R.id.order_summary_text_view);
        createMessageOrder.setText("");
        TextView displayMessageSummary = (TextView) findViewById(R.id.order_summary_text_view_total);
        displayMessageSummary.setText("");
        Button new_Order = findViewById(R.id.newOrder);
        new_Order.setVisibility(View.GONE);
        Button submitOrder = findViewById(R.id.button);
        submitOrder.setVisibility(View.VISIBLE);
        ImageView underButton = findViewById(R.id.img_under_button);
        underButton.setVisibility(View.VISIBLE);
        ImageView underNewOrder = findViewById(R.id.img_under_new_order);
        underNewOrder.setVisibility(View.GONE);
        Spinner startSpinner = findViewById(R.id.sortOfCoffeeSpinner);
        startSpinner.setSelection(0);
        Spinner startSpinner2 = findViewById(R.id.topping1);
        startSpinner2.setSelection(0);
        Spinner startSpinner3 = findViewById(R.id.topping2);
        startSpinner3.setSelection(0);
    }


    /**
     * Spinner for Coffee**/

    public void startSpinner() {
        final Spinner dropdownCoffee = findViewById(R.id.sortOfCoffeeSpinner);
        dropdownCoffee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    default:
                        typeOfCoffee = getString(R.string.esspreso);
                        priceOfOneCoffee = 40;
                        break;
                    case 1:
                        typeOfCoffee = getString(R.string.late);
                        priceOfOneCoffee = 60;
                        break;
                    case 2:
                        typeOfCoffee = getString(R.string.capucino);
                        priceOfOneCoffee = 60;
                        break;
                    case 3:
                        typeOfCoffee = getString(R.string.lungo);
                        priceOfOneCoffee = 40;
                        break;
                    case 4:
                        typeOfCoffee = getString(R.string.ristreto);
                        priceOfOneCoffee = 40;
                        break;
                }

            }


            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}

    /** Spinner fot topping **/
    public void startSpinner2() {
        Spinner dropdownTopping = findViewById(R.id.topping1);
        dropdownTopping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    default:
                        messageCream = getString(R.string.without_topping);
                        break;
                    case 1:
                        messageCream = getString(R.string.whiped_cream_selected);
                        priceOfTopping = 10;
                        break;
                    case 2:
                        messageCream = getString(R.string.ice_cream);
                        priceOfTopping = 20;
                        break;
                    case 3:
                        messageCream = getString(R.string.cookie);
                        priceOfTopping = 15;
                        break;


                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}

    /** Spinner fot topping 2**/
    public void startSpinner3() {
        Spinner dropdownTopping2 = findViewById(R.id.topping2);
        dropdownTopping2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    default:
                        messageChocolate = getString(R.string.without_topping);
                        break;
                    case 1:
                        messageChocolate = getString(R.string.chocolate_selected);
                        break;
                    case 2:
                        messageChocolate = getString(R.string.nuts);
                        break;
                    case 3:
                        messageChocolate = getString(R.string.cinamon);
                        break;

                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
