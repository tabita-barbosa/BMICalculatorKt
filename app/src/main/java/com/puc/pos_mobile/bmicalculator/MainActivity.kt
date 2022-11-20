package com.puc.pos_mobile.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.EditText
import com.puc.pos_mobile.bmicalculator.databinding.ActivityMainBinding
import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    // variaveis
    private lateinit var binding: ActivityMainBinding

    var alturaFinal = 0.0
    var pesoFinal = 0.0
    var valorResultado: Double = 0.0
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
            verificaPeso()
            verificaAltura()
            verificaIdade()
            calculaImc()
            showResultado()
        }
    }

    private fun showResultado(){
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
    }

    private fun verificaPeso(): Double{
        if (!binding.pesoId.text.isNullOrBlank() || binding.pesoId.toString().toDouble() > 0 ) {
            pesoFinal = binding.pesoId.text.toString().toDouble()
        } else {
            binding.btnCalcular.isEnabled = false
            Toast.makeText(this, "Digite um valor diferente de 0 no campo peso", Toast.LENGTH_SHORT).show()
            binding.pesoId.requestFocus()
        }
        return pesoFinal
    }

    private fun verificaAltura(): Double{
        if (!binding.alturaId.text.isNullOrBlank() || binding.alturaId.toString().toDouble() > 0) {
            alturaFinal = binding.alturaId.text.toString().toDouble()
        } else {
            binding.btnCalcular.isEnabled = false
            Toast.makeText(this, "Digite um valor diferente de 0 no campo altura", Toast.LENGTH_SHORT).show()
            binding.alturaId.requestFocus()
        }
        return alturaFinal
    }

    private fun verificaIdade(): Int {
        if (binding.rbAdulto.isChecked) grupoEtario = 1
        if (binding.rbIdoso.isChecked) grupoEtario = 2
        return grupoEtario
    }

    private fun calculaImc(): Double{
        valorResultado = (pesoFinal/(alturaFinal*2))*100
        return valorResultado
    }

}