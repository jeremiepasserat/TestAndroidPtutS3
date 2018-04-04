package com.example.chrx.testaixenbus.Applications;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chrx.testaixenbus.R;

/**
 * Created by Baptiste on 04/04/2018.
 */

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int[] slide_images = { //Different pages icons
            R.drawable.fp_icon,
            R.drawable.sp_icon,
            R.drawable.tp_icon
    };

    public String[] slide_headings = { //Headers texts
            "Bienvenue",
            "Horaires",
            "Itinéraires"
    };

    public String[] slide_descs = { //Descripting texts
            "Aix en Bus est une application de l'entreprise Aix en Bus qui pourra vous fournir toutes les informations dont vous aurez besoin pour utiliser nos lignes quotidiennement sans embûches, lancez-vous ! \n (Swipez vers la gauche)",
            "Les horaires liées aux lignes de bus sont disponibles dans l'onglet du même nom, ainsi que les horaires orientés par arrêt ! En effet, vous pouvez profiter des horaires qui concernent uniquement vos arrêts !",
            "Une fonctionnalité est aussi dédiée au traçage d\'itinéraires, vous permettant alors de vous déplacer en ville paisiblement en optimisant votre route ! Si vous rencontrez le moindre soucis, n'hésitez pas à nous le signaler via l'onglet dédié."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = view.findViewById(R.id.slideImage);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        TextView slideDescription = view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}