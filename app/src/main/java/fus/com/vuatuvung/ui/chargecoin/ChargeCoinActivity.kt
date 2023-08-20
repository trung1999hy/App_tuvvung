package fus.com.vuatuvung.ui.chargecoin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList
import dagger.hilt.android.AndroidEntryPoint
import fus.com.vuatuvung.R
import fus.com.vuatuvung.databinding.ActivityChargeCoinBinding
import fus.com.vuatuvung.utils.Constants

class ChargeCoinActivity : AppCompatActivity(), PurchaseInAppAdapter.OnClickListener {
    private var adapter: PurchaseInAppAdapter? = null
    private var billingClient: BillingClient? = null
    private var handler: Handler? = null
    private var productDetailsList: MutableList<ProductDetails>? = null
    private var onPurchaseResponse: OnPurchaseResponse? = null
    private var listData: RecyclerView? = null
    private var imgBack: ImageView? = null
    private lateinit var binding: ActivityChargeCoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_charge_coin)
        initViews()
        imgBack!!.setOnClickListener { onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        billingClient!!.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult: BillingResult, list: List<Purchase> ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                for (purchase in list) {
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                        verifyInAppPurchase(purchase)
                    }
                }
            }
        }
    }

    private fun initViews() {
        listData = findViewById(R.id.listData)
        imgBack = findViewById(R.id.imvBack)
        adapter = PurchaseInAppAdapter()
        listData?.setHasFixedSize(true)
        listData?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listData?.adapter = adapter
        adapter!!.setOnClickListener(this)
        productDetailsList = ArrayList()
        handler = Handler()
        billingClient = BillingClient.newBuilder(this).enablePendingPurchases()
            .setListener { billingResult: BillingResult?, list: List<Purchase?>? -> }
            .build()
        establishConnection()
    }

    fun establishConnection() {
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    showProducts()
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                establishConnection()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    fun showProducts() {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(inAppProductList)
            .build()
        billingClient!!.queryProductDetailsAsync(
            params
        ) { billingResult: BillingResult?, prodDetailsList: List<ProductDetails> ->
            // Process the result
            productDetailsList!!.clear()
            handler!!.postDelayed({

                //                        hideProgressDialog();
                productDetailsList!!.addAll(prodDetailsList)
                adapter!!.setData(this, productDetailsList)
                if (prodDetailsList.isEmpty()) Toast.makeText(
                    this@ChargeCoinActivity,
                    "prodDetailsList, size = 0",
                    Toast.LENGTH_SHORT
                ).show()
            }, 2000)
        }
    }

    //Product 1
    //Product 2
    //Product 3
    //Product 4
    //Product 5
    //Product 6
    private val inAppProductList: ImmutableList<QueryProductDetailsParams.Product>
        private get() = ImmutableList.of( //Product 1
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_10_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 2
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_20_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 3
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_50_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 4
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_100_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 5
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_150_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 6
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constants.KEY_200_COIN)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

    fun verifyInAppPurchase(purchases: Purchase) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchases.purchaseToken)
            .build()
        billingClient!!.acknowledgePurchase(acknowledgePurchaseParams) { billingResult: BillingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val proId = purchases.products[0]
                val quantity = purchases.quantity
                setPurchaseResponse(object : OnPurchaseResponse {
                    override fun onResponse(proId: String?, quantity: Int) {
                        proId?.let {
                            setupResult(
                                it,
                                quantity
                            )
                        }
                    }
                })
                onPurchaseResponse!!.onResponse(proId, quantity)
                allowMultiplePurchases(purchases)
                //                val coinContain =
                //                    MainApp.newInstance()?.preference?.getValueCoin()?.plus(getCoinFromKey(proId))
                //                coinContain?.let { MainApp.newInstance()?.preference?.setValueCoin(it) }
                //                //                Toast.makeText(PurchaseInAppActivity.this, "verifyInAppPurchase Mua ok--> " + proId, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun allowMultiplePurchases(purchase: Purchase) {
        val consumeParams = ConsumeParams
            .newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient!!.consumeAsync(consumeParams) { billingResult, s ->
            Toast.makeText(
                this@ChargeCoinActivity,
                " Resume item ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClickItem(item: ProductDetails) {
        launchPurchaseFlow(item)
    }

    private fun launchPurchaseFlow(productDetails: ProductDetails) {
        // handle item select
        //        assert productDetails.getSubscriptionOfferDetails() != null;
        val productDetailsParamsList = ImmutableList.of(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .build()
        )
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()
        billingClient!!.launchBillingFlow(this, billingFlowParams)
    }

    private fun setupResult(proId: String, quantity: Int) {
        val intent = Intent()
        //        int totalCoin = MainApp.newInstance().getPreference().getValueCoin();
        //        int remainCoin = totalCoin + getCoinFromKey(proId) * quantity;
        //        MainApp.newInstance().getPreference().setValueCoin(remainCoin);
        //        intent.putExtra(Constants.COIN_ORDER_RESULT, remainCoin + "");
        setResult(RESULT_OK, intent)
        runOnUiThread { onBackPressed() }
    }

    private fun getCoinFromKey(coinId: String): Int {
        return when (coinId) {
            Constants.KEY_10_COIN -> 100
            Constants.KEY_20_COIN -> 150
            Constants.KEY_50_COIN -> 300
            Constants.KEY_100_COIN -> 500
            Constants.KEY_150_COIN -> 700
            Constants.KEY_200_COIN -> 999
            else -> 0
        }
    }

    internal interface OnPurchaseResponse {
        fun onResponse(proId: String?, quantity: Int)
    }

    private fun setPurchaseResponse(onPurchaseResponse: OnPurchaseResponse) {
        this.onPurchaseResponse = onPurchaseResponse
    }

}