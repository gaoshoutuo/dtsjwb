package com.zjdt.dtsjwb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zjdt.dtsjwb.R;

public class CCountdownActivity extends BaseActivity {
    //显示倒数30天的  现在改到 offline 去好了

    //没改  设计countdown     对各类设备进行维保的倒计时

    //好吧 又改了 都规划好了

    /**
     * 查询历史
     * 统计离得最近的  按机房来好了 机房的ups维保  空调维保
     * 机房四项  用户id  用户姓名   上次维保的工程师id
     * ups平均时间  (天)
     * air平均时间
     * device_another_1(这些就是其他设备)
     * device_another_2
     * device_another_3
     * device_another_4
     * flag_set  为了避免全表扫描  每次查完都把离得最近的id 索引记录一下
     *
     * 算平均值好了  所有的机器离目前时间算平均值
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccountdown);
    }
}
