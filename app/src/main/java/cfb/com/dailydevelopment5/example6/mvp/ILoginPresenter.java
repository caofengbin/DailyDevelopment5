package cfb.com.dailydevelopment5.example6.mvp;

/**
 * Presenter层接口定义
 * 定义业务逻辑所需要的接口方法
 *
 * Created by fengbincao on 2018/3/18.
 */

interface ILoginPresenter {

    void clear();

    void doLogin(String name, String password);
}
