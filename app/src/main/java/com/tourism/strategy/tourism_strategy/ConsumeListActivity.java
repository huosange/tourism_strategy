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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tourism.strategy.tourism_strategy.adapter.ConsumeAdapter;
import com.tourism.strategy.tourism_strategy.greendao.manager.EntityManager;
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

    private String[] stars = new String[]{"交通", "餐饮", "住宿", "门票", "购物", "娱乐", "其他"};
    private List<Integer> colors = new ArrayList<>();
    private List<Consume> list = new ArrayList<>();
    private float[] values = new float[7];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        ButterKnife.bind(this);
        setTitle("消费明细");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<Consume> consumes = EntityManager.getInstance().getConsumeDao().queryBuilder().list();
        if (consumes != null && consumes.size() > 0) {
            list.addAll(consumes);
        }
        ConsumeAdapter adapter = new ConsumeAdapter(list);
        recyclerview.setAdapter(adapter);
        colors.add(Color.parseColor("#FFFDD201"));
        colors.add(Color.parseColor("#FF54C6FC"));
        colors.add(Color.parseColor("#FFFD7467"));
        colors.add(Color.parseColor("#FF4DA0F8"));
        colors.add(Color.parseColor("#FF43DB5D"));
        colors.add(Color.parseColor("#FF7030A1"));
        colors.add(Color.parseColor("#FF6AF8C3"));

        pieChart.setRotationEnabled(false);
        pieChart.setDrawHoleEnabled(false);

        for (Consume consume : list) {
            switch (consume.getType()) {
                case 0:
                    values[0] += consume.getMoney();
                    break;
                case 1:
                    values[1] += consume.getMoney();
                    break;
                case 2:
                    values[2] += consume.getMoney();
                    break;
                case 3:
                    values[3] += consume.getMoney();
                    break;
                case 4:
                    values[4] += consume.getMoney();
                    break;
                case 5:
                    values[5] += consume.getMoney();
                    break;
                case 6:
                    values[6] += consume.getMoney();
                    break;
            }
        }

        List<PieEntry> temp = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            PieEntry entry = new PieEntry(values[i], stars[i]);
            temp.add(entry);
        }
        PieDataSet pieDataSet = new PieDataSet(temp, "");
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
            if (value < 5) {
                //小于5%，则不显示文字
                return "";
            }
            return mFormat.format(value) + "%";
        }
    }
}
