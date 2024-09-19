package com.example.functionaltraining.Presentation.AccessAcount.Login.Interfaces;

import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Utils.DialogueGenerico;

public interface ILoginView {
    void credentialsCorrect();
    void credentialsIncorrect();
    void showDialogFragment(int title, String detail, DialogueGenerico.TypeDialogue type);
}
