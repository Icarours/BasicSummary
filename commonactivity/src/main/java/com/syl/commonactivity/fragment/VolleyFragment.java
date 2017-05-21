package com.syl.commonactivity.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.syl.commonactivity.R;
import com.syl.commonactivity.base.BaseFragment;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Bright on 2017/5/12.
 *
 * @Describe Volley请求
 * @Called
 */

public class VolleyFragment extends BaseFragment implements View.OnClickListener {
    private final String load = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEBzCCA3CgAwIBAgIBADANBgkqhkiG9w0BAQQFADCBlDELMAkGA1UEBhMCVVMxCzAJBgNVBAgT\n" +
            "AldBMSEwHwYDVQQKExhVbml2ZXJzaXR5IG9mIFdhc2hpbmd0b24xFDASBgNVBAsTC1VXIFNlcnZp\n" +
            "Y2VzMRcwFQYDVQQDEw5VVyBTZXJ2aWNlcyBDQTEmMCQGCSqGSIb3DQEJARYXaGVscEBjYWMud2Fz\n" +
            "aGluZ3Rvbi5lZHUwHhcNMDMwMjI1MTgyNTA5WhcNMzAwOTAzMTgyNTA5WjCBlDELMAkGA1UEBhMC\n" +
            "VVMxCzAJBgNVBAgTAldBMSEwHwYDVQQKExhVbml2ZXJzaXR5IG9mIFdhc2hpbmd0b24xFDASBgNV\n" +
            "BAsTC1VXIFNlcnZpY2VzMRcwFQYDVQQDEw5VVyBTZXJ2aWNlcyBDQTEmMCQGCSqGSIb3DQEJARYX\n" +
            "aGVscEBjYWMud2FzaGluZ3Rvbi5lZHUwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALwCo6h4\n" +
            "T44m+7ve+BrnEqflqBISFaZTXyJTjIVQ39ZWhE0B3LafbbZYju0imlQLG+MEVAtNDdiYICcBcKsa\n" +
            "pr2dxOi31Nv0moCkOj7iQueMVU4E1TghYIR2I8hqixFCQIP/CMtSDail/POzFzzdVxI1pv2wRc5c\n" +
            "L6zNwV25gbn3AgMBAAGjggFlMIIBYTAdBgNVHQ4EFgQUVdfBM8b6k/gnPcsgS/VajliXfXQwgcEG\n" +
            "A1UdIwSBuTCBtoAUVdfBM8b6k/gnPcsgS/VajliXfXShgZqkgZcwgZQxCzAJBgNVBAYTAlVTMQsw\n" +
            "CQYDVQQIEwJXQTEhMB8GA1UEChMYVW5pdmVyc2l0eSBvZiBXYXNoaW5ndG9uMRQwEgYDVQQLEwtV\n" +
            "VyBTZXJ2aWNlczEXMBUGA1UEAxMOVVcgU2VydmljZXMgQ0ExJjAkBgkqhkiG9w0BCQEWF2hlbHBA\n" +
            "Y2FjLndhc2hpbmd0b24uZWR1ggEAMAwGA1UdEwQFMAMBAf8wKwYDVR0RBCQwIoYgaHR0cDovL2Nl\n" +
            "cnRzLmNhYy53YXNoaW5ndG9uLmVkdS8wQQYDVR0fBDowODA2oDSgMoYwaHR0cDovL2NlcnRzLmNh\n" +
            "Yy53YXNoaW5ndG9uLmVkdS9VV1NlcnZpY2VzQ0EuY3JsMA0GCSqGSIb3DQEBBAUAA4GBAIn0PNmI\n" +
            "JjT9bM5d++BtQ5UpccUBI9XVh1sCX/NdxPDZ0pPCw7HOOwILumpulT9hGZm9Rd+W4GnNDAMV40we\n" +
            "s8REptvOZObBBrjaaphDe1D/MwnrQythmoNKc33bFg9RotHrIfT4EskaIXSx0PywbyfIR1wWxMpr\n" +
            "8gbCjAEUHNF/\n" +
            "-----END CERTIFICATE-----";
    private View mRootView;
    private ImageView mIvImg;

    @Override
    public View initRootView() {
        mRootView = View.inflate(getContext(), R.layout.fragment_volley, null);
        return mRootView;
    }

    @Override
    public void initView() {
        mIvImg = (ImageView) mRootView.findViewById(R.id.iv_img);
    }

    @Override
    public void initListener() {
        mRootView.findViewById(R.id.btn_load_img).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load_img:
                loadImg();
                System.out.println("clicked--------");
                break;
            default:
                break;
        }
    }

    private void loadImg() {
       /* new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://certs.cac.washington.edu/CAtest/");
                    URLConnection urlConnection = url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    printInputStream(in);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
        //生成SSLSocketFactory
        SSLSocketFactory sslSocketFactory = initSSLSocketFactory();

        //HurlStack两个参数默认都是null,如果传入SSLSocketFactory，那么会以Https的方式来请求网络
        HurlStack stack = new HurlStack(null, sslSocketFactory);

        //通常，我们调用的是Volley.newRequestQueue(context),HurlStack为默认的，也就是不处理Https的情况
        //现在传入处理Https的HurlStack，Volley就会去处理相应的请求
        RequestQueue requestQueue = Volley.newRequestQueue(getContext(), stack);

        //去访问网络
        /*StringRequest stringRequest = new StringRequest("https://certs.cac.washington.edu/CAtest/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponse", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                });
        requestQueue.add(stringRequest);*/
    }

    private void printInputStream(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String rs = sb.toString();
        Log.d("inputSteam", rs);
    }

    private SSLSocketFactory initSSLSocketFactory() {
        //生成证书:Certificate
        CertificateFactory cf = null;
        SSLSocketFactory factory = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = new ByteArrayInputStream(load.getBytes());
            Certificate ca = null;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                try {
                    caInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //初始化公钥:keyStore
            String keyType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            //初始化TrustManagerFactory
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(algorithm);
            managerFactory.init(keyStore);

            //初始化sslContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, managerFactory.getTrustManagers(), null);
            factory = sslContext.getSocketFactory();

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return factory;
    }
}
