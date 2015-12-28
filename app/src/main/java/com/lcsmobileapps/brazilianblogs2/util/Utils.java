package com.lcsmobileapps.brazilianblogs2.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.lcsmobileapps.brazilianblogs2.R;
import com.lcsmobileapps.brazilianblogs2.sync.account.StubSyncService;

/**
 * Created by Leandro on 11/12/2015.
 */
public class Utils {
    public final static int FIRST_BACKGROUND = R.drawable.background_01_nao_salvo;
    public final static int FIRST_MENU = R.id.nav_01_naosalvo;

    public static Account createAccount(Context context) {
        Account newAccount = new Account(
                StubSyncService.ACCOUNT_NAME, StubSyncService.ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                       Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */


        }
        return newAccount;
    }

}
