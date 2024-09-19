package com.example.functionaltraining.Presentation.AccessAcount.Login.Implementations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Presentation.AccessAcount.Login.Interfaces.ILoginView;
import com.example.functionaltraining.Presentation.Dash.Home.HomeActivity;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends BaseFragment {
    private LoginPresenter presenter;
    private UserDao dao;
    private TextInputEditText etEmailLogin;
    private EditText etPassLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setCustomView(inflater.inflate(R.layout.fragment_login, container, false));
        dao = new UserDao(getContext());
        presenter = new LoginPresenter(getContext(), new listenerPresenter(), dao);

        TextView tvRegisterUser = getCustomView().findViewById(R.id.tv_register_user);
        etEmailLogin = getCustomView().findViewById(R.id.et_email_login);
        etPassLogin = getCustomView().findViewById(R.id.et_Pass_Login);
        Button btnStartSection = getCustomView().findViewById(R.id.btn_start_section);

        btnStartSection.setOnClickListener( v-> {
            User user = new User();
            user.setEmail(etEmailLogin.getText().toString());
            user.setPassword(etPassLogin.getText().toString());
            presenter.startSection(user);
        });
        tvRegisterUser.setOnClickListener(v-> Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registerFragment));

        return getCustomView();
    }

    private class listenerPresenter implements ILoginView {
        @Override
        public void credentialsCorrect() {
            Toast.makeText(getContext(), getString(R.string.login_user), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }

        @Override
        public void credentialsIncorrect() {
            dialogueFragment(R.string.credentials_incorrect,
                    getString(R.string.details_credentials_incorrect),
                    DialogueGenerico.TypeDialogue.ERROR);
        }

        @Override
        public void showDialogFragment(int title, String detail, DialogueGenerico.TypeDialogue type) {
            dialogueFragment(title, detail, type);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.closeDb();
    }
}