package com.example.functionaltraining.Presentation.Dash.Home.ui.dias_entrenamiento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.R;
import com.example.functionaltraining.databinding.FragmentDiasBinding;

public class DiasEntrenamientoFragment extends BaseFragment {

    private FragmentDiasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDiasBinding.inflate(inflater, container, false);
        setCustomView(binding.getRoot());

        imagenOnline(getString(R.string.url_image_dias), binding.ivDias);

        return getCustomView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}