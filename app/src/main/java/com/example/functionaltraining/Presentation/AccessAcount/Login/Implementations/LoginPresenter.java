package com.example.functionaltraining.Presentation.AccessAcount.Login.Implementations;

import android.content.Context;

import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.example.functionaltraining.DataAccess.SharedPreferences.SessionManager;
import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Presentation.AccessAcount.Login.Interfaces.ILoginView;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;

public class LoginPresenter {
    private final Context context;
    private final ILoginView loginView;
    private final UserDao dao;
    private final SessionManager sessionManager;

    public LoginPresenter( Context context, ILoginView view, UserDao dao) {
        this.context = context;
        this.loginView = view;
        this.sessionManager = new SessionManager(context);
        this.dao = dao;
        dao.openDb();
    }

    public void startSection(User user){
        if (!user.validateCredentialsUser()) {
            loginView.showDialogFragment(R.string.fields_empty, context.getString(R.string.details_fields_empty), DialogueGenerico.TypeDialogue.ADVERTENCIA);
            return;
        }
        String idUser = String.valueOf(user.initUserSection(dao));
        if (idUser.equals("-1")){
            loginView.credentialsIncorrect();
        }else {
            sessionManager.createLoginSession(user.getEmail(), idUser);
            loginView.credentialsCorrect();
        }
    }
}
