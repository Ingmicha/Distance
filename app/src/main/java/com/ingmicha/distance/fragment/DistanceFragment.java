package com.ingmicha.distance.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingmicha.distance.R;
import com.ingmicha.distance.controller.DistanceController;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/30/18.
 */
public class DistanceFragment extends Fragment implements DistanceView{

    private TextView distance;
    private TextView time;
    private DistanceView distanceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        distanceView = this;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_distance, container, false);
        setUpToolbar(view);

        final TextInputEditText from = view.findViewById(R.id.input_edit_from);
        final TextInputEditText to = view.findViewById(R.id.input_edit_to);

        MaterialButton send = view.findViewById(R.id.mb_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from.getText() != null && from.getText().length() >= 0){
                    if (to.getText() != null && to.getText().length() >= 0){
                        new DistanceController(getContext(),from.getText().toString(),to.getText().toString(),distanceView).result();
                    }else {
                        to.setError("Este campo no puede estar vacio");
                    }
                }else {
                    from.setError("Este campo no puede estar vacio");
                }
            }
        });

        distance = view.findViewById(R.id.tv_distance);
        time = view.findViewById(R.id.tv_time);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void showDistanceDetail(String distance, String time) {
        this.distance.setText(distance);
        this.time.setText(time);
    }

    @Override
    public void showDistanceError() {

    }
}
