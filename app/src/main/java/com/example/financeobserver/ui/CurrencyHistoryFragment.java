package com.example.financeobserver.ui;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.financeobserver.R;
import com.example.financeobserver.models.CurrencyHistoryItem;
import com.example.financeobserver.models.CurrencyHistoryResponse;
import com.example.financeobserver.viewmodels.MainViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;


public class CurrencyHistoryFragment extends Fragment {

    private static final String CURRENCY_NAME = "currency_name";
    private LineChart chart;
    private MainViewModel viewModel;
    private String mCurrencyName;
    private List<String> currencyValueList = new ArrayList<>();
    List<String> date = new ArrayList<>();


    public static CurrencyHistoryFragment newInstance(String mCurrencyName) {
        CurrencyHistoryFragment fragment = new CurrencyHistoryFragment();
        Bundle args = new Bundle();
        args.putString(CURRENCY_NAME, mCurrencyName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        //viewModel.loadCurrencyHistory();
        if (getArguments() != null) {
            mCurrencyName = getArguments().getString(CURRENCY_NAME );
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         /*viewModel.getCurrencyHistory().observe(getViewLifecycleOwner(), new Observer<CurrencyHistoryResponse>() {
                  @Override
                    public void onChanged(CurrencyHistoryResponse currencyHistoryResponse) {
                       for (int i=0; i<currencyHistoryResponse.getData().size(); i++){
                           CurrencyHistoryItem item= currencyHistoryResponse.getData().get(i);
                           date.add(item.getDate());
                           currencyValueList.add(item.getValue().replace(',','.'));
                        }
                       populateLineChart();
                    }
                });*/
        return inflater.inflate(R.layout.fragment_currency_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         chart=  view.findViewById(R.id.lineChart);


    }

    private void populateLineChart(){

        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < currencyValueList.size(); i++){
            float y = Float.parseFloat(currencyValueList.get(i));
            entries.add(new Entry(i, y));
        }
        chart.setTouchEnabled(true);
        chart.setPinchZoom(true);

        LineDataSet dataset = new LineDataSet(entries, null);

        //cubic lines
        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //To Fill area below line, disable displayed values:
        dataset.setDrawFilled(true);
        dataset.setDrawValues(false);

        //Set fill color and line color:
        if(Float.parseFloat(currencyValueList.get(0)) > Float.parseFloat(currencyValueList.get(currencyValueList.size() - 1))){
            dataset.setFillColor(ContextCompat.getColor(getContext(),R.color.light_green));
            dataset.setColor(ContextCompat.getColor(getContext(),R.color.shamrock_green));
        }
        else{
            dataset.setFillColor(ContextCompat.getColor(getContext(), R.color.light_red));
            dataset.setColor(ContextCompat.getColor(getContext(), R.color.colorBlack));
        }

        //Disable transparency (values range 0-255) and disable drawing circles on the main chart line:
        dataset.setFillAlpha(255);
        dataset.setDrawCircles(false);
        ArrayList<String> dates= new ArrayList<>();
        dates.add(date.get(0));
        int size=date.size();
        int middle= size/2;
        dates.add(date.get(middle));
        dates.add(date.get(size-1));
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(dates));

        chart.setAutoScaleMinMaxEnabled(true);


       // chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setLabelCount(date.size(),true);
        LineData lineData = new LineData(dataset);


        chart.getDescription().setText("");
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setData(lineData);
        chart.invalidate();

    }

    private void setData() {

        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 50));
        values.add(new Entry(2, 100));
        values.add(new Entry(3, 80));
        values.add(new Entry(4, 120));
        values.add(new Entry(5, 110));
        values.add(new Entry(7, 150));
        values.add(new Entry(8, 250));
        values.add(new Entry(9, 190));

        LineDataSet set1;
        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "Sample Data");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.DKGRAY);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            chart.setData(data);
        }
    }

}
