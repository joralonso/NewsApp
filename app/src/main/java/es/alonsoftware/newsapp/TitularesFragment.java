package es.alonsoftware.newsapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import es.alonsoftware.newsapp.adapters.TitularAdapter;
import es.alonsoftware.newsapp.clases.DAO;
import es.alonsoftware.newsapp.clases.Titular;

public class TitularesFragment extends Fragment {

    private RelativeLayout progress;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Titular> titulares;

    private static final String CATEGORIA = "param1";

    private int categoria;

    private OnFragmentInteractionListener mListener;

    public TitularesFragment() {
        // Required empty public constructor
    }

    public static TitularesFragment newInstance(int categoriaid) {
        TitularesFragment fragment = new TitularesFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORIA, categoriaid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoria = getArguments().getInt(CATEGORIA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        progress = (RelativeLayout) rootView.findViewById(R.id.progress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout);

        if (savedInstanceState != null){
            titulares = (List<Titular>) savedInstanceState.getSerializable("lista");
            handlerTitulares.sendEmptyMessage(0);
        }else{
            cargar();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargar();
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("lista", (Serializable) titulares);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void cargar(){
        swipeRefreshLayout.setRefreshing(true);
        new Thread() {
            public void run() {

                titulares = DAO.getCategoria(categoria);
                handlerTitulares.sendEmptyMessage(0);
            }
        }.start();
    }

    private Handler handlerTitulares = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progress.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            if (titulares != null && titulares.size() > 0) {
                TitularAdapter itemAdapter = new TitularAdapter(titulares);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(itemAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                Toast.makeText(getContext(), getString(R.string.no_news), Toast.LENGTH_SHORT).show();
            }
        }

    };
}
