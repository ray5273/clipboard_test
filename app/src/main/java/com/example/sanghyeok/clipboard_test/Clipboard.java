package com.example.sanghyeok.clipboard_test;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Clipboard extends Service {
    private ClipboardManager mCM;
    IBinder mBinder;
    int mStartMode;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCM = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mCM.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

            @Override
            public void onPrimaryClipChanged() {
                String newClip = mCM.getPrimaryClip().toString();
                Toast.makeText(getApplicationContext(), newClip.toString(),  Toast.LENGTH_LONG).show();
                Log.i("LOG", newClip.toString() + "");

                //여기서 parsing을 하면 되긴 하겠다
                if(newClip.contains("hello")){
                    startService(new Intent(getApplicationContext(), MyService.class));
                }

            }
        });
        return mStartMode;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}