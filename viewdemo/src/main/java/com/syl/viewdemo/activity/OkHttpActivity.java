package com.syl.viewdemo.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.syl.viewdemo.R;
import com.syl.viewdemo.utils.StringTool;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author   Bright
 * date     2017/5/19 1:49
 * desc
 * okHttp
 * https请求的一般步骤:
 * 1.加载服务器签名证书
 * 2.创建认证管理器TrustManager[]
 * 3.创建SSLContext
 * 4.创建SSLSocketFactory,HttpsUrlConnection.setSSLSocketFactory
 *
 * 加载请求返回的网页用mWvWeb.loadDataWithBaseURL();
 * 如果用mWvWeb.loadData();会出现中文乱码
 */
public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = OkHttpActivity.class.getSimpleName();
    @BindView(R.id.btn_https_get)
    Button mBtnHttpsGet;
    @BindView(R.id.btn_https_get2)
    Button mBtnHttpGet;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.wv_web)
    WebView mWvWeb;
    private X509Certificate mServerCert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
        WebSettings settings = mWvWeb.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);

    }

    @OnClick({R.id.btn_https_get, R.id.btn_https_get2, R.id.btn_https_get3})
    public void btn_https_get(View view) {
        switch (view.getId()) {
            case R.id.btn_https_get:
                btn_https_get();
                break;
            case R.id.btn_https_get2:
                btn_https_get2();
                break;
            case R.id.btn_https_get3:
                btn_https_get3();
                break;
            default:
                break;
        }
    }

    /**
     * 使用okHttp发起https_get请求
     */
    private void btn_https_get3() {
        final ProgressDialog progressDialog = new ProgressDialog(OkHttpActivity.this);
        progressDialog.setMessage("正在加载,请稍后..");
        progressDialog.setTitle("加载中..");
        progressDialog.show();

        //1.加载服务器签名证书,拿到证书
        try {
            String certName = "githubcom.crt";
            InputStream certInput = new BufferedInputStream(getAssets().open(certName));
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            mServerCert = (X509Certificate) certificateFactory.generateCertificate(certInput);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        String https_url = "https://github.com/hongyangAndroid/okhttputils";
        try {
            //2.获取证书管理器,校验证书
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            if (chain == null) {
                                throw new IllegalArgumentException("Check Server x509Certificate is null");
                            }
                            if (chain.length < 0) {
                                throw new IllegalArgumentException("Check Server x509Certificate is empty");
                            }
                            for (X509Certificate cert :
                                    chain) {
                                //校验证书,检查服务器端签名
                                cert.checkValidity();
                                try {
                                    //和预埋的app证书做对比
                                    cert.verify(mServerCert.getPublicKey());
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                } catch (InvalidKeyException e) {
                                    e.printStackTrace();
                                } catch (NoSuchProviderException e) {
                                    e.printStackTrace();
                                } catch (SignatureException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };
            //3.初始化SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, null);
            //4.创建SSLSocketFactory
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            URL url = new URL(https_url);
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.newBuilder()
                    .sslSocketFactory(socketFactory, (X509TrustManager) trustAllCerts[0])
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream inputStream = response.body().byteStream();

                    final String s = StringTool.inputStream2String(inputStream);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWvWeb.loadDataWithBaseURL(null,s, "text/html", "utf-8",null);
                            //System.out.println(s);
                            progressDialog.dismiss();
                        }
                    });
                }
            });

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 访问带数字证书的https的网站
     */
    private void btn_https_get2() {
        httpsVerify();
        //getUnSafeFromServer();
    }

    private void btn_https_get() {
        final ProgressDialog progressDialog = new ProgressDialog(OkHttpActivity.this);
        progressDialog.setMessage("正在加载,请稍后..");
        progressDialog.setTitle("加载中..");
        progressDialog.show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //"https://wikipedia.org"维基百科被国内屏蔽,如果不翻墙,是访问不到的
                    URL url = new URL("https://wikipedia.org");
                    URLConnection urlConnection = url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    final String s = StringTool.inputStream2String(in);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // mTvContent.setText(s);
                            mWvWeb.loadData(s, "text/html", "UTF-8");
                            progressDialog.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 带数字证书的https
     *
     * @throws NoSuchAlgorithmException
     */
    public void getUnSafeFromServer() {
        final ProgressDialog progressDialog = new ProgressDialog(OkHttpActivity.this);
        progressDialog.setMessage("正在加载,请稍后..");
        progressDialog.setTitle("加载中..");
        progressDialog.show();
        final String https_url = "https://certs.cac.washington.edu/";

        //1.加载服务器签名证书,拿到证书
        try {
            String certName = "certscacwashingtonedu.crt";
            InputStream certInput = new BufferedInputStream(getAssets().open(certName));
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            mServerCert = (X509Certificate) certificateFactory.generateCertificate(certInput);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        new AsyncTask<String, Void, Boolean>() {

            private String mS;

            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    TrustManager[] trustAllCerts = new TrustManager[]{
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                                }

                                @Override
                                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                                    if (chain == null) {
                                        throw new IllegalArgumentException("Check Server x509Certificate is null");
                                    }
                                    if (chain.length < 0) {
                                        throw new IllegalArgumentException("Check Server x509Certificate is empty");
                                    }
                                    for (X509Certificate cert :
                                            chain) {
                                        //检查服务器端签名
                                        cert.checkValidity();
                                        try {
                                            //和预埋的app证书做对比
                                            cert.verify(mServerCert.getPublicKey());
                                        } catch (NoSuchAlgorithmException e) {
                                            e.printStackTrace();
                                        } catch (InvalidKeyException e) {
                                            e.printStackTrace();
                                        } catch (NoSuchProviderException e) {
                                            e.printStackTrace();
                                        } catch (SignatureException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[0];
                                }
                            }
                    };
                    SSLContext sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, trustAllCerts, null);

                    URL url = new URL(https_url);
                    final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                        /**
                         * 校验证书的服务器域名是否相等
                         * @param hostname
                         * @param session
                         * @return
                         */
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            HostnameVerifier hv = httpsURLConnection.getHostnameVerifier();
                            boolean b = hv.verify("*.washington.edu", session);
                            return b;
                        }
                    };
                    //对服务器域名证书进行校验
                    //httpsURLConnection.setHostnameVerifier(hostnameVerifier);
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    mS = StringTool.inputStream2String(inputStream);
                    return true;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return false;
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    return false;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (!aBoolean) {
                    Log.d(TAG, "ssl error");
                }
                mWvWeb.loadData(mS, "text/html", "UTF-8");
                progressDialog.dismiss();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(Boolean aBoolean) {
                super.onCancelled(aBoolean);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(https_url);
    }

    /**
     * 使用签名证书访问https
     */
    public void httpsVerify() {
        final ProgressDialog progressDialog = new ProgressDialog(OkHttpActivity.this);
        progressDialog.setMessage("正在加载,请稍后..");
        progressDialog.setTitle("加载中..");
        progressDialog.show();


        //1.加载服务器签名证书,拿到证书
        try {
            String certName = "certscacwashingtonedu.crt";
            InputStream certInput = new BufferedInputStream(getAssets().open(certName));
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            mServerCert = (X509Certificate) certificateFactory.generateCertificate(certInput);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        final String https_url = "https://certs.cac.washington.edu/";
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    //2.获取证书管理器,校验证书
                    TrustManager[] trustAllCerts = new TrustManager[]{
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                                }
                                @Override
                                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                                    if (chain == null) {
                                        throw new IllegalArgumentException("Check Server x509Certificate is null");
                                    }
                                    if (chain.length < 0) {
                                        throw new IllegalArgumentException("Check Server x509Certificate is empty");
                                    }
                                    for (X509Certificate cert :
                                            chain) {
                                        //校验证书,检查服务器端签名
                                        cert.checkValidity();
                                        try {
                                            //和预埋的app证书做对比
                                            cert.verify(mServerCert.getPublicKey());
                                        } catch (NoSuchAlgorithmException e) {
                                            e.printStackTrace();
                                        } catch (InvalidKeyException e) {
                                            e.printStackTrace();
                                        } catch (NoSuchProviderException e) {
                                            e.printStackTrace();
                                        } catch (SignatureException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[0];
                                }
                            }
                    };
                    //3.初始化SSLContext
                    SSLContext sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, trustAllCerts, null);

                    URL url = new URL(https_url);
                    final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

                    //4.创建SSLSocketFactory
                    SSLSocketFactory socketFactory = sslContext.getSocketFactory();
                    //给httpsURLConnection设置SSLSocketFactory
                    httpsURLConnection.setSSLSocketFactory(socketFactory);
                    //这一段代码暂时不需要,不需要校验证书的服务器域名是否相等
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                        /**
                         * 校验证书的服务器域名是否相等
                         * @param hostname
                         * @param session
                         * @return
                         */
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            HostnameVerifier hv = httpsURLConnection.getHostnameVerifier();
                            boolean b = hv.verify("*.washington.edu", session);
                            return b;
                        }
                    };
                    //对服务器域名证书进行校验(不需要对服务器域名进行校验)
                    //httpsURLConnection.setHostnameVerifier(hostnameVerifier);
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    final String s = StringTool.inputStream2String(inputStream);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWvWeb.loadData(s, "text/html", "ISO-8859-1");
                            progressDialog.dismiss();
                        }
                    });
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
