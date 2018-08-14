package com.mutyaba.multiply;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Activity implements PurchasesUpdatedListener{

    private BillingClient mBillingClient;
    private TextView adPrice;
    private SharedPreferences sharedPreferences;

    private View.OnClickListener adPurchase = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sharedPreferences = getSharedPreferences("com.mutyaba.multiply.scores", Context.MODE_PRIVATE);
            boolean ads = sharedPreferences.getBoolean("ads", true);
            if(ads) {
                BillingFlowParams flowParams = BillingFlowParams.newBuilder().setSku("no_ads").setType(BillingClient.SkuType.INAPP).build();
                mBillingClient.launchBillingFlow(Shop.this, flowParams);
            } else {
                Toast.makeText(Shop.this, "Product already purchased", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_activity);

        adPrice = findViewById(R.id.remove_ads_price);
        LinearLayout adBuy = findViewById(R.id.remove_ads);
        adBuy.setOnClickListener(adPurchase);
        mBillingClient = BillingClient.newBuilder(this).setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(int responseCode) {
                if(responseCode == BillingClient.BillingResponse.OK){
                    //Billing Client ready, query purchases.
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });

        List skuList = new ArrayList();
        skuList.add("no_ads");
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        mBillingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        String sku = skuDetails.getSku();
                        String price = skuDetails.getPrice();
                        if ("no_ads".equals(sku)) {
                            adPrice.setText(price);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#cccccc"));
    }

    @Override
    public void onPurchasesUpdated(int responseCode, List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
            sharedPreferences = getSharedPreferences("com.mutyaba.multiply.scores", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("ads", false);
            editor.apply();
            startActivity(new Intent(this, StartGame.class));
        }
    }
}
