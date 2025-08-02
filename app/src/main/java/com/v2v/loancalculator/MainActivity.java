package com.v2v.loancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loanAmountInput, interestRateInput, loanTermInput;
    TextView monthlyPaymentOutput, totalInterestOutput;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loanAmountInput = findViewById(R.id.loanAmountInput);
        interestRateInput = findViewById(R.id.interestRateInput);
        loanTermInput = findViewById(R.id.loanTermInput);
        monthlyPaymentOutput = findViewById(R.id.monthlyPaymentOutput);
        totalInterestOutput = findViewById(R.id.totalInterestOutput);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoan();
            }
        });
    }

    private void calculateLoan() {
        try {
            double loanAmount = Double.parseDouble(loanAmountInput.getText().toString());
            double annualInterestRate = Double.parseDouble(interestRateInput.getText().toString());
            int loanTermYears = Integer.parseInt(loanTermInput.getText().toString());

            double monthlyInterestRate = annualInterestRate / 100 / 12;
            int numberOfPayments = loanTermYears * 12;

            double monthlyPayment = (loanAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

            double totalPayment = monthlyPayment * numberOfPayments;
            double totalInterest = totalPayment - loanAmount;

            monthlyPaymentOutput.setText(String.format("₹ %.2f", monthlyPayment));
            totalInterestOutput.setText(String.format("₹ %.2f", totalInterest));

        } catch (Exception e) {
            Toast.makeText(this, "Enter valid inputs", Toast.LENGTH_SHORT).show();
        }
    }
}
