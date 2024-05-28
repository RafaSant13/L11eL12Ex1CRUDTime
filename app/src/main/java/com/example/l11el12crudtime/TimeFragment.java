package com.example.l11el12crudtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimeFragment extends Fragment {

    private View view;
    private EditText etCodigo;
    private EditText etNomeTime;
    private EditText etCidade;
    private Button btnBuscarTime;
    private Button btnInserirTime;
    private Button btnAtualizarTime;
    private Button btnApagarTime;
    private Button btnListarTime;
    private TextView tvTime;

    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time, container, false);
        etCodigo = view.findViewById(R.id.etCodigo);
        etNomeTime = view.findViewById(R.id.etNomeTime);
        etCidade = view.findViewById(R.id.etCidade);
        btnBuscarTime = view.findViewById(R.id.btnBuscarTime);
        btnInserirTime = view.findViewById(R.id.btnInserirTime);
        btnAtualizarTime = view.findViewById(R.id.btnAtualizarTime);
        btnApagarTime = view.findViewById(R.id.btnBuscarTime);
        btnListarTime = view.findViewById(R.id.btnListarTime);
        tvTime = view.findViewById(R.id.tvTime);
        tvTime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTime.setMovementMethod(new ScrollingMovementMethod());

        btnBuscarTime.setOnClickListener(op -> buscar());
        btnInserirTime.setOnClickListener(op -> inserir());
        btnAtualizarTime.setOnClickListener(op -> atualizar());
        btnApagarTime.setOnClickListener(op -> apagar());
        btnListarTime.setOnClickListener(op -> listar());
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