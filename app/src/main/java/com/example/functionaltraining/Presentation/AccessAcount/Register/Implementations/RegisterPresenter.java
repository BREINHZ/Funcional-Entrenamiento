package com.example.functionaltraining.Presentation.AccessAcount.Register.Implementations;

import android.content.Context;

import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.example.functionaltraining.DataAccess.SharedPreferences.SessionManager;
import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Presentation.AccessAcount.Login.Interfaces.ILoginView;
import com.example.functionaltraining.Presentation.AccessAcount.Register.Interfaces.IRegisterUserView;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;

public class RegisterPresenter {
    private final Context context;
    private final IRegisterUserView view;
    private final UserDao dao;

    public RegisterPresenter(Context context, IRegisterUserView view, UserDao dao) {
        this.context = context;
        this.view = view;
        this.dao = dao;
        dao.openDb();
    }

    public void registerUser(User user){
        if (!user.validatePassEqualConfirPass()) {
            view.showDialogFragment(
                    R.string.password,
                    R.string.details_password,
                    DialogueGenerico.TypeDialogue.ADVERTENCIA
            );
            return;
        }
        if (!user.validateFieldsUser()) {
            view.showDialogFragment(R.string.fields_empty, R.string.details_fields_empty, DialogueGenerico.TypeDialogue.ADVERTENCIA);
            return;
        }
        if (user.insertUser(dao) == -1){
            view.showDialogFragment(R.string.btn_create, R.string.user_register_incorrectt, DialogueGenerico.TypeDialogue.ERROR);;
        }else {
            view.registerUserCorrect();
        }
    }
}
