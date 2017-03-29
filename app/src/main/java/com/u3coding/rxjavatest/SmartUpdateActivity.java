package com.u3coding.rxjavatest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.u3coding.rxjavatest.utils.DiffUtils;
import com.u3coding.rxjavatest.utils.PatchUtils;

import java.io.File;

/**
 * Created by ${U3} on 2017/3/17.
 */

public class SmartUpdateActivity extends Activity{
    String externalStoragePath = Environment.getExternalStorageDirectory().getPath() + "/";
    String newPath = externalStoragePath + "new.apk";
    String oldPath = externalStoragePath + "old.apk";
    String patchPath = externalStoragePath + "patch.patch";
    String mergePath = externalStoragePath + "merge.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


    }

    public void generateDiffPatch(View view) {
        //产生差异patch包
        int resultCode = DiffUtils.diff(oldPath, newPath, patchPath);
        if (resultCode == 0) {
            Toast.makeText(this, "generateDiffPatch success!", Toast.LENGTH_SHORT).show();
        }
    }

    public void mergePatchApk(View view) {
        //合并差异patch包，生成新apk
        int resultCode = PatchUtils.patch(oldPath, mergePath, patchPath);
        if (resultCode == 0) {
            Toast.makeText(this, "mergePatchApk success!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePatchApk(View view) {
        File mergeFile = new File(mergePath);
        if (mergeFile.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(mergeFile), "application/vnd.android.package-archive");
            startActivity(intent);
        }
    }

    //加载smartupdate库
    static {
        System.loadLibrary("smartupdate");
    }
}
