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
import android.widget.Toast;

import com.example.l11el12crudtime.controller.TimeController;
import com.example.l11el12crudtime.model.Time;
import com.example.l11el12crudtime.persistance.TimeDao;

import java.sql.SQLException;
import java.util.List;

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
    private TimeController tc;

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
        btnApagarTime = view.findViewById(R.id.btnApagarTime);
        btnListarTime = view.findViewById(R.id.btnListarTime);
        tvTime = view.findViewById(R.id.tvTime);
        tvTime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTime.setMovementMethod(new ScrollingMovementMethod());

        tc = new TimeController(new TimeDao(view.getContext()));

        btnBuscarTime.setOnClickListener(op -> buscar());
        btnInserirTime.setOnClickListener(op -> inserir());
        btnAtualizarTime.setOnClickListener(op -> atualizar());
        btnApagarTime.setOnClickListener(op -> apagar());
        btnListarTime.setOnClickListener(op -> listar());
        return view;
    }

    private void buscar() {
        Time t = montaTime();
        try {
            t = tc.findOne(t);
            if (!t.getNome().equals("")){
                preencheCampos(t);
            }
            else {
                Toast.makeText(view.getContext(), "Time não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void inserir() {
        Time t = montaTime();
        try {
            tc.insert(t);
            Toast.makeText(view.getContext(), "Time inserido com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void atualizar() {
        Time t = montaTime();
        try {
            tc.update(t);
            Toast.makeText(view.getContext(), "Time atualizado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void apagar() {
        Time t = montaTime();
        try {
            tc.delete(t);
            Toast.makeText(view.getContext(), "Time apagado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void listar() {
        try {
            List<Time> times = tc.findAll();
            StringBuffer buffer = new StringBuffer();
            for (Time t : times){
                buffer.append(t.toString()+"\n");
            }
            tvTime.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Time montaTime(){
        int codigo = Integer.parseInt(etCodigo.getText().toString());
        String nome  = etNomeTime.getText().toString();
        String cidade = etCidade.getText().toString();
        Time t =  new Time();
        t.setCodigo(codigo);
        t.setNome(nome);
        t.setCidade(cidade);
        return t;
    }

    private void preencheCampos(Time t){
        etCodigo.setText(String.valueOf(t.getCodigo()));
        etNomeTime.setText(t.getNome());
        etCidade.setText(t.getCidade());
    }

    private void limpaCampos(){
        etCodigo.setText("");
        etNomeTime.setText("");
        etCidade.setText("");
    }
}