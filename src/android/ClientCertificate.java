
package org.apache.cordova.plugin;

import android.annotation.TargetApi;
import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.ICordovaClientCertRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ClientCertificate extends CordovaPlugin {

    private String p12path = "";
    private String p12password = "";
    private CallbackContext callbackContext = null;


    @Override
    public Boolean shouldAllowBridgeAccess(String url) {
        return super.shouldAllowBridgeAccess(url);
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onReceivedClientCertRequest(CordovaWebView view, ICordovaClientCertRequest request) {
        KeyStore keystore = null;
        InputStream astream = null;
        try {
            keystore = KeyStore.getInstance("PKCS12");
            astream = cordova.getActivity().getApplicationContext().getAssets().open(p12path);
            keystore.load(astream, p12password.toCharArray());
            astream.close();
            Enumeration e = keystore.aliases();
            if (e.hasMoreElements()) {
                String ealias = (String) e.nextElement();
                PrivateKey key = (PrivateKey) keystore.getKey(ealias, p12password.toCharArray());
                java.security.cert.Certificate[] chain = keystore.getCertificateChain(ealias);
                X509Certificate[] certs = Arrays.copyOf(chain, chain.length, X509Certificate[].class);
                request.proceed(key, certs);
            } else {
                request.ignore();
            }
            JSONObject object = new JSONObject();
            object.put("p12Path", p12path);
            object.put("pass", p12password);
            callbackContext.success(object);
        } catch (KeyStoreException e) {
            resoleCatch(e, request);
            callbackContext.error("KeyStoreException");
        } catch (IOException e) {
            resoleCatch(e, request);
            callbackContext.error("IOException:" + e.getMessage());
        } catch (CertificateException e) {
            resoleCatch(e, request);
            callbackContext.error("CertificateException:" + e.getMessage());
        } catch (UnrecoverableKeyException e) {
            resoleCatch(e, request);
            callbackContext.error("UnrecoverableKeyException:" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            resoleCatch(e, request);
            callbackContext.error("NoSuchAlgorithmException:" + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void resoleCatch(Exception e, ICordovaClientCertRequest request) {
        e.printStackTrace();
        request.ignore();
    }

    @Override
    public boolean execute(String action, JSONArray a, CallbackContext c) throws JSONException {
        callbackContext = c;
        if (action.equals("register")) {
            p12path = "www/" + a.getString(0);
            p12password = a.getString(1);
            return true;
        }
        return false;
    }


}