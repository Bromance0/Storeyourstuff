package adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeyourstuff.R;
import GetSet.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> listUsers;

    public UsersAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }



    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.TextViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.TextViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.TextViewPassword);
        }
    }


}