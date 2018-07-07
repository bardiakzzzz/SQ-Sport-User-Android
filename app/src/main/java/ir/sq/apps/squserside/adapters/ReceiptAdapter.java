package ir.sq.apps.squserside.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.models.Receipt;
import ir.sq.apps.squserside.uiControllers.TypeFaceHandler;

public class ReceiptAdapter extends
        RecyclerView.Adapter<ReceiptAdapter.ViewHolder> {

    private static final String TAG = ReceiptAdapter.class.getSimpleName();

    private Context context;
    private List<Receipt> list;
    private OnItemClickListener onItemClickListener;

    public ReceiptAdapter(Context context, List<Receipt> list,
                          OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Todo Butterknife bindings
        @BindView(R.id.receipt_name_text_view)
        TextView receiptNameTextView;
        @BindView(R.id.receipt_date_text_view)
        TextView receiptDateTextView;
        @BindView(R.id.receipt_price_text_view)
        TextView receiptPriceTextView;
        @BindView(R.id.receipt_state_image_view)
        ImageView receiptStateImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final Receipt model,
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

        View view = inflater.inflate(R.layout.layout_receipt_item, parent, false);
        ButterKnife.bind(this, view);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.receiptNameTextView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
        viewHolder.receiptDateTextView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
        viewHolder.receiptPriceTextView.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Receipt item = list.get(position);
        holder.receiptNameTextView.setText(item.getClubName());
        holder.receiptDateTextView.setText(item.getDate());
        holder.receiptPriceTextView.setText(item.getPrice() + " " + context.getString(R.string.currency_string));
        if(new Random().nextBoolean())
            holder.receiptStateImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.circle_active));
        else
            holder.receiptStateImageView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.circle_di_active));

        //Todo: Setup viewholder for item
        holder.bind(item, onItemClickListener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}