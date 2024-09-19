package com.example.functionaltraining.Presentation.Dash.Home.ui.plan_entrenaminto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.R;
import com.example.functionaltraining.databinding.FragmentPlanBinding;

public class PlanFragment extends BaseFragment {

    private FragmentPlanBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPlanBinding.inflate(inflater, container, false);
        setCustomView(binding.getRoot());

        showYoutubeVideo(binding.webViewPlan, getString(R.string.url_video_plan));

        return getCustomView();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}