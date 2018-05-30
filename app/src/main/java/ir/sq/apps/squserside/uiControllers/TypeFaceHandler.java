package ir.sq.apps.squserside.uiControllers;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;


public class TypeFaceHandler {
    private Typeface en_light;
    private Typeface fa_bold;
    private Typeface fa_light;
    private static TypeFaceHandler typeFaceHandler;

    public static TypeFaceHandler getInstance(Context context){
        if (typeFaceHandler == null){
            typeFaceHandler = new TypeFaceHandler(context.getAssets());
        }
        return typeFaceHandler;
    }

    private TypeFaceHandler(AssetManager manager) {
        en_light = Typeface.createFromAsset(manager, "fonts/En_Light.ttf");
        fa_bold = Typeface.createFromAsset(manager, "fonts/Fa_Bold.ttf");
        fa_light = Typeface.createFromAsset(manager, "fonts/Fa_Light.ttf");
    }

    public Typeface getEn_light() {
        return en_light;
    }

    public Typeface getFa_bold() {
        return fa_bold;
    }

    public Typeface getFa_light() {
        return fa_light;
    }
}
