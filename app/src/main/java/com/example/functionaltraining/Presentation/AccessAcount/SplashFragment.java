package com.example.functionaltraining.Presentation.AccessAcount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.DataAccess.SharedPreferences.SessionManager;
import com.example.functionaltraining.Presentation.Dash.Home.HomeActivity;
import com.example.functionaltraining.R;

public class SplashFragment extends BaseFragment {
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setCustomView(inflater.inflate(R.layout.fragment_splash, container, false));
        return getCustomView();
    }

    @Override
    public void onResume() {
        super.onResume();
        sessionManager = new SessionManager(requireContext());
        new Handler().postDelayed(() -> {
            if (sessionManager.isLoggedIn()) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            } else {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment);
            }
        }, 4000);
    }
}