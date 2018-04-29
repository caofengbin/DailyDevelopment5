package cfb.com.dailydevelopment5.example12.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import cfb.com.dailydevelopment5.R;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

//        ImageView mImageView = (ImageView) findViewById(R.id.main_image);
//        // 最简单的使用方式
//        String imageUrl = "http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg";
//        Picasso.with(this)
//                .load(imageUrl)
//                .into(mImageView);
//        Picasso.with(this).setIndicatorsEnabled(true);

        GridView mGridView = (GridView) findViewById(R.id.main_grid_view);
        List<String> downloadUrl = new ArrayList<>();
        downloadUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1faaywuvd20j20u011gdli.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034gw1fa9dca082pj20u00u0wjc.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1fa42ktmjh4j20u011hn8g.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f9tmhxq87lj20u011htae.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f9nuk0nvrdj20u011haci.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034gw1f9lmfwy2nij20u00u076w.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f9us52puzsj20u00u0jtd.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f9tmhxq87lj20u011htae.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034gw1f9shm1cajkj20u00jy408.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034gw1f9lmfwy2nij20u00u076w.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f9b46kpoeoj20ku0kuwhc.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f95hzq3p4rj20u011htbm.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1f8zlenaornj20u011idhv.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f8xz7ip2u5j20u011h78h.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f8p9eahanlj20u011h42y.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f8lox7c1pbj20u011h12x.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f820oxtdzzj20hs0hsdhl.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f7y296c5taj20u00u0tay.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1f7lughzrjmj20u00k9jti.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f740f701gqj20u011hgo9.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f6uv5gbsa9j20u00qxjt6.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f6pnw6i7lqj20u00u0tbr.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f5xwnxj2vmj20dw0dwjsc.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f5s5382uokj20fk0ncmyt.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f5md1e68p9j20fk0ncgo0.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034gw1f5pu0w0r56j20m80rsjuy.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f5iv5babirj20zk0nptcn.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f5e7x5vlfyj20dw0euaax.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f5d36vpqyuj20zk0qo7fc.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f5byokn81tj20dw0hiwfe.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f550nt5zklj20zk19rtf3.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f52pe9xxn5j20dw0kidh6.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f4saelbb4oj20zk0qoage.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f4vmdn2f5nj20kq0rm755.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz7s830fj20gg0o00y5.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqrpw1fvj21911wlb2b.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqribdg1j21911w0kjn.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f4nog8tjfrj20eg0mgab7.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f4mi70ns1bj20i20vedkh.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1f4kron1wqaj20ia0rf436.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034gw1f4hvgpjjapj20ia0ur0vr.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034gw1f4fkmatcvdj20hs0qo78s.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/610dc034jw1f4d4iji38kj20sg0izdl1.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f49s6i5pg7j20go0p043b.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f46bsdcls2j20sg0izac0.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/610dc034jw1f454lcdekoj20dw0kumzj.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f41lxgc3x3j20jh0tcn14.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/610dc034jw1f40k4dyrhhj20iz0sg41b.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f3zen8idmkj20dw0kun0i.jpg");
        downloadUrl.add("http://ww1.sinaimg.cn/large/7a8aed7bjw1f3y998rv5uj20m80vxq6c.jpg");
        downloadUrl.add("http://ww4.sinaimg.cn/large/610dc034jw1f3x32bd1hcj20d90k03zx.jpg");
        downloadUrl.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3tkjebzzpj20kg0v7q9h.jpg");
        downloadUrl.add("http://ww3.sinaimg.cn/large/7a8aed7bjw1f3rdepqtnij21kw2dc1cx.jpg");

        ImageAdapter imageAdapter = new ImageAdapter(this, downloadUrl);
        mGridView.setAdapter(imageAdapter);
    }
}
