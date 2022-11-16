package com.puc.pos_mobile.bmicalculator

import android.content.Intent
import android.icu.math.BigDecimal
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puc.pos_mobile.bmicalculator.databinding.ActivityResultadoBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultadoBinding

    var valorIMC = ""
    var informacao = ""
    var classificacao = ""
    var backColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configListener()
        setupView()
    }

    private fun formatResultado(resultado: Double): String {
        val df = DecimalFormat("#,###0.00")
        df.roundingMode = RoundingMode.HALF_EVEN
        return df.format(resultado)
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

        if (idade.equals(1)) {
            if (resultado <= 18.5) {
                valorIMC = formatResultado(resultado)
                binding.classificacao.text = "Abaixo do peso"
                informacao = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de\n" +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado > 18.5 && resultado <= 24.9) {
//                valorIMC = formatResultado(resultado)
                classificacao = "Peso normal"
                informacao = "Você está no peso ideal. Parabéns! Mantenha seus hábitos alimentares e cuidados com a " +
                        "saúde"
                backColor = getColor(R.color.azul)
            } else if (resultado >= 25.0 && resultado <= 29.9) {
//                valorIMC = formatResultado(resultado)
                classificacao = "Excesso de peso"
                informacao = "Atenção! Alguns quilos a mais já são suficientes para que algumas pessoas desenvolvam doenças associadas, como\n" +
                        "diabetes e hipertensão. É importante rever seus hábitos. Procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado >= 30.0 && resultado <= 34.9) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Obesidade classe 1"
                informacao = "Sinal de alerta! O excesso de peso é fator de risco para desenvolvimento de outros problemas de saúde. A boa notícia\n" +
                        "é que uma pequena perda de peso já traz benefícios à saúde. Procure um médico para definir o tratamento mais adequado\n" +
                        "para você"
                backColor = getColor(R.color.vermelho)
            } else if (resultado >= 35.0 && resultado <= 29.9) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Obesidade classe 2"
                informacao = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas está entre alto e muito alto. Busque\n" +
                        "ajuda de um profissional de saúde; não perca tempo."
                getColor(R.color.vermelho)
            } else if (resultado >= 40.0) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Obesidade classe 3"
                informacao = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas é muito alto. Busque ajuda de um\n" +
                        "profissional de saúde; não perca tempo."
                backColor = getColor(R.color.vermelho)
            }
        } else if (idade.equals(2)){
            if (resultado < 22) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Abaixo do peso"
                informacao = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de\n" +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico."
                backColor = getColor(R.color.amarelo)
            } else if (resultado > 22.0 && resultado < 27.0) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Peso adequado"
                informacao = "Você está no peso ideal. Parabéns! Mantenha seus hábitos alimentares e cuidados com a " +
                        "saúde"
                backColor = getColor(R.color.azul)
            } else if (resultado >= 27.0) {
//                valorIMC = String.format("%.1f", resultado).toDouble()
                classificacao = "Sobrepeso"
                informacao = "Sinal de alerta! O excesso de peso é fator de risco para desenvolvimento de outros problemas de saúde. A boa notícia\n" +
                        "é que uma pequena perda de peso já traz benefícios à saúde. Procure um médico para definir o tratamento mais adequado\n" +
                        "para você"
                backColor = getColor(R.color.vermelho)
            }
        }

        binding.informacao.text = informacao
        binding.valorIMC.setTextColor(backColor)
        binding.classificacao.setTextColor(backColor)
        binding.valorIMC.text = valorIMC.toString()
    }
}