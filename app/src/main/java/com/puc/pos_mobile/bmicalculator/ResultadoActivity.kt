package com.puc.pos_mobile.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puc.pos_mobile.bmicalculator.databinding.ActivityResultadoBinding


class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultadoBinding

    var valorIMC: Double = 0.0
    var informacao = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configListener()
        setupView()
    }

    private fun formatResultado(resultado: Double): Double {
        var result:Double = String.format("%.2f", resultado).toDouble()
        return result
    }

    private fun configListener(){
        binding.btnInicio.setOnClickListener(){
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupView(){
        val i = intent
        val idade = i.getIntExtra(MainActivity.IDADE, 1)
        val resultado = i.getDoubleExtra(MainActivity.RESULTADO, 0.00)

        if (idade == 1) {
            if (resultado <= 18.5) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.peso_baixo)
                binding.informacao.text = getString(R.string.txt_baixo)
                binding.classificacao.setBackgroundColor(getColor(R.color.amarelo))
                binding.valorIMC.setBackgroundColor(getColor(R.color.amarelo))
            } else if (resultado > 18.5 && resultado <= 24.9) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.peso_normal)
                informacao = getString(R.string.txt_normal)
                binding.classificacao.setBackgroundColor(getColor(R.color.azul))
                binding.valorIMC.setBackgroundColor(getColor(R.color.azul))
            } else if (resultado in 25.0..29.9) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.excesso_peso)
                binding.informacao.text = getString(R.string.txt_excesso)
                binding.classificacao.setBackgroundColor(getColor(R.color.amarelo))
                binding.valorIMC.setBackgroundColor(getColor(R.color.amarelo))
            } else if (resultado in 30.0..34.9) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.obesidadeUm)
                binding.informacao.text = getString(R.string.txt_obesidadeUm)
                binding.classificacao.setBackgroundColor(getColor(R.color.vermelho))
                binding.valorIMC.setBackgroundColor(getColor(R.color.vermelho))
            } else if (resultado in 29.9..35.0) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.obesidadeDois)
                binding.informacao.text = getString(R.string.txt_obesidadeDois)
                binding.classificacao.setBackgroundColor(getColor(R.color.vermelho))
                binding.valorIMC.setBackgroundColor(getColor(R.color.vermelho))
            } else if (resultado >= 40.0) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.obesidadeTres)
                binding.informacao.text = getString(R.string.txt_obesidadeTres)
                binding.classificacao.setBackgroundColor(getColor(R.color.vermelho))
                binding.valorIMC.setBackgroundColor(getColor(R.color.vermelho))
            }
        } else if (idade == 2){
            if (resultado < 22) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.peso_baixo)
                binding.informacao.text = getString(R.string.txt_baixo)
                binding.classificacao.setBackgroundColor(getColor(R.color.amarelo))
                binding.valorIMC.setBackgroundColor(getColor(R.color.amarelo))
            } else if (resultado > 22.0 && resultado < 27.0) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.peso_okay)
                binding.informacao.text = getString(R.string.txt_adequado)
                binding.classificacao.setBackgroundColor(getColor(R.color.azul))
                binding.valorIMC.setBackgroundColor(getColor(R.color.azul))
            } else if (resultado >= 27.0) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = getString(R.string.sobrepeso)
                binding.informacao.text = getString(R.string.txt_sobrepeso)
                binding.classificacao.setBackgroundColor(getColor(R.color.vermelho))
                binding.valorIMC.setBackgroundColor(getColor(R.color.vermelho))
            }
        }

        binding.valorIMC.text = valorIMC.toString()
    }
}