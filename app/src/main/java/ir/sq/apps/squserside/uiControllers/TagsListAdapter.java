package ir.sq.apps.squserside.uiControllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;

/**
 * Created by Mohammad on 5/30/2018.
 */
public class TagsListAdapter extends
        RecyclerView.Adapter<TagsListAdapter.ViewHolder> {

    private static final String TAG = TagsListAdapter.class.getSimpleName();
    private Context context;
    private List<String> list;
    private OnItemClickListener onItemClickListener;

    public TagsListAdapter(Context context, List<String> list,
                           OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Todo Butterknife bindings
        @BindView(R.id.tag_textview)
        TextView tagTextview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final String model,
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

        View view = inflater.inflate(R.layout.layout_item_tag, parent, false);
        ButterKnife.bind(this, view);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = list.get(position);

        //Todo: Setup viewholder for item 
        holder.bind(item, onItemClickListener);
        holder.tagTextview.setText(item);
        holder.tagTextview.setTypeface(TypeFaceHandler.getInstance(context).getFa_light());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}