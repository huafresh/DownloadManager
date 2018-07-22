package com.hua.download;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    String url3 = "http://gdown.baidu.com/data/wisegame/d2fbbc8e64990454/wangyiyunyinle_87.apk";

    private boolean isPause = false;
    private long curPos = 0;
    private String localPath;
    private OkHttpClient client;
    private Request request;
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPause = false;
                request = new Request.Builder()
                        .url(url3)
                        .get()
                        .addHeader("range", "bytes=" + curPos + "-")
                        .build();
                call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("@@@hua", "failed");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("@@@hua", "success");
                        ResponseBody body = response.body();
                        InputStream in = body.byteStream();
                        RandomAccessFile randomAccessFile = new RandomAccessFile(localPath, "rwd");
                        FileChannel channel = randomAccessFile.getChannel();
                        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, curPos, body.contentLength());
                        int len = -1;
                        byte[] buffer = new byte[2048];
                        while ((len = in.read(buffer, 0, buffer.length)) != -1 && !isPause) {
                            mappedByteBuffer.put(buffer, 0, len);
                            curPos += len;
                            Log.e("@@@hua", "已下载：" + curPos);
                        }
                        in.close();
                        channel.close();
                        randomAccessFile.close();
                    }
                });
            }
        });
        Button pause = findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPause = true;
                if (call != null) {
                    call.cancel();
                }
            }
        });

        String dest = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testDown";
        localPath = dest + "/test.apk";
        File destFile = new File(dest);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        File file = new File(localPath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        client = new OkHttpClient();

    }
}
