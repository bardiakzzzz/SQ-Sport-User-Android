package ir.sq.apps.squserside.uiControllers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import ir.sq.apps.squserside.R;


public class DialogHandler {
    private Context context;

    public DialogHandler(Context context) {
        this.context = context;
    }

    public ProgressDialog getInstanceProgress() {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setIndeterminate(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("لطفا چند لحظه صبر کنید...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setIndeterminate(false);
        dialog.setIndeterminateDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.loading_anim, null));
        dialog.setCancelable(true);
        return dialog;
    }

    public void showAlert(DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setPositiveButton("بله", listener);
        builder.setMessage("آیا مطمئن هستید؟");
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.darkGreen));
            }
        });
        dialog.show();
    }

    public void showAlert(DialogInterface.OnClickListener listener, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setPositiveButton("بله", listener);
        builder.setMessage(text);
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.darkGreen));
            }
        });
        dialog.show();
    }

    public void showAlert(DialogInterface.OnClickListener listenerYes, String text,
                          DialogInterface.OnClickListener listenerNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setPositiveButton("بله", listenerYes);
        builder.setMessage(text);
        builder.setNegativeButton("خیر", listenerNo);
        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.darkGreen));
            }
        });
        dialog.show();
    }
}
