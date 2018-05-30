package ir.sq.apps.squserside.uiControllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.UrlHandler;
import ir.sq.apps.squserside.models.Club;
import me.grantland.widget.AutofitTextView;

/**
 * Created by Mohammad on 4/8/2018.
 */
public class ClubsListAdapter extends
        RecyclerView.Adapter<ClubsListAdapter.ViewHolder> {

    private static final String TAG = ClubsListAdapter.class.getSimpleName();


    private Context context;
    private List<Club> list;
    private OnItemClickListener onItemClickListener;

    public ClubsListAdapter(Context context, List<Club> list,
                            OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Todo Butterknife bindings
        @BindView(R.id.txtRate)
        AutofitTextView txtRate;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.txtName)
        AutofitTextView txtName;
        @BindView(R.id.txtPrice)
        AutofitTextView txtPrice;
        @BindView(R.id.imgBg)
        ANImageView bg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final Club model,
                         final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition());

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_item_club, parent, false);
        ButterKnife.bind(this, view);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Club item = list.get(position);

        //Todo: Setup viewholder for item 
        holder.bind(item, onItemClickListener);
        holder.txtName.setTypeface(TypeFaceHandler.getInstance(context).getFa_bold());
        holder.txtPrice.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
        holder.txtRate.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());
        double rate = item.getRate();
        if (rate - (int) rate != 0) {
            int t = (int) rate;
            holder.txtRate.setText(t + "/5");
        }
        holder.txtRate.setText(rate + "/5");
        holder.bg.setDefaultImageResId(R.drawable.no_image);
        holder.bg.setErrorImageResId(R.drawable.failed_image);
        holder.bg.setImageUrl(UrlHandler.getImageClubURL.getUrl() + item.getImageName(0));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}