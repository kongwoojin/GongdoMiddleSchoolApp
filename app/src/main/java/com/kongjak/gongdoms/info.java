package com.kongjak.gongdoms;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.*;
import android.preference.Preference;
import android.support.v7.widget.Toolbar;

/**
 * Created by kongwoojin on 2016-09-18.
 */
public class info extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.info);
        setOnPreferenceClick(findPreference("allSource"));

        setContentView(R.layout.activity_setting);

        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        actionbar.setTitle(R.string.app_name);
        actionbar.setTitleTextColor(Color.WHITE);

    }
    private void setOnPreferenceClick(Preference mPreference) {
        mPreference.setOnPreferenceClickListener(onPreferenceClickListener);
    }
    private Preference.OnPreferenceClickListener onPreferenceClickListener = new Preference.OnPreferenceClickListener() {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String getKey = preference.getKey();
            if ("allSource".equals(getKey)) {
                android.support.v7.app.AlertDialog.Builder builder =
                        new android.support.v7.app.AlertDialog.Builder(info.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle(R.string.opensource);
                builder.setMessage(R.string.allsource);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
            return true;

        }
    };
}