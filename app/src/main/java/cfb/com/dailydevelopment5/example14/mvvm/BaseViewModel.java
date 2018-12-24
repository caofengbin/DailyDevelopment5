package cfb.com.dailydevelopment5.example14.mvvm;

import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by fengbincao on 2018/12/24.
 */

public abstract class BaseViewModel implements ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseViewModel() {
        //App.inject(this);
    }

    @Override
    public void onAttach() {
    }

    @Override
    public void onDetach() {
    }

    public final void onViewAttachedToWindow(View view) {
        onAttach();
    }

    public final void onViewDetachedFromWindow(View view) {
        compositeDisposable.clear();
        onDetach();
    }

    protected void addToAutoDispose(Disposable... disposables) {
        compositeDisposable.addAll(disposables);
    }

}
