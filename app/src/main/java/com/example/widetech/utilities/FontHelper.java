package com.example.widetech.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class FontHelper {
    private Context context;
    private Typeface REGULAR;
    private Typeface BOLD;

    public FontHelper(Context context) {
        this.context = context;
        init();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public Methods
    ///////////////////////////////////////////////////////////////////////////
    public void applyFont(ViewGroup view) {
        if (view != null) {
            apply(view);
        }
    }

    public void applyRegularFont(View view) {
        if (view != null) {
            try {
                if (view instanceof ViewGroup) {
                    ViewGroup parentView = (ViewGroup) view;
                    for (int i = 0; i < parentView.getChildCount(); i++) {
                        View viewAux = parentView.getChildAt(i);

                        applyRegular(viewAux);
                    }
                } else {
                    applyRegular(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void applyBoldFont(View view) {
        if (view != null) {
            try {
                if (view instanceof ViewGroup) {
                    ViewGroup parentView = (ViewGroup) view;
                    for (int i = 0; i < parentView.getChildCount(); i++) {
                        View viewAux = parentView.getChildAt(i);

                        applyBold(viewAux);
                    }
                } else {
                    applyBold(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        try {
            REGULAR = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
            BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apply(ViewGroup parentView) {
        try {
            for (int i = 0; i < parentView.getChildCount(); i++) {
                View view = parentView.getChildAt(i);

                if (view instanceof CheckBox) {
                    Typeface typeFace = ((CheckBox) view).getTypeface();
                    if (typeFace == null)
                        ((CheckBox) view).setTypeface(REGULAR);
                    else {
                        if (typeFace.isBold())
                            ((CheckBox) view).setTypeface(BOLD);
                        else
                            ((CheckBox) view).setTypeface(REGULAR);
                    }
                } else if (view instanceof RadioButton) {
                    Typeface typeFace = ((RadioButton) view).getTypeface();
                    if (typeFace == null)
                        ((RadioButton) view).setTypeface(REGULAR);
                    else {
                        if (typeFace.isBold())
                            ((RadioButton) view).setTypeface(BOLD);
                        else
                            ((RadioButton) view).setTypeface(REGULAR);
                    }
                } else if (view instanceof Button) {
                    Typeface typeFace = ((Button) view).getTypeface();
                    if (typeFace == null)
                        ((Button) view).setTypeface(REGULAR);
                    else {
                        if (typeFace.isBold())
                            ((Button) view).setTypeface(BOLD);
                        else
                            ((Button) view).setTypeface(REGULAR);
                    }
                } else if (view instanceof EditText) {
                    Typeface typeFace = ((EditText) view).getTypeface();
                    if (typeFace == null)
                        ((EditText) view).setTypeface(REGULAR);
                    else {
                        if (typeFace.isBold())
                            ((EditText) view).setTypeface(BOLD);
                        else
                            ((EditText) view).setTypeface(REGULAR);
                    }
                } else if (view instanceof TextView) {
                    Typeface typeFace = ((TextView) view).getTypeface();
                    if (typeFace == null)
                        ((TextView) view).setTypeface(REGULAR);
                    else {
                        if (typeFace.isBold())
                            ((TextView) view).setTypeface(BOLD);
                        else
                            ((TextView) view).setTypeface(REGULAR);
                    }
                } else if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
                    apply((ViewGroup) view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyRegular(View view) {
        if (view instanceof CheckBox) {
            ((CheckBox) view).setTypeface(REGULAR);
        } else if (view instanceof RadioButton) {
            ((RadioButton) view).setTypeface(REGULAR);
        } else if (view instanceof Button) {
            ((Button) view).setTypeface(REGULAR);
        } else if (view instanceof EditText) {
            ((EditText) view).setTypeface(REGULAR);
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(REGULAR);
        } else if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            apply((ViewGroup) view);
        }
    }

    private void applyBold(View view) {
        if (view instanceof CheckBox) {
            ((CheckBox) view).setTypeface(BOLD);
        } else if (view instanceof RadioButton) {
            ((RadioButton) view).setTypeface(BOLD);
        } else if (view instanceof Button) {
            ((Button) view).setTypeface(BOLD);
        } else if (view instanceof EditText) {
            ((EditText) view).setTypeface(BOLD);
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(BOLD);
        } else if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            apply((ViewGroup) view);
        }
    }
}
