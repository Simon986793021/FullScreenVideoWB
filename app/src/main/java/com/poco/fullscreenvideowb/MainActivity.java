package com.poco.fullscreenvideowb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Simon
 * @time 2018/9/4
 * @note MainActivity
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void goToVideoActivity(String url) {
        Intent intent =new Intent(MainActivity.this,VideoActivity.class);
        intent.putExtra(VideoActivity.VIDEO_URL,url);
        startActivity(intent);
    }

    //腾讯普通网页
    @OnClick(R.id.bt_tx_normal)
    void txNormalClick() {
        goToVideoActivity("https://v.qq.com/x/cover/amxtb0jnbmlfggn/y0770dxf6xx.html");
    }



    //腾讯全屏网页
    @OnClick(R.id.bt_tx_full_screen)
    void txFullScreenClick() {
        goToVideoActivity("https://v.qq.com/txp/iframe/player.html?vid=b0765olk75e");
    }

    //爱奇艺普通网页
    @OnClick(R.id.bt_aqy_nomal)
    void aqyNormalClick() {
        goToVideoActivity("http://www.iqiyi.com/v_19rqz97sb0.html");
    }

    //爱奇艺全屏网页
    @OnClick(R.id.bt_aqy_full_screen)
    void aqyFullScreenClick() {
        goToVideoActivity("http://open.iqiyi.com/developer/player_js/coopPlayerIndex.html?vid=5e54f1fec36034f67521abf755dd3f93&tvId=1294428000&accessToken=2.f22860a2479ad60d8da7697274de9346&appKey=3955c3425820435e86d0f4cdfe56f5e7&appId=1368&height=100%&width=100%");
    }

    //优酷普通网页
    @OnClick(R.id.bt_yk_normal)
    void ykNormalClick() {
        goToVideoActivity("https://v.youku.com/v_show/id_XMzgwODIzNzgwOA==.html?spm=a2h0j.11185381.bpmodule-playpage-segments.5~5!3~A&&s=69efbfbdefbfbd343a00");
    }

    //优酷全屏网页
    @OnClick(R.id.bt_yk_full_screen)
    void ykFullScreenClick() {
        goToVideoActivity("http://player.youku.com/embed/XMzgwODIzNzgwOA==");
    }


}
