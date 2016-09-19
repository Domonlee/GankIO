package domon.cn.gankio.utils.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Domon on 16-9-19.
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog mProgressDialog;

    private Context mContext;
    private boolean mCancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener,
                                 boolean cancelable) {
        super();
        this.mContext = context;
        this.mCancelable = cancelable;
        this.mProgressCancelListener = progressCancelListener;
    }

    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);

            mProgressDialog.setCancelable(mCancelable);

            if (mCancelable) {
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgressDialog();
                    }
                });
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
            default:
                break;
        }
    }
}

