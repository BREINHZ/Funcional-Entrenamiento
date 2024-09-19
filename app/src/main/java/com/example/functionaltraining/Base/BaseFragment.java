package com.example.functionaltraining.Base;

import static com.example.functionaltraining.Utils.Util.hideKeyboard;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;
import com.example.functionaltraining.Utils.Util;
import com.squareup.picasso.Picasso;

public class BaseFragment extends Fragment {

    private View view;

    public View getCustomView() {
        return view;
    }
    public void setCustomView(View view) {
        this.view = view;
    }

    public void dialogueFragment(int title, String detail, DialogueGenerico.TypeDialogue type) {
        DialogueGenerico.getInstance()
                .withTitle(title)
                .withText(detail)
                .withTypeDialogue(type)
                .withTextBtnAccept(R.string.btn_aceptar)
                .withActionBtnAccept(() -> Log.e("", ""));

        if (getContext() != null) {
            Util.showDialogueGenerico(getContext());
        }
    }

    public void imagenOnline(String imageUrl, ImageView imageView){
        Picasso.get()
                .load(imageUrl) // URL de la imagen
                .placeholder(R.drawable.splash_image) // Imagen de carga temporal
                .error(R.drawable.splash_image) // Imagen de error si la URL no funciona
                .into(imageView); // Donde se cargará la imagen
    }

    public void showYoutubeVideo(WebView webView, String url){
        String video =  String.format("<iframe width=\"100%%\" height=\"100%%\" src=\"%s\" title=\"Examen físico del adulto\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", url);
        webView.loadData(video, "text/html", "utf-8");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }

    public void hideKeyboardFragment(){
        hideKeyboard(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onResume() {
        super.onResume();
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                hideKeyboardFragment();
            }
            return false; // Devuelve false para que otros eventos táctiles sigan siendo manejados
        });
    }
}
