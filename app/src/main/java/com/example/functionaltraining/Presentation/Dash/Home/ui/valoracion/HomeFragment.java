package com.example.functionaltraining.Presentation.Dash.Home.ui.valoracion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.R;
import com.example.functionaltraining.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setCustomView(binding.getRoot());

        showYoutubeVideo(binding.webView, getString(R.string.url_video_valoracion));

        return getCustomView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}