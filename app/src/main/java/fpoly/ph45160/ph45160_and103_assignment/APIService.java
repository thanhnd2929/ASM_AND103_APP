package fpoly.ph45160.ph45160_and103_assignment;

import java.util.List;

import fpoly.ph45160.ph45160_and103_assignment.Cart.Model.BillModel;
import fpoly.ph45160.ph45160_and103_assignment.Home.Model.ProductModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @GET("products")
    Call<List<ProductModel>> getProduct();

    @GET("products/{id}")
    Call<ProductModel> getProductById(@Path("id") String id);

    @POST("products")
    Call<ProductModel> createProduct(@Body ProductModel productModel);

    @PUT("products/{id}")
    Call<ProductModel> updateProduct(@Path("id") String id, @Body ProductModel productModel);

//
    @GET("bills")
    Call<List<BillModel>> getBill();

    @DELETE("bills/{id}")
    Call<Void> deleteBill(@Path("id") String id);

    @POST("bills")
    Call<BillModel> createBill(@Body BillModel billModel);


}
