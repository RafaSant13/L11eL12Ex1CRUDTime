package com.example.l11el12crudtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class JogadorFragment extends Fragment {

    private View view;
    private EditText etId;
    private EditText etNomeJogador;
    private EditText etData;
    private EditText etAltura;
    private EditText etPeso;
    private Spinner spTime;
    private Button btnBuscarJogador;
    private Button btnInserirJogador;
    private Button btnAtualizarJogador;
    private Button btnApagarJogador;
    private Button btnListarJogador;
    private TextView tvJogador;


    public JogadorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jogador, container, false);
        etId = view.findViewById(R.id.etId);
        etNomeJogador = view.findViewById(R.id.etNomeJogador);
        etData = view.findViewById(R.id.etData);
        etAltura = view.findViewById(R.id.etAltura);
        etPeso = view.findViewById(R.id.etPeso);
        spTime = view.findViewById(R.id.spTime);
        btnBuscarJogador = view.findViewById(R.id.btnBuscarJogador);
        btnInserirJogador = view.findViewById(R.id.btnInserirJogador);
        btnAtualizarJogador = view.findViewById(R.id.btnAtualizarJogador);
        btnApagarJogador = view.findViewById(R.id.btnApagarJogador);
        btnListarJogador = view.findViewById(R.id.btnListarJogador);
        tvJogador = view.findViewById(R.id.tvJogador);
        tvJogador.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvJogador.setMovementMethod(new ScrollingMovementMethod());

        btnBuscarJogador.setOnClickListener(op -> buscar());
        btnInserirJogador.setOnClickListener(op -> inserir());
        btnAtualizarJogador.setOnClickListener(op -> atualizar());
        btnApagarJogador.setOnClickListener(op -> apagar());
        btnListarJogador.setOnClickListener(op -> listar());
        return view;
    }

    private void buscar() {

    }

    private void inserir() {

    }

    private void atualizar() {

    }

    private void apagar() {

    }

    private void listar() {

    }
}