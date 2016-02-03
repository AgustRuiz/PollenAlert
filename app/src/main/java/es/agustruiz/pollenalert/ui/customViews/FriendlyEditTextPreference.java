package es.agustruiz.pollenalert.ui.customViews;

import android.content.Context;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;

public class FriendlyEditTextPreference extends EditTextPreference {

    public FriendlyEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FriendlyEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FriendlyEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FriendlyEditTextPreference(Context context) {
        super(context);
    }

    @Override
    public CharSequence getSummary() {
        String text = getText();
        if (TextUtils.isEmpty(text)) {
            return getEditText().getHint();
        } else {
            CharSequence summary = super.getSummary();
            if (summary != null) {
                return String.format(summary.toString(), text);
            } else {
                return null;
            }
        }
    }

    protected void updateFriendlySummary(CharSequence newValue) {
        this.setSummary(newValue);
    }

    @Override
    protected boolean callChangeListener(Object newValue) {
        boolean result = super.callChangeListener(newValue);
        if (result) {
            this.updateFriendlySummary((CharSequence) newValue);
        }
        return result;
    }
}
