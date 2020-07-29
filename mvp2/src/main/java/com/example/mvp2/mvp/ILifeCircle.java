package com.example.mvp2.mvp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public interface ILifeCircle {
    void onCreate(Bundle savedInstanceState,Intent intent,Bundle getArguments);

    void onActivityCreated(Bundle savedInstanceState,Intent intent,Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView(IMvpView iMvpView);
}
