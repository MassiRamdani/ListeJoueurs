package fr.rmdcoding.listejoueurs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.rmdcoding.listejoueurs.R;
import fr.rmdcoding.listejoueurs.models.Joueur;


public class JoueurAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
     private String urlPic;
    private List<Joueur> joueurResults;
    private Context context;

    private boolean isLoadingAdded = false;

    public JoueurAdapter(Context context) {
        this.context = context;
        joueurResults = new ArrayList<>();
    }

    public List<Joueur> getJoueurs() {
        return joueurResults;
    }

    public void setJoueurs(List<Joueur> joueurResults) {
        this.joueurResults = joueurResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_joueur, parent, false);
        viewHolder = new JoueurVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Joueur result = joueurResults.get(position); 

        switch (getItemViewType(position)) {
            case ITEM:
                final JoueurVH joueurVH = (JoueurVH) holder;
                DecimalFormat df = new DecimalFormat("#0.00");

                joueurVH.nameJoueur.setText(result.getName()+ "("+result.getCountry()+")");


                joueurVH.city_joueur.setText(String.valueOf(result.getCity()));
                joueurVH.speedJoueur.setText("Speed:"+df.format((result.getSpeed_score())));
                joueurVH.staminaJoueur.setText("Stamina:"+df.format((result.getStamina_score())));
                joueurVH.dribblingJoueur.setText("Dribbling:"+df.format((result.getDribbling_score())));
                joueurVH.passingJoueur.setText("Passing:"+df.format((result.getPassing_score())));
                joueurVH.shootingJoueur.setText("Shooting:"+df.format((result.getShooting_score())));
                joueurVH.scoreJoueur.setText("Score:"+df.format((result.getScore())));


                if (result.getProfile_pic() == null){
                      urlPic="https://erasmuscoursescroatia.com/wp-content/uploads/2015/11/no-user.jpg";
                }else{
                      urlPic=result.getProfile_pic();

                }
                Glide.with(context)
                        .load(urlPic)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                joueurVH.joueur_progress.setVisibility(View.GONE);
                                return false;
                            }


                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                 joueurVH.joueur_progress.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                        .centerCrop()
                        .crossFade()
                        .into(joueurVH.image_joueur);

                break;

            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return joueurResults == null ? 0 : joueurResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == joueurResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Joueur r) {
        joueurResults.add(r);
        notifyItemInserted(joueurResults.size() - 1);
    }

    public void addAll(List<Joueur> joueurList) {
        for (Joueur result : joueurList) {
            add(result);
        }
    }

    public void remove(Joueur r) {
        int position = joueurResults.indexOf(r);
        if (position > -1) {
            joueurResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Joueur());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = joueurResults.size() - 1;
        Joueur result = getItem(position);

        if (result != null) {
            joueurResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Joueur getItem(int position) {
        return joueurResults.get(position);
    }




   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class JoueurVH extends RecyclerView.ViewHolder {
        private TextView nameJoueur;

        private TextView city_joueur;
        private ImageView image_joueur;
        private ProgressBar joueur_progress;
        private TextView speedJoueur;
        private TextView staminaJoueur;
        private TextView dribblingJoueur;
        private TextView scoreJoueur;
        private TextView passingJoueur;
        private TextView shootingJoueur;
        public JoueurVH(View itemView) {
            super(itemView);

            nameJoueur =  itemView.findViewById(R.id.name_joueur);
            scoreJoueur =   itemView.findViewById(R.id.score_joueur);
            city_joueur =  itemView.findViewById(R.id.city_joueur);
            image_joueur =   itemView.findViewById(R.id.image_joueur);
            speedJoueur =  itemView.findViewById(R.id.speed_joueur);
            staminaJoueur =  itemView.findViewById(R.id.stamina_joueur);
            dribblingJoueur =  itemView.findViewById(R.id.dribbling_joueur);
            scoreJoueur =  itemView.findViewById(R.id.score_joueur);
            passingJoueur =  itemView.findViewById(R.id.passing_joueur);
            shootingJoueur =  itemView.findViewById(R.id.shooting_joueur);
            joueur_progress =   itemView.findViewById(R.id.joueur_progress);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}