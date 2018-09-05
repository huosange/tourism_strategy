package com.tourism.strategy.tourism_strategy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tourism.strategy.tourism_strategy.adapter.ConsumeAdapter;
import com.tourism.strategy.tourism_strategy.model.Consume;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全部消费列表
 */
public class ConsumeListActivity extends BaseActivity {

    @BindView(R.id.pieChart)
    public PieChart pieChart;
    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private String[] stars = new String[]{"交通", "餐饮", "住宿", "门票", "娱乐"};
    private List<Integer> colors = new ArrayList<>();
    private List<Consume> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        ButterKnife.bind(this);
        setTitle("消费明细");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 5; i++) {
            Consume consume = new Consume("2018年09月04号 17:38", 100.3, "和朋友去ktv，顺带啤酒小龙虾","");
            list.add(consume);
        }
        ConsumeAdapter adapter = new ConsumeAdapter(list);
        recyclerview.setAdapter(adapter);
        colors.add(Color.parseColor("#FFFDD201"));
        colors.add(Color.parseColor("#FF54C6FC"));
        colors.add(Color.parseColor("#FFFD7467"));
        colors.add(Color.parseColor("#FF4DA0F8"));
        colors.add(Color.parseColor("#FF43DB5D"));

        pieChart.setDrawHoleEnabled(false);
        List<PieEntry> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieEntry entry = new PieEntry(i + 1, stars[i]);
            list.add(entry);
        }
        PieDataSet pieDataSet = new PieDataSet(list, "");
        colors.add(Color.parseColor("#f17548"));
        colors.add(Color.parseColor("#FF9933"));
        pieDataSet.setValueTextColor(Color.parseColor("#ffffff"));
        pieDataSet.setValueTextSize(13);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieChart.setDrawEntryLabels(false);
        Description description = new Description();
        description.setTextSize(13);
        description.setText("消费分布");
        pieChart.setDescription(description);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setData(pieData);
    }

    public class PercentFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public PercentFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value) + "%";
        }
    }
}
