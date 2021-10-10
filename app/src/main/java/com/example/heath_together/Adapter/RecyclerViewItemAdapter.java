package com.example.heath_together.Adapter;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.heath_together.Main3;
import com.example.heath_together.Object.DTO.AccountListItem;
import com.example.heath_together.Object.DTO.GroupListItem;
import com.example.heath_together.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewItemAdapter.ViewHolder> implements Filterable {



    private List<AccountListItem> accountList ;
    private List<AccountListItem> accountListFull;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        LinearLayout LinearLayout_member;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.RecyclerviewItem_MemberName) ;
            LinearLayout_member = itemView.findViewById(R.id.memberLayout);

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecyclerViewItemAdapter(List<AccountListItem> accountList) {
        this.accountList= accountList;
        accountListFull = new ArrayList<AccountListItem>(accountList);
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerViewItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        RecyclerViewItemAdapter.ViewHolder vh = new RecyclerViewItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerViewItemAdapter.ViewHolder holder, int position) {
        holder.textView1.setText(accountList.get(position).getAccountName());
        //public void on Click =>아이템이 눌렸을때 프로필로 입장하는 함수.


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return accountList.size() ;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint){



            List<AccountListItem> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length()==0) {
                filteredList.addAll(accountListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(AccountListItem item:accountListFull){
                    if(item.getAccountName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            accountList.clear();
            accountList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
    public void addItem(AccountListItem item) {
        accountList.add(item);
    }
}