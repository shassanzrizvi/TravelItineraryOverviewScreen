package com.hassan.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ItineraryOverviewActivity extends AppCompatActivity {

    private LinearLayout itineraryContainer;
    private ItineraryDBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_overview);

        itineraryContainer = findViewById(R.id.itineraryContainer);
        dbHelper = new ItineraryDBHelper(this);

        // Load itineraries from database
        loadItineraries();
    }

    private void loadItineraries() {
        itineraryContainer.removeAllViews(); // Clear previous entries
        Cursor cursor = dbHelper.getItineraries();

        if (cursor.getCount() == 0) {
            // Show a message if no itineraries are found
            TextView emptyMessage = new TextView(this);
            emptyMessage.setText("No itineraries available. Enjoy the dummy itineraries below!");
            emptyMessage.setTextSize(18);
            emptyMessage.setTextColor(getResources().getColor(android.R.color.darker_gray));
            itineraryContainer.addView(emptyMessage);
            return;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String destination = cursor.getString(1);
            String dates = cursor.getString(2);
            String description = cursor.getString(3);

            addItineraryCard(id, destination, dates, description);
        }
    }

    private void addItineraryCard(int id, String destination, String dates, String description) {
        CardView card = new CardView(this);
        card.setCardElevation(8);
        card.setRadius(12);
        card.setUseCompatPadding(true);
        card.setContentPadding(16, 16, 16, 16);

        LinearLayout cardContent = new LinearLayout(this);
        cardContent.setOrientation(LinearLayout.VERTICAL);

        TextView tvDestination = new TextView(this);
        tvDestination.setText(destination);
        tvDestination.setTextSize(20);
        tvDestination.setTextColor(getResources().getColor(android.R.color.black));
        tvDestination.setPadding(0, 0, 0, 8);

        TextView tvDates = new TextView(this);
        tvDates.setText(dates);
        tvDates.setTextSize(16);
        tvDates.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        tvDates.setPadding(0, 0, 0, 8);

        TextView tvDescription = new TextView(this);
        tvDescription.setText(description);
        tvDescription.setTextSize(14);
        tvDescription.setTextColor(getResources().getColor(android.R.color.darker_gray));

        cardContent.addView(tvDestination);
        cardContent.addView(tvDates);
        cardContent.addView(tvDescription);

        card.addView(cardContent);
        itineraryContainer.addView(card);
    }
}
