package com.example.functionaltraining.Presentation.AccessAcount.Register.Interfaces;

import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Utils.DialogueGenerico;

public interface IRegisterUserView {
    void registerUserCorrect();
    void showDialogFragment(int title, int detail, DialogueGenerico.TypeDialogue type);
}
