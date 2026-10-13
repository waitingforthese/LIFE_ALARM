# Firebase configuration required after package rename

New Android package / applicationId: `rahul.jagtap.life_alarm`

The included `app/google-services.json` is intentionally preserved from the original project. Its registered Android clients are `rahul.jagtap.lifealarm` and `rahul.jagtap.myaccountant`; neither matches the new package. Before building/running the app, open Firebase Console for project `my-accountant-e5b00`, add a new Android app with package `rahul.jagtap.life_alarm`, download its fresh `google-services.json`, and replace `app/google-services.json`. Reconfigure SHA-1/SHA-256 fingerprints and Google Sign-In OAuth clients as applicable. Do not edit only the package_name inside the old JSON: that does not register the new Android app and may break Firebase/Google Sign-In.
