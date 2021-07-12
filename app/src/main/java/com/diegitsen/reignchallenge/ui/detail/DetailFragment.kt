package com.diegitsen.reignchallenge.ui.detail

import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.diegitsen.reignchallenge.R


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    var webView: WebView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        if (arguments != null) {
            val args = DetailFragmentArgs.fromBundle(requireArguments())//PhoneValidationFragmentArgs.fromBundle(requireArguments())
            val url  = args.url
            url.let {
                webView = view.findViewById<View>(R.id.webView) as WebView?
                webView?.settings?.javaScriptEnabled = true;
                // webView?.setWebViewClient(IgnoreSSLErrortWebViewClient())

                webView?.webViewClient = object : WebViewClient() {
                    override fun onReceivedSslError(
                        view: WebView,
                        handler: SslErrorHandler,
                        error: SslError
                    ) {
                        handler.proceed() //skip ssl error
                    }
                }
                webView?.loadUrl(it)
            }

            //webView?.loadUrl(url.replace("http","https"))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}