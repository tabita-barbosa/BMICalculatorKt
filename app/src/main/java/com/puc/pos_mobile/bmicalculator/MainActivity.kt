package com.puc.pos_mobile.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.EditText
import com.puc.pos_mobile.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    // variaveis
    private lateinit var binding: ActivityMainBinding

    var valorAltura = findViewById(R.id.pesoId) as EditText
    var valorPeso = findViewById(R.id.pesoId) as EditText
    var alturaFinal = 0.0
    var pesoFinal = 0.0
    var valorResultado = 0.0
    var grupoEtario = 0

    companion object {
        const val IDADE = "IMAGEM"
        const val RESULTADO = "RESULTADO"
    }

    // life cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    // listeners
    private fun configListeners() {
        configurarRG()
        configButtonListener()
    }

    private fun configButtonListener() {
        binding.btnCalcular.setOnClickListener {
            showResultado()
        }
    }

    private fun showResultado(){
        verificaPeso()
        verificaAltura()
        calculaImc()

        val intent = Intent(this, ResultadoActivity::class.java).apply {
            putExtra(IDADE, grupoEtario)
            putExtra(RESULTADO, valorResultado)
        }
        startActivity(intent)
    }

    // buttons methods
    private fun configurarRG() {
        binding.rgGrupoEtario.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAdulto -> {
                    grupoEtario = 1
                    mudarCorBotao(1)
                }
                R.id.rbIdoso -> {
                    grupoEtario = 2
                    mudarCorBotao(2)
                }
            }
        }
    }

    private fun mudarCorBotao(i: Int) {
        val color = when (i) {
            1 -> {
                getColor(R.color.black)
            }
            else -> {
                getColor(R.color.teal_700)
            }
        }
        binding.btnCalcular.setBackgroundColor(color)
    }

    private fun verificaPeso(){
        if (valorPeso.getText().toString().trim().isEmpty() || Integer.parseInt(valorPeso.getText().toString()) > 0) {
            pesoFinal = valorPeso.text.toString().toDouble()
        } else {
            Toast.makeText(this, "Digite um valor diferente de 0 no campo peso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verificaAltura(){
        if (valorAltura.getText().toString().trim().isEmpty() || Integer.parseInt(valorAltura.getText().toString()) > 0) {
            alturaFinal = valorAltura.text.toString().toDouble()
        } else {
            Toast.makeText(this, "Digite um valor diferente de 0 no campo altura", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculaImc(){
        valorResultado = pesoFinal/alturaFinal.pow(2)
        print(valorResultado)
    }

}