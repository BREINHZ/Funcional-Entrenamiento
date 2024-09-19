package com.example.functionaltraining.Presentation.Dash.Home.ui.rutinas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.R;
import com.example.functionaltraining.databinding.FragmentRutinasBinding;

public class RutinasFragment extends BaseFragment {

    private FragmentRutinasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRutinasBinding.inflate(inflater, container, false);
        setCustomView(binding.getRoot());

        imagenOnline(getString(R.string.url_image_rutinas), binding.ivRutinas);

        return getCustomView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}