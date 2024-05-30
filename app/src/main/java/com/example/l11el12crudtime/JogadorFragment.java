package com.example.l11el12crudtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l11el12crudtime.controller.JogadorController;
import com.example.l11el12crudtime.controller.TimeController;
import com.example.l11el12crudtime.model.Jogador;
import com.example.l11el12crudtime.model.Time;
import com.example.l11el12crudtime.persistance.JogadorDao;
import com.example.l11el12crudtime.persistance.TimeDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


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
    private JogadorController jc;
    private TimeController tc;
    private List<Time> times;


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

        jc = new JogadorController(new JogadorDao(view.getContext()));
        tc = new TimeController(new TimeDao(view.getContext()));

        preencheSpinner();

        btnBuscarJogador.setOnClickListener(op -> buscar());
        btnInserirJogador.setOnClickListener(op -> inserir());
        btnAtualizarJogador.setOnClickListener(op -> atualizar());
        btnApagarJogador.setOnClickListener(op -> apagar());
        btnListarJogador.setOnClickListener(op -> listar());
        return view;
    }


    private void buscar() {
        Jogador j = montaJogador();
        try {
            j = jc.findOne(j);
            if (!j.getNome().equals("")){
                preencheCampos(j);
            }
            else {
                Toast.makeText(view.getContext(), "Jogador não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void inserir() {
        int spPos = spTime.getSelectedItemPosition();
        if (spPos > 0) {
            Jogador j = montaJogador();
            try {
                jc.insert(j);
                Toast.makeText(view.getContext(), "Jogador inserido com sucesso!", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um time", Toast.LENGTH_LONG).show();
        }
    }

    private void atualizar() {
        int spPos = spTime.getSelectedItemPosition();
        if (spPos > 0) {
            Jogador j = montaJogador();
            try {
                jc.update(j);
                Toast.makeText(view.getContext(), "Jogador atualizado com sucesso!", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um time", Toast.LENGTH_LONG).show();
        }
    }

    private void apagar() {
        Jogador j = montaJogador();
        try {
            jc.delete(j);
            Toast.makeText(view.getContext(), "Jogador apagado com sucesso!", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void listar() {
        try {
            List<Jogador> jogadores = jc.findAll();
            StringBuffer buffer = new StringBuffer();
            for (Jogador j : jogadores){
                buffer.append(j.toString()+"\n");
            }
            tvJogador.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinner() {
        Time time0 = new Time();
        time0.setCodigo(0);
        time0.setNome("Selecione o Time");
        time0.setCidade("");

        try {
            times = tc.findAll();
            times.add(0, time0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, times);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTime.setAdapter(ad);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Jogador montaJogador(){
        Jogador j =  new Jogador();
        j.setId(Integer.parseInt(etId.getText().toString()));
        j.setNome(etNomeJogador.getText().toString());
        if (etData.getText().toString().equals("")){
            j.setDataNasc(LocalDate.parse("0001-01-01"));
        } else {
            j.setDataNasc(LocalDate.parse(etData.getText().toString()));
        }
        if (etAltura.getText().toString().equals("")){
            j.setAltura(0);
        } else {
            j.setAltura(Float.parseFloat(etAltura.getText().toString()));
        }
        if (etPeso.getText().toString().equals("")){
            j.setPeso(0);
        } else {
            j.setPeso(Float.parseFloat(etPeso.getText().toString()));
        }
        j.setTime((Time) spTime.getSelectedItem());

        return j;
    }

    private void preencheCampos(Jogador j){
        etId.setText(String.valueOf(j.getId()));
        etNomeJogador.setText(j.getNome());
        etData.setText(String.valueOf(j.getDataNasc()));
        etAltura.setText(String.valueOf(j.getAltura()));
        etPeso.setText(String.valueOf(j.getPeso()));

        int cont = 0;
        for (Time t: times){
            if (t.getCodigo() == j.getTime().getCodigo()){
                spTime.setSelection(cont);
            }
            else {
                cont++;
            }
        }
        if (cont > times.size()){
            spTime.setSelection(0);
        }
    }

    private void limpaCampos(){
        etId.setText("");
        etNomeJogador.setText("");
        etData.setText("");
        etAltura.setText("");
        etPeso.setText("");
        spTime.setSelection(0);
    }
}