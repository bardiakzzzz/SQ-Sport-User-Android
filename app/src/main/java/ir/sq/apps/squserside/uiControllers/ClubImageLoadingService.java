package ir.sq.apps.squserside.uiControllers;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ir.sq.apps.squserside.models.Club;
import ss.com.bannerslider.ImageLoadingService;

/**
 * Created by Mohammad on 5/30/2018.
 */

public class ClubImageLoadingService implements ImageLoadingService {

    public Context context;

    public ClubImageLoadingService(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawable).into(imageView);
    }
}

