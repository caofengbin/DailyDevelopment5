package cfb.com.dailydevelopment5.example11.zhihu;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import cfb.com.dailydevelopment5.R;

/**
 * Created by caofengbin on 2018/4/10.
 */

public class ItemAdapter extends BaseRecyclerAdapter<String> {

    public ItemAdapter(Context context, List<String> datas) {
        super(context, R.layout.zhihu_item_string, datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position) {
        TextView tv=holder.getView(R.id.tv);
        tv.setText(item);

    }
}
