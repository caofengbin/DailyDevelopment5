package cfb.com.dailydevelopment5.example12.picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cfb.com.dailydevelopment5.R;

/**
 * Created by fengbincao on 2018/4/27.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDownLoadList = new ArrayList<>();

    ImageAdapter(Context context, List<String> downLoadList) {
        this.mContext = context;
        this.mDownLoadList = downLoadList;
    }

    @Override
    public int getCount() {
        return mDownLoadList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDownLoadList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageViewHolder imageViewHolder;
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.image_view_list_layout, null);
            imageViewHolder = new ImageViewHolder();
            imageViewHolder.mImageView = (ImageView) view.findViewById(R.id.image_view_list);

            view.setTag(imageViewHolder);
        } else {
            view = convertView;
            imageViewHolder = (ImageViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mDownLoadList.get(position))
                .tag("PhotoTag")
                .fit()
                .into(imageViewHolder.mImageView);

        Picasso.with(mContext).setIndicatorsEnabled(true);
        return view;
    }

    private static class ImageViewHolder {
        ImageView mImageView;
    }

}
