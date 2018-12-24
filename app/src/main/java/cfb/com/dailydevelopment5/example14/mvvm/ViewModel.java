package cfb.com.dailydevelopment5.example14.mvvm;

/**
 * 使用了 data binding 库中的 View.OnAttachStateListener 来实现绑定，
 * 然后将 android:onViewAttachedToWindow 和 android:onViewDetachedFromWindow 映射到 ViewModel 类的对应方法当中。
 * 实现这些方法，并将其关联到 ViewModel 接口的 onAttach 和 onDetach 方法
 * Created by fengbincao on 2018/12/24.
 */
public interface ViewModel {

    void onAttach();

    void onDetach();

}
