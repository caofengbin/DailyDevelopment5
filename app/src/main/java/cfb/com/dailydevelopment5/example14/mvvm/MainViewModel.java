package cfb.com.dailydevelopment5.example14.mvvm;

import android.databinding.ObservableField;

/**
 * Created by fengbincao on 2018/12/24.
 */
public class MainViewModel extends BaseViewModel {

    public final ObservableField<MvvmComponent> mainComponent = new ObservableField<>();

    @Override
    public void onAttach() {
        super.onAttach();

        // 这里添加注册数据源的方法
//        addToAutoDispose(
//                RxOkuki.onPlace(okuki, SwapiListPlace.class).subscribe(
//                        mainComponent::set,
//                        Errors.log()
//                ),
//                RxOkuki.onPlace(okuki, SwapiImagePlace.class).subscribe(
//                        mainComponent::set,
//                        Errors.log()
//                )
//        );
//        if (okuki.getCurrentPlace() == null) {
//            okuki.gotoPlace(new SwapiListPlace(), HistoryAction.TRY_BACK_TO_SAME_TYPE);
//        }
    }

    boolean handleBack() {
        //return okuki.goBack();
        return true;
    }
}
