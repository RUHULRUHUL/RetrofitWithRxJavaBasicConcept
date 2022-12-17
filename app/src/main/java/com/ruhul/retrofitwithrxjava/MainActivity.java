package com.ruhul.retrofitwithrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import com.ruhul.retrofitwithrxjava.model.Slider;
import com.ruhul.retrofitwithrxjava.model.SliderBannerResponse;
import com.ruhul.retrofitwithrxjava.network.RetrofitClient;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient.getApiServices().getSliders()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<SliderBannerResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("sliderItem", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull SliderBannerResponse sliderBannerResponse) {
                        List<Slider> sliderList = sliderBannerResponse.getData();
                        Log.d("sliderItem", "sliderItem: " + sliderBannerResponse.toString());

                        for (Slider slider : sliderList) {
                            Log.d("sliderItem", "sliderItem: " + slider.getImage());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("sliderItem", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("sliderItem", "onComplete");
                    }
                });


    }
}