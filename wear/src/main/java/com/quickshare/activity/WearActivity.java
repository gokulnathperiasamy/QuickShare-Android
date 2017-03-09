package com.quickshare.activity;

import android.os.Bundle;
import android.app.Activity;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wearable.Asset;
import com.quickshare.R;
import com.quickshare.application.Constant;
import com.quickshare.utility.PreferenceHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class WearActivity extends Activity {

    private TextView mTextUpdateCard;
    private ImageView cardImage;

    private GoogleApiClient apiClient;
    private final Handler handler = new Handler();
    private final PreferenceHelper preferenceHelper = new PreferenceHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextUpdateCard = (TextView) stub.findViewById(R.id.text_update_card);
                mTextUpdateCard.setText(getString(R.string.update_card_message));
                cardImage = (ImageView) stub.findViewById(R.id.cardImage);
                if (preferenceHelper.isProfileSet()) {
                    Bitmap bitmap = preferenceHelper.getCardImageData();
                    if (bitmap != null) {
                        setCardImageBitmap(bitmap);
                    }
                }
            }
        });

        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Wearable.DataApi.addListener(apiClient, onDataChangedListener);
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                    }
                })
                .addOnConnectionFailedListener(new OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                    }
                })
                .addApi(Wearable.API)
                .build();

        apiClient.connect();
    }

    private void setCardImageBitmap(Bitmap bitmap) {
        mTextUpdateCard.setVisibility(View.GONE);
        cardImage.setImageBitmap(bitmap);
        cardImage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public DataApi.DataListener onDataChangedListener = new DataApi.DataListener() {
        @Override
        public void onDataChanged(DataEventBuffer dataEvents) {
            for (DataEvent event : dataEvents) {
                if (event.getType() == DataEvent.TYPE_CHANGED && event.getDataItem().getUri().getPath().equals("/image")) {
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                    final Asset profileAsset = dataMapItem.getDataMap().getAsset(Constant.WEAR_CARD_IMAGE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = loadBitmapFromAsset(profileAsset);
                            handler.post(onNewBitmap(bitmap));
                        }
                    }).start();
                }
            }
        }
    };

    private Runnable onNewBitmap(final Bitmap bitmap) {
        return new Runnable() {
            @Override
            public void run() {
                if (bitmap != null) {
                    setCardImageBitmap(bitmap);
                    saveToSharedPreference(bitmap);
                }
            }
        };
    }

    private void saveToSharedPreference(Bitmap bitmap) {
        preferenceHelper.setIsProfileSet(true);
        preferenceHelper.setCardImageData(bitmap);
        showSuccessToast();
    }

    private void showSuccessToast() {
        Toast toast = Toast.makeText(this, getString(R.string.card_updated_message), Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(getResources().getColor(R.color.primaryColorDark));
        v.setBackgroundColor(getResources().getColor(R.color.white));
        toast.show();
    }

    public Bitmap loadBitmapFromAsset(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("Asset must be non-null");
        }
        ConnectionResult result = apiClient.blockingConnect(3000, TimeUnit.MILLISECONDS);
        if (!result.isSuccess()) {
            return null;
        }
        InputStream assetInputStream = Wearable.DataApi.getFdForAsset(apiClient, asset).await().getInputStream();
        apiClient.disconnect();

        if (assetInputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(assetInputStream);
    }
}