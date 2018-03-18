package cfb.com.dailydevelopment5.example6.mvp;

/**
 * View层定义的视图逻辑相关的接口方法
 * 具体的实现类，为Activity/Fragment
 *
 * Created by fengbincao on 2018/3/18.
 */

interface ILoginView {

    void onClearText();

    void onLoginResult(Boolean result, int code);
}
