package domon.cn.gankio.utils.progress;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by Domon on 16-9-19.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context mContext;

    public ProgressSubscriber(SubscriberOnNextListener subscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = subscriberOnNextListener;
        this.mContext = context;
        mProgressDialogHandler = new ProgressDialogHandler(mContext, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG);
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG);
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        // TODO: 16-9-19 err
    }

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    @Override
    public void onCancelProgressDialog() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
