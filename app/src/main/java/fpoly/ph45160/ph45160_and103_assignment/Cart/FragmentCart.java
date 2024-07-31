package fpoly.ph45160.ph45160_and103_assignment.Cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.ph45160.ph45160_and103_assignment.APIService;
import fpoly.ph45160.ph45160_and103_assignment.Cart.Adapter.BillAdapter;
import fpoly.ph45160.ph45160_and103_assignment.Cart.Model.BillModel;
import fpoly.ph45160.ph45160_and103_assignment.R;
import fpoly.ph45160.ph45160_and103_assignment.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCart extends Fragment {

    private RecyclerView recyclerView;
    private BillAdapter billAdapter;
    private List<BillModel> billList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.rcBill);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadBills();

        return view;
    }

    private void loadBills() {
        APIService apiService = RetrofitClient.getInstance().create(APIService.class);
        Call<List<BillModel>> call = apiService.getBill();
        call.enqueue(new Callback<List<BillModel>>() {
            @Override
            public void onResponse(Call<List<BillModel>> call, Response<List<BillModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    billList = response.body();
                    billAdapter = new BillAdapter(billList, getContext());
                    recyclerView.setAdapter(billAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<BillModel>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
