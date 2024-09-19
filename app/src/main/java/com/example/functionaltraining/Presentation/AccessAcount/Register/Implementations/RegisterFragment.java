package com.example.functionaltraining.Presentation.AccessAcount.Register.Implementations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.functionaltraining.Base.BaseFragment;
import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Presentation.AccessAcount.Register.Interfaces.IRegisterUserView;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;
import com.example.functionaltraining.Utils.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends BaseFragment {

    private RegisterPresenter presenter;
    private UserDao dao;
    private TextInputEditText email, name, phone, pass;
    private TextInputLayout tlEmail, tlPass;
    private EditText confPass;
    private Button createUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setCustomView(inflater.inflate(R.layout.fragment_register, container, false));

        dao = new UserDao(getContext());
        presenter = new RegisterPresenter(getContext(), new listenerPresenter(), dao);

        tlEmail = getCustomView().findViewById(R.id.textField_email_register);
        tlPass = getCustomView().findViewById(R.id.textField_pass_register);
        email = getCustomView().findViewById(R.id.et_email_register);
        name = getCustomView().findViewById(R.id.et_name_register);
        phone = getCustomView().findViewById(R.id.et_phone_register);
        pass = getCustomView().findViewById(R.id.et_pass_register);
        confPass = getCustomView().findViewById(R.id.et_conf_pass_register);
        createUser = getCustomView().findViewById(R.id.btnCrear_register);

        return getCustomView();
    }

    @Override
    public void onResume() {
        super.onResume();
        createUser.setOnClickListener(v ->{
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setName(name.getText().toString());
            user.setPhone(phone.getText().toString());
            user.setPassword(pass.getText().toString());
            user.setConfPassword(confPass.getText().toString());
            presenter.registerUser(user);
        });
        Util.validateEmailFormat(email, tlEmail, getString(R.string.error_correo_formato));
        Util.validateSizePassCharacters(pass, tlPass);
    }

    private class listenerPresenter implements IRegisterUserView {
        @Override
        public void registerUserCorrect() {
            Toast.makeText(getContext(), getString(R.string.user_create), Toast.LENGTH_LONG).show();
            Navigation.findNavController(requireView()).navigateUp();
        }

        @Override
        public void showDialogFragment(int title, int detail, DialogueGenerico.TypeDialogue type) {
            dialogueFragment(title, getString(detail), type);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dao.closeDb();
    }
}